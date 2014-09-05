package io.github.mattkx4.morefurnaces.tileentity.tier3;

import io.github.mattkx4.morefurnaces.blocks.tier3.QuartzFurnaceT3;
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

public class TileEntityQuartzFurnaceT3 extends TileEntity implements ISidedInventory{
private String localizedName;
	
	private static final int[] slots_top = new int[]{0,1,2};
	private static final int[] slots_bottom = new int[]{4,5,6};
	private static final int[] slots_side = new int[]{3};
	
	private ItemStack[] slots = new ItemStack [7];
	
	// Inverse of furnace efficiency for fuels, 
	public double furnaceEfficiency = 0.5D; 

	// Speed of the furnace. A lower integer means a faster speed (Regular furnace is 200)
	public int furnaceSpeed = 200;
	
	// Number of ticks the furnace will burn for
	public int burnTime;
	
	// Number of ticks a fresh piece of fuel will burn for
	public int currentItemBurnTime;
	
	// Number of ticks an item has been cooking for
	public int cookTime1;
	public int cookTime2;
	public int cookTime3;
	
	
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
	// Returns that itemstack that was in the slot before closing and clears the slot
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.slots[i] != null){
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
			return null;
	}

	@Override
	// Sets a certain slot to the specified ItemStack
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	// Gets the custom inventory name
	public String getInventoryName(){
		return this.hasCustomInventoryName() ? this.localizedName : "container.quartzFurnaceT3";
	}
	
	// Checks to see if inventory has custom name
	public boolean hasCustomInventoryName(){
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	// Sets GUI Display Name
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
        this.cookTime1 = (int)nbt.getShort("CookTime1");
        this.cookTime2 = (int)nbt.getShort("CookTime2");
        this.cookTime3 = (int)nbt.getShort("CookTime3");
        this.currentItemBurnTime = (int)nbt.getShort("CurrentItemBurnTime");

        if (nbt.hasKey("CustomName")){
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short)this.burnTime);
        nbt.setShort("CookTime1", (short)this.cookTime1);
        nbt.setShort("CookTime2", (short)this.cookTime2);
        nbt.setShort("CookTIme3",  (short)this.cookTime3);
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
	// Returns inventory stack limit
	public int getInventoryStackLimit() {
		return 64;
	}
	
    // Gets the cooking progress (Scaled) for item 1
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled1(int i){
		return this.cookTime1 * i / this.furnaceSpeed;
	}
	
    // Gets the cooking progress (Scaled) for item 2
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled2(int i){
		return this.cookTime2 * i / this.furnaceSpeed;
	}
	
    // Gets the cooking progress (Scaled) for item 3
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled3(int i){
		return this.cookTime3 * i / this.furnaceSpeed;
	}
		
	// Get the remaining burn time (Scaled)
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i){
		if(this.currentItemBurnTime == 0){
			this.currentItemBurnTime = furnaceSpeed;
		}
		return this.burnTime * i / this.currentItemBurnTime;
	}
	
	// Check if the furnace is burning
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	// Update the Furnace
	public void updateEntity(){
		boolean flag = isBurning();
		boolean flag1 = false;
		
		if(this.burnTime > 0){
			--this.burnTime;
		}
		if(!this.worldObj.isRemote) {
			//if the burnTime has reached zero and there is an item that can be smelted
			if(this.burnTime == 0 && (this.canSmelt1() || this.canSmelt2() || this.canSmelt3())) {
				//set currentItemBurnTime and burnTime to the fuel item burn time || add a '+1' after fuel efficiency to create an ever lasting furnace
				this.currentItemBurnTime = this.burnTime = (int) (((double)getItemBurnTime(this.slots[3]) / (double)this.furnaceEfficiency));

				if(this.isBurning()) {
					flag1 = true;

					//update for fuel slot item
					if(this.slots[3] != null) {
						this.slots[3].stackSize--;

						if(this.slots[3].stackSize == 0) {
							this.slots[3] = this.slots[3].getItem().getContainerItem(this.slots[3]);
						}
					}
				}
			}
			
			if(this.isBurning() && this.canSmelt1()) {
				++this.cookTime1;
	
				if(this.cookTime1 == this.furnaceSpeed) {
					this.cookTime1 = 0;
					this.smeltItem1();
					flag1 = true;
				}
			}else{
				this.cookTime1 = 0;
			}
			
			if(this.isBurning() && this.canSmelt2()) {
				++this.cookTime2;
	
				if(this.cookTime2 == this.furnaceSpeed) {
					this.cookTime2 = 0;
					this.smeltItem2();
					flag1 = true;
				}
			}else{
				this.cookTime2 = 0;
			}

			if(this.isBurning() && this.canSmelt3()) {
				++this.cookTime3;
	
				if(this.cookTime3 == this.furnaceSpeed) {
					this.cookTime3 = 0;
					this.smeltItem3();
					flag1 = true;
				}
			}else{
				this.cookTime3 = 0;
			}

			if(flag != this.isBurning()) {
				flag1 = true;
				QuartzFurnaceT3.updateQuartzFurnaceT3State(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if(flag1){
			this.markDirty();
		}
	}
	
	// Check to see if item in slot 0 can be smelted into slot 4
	public boolean canSmelt1(){
		if(this.slots[0] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
		
			if(itemstack == null) return false;
			if(this.slots[4] == null) return true;
			if(!this.slots[4].isItemEqual(itemstack)) return false;
			
			int result = slots[4].stackSize + itemstack.stackSize;
			
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
	}
	
	//Check to see if item in slot 1 can be smelted into slot 5
	public boolean canSmelt2(){
		if(this.slots[1] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[1]);
			
			if(itemstack == null) return false;
			if(this.slots[5] == null) return true;
			if(!this.slots[5].isItemEqual(itemstack)) return false;
			
			int result = slots[5].stackSize + itemstack.stackSize;
			
			return result <=getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
	}
	
	//Check to see if item in slot 2 can be smelted into slot 6
		public boolean canSmelt3(){
			if(this.slots[2] == null){
				return false;
			}else{
				ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[2]);
				
				if(itemstack == null) return false;
				if(this.slots[6] == null) return true;
				if(!this.slots[6].isItemEqual(itemstack)) return false;
				
				int result = slots[6].stackSize + itemstack.stackSize;
				
				return result <=getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
			}
		}
	
	// Smelt the item in slot 0 and put the result in the slot 4
	public void smeltItem1(){
		if(this.canSmelt1()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			if(this.slots[4] == null){
				this.slots[4] = itemstack.copy();
			}else if(this.slots[4].getItem() == itemstack.getItem()){
				this.slots[4].stackSize += itemstack.stackSize;
			}
	
			--this.slots[0].stackSize;
	
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
			}
		}
	}
	
	//Smelt the item in slot 1 and put the result into slot 5
	public void smeltItem2(){
		if(this.canSmelt2()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[1]);
			if(this.slots[5] == null){
				this.slots[5] = itemstack.copy();
			}else if(this.slots[5].getItem() == itemstack.getItem()){
				this.slots[5].stackSize += itemstack.stackSize;
			}
			
			-- this.slots[1].stackSize;
			
			if(this.slots[1].stackSize <= 0){
				this.slots[1] = null;
			}
		}
	}
	
	//Smelt the item in slot 2 and put the result into slot 6
	public void smeltItem3(){
		if(this.canSmelt3()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[2]);
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			
			-- this.slots[2].stackSize;
			
			if(this.slots[2].stackSize <= 0){
				this.slots[2] = null;
			}
		}
	}


	// Get fuel burn times
	public static int getItemBurnTime(ItemStack itemstack){
		if(itemstack == null){
			return 0;
		}else{
			Item item = itemstack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
				Block block = Block.getBlockFromItem(item);
							
				//insert block based fuels
                if (block == Blocks.wooden_slab) return 150;
                if (block.getMaterial() == Material.wood)return 300;
                if(block == Blocks.coal_block) return 14400;
			}	
			
				//insert item based fuels
				if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item == Items.stick) return 100;
	            if (item == Items.coal) return 1600;
	            if (item == Items.lava_bucket) return 20000;
	            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
	            if (item == Items.blaze_rod) return 2400;
			}	            
		return GameRegistry.getFuelValue(itemstack);
	}
	
	// Checks if the specified item is a fuel
	public static  boolean isItemFuel(ItemStack itemstack){
		return getItemBurnTime(itemstack) > 0;
	}
	
	// Checks to see if the Player is in range of furnace
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {}
	public void closeInventory() {}

	// Checks to see if item can go in specified slot
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i == 4 || i == 5 || i == 6){
			return false;
		}else if(i == 3){
			if(isItemFuel(itemstack)){
				return true;
			}
		}else if(i == 0 || i == 1 || i == 2){
			return true;
			}
		return false;
	}
	
	// What slots are accessible from the different sides
	public int[] getAccessibleSlotsFromSide(int i) {
		return (i == 0 || i == 1 || i == 2) ? slots_bottom : (i == 3 ? slots_top : slots_side);
	}

	// Checks to see if hopper can insert item into specified slot
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	// Checks to see if a hopper can extract a certain item from specified slot
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		//yes as long as its not from slot 0, slot 1 or the item is a bucket 
		return j != 0 || j !=1 || i != 2 || j != 3 || itemstack.getItem() == Items.bucket;
	}
}
