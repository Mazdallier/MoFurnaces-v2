package io.github.mattkx4.morefurnaces.blocks;

import io.github.mattkx4.morefurnaces.blocks.tier2.MFMT2Blocks;
import io.github.mattkx4.morefurnaces.items.MFMItems;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DiamondFurnace extends BlockContainer{

private final boolean isActive;
	
	/*
	 * Boolean to tell if the furnace is active
	 */
	private Random rand = new Random();
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	private static boolean keepInventory;
	
	public DiamondFurnace(boolean isActive) {
		super(Material.rock);
		this.isActive = isActive;
		this.setHarvestLevel("pickaxe", 2);
	}
	
	/*
	 * What item is dropped from the block
	 */
	public Item getItemDropped(int i, Random random, int j){
		return Item.getItemFromBlock(MFMBlocks.DiamondFurnaceIdle);	
	}	
	
	/*
	 * Called whenever the block is added to the world
	 */
	public void onBlockAdded (World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}	

	/*
	 * Set the default direction of the block in the world
	 */
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote){
			Block b1 = world.getBlock(x, y, z-1);
			Block b2 = world.getBlock(x, y, z+1);
			Block b3 = world.getBlock(x-1, y, z);
			Block b4 = world.getBlock(x+1, y, z);
			
			byte b0 = 3;
			
			if(b1.func_149730_j() && !b2.func_149730_j()){
				b0=3;				
			}
			
			if(b2.func_149730_j() && !b1.func_149730_j()){
				b0=2;				
			}	
			
			if(b3.func_149730_j() && !b4.func_149730_j()){
				b0=5;
			}
			
			if(b4.func_149730_j() && !b3.func_149730_j()){
				b0=4;				
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	/*
	 * Set different icon sides arguments:side, metadata
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return metadata == 0 && side == 3 ? this.iconFront : side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != metadata ? this.blockIcon : this.iconFront));
	}	
	
	/*
	 * Register item side textures to icons
	 */
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Strings.MODID + ":DiamondFurnace_side");
		this.iconFront = iconRegister.registerIcon(Strings.MODID + ":" + (this.isActive ? "DiamondFurnace_front_active" : "DiamondFurnace_front_idle"));
		this.iconTop = iconRegister.registerIcon(Strings.MODID + ":DiamondFurnace_top");
	}
	
	/*
	 * Called upon on block activation (right click) to open the GUI
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz){
		// Tiers the Diamond Furnace to Tier 2
		if(player.getCurrentEquippedItem() != null) {
			if(player.getCurrentEquippedItem().getItem() == MFMItems.Tier2Device) {
				TileEntityDiamondFurnace tileentity = (TileEntityDiamondFurnace)world.getTileEntity(x, y, z);
				ItemStack input;
				ItemStack fuel;
				ItemStack product;
				if(tileentity.getStackInSlot(0) != null) {
					input = tileentity.getStackInSlot(0).copy();
				} else {
					input = null;
				}
				if(tileentity.getStackInSlot(1) != null) {
					fuel = tileentity.getStackInSlot(1).copy();
				} else {
					fuel = null;
				}
				if(tileentity.getStackInSlot(2) != null) {
					product = tileentity.getStackInSlot(2).copy();
				} else {
					product = null;
				}
				tileentity.setInventorySlotContents(0, new ItemStack(Items.diamond));
				tileentity.setInventorySlotContents(1, null);
				tileentity.setInventorySlotContents(2, null);

				//fix  to the block direction resetting problem
				int i = world.getBlockMetadata(x, y, z);
				world.setBlock(x, y, z, MFMT2Blocks.DiamondFurnaceT2Idle);
				world.setBlockMetadataWithNotify(x, y, z, i, 2);
				
				TileEntityDiamondFurnaceT2 tileentityT2 = (TileEntityDiamondFurnaceT2)world.getTileEntity(x, y, z);
				if(input != null){ tileentityT2.setInventorySlotContents(0, input); }
				if(fuel != null){ tileentityT2.setInventorySlotContents(2, fuel); }
				if(product != null){ tileentityT2.setInventorySlotContents(3, product); }	
				return true;
			}
		}
		if(!world.isRemote) {
			FMLNetworkHandler.openGui(player, MoFurnacesMod.instance, MoFurnacesMod.guiIDDiamondFurnace, world, x, y, z);
		}
		return true;
	}

	public static void updateDiamondFurnaceState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		
		if(active == true){
			worldObj.setBlock(xCoord, yCoord, zCoord, MFMBlocks.DiamondFurnaceActive);
		}else{
			worldObj.setBlock(xCoord, yCoord, zCoord, MFMBlocks.DiamondFurnaceIdle);
		}
		keepInventory = false;
		
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if(tileentity != null){
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}
	
	/*
	 * Create the tile entity
	 */
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityDiamondFurnace();
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack){
		int l = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		
		if(l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		
		if(l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if(l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		
		if(itemstack.hasDisplayName()){
			((TileEntityDiamondFurnace)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}

	/*
	 * What to do to the block and it's contents when it is broken
	 */
	public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldmetadata){
        if (!keepInventory){
            TileEntityDiamondFurnace tileentity = (TileEntityDiamondFurnace)world.getTileEntity(x, y, z);

            if (tileentity != null){
                for (int i = 0; i < tileentity.getSizeInventory(); ++i){
                    ItemStack itemstack = tileentity.getStackInSlot(i);

                    if (itemstack != null){
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0){
                            int j = this.rand.nextInt(21) + 10;

                            if (j > itemstack.stackSize){
                                j = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j;
                            EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()){
                                item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }
                            //omit following at a later date
                            /**
                            float f3 = 0.05F;
                            item.motionX = (double)((float)this.rand.nextGaussian() * f3);
                            item.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                            item.motionZ = (double)((float)this.rand.nextGaussian() * f3);
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
     * A randomly called display update to add particles or other items for display
     * in this case it is adding smoke and flames.
     */
   @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random){
        if (this.isActive){
            int direction = world.getBlockMetadata(x, y, z);
            float x1 = (float)x + 0.5F;
            float y1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float z1 = (float)z + 0.5F;
            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            if(direction == 4){
            	world.spawnParticle("smoke", (double)(x1 - f), (double)y1, (double)(z1 + f1), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(x1 - f), (double)y1, (double)(z1 + f1), 0.0D, 0.0D, 0.0D);
            }else if (direction == 5){
            	world.spawnParticle("smoke", (double)(x1 + f), (double)y1, (double)(z1 + f1), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(x1 + f), (double)y1, (double)(z1 + f1), 0.0D, 0.0D, 0.0D);
            }else if (direction == 2){
            	world.spawnParticle("smoke", (double)(x1 + f1), (double)y1, (double)(z1 - f), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(x1 + f1), (double)y1, (double)(z1 - f), 0.0D, 0.0D, 0.0D);
            }else if (direction == 3){
            	world.spawnParticle("smoke", (double)(x1 + f1), (double)y1, (double)(z1 + f), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(x1 + f1), (double)y1, (double)(z1 + f), 0.0D, 0.0D, 0.0D);
            }
        }
    }

   /*
    * If this returns true, then comparators facing away from this block will use the value from
    * getComparatorInputOverride instead of the actual redstone signal strength.
    */
   public boolean hasComparatorInputOverride(){
        return true;
    }

   /*
    * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
    * strength when this block inputs to a comparator.
    */
   public int getComparatorInputOverride(World world, int x, int y, int z, int k){
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
    }

   /*
    * Gets an item for the block being called on. Args: world, x, y, z
    */
   @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z){
        return Item.getItemFromBlock(MFMBlocks.DiamondFurnaceIdle);
    }
}
