package io.github.mattkx4.morefurnaces.tileentity;

import io.github.mattkx4.morefurnaces.blocks.NetherrackFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityNetherrackFurnace extends TileEntity implements ISidedInventory{
	
	private String localizedName;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2,1};
	private static final int[] slots_side = new int[]{1};
	
	private ItemStack[] slots = new ItemStack [3];
	
	// Inverse of furnace efficiency for fuels, 
	public int furnaceEfficiency = 1;

	// Speed of the furnace. A lower integer means a faster speed (Regular furnace is 200)
	public int furnaceSpeed = 450;

	// Number of ticks the furnace will burn for
	public int burnTime;
	
	// Number of ticks a fresh piece of fuel will burn for
	public int currentItemBurnTime;
	
	// Number of ticks an item has been cooking for
	public int cookTime;
	
	
	// Gets the number of slots in the furnace, (example: [3])
	public int getSizeInventory(){
		return this.slots.length;
	}	

	@Override
	// Gets the items via a number that corresponds to the slot
	public ItemStack getStackInSlot(int i){
		return this.slots[i];
	}

	@Override
	// Decrease the number of items in specified slot
	public ItemStack decrStackSize(int i, int j) {
		if(this.slots[i] != null){
			ItemStack itemstack;
			
			if(this.slots[i].stackSize <= j){
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			}else{
				itemstack = this.slots[i].splitStack(j);
				
				if(this.slots[i].stackSize == 0){
					this.slots[i] = null;
				}
				return itemstack;
			}
		}else{
			return null;
		}
	}
	
	@Override
	//runs past each slot on Gui closure and determines whether or not to drop an item
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.slots[i] != null){
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
			return null;
	}

	@Override
	//sets the given item stack to a specified slot
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	public String getInventoryName(){
		return this.hasCustomInventoryName() ? this.localizedName : "container.netherrackFurnace";
	}
	
	public boolean hasCustomInventoryName(){
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public void setGuiDisplayName(String displayName){
		this.localizedName = displayName;
	}

	public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i){
            NBTTagCompound compound = list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < this.slots.length){
                this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.burnTime = (int)nbt.getShort("BurnTime");
        this.cookTime = (int)nbt.getShort("CookTime");
        this.currentItemBurnTime = (int)nbt.getShort("CurrentItemBurnTime");

        if (nbt.hasKey("CustomName")){
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short)this.burnTime);
        nbt.setShort("CookTime", (short)this.cookTime);
        nbt.setShort("CurrentBurnTime", (short)this.currentItemBurnTime);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i){
            if (this.slots[i] != null){
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if (this.hasCustomInventoryName()){
            nbt.setString("CustomName", this.localizedName);
        }
    }

    @Override
	//what is the limit of a stack
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i){
		return this.cookTime * i / this.furnaceSpeed;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i){
		if(this.currentItemBurnTime == 0){
			this.currentItemBurnTime = furnaceSpeed;
		}
		return this.burnTime * i / this.currentItemBurnTime;
	}
	
	//check if the furnace is burning
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	public void updateEntity(){
		boolean flag = isBurning();
		boolean flag1 = false;
		
		if(this.burnTime > 0){
			--this.burnTime;
		}
		if(!this.worldObj.isRemote) {
			//if the burnTime has reached zero and there is an item that can be smelted
			if(this.burnTime == 0 && this.canSmelt()) {
				//set currentItemBurnTime and burnTime to the fuel item burn time || add a '+1' after fuel efficiency to create an ever lasting furnace
				this.currentItemBurnTime = this.burnTime = (int) (((double)getItemBurnTime(this.slots[1]) / (double)this.furnaceEfficiency));

				if(this.isBurning()) {
					flag1 = true;

					if(this.slots[1] != null) {
						this.slots[1].stackSize--;

						if(this.slots[1].stackSize == 0) {
							this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
						}
					}
				}
			}
			if(this.isBurning() && this.canSmelt()) {
			++this.cookTime;

			if(this.cookTime == this.furnaceSpeed) {
				this.cookTime = 0;
				this.smeltItem();
				flag1 = true;
				}
			}else{
				this.cookTime = 0;
			}

			if(flag != this.isBurning()) {
				flag1 = true;
				NetherrackFurnace.updateNetherrackFurnaceState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if(flag1){
			this.markDirty();
		}
	}
	
	//check to see is the item can be smelted
	public boolean canSmelt(){
		if(this.slots[0] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
		
			if(itemstack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(itemstack)) return false;
			
			int result = slots[2].stackSize + itemstack.stackSize;
			
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
	}
	
	//turn one item from the input into one item of the output
	public void smeltItem(){
		if(this.canSmelt()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			if(this.slots[2] == null){
				this.slots[2] = itemstack.copy();
			}else if(this.slots[2].getItem() == itemstack.getItem()){
				this.slots[2].stackSize += itemstack.stackSize;
			}
	
			--this.slots[0].stackSize;
	
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
			}
		}
	}

	//get the item burn times
	public static int getItemBurnTime(ItemStack itemstack){
		if(itemstack == null){
			return 0;
		}else{
			Item item = itemstack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
				Block block = Block.getBlockFromItem(item);
							
				//insert block based fuels
                if (block == Blocks.netherrack) return 20000;
			}	
			
				//insert item based fuels
	            if (item == Items.lava_bucket) return 40000;
	            if (item == Items.blaze_rod) return 4800;
	            if (item == Items.blaze_powder) return 2400;
			}	            
		//making this a zero will remove all other items as fuel possibilities
		return 0;
	}
	
	//determines the number of ticks a new piece of fuel will keep the furnace burning for
	public static  boolean isItemFuel(ItemStack itemstack){
		return getItemBurnTime(itemstack) > 0;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {}
	
	public void closeInventory() {}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
	}
	
	//what sides access which slots
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}

	//can a hopper insert an item into a slot
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

		//can a hopper extract an item from a slot
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		//yes as long as its not from slot 0, slot 1 or the item is a bucket 
		return j != 0 || i!= 1 || itemstack.getItem() == Items.bucket;
	}
}
