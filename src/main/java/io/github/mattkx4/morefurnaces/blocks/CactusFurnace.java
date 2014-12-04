package io.github.mattkx4.morefurnaces.blocks;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.particles.EntityCactusFlameFX;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityCactusFurnace;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CactusFurnace extends BlockContainer implements IPlantable {

	/*
	 * Boolean to tell if the furnace is active
	 */
	private final boolean isActive;

	private Random rand = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	private static boolean keepInventory;

	private EffectRenderer effect_renderer;

	public CactusFurnace(boolean isActive) {
		super(Material.cactus);
		this.isActive = isActive;
		this.setHarvestLevel("pickaxe", 0);
	}

	/*
	 * What item is dropped from the block
	 */
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(MFMBlocks.CactusFurnaceIdle);
	}

	/*
	 * Called whenever the block is added to the world
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	/*
	 * Set the default direction of the block in the world
	 */
	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block b1 = world.getBlock(x, y, z - 1);
			Block b2 = world.getBlock(x, y, z + 1);
			Block b3 = world.getBlock(x - 1, y, z);
			Block b4 = world.getBlock(x + 1, y, z);

			byte b0 = 3;

			if (b1.func_149730_j() && !b2.func_149730_j()) {
				b0 = 3;
			}

			if (b2.func_149730_j() && !b1.func_149730_j()) {
				b0 = 2;
			}

			if (b3.func_149730_j() && !b4.func_149730_j()) {
				b0 = 5;
			}

			if (b4.func_149730_j() && !b3.func_149730_j()) {
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	/*
	 * Set different icon sides arguments:side, metadata
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return metadata == 0 && side == 3 ? this.iconFront
				: side == 1 ? this.iconTop : (side == 0 ? this.iconTop
						: (side != metadata ? this.blockIcon : this.iconFront));
	}

	/*
	 * Register item side textures to icons
	 */
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Strings.MODID
				+ ":CactusFurnace_side");
		this.iconFront = iconRegister.registerIcon(Strings.MODID
				+ ":"
				+ (this.isActive ? "CactusFurnace_front_active"
						: "CactusFurnace_front_idle"));
		this.iconTop = iconRegister.registerIcon(Strings.MODID
				+ ":CactusFurnace_top");
	}

	/*
	 * Called upon on block activation (right click) to open the GUI
	 */
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitx, float hity, float hitz) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, MoFurnacesMod.instance,
					MoFurnacesMod.guiIdSteelFurnace, world, x, y, z);
		}
		return true;
	}

	public static void updateCactusFurnaceState(boolean active, World worldObj,
			int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;

		if (active == true) {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					MFMBlocks.CactusFurnaceActive);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					MFMBlocks.CactusFurnaceIdle);
		}
		keepInventory = false;

		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}

		// get the block metadata (direction)
		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		// set a new variable to the grow counter
		int variable = TileEntityCactusFurnace.growCounter;
		// if the grow counter is larger than 2
		ItemStack itemStack = new ItemStack(Blocks.cactus);
		itemStack.stackSize = 1;
		if (variable > 63) {
			// depending on the metadata spawn a cactus block in front of it
			if (l == 5) {
				EntityItem item = new EntityItem(worldObj, xCoord + 1, yCoord,
						zCoord + 1, new ItemStack(itemStack.getItem(), 1,
								itemStack.getItemDamage()));
				if (itemStack.hasTagCompound()) {
					item.getEntityItem().setTagCompound(
							(NBTTagCompound) itemStack.getTagCompound().copy());
				}
				worldObj.spawnEntityInWorld(item);
			}
			if (l == 4) {
				EntityItem item = new EntityItem(worldObj, xCoord - 1, yCoord,
						zCoord + 1, new ItemStack(itemStack.getItem(), 1,
								itemStack.getItemDamage()));
				if (itemStack.hasTagCompound()) {
					item.getEntityItem().setTagCompound(
							(NBTTagCompound) itemStack.getTagCompound().copy());
				}
				worldObj.spawnEntityInWorld(item);
			}
			if (l == 2) {
				EntityItem item = new EntityItem(worldObj, xCoord + 1, yCoord,
						zCoord - 1, new ItemStack(itemStack.getItem(), 1,
								itemStack.getItemDamage()));
				if (itemStack.hasTagCompound()) {
					item.getEntityItem().setTagCompound(
							(NBTTagCompound) itemStack.getTagCompound().copy());
				}
				worldObj.spawnEntityInWorld(item);
			}
			if (l == 3) {
				EntityItem item = new EntityItem(worldObj, xCoord + 1, yCoord,
						zCoord + 1, new ItemStack(itemStack.getItem(), 1,
								itemStack.getItemDamage()));
				if (itemStack.hasTagCompound()) {
					item.getEntityItem().setTagCompound(
							(NBTTagCompound) itemStack.getTagCompound().copy());
				}
				worldObj.spawnEntityInWorld(item);
			}
			// set grow counter to zero
			TileEntityCactusFurnace.growCounter = 0;
		}
	}

	/*
	 * Create the tile entity
	 */
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityCactusFurnace();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entityplayer, ItemStack itemstack) {
		int l = MathHelper
				.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (itemstack.hasDisplayName()) {
			((TileEntityCactusFurnace) world.getTileEntity(x, y, z))
					.setGuiDisplayName(itemstack.getDisplayName());
		}

	}

	/*
	 * What to do to the block and it's contents when it is broken
	 */
	public void breakBlock(World world, int x, int y, int z, Block oldblock,
			int oldmetadata) {
		if (!keepInventory) {
			TileEntityCactusFurnace tileentity = (TileEntityCactusFurnace) world
					.getTileEntity(x, y, z);

			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); ++i) {
					ItemStack itemstack = tileentity.getStackInSlot(i);

					if (itemstack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;

							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;
							EntityItem item = new EntityItem(world,
									(double) ((float) x + f),
									(double) ((float) y + f1),
									(double) ((float) z + f2), new ItemStack(
											itemstack.getItem(), j,
											itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}
							// omit following
							/**
							 * float f3 = 0.05F; item.motionX =
							 * (double)((float)this.rand.nextGaussian() * f3);
							 * item.motionY =
							 * (double)((float)this.rand.nextGaussian() * f3 +
							 * 0.2F); item.motionZ =
							 * (double)((float)this.rand.nextGaussian() * f3);
							 */
							world.spawnEntityInWorld(item);
						}
					}
				}

				world.func_147453_f(x, y, z, oldblock);
			}
		}

		super.breakBlock(world, x, y, z, oldblock, oldmetadata);
	}

	/*
	 * A randomly called display update to add particles or other items for
	 * display in this case it is adding smoke and flames.
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z,
			Random random) {

		if (this.isActive) {
			int direction = world.getBlockMetadata(x, y, z);
			float x1 = (float) x + 0.5F;
			float y1 = (float) y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float z1 = (float) z + 0.5F;
			float f = 0.52F;
			float f1 = random.nextFloat() * 0.6F - 0.3F;

			// spawn in the smoke and custom flame particle
			if (direction == 4) {
				world.spawnParticle("smoke", (double) (x1 - f), (double) y1,
						(double) (z1 + f1), 0.0D, 0.0D, 0.0D);
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntityCactusFlameFX(world,
								(double) (x1 - f), (double) y1,
								(double) (z1 + f1), 0.0D, 0.0D, 0.0D));

			} else if (direction == 5) {
				world.spawnParticle("smoke", (double) (x1 + f), (double) y1,
						(double) (z1 + f1), 0.0D, 0.0D, 0.0D);
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntityCactusFlameFX(world,
								(double) (x1 + f), (double) y1,
								(double) (z1 + f1), 0.0D, 0.0D, 0.0D));
			} else if (direction == 2) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) y1,
						(double) (z1 - f), 0.0D, 0.0D, 0.0D);
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntityCactusFlameFX(world,
								(double) (x1 + f1), (double) y1,
								(double) (z1 - f), 0.0D, 0.0D, 0.0D));
			} else if (direction == 3) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) y1,
						(double) (z1 + f), 0.0D, 0.0D, 0.0D);
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntityCactusFlameFX(world,
								(double) (x1 + f1), (double) y1,
								(double) (z1 + f), 0.0D, 0.0D, 0.0D));
			}
		}
	}

	/*
	 * If this returns true, then comparators facing away from this block will
	 * use the value from getComparatorInputOverride instead of the actual
	 * redstone signal strength.
	 */
	public boolean hasComparatorInputOverride() {
		return true;
	}

	/*
	 * If hasComparatorInputOverride returns true, the return value from this is
	 * used instead of the redstone signal strength when this block inputs to a
	 * comparator.
	 */
	public int getComparatorInputOverride(World world, int x, int y, int z,
			int k) {
		return Container.calcRedstoneFromInventory((IInventory) world
				.getTileEntity(x, y, z));
	}

	/*
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_,
			int p_149674_4_, Random p_149674_5_) {

		if (p_149674_1_.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_)) {
			int l;

			for (l = 1; p_149674_1_.getBlock(p_149674_2_, p_149674_3_ - l,
					p_149674_4_) == this; ++l) {
				;
			}

			if (l < 3) {
				int i1 = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_,
						p_149674_4_);

				if (i1 == 15) {
					p_149674_1_.setBlock(p_149674_2_, p_149674_3_ + 1,
							p_149674_4_, this);
					p_149674_1_.setBlockMetadataWithNotify(p_149674_2_,
							p_149674_3_, p_149674_4_, 0, 4);
					this.onNeighborBlockChange(p_149674_1_, p_149674_2_,
							p_149674_3_ + 1, p_149674_4_, this);
				} else {
					p_149674_1_.setBlockMetadataWithNotify(p_149674_2_,
							p_149674_3_, p_149674_4_, i1 + 1, 4);
				}
			}
		}
	}

	/*
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_,
			int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((double) ((float) p_149668_2_ + f),
				(double) p_149668_3_, (double) ((float) p_149668_4_ + f),
				(double) ((float) (p_149668_2_ + 1) - f),
				(double) ((float) (p_149668_3_ + 1) - f),
				(double) ((float) (p_149668_4_ + 1) - f));
	}

	/*
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_,
			int p_149633_2_, int p_149633_3_, int p_149633_4_) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((double) ((float) p_149633_2_ + f),
				(double) p_149633_3_, (double) ((float) p_149633_4_ + f),
				(double) ((float) (p_149633_2_ + 1) - f),
				(double) (p_149633_3_ + 1),
				(double) ((float) (p_149633_4_ + 1) - f));
	}

	/*
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/*
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	/*
	 * The type of render function that is called for this block
	 */
	public int getRenderType() {
		return 13;
	}

	/*
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_,
			int p_149742_3_, int p_149742_4_) {
		return !super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_,
				p_149742_4_) ? false : this.canBlockStay(p_149742_1_,
				p_149742_2_, p_149742_3_, p_149742_4_);
	}

	/*
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_,
			int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		if (!this.canBlockStay(p_149695_1_, p_149695_2_, p_149695_3_,
				p_149695_4_)) {
			p_149695_1_.func_147480_a(p_149695_2_, p_149695_3_, p_149695_4_,
					true);
		}
	}

	/*
	 * Can this block stay at this position. Similar to canPlaceBlockAt except
	 * gets checked often with plants.
	 */
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_,
			int p_149718_3_, int p_149718_4_) {
		if (p_149718_1_.getBlock(p_149718_2_ - 1, p_149718_3_, p_149718_4_)
				.getMaterial().isSolid()) {
			return false;
		} else if (p_149718_1_
				.getBlock(p_149718_2_ + 1, p_149718_3_, p_149718_4_)
				.getMaterial().isSolid()) {
			return false;
		} else if (p_149718_1_
				.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ - 1)
				.getMaterial().isSolid()) {
			return false;
		} else if (p_149718_1_
				.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ + 1)
				.getMaterial().isSolid()) {
			return false;
		} else {
			Block block = p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1,
					p_149718_4_);
			return block.canSustainPlant(p_149718_1_, p_149718_2_,
					p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, this);

		}
	}

	/*
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_,
			int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
		p_149670_5_.attackEntityFrom(DamageSource.cactus, 1.0F);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Desert;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return -1;
	}
}