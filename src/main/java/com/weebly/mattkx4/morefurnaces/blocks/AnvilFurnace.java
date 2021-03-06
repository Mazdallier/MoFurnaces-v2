package com.weebly.mattkx4.morefurnaces.blocks;

import java.util.Random;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;
import com.weebly.mattkx4.morefurnaces.particles.EntityRedstoneFlameFX;
import com.weebly.mattkx4.morefurnaces.renderer.RenderAnvilFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
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
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AnvilFurnace extends BlockContainer {

	/*
	 * Boolean to tell if the furnace is active
	 */
	public final boolean isActive;

	private Random rand = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	private static boolean keepInventory;

	public AnvilFurnace(boolean isActive) {
		super(Material.rock);
		this.isActive = isActive;
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockBounds(0F, 0f, 0F, 1F, 1F, 1F);
	}

	// render type for custom rendering
	public int getRenderType() {
		return -1;
	}

	// set the block to not opaque for custom rendering
	public boolean isOpaqueCube() {
		return false;
	}

	// set to false the boolean render as a normal block for custom rendering
	public boolean renderAsNormalBlock() {
		return false;
	}

	/*
	 * What item is dropped from the block
	 */
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(MFMBlocks.AnvilFurnaceIdle);
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
				+ ":AnvilFurnace_block");
	}

	/*
	 * Called upon on block activation (right click) to open the GUI
	 */
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitx, float hity, float hitz) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, MoFurnacesMod.instance,
					MoFurnacesMod.guiIDAnvilFurnace, world, x, y, z);
		}
		return true;
	}

	public static void updateAnvilFurnaceState(boolean active, World worldObj,
			int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;

		if (active == true) {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					MFMBlocks.AnvilFurnaceActive);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					MFMBlocks.AnvilFurnaceIdle);
		}
		keepInventory = false;

		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	/*
	 * Create the tile entity
	 */
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityAnvilFurnace();
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
			((TileEntityAnvilFurnace) world.getTileEntity(x, y, z))
					.setGuiDisplayName(itemstack.getDisplayName());
		}
	}

	/*
	 * What to do to the block and it's contents when it is broken
	 */
	public void breakBlock(World world, int x, int y, int z, Block oldblock,
			int oldmetadata) {
		if (!keepInventory) {
			TileEntityAnvilFurnace tileentity = (TileEntityAnvilFurnace) world
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
			// int direction = world.getBlockMetadata(x, y, z);
			float x1 = (float) x + 0.5F;
			float y1 = (float) y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float z1 = (float) z + 0.5F;
			float f = 0.52F;
			float f1 = random.nextFloat() * 0.6F - 0.3F;

			world.spawnParticle("smoke", (double) (x1 - f), (double) y1 + 0.5F,
					(double) (z1 + f1), 0.0D, 0.0D, 0.0D);
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new EntityRedstoneFlameFX(world,
							(double) (x1 - f), (double) y1 + 0.5F,
							(double) (z1 + f1), 0.0D, 0.0D, 0.0D));
			world.spawnParticle("smoke", (double) (x1 + f1),
					(double) y1 + 0.5F, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new EntityRedstoneFlameFX(world,
							(double) (x1 + f), (double) y1 + 0.5F,
							(double) (z1 + f1), 0.0D, 0.0D, 0.0D));
			world.spawnParticle("smoke", (double) (x1 + f1),
					(double) y1 + 0.5F, (double) (z1 - f1), 0.0D, 0.0D, 0.0D);
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new EntityRedstoneFlameFX(world,
							(double) (x1 + f1), (double) y1 + 0.5F,
							(double) (z1 - f), 0.0D, 0.0D, 0.0D));
			world.spawnParticle("smoke", (double) (x1 + f1),
					(double) y1 + 0.5F, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new EntityRedstoneFlameFX(world,
							(double) (x1 + f1), (double) y1 + 0.5F,
							(double) (z1 + f), 0.0D, 0.0D, 0.0D));
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
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(MFMBlocks.AnvilFurnaceIdle);
	}

	/*
	 * Called when an entity runs into the block.
	 */
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_,
			int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
		p_149670_5_.attackEntityFrom(DamageSource.cactus, 1.0F);
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
}
