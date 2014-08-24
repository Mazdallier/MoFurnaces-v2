package io.github.mattkx4.morefurnaces.tileentity;

import java.util.List;

import io.github.mattkx4.morefurnaces.blocks.RedstoneFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
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
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRedstoneFurnace extends TileEntity implements ISidedInventory, IHopper{
	
	private String localizedName;
    private int unknownInteger = -1;
	
	private static final int[] slots_top = new int[]{0,1,2,3,4};	//inputs
	private static final int[] slots_bottom = new int[]{6,5};		//output
	private static final int[] slots_side = new int[]{5};			//fuel
	
	private ItemStack[] slots = new ItemStack [7];
	
	// Inverse of furnace efficiency for fuels, 
	public int furnaceEfficiency = 1;

	// Speed of the furnace. A lower integer means a faster speed (Regular furnace is 200)
	public int furnaceSpeed = 200;
	
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
		return this.hasCustomInventoryName() ? this.localizedName : "container.redstoneFurnace";
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
	// Returns inventory stack limit
	public int getInventoryStackLimit() {
		return 64;
	}
	
    // Gets the cooking progress (Scaled)
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i){
		return this.cookTime * i / this.furnaceSpeed;
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
		
		//following code is from the hopper tile entity class
		if (this.worldObj != null && !this.worldObj.isRemote){
            --this.unknownInteger;

            if (!this.func_145888_j()){
                this.func_145896_c(0);
                this.func_145887_i();
            }
        }
		//end of the hopper tile entity class code
		
		boolean flag = isBurning();
		boolean flag1 = false;
		
		if(this.burnTime > 0){
			--this.burnTime;
		}
		if(!this.worldObj.isRemote) {
			//if the burnTime has reached zero and there is an item that can be smelted
			if(this.burnTime == 0 && this.canSmelt()) {
				//set currentItemBurnTime and burnTime to the fuel item burn time || add a '+1' after fuel efficiency to create an ever lasting furnace
				this.currentItemBurnTime = this.burnTime = (int) (((double)getItemBurnTime(this.slots[5]) / (double)this.furnaceEfficiency));

				if(this.isBurning()) {
					flag1 = true;

					if(this.slots[5] != null) {
						this.slots[5].stackSize--;

						if(this.slots[5].stackSize == 0) {
							this.slots[5] = this.slots[5].getItem().getContainerItem(this.slots[5]);
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
				RedstoneFurnace.updateRedstoneFurnaceState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if(flag1){
			this.markDirty();
		}
	}
	
	// Check to see if item can be smelted
	// checks all five inputs and single output
	public boolean canSmelt(){
		//if all of the slots have nothing in them then nothing can smelt
		if(this.slots[0] == null && this.slots[1] == null && this.slots[2] == null && this.slots[3] == null && this.slots[4] == null){
			//return that nothing can smelt
			return false;
		}else if(this.slots[0] != null){
			//identify the item that is in the slot 0
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			//double check to see if the item is nothing then return that nothing can be smelted
			if(itemstack == null) return false;
			//if there is nothing in the output slot then return that something can smelt
			if(this.slots[6] == null) return true;
			//if there is an item in the output slot and it doesn't match the item in the input slot 
			//then return that nothing can be smelted
			if(!this.slots[6].isItemEqual(itemstack)) return false;
			//set the integer result as the stack size of the output added to the stack size of the input
			int result = slots[6].stackSize + itemstack.stackSize;
			
			//return that something can be smelted if the combined itemstack size is less than the 
			//item stack size limit and the inventory stack size limit.
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}else if(this.slots[1] != null){
			//identify the item that is in the slot 1
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[1]);
			//double check to see if the item is nothing then return that nothing can be smelted
			if(itemstack == null) return false;
			//if there is nothing in the output slot then return that something can smelt
			if(this.slots[6] == null) return true;
			//if there is an item in the output slot and it doesn't match the item in the input slot 
			//then return that nothing can be smelted
			if(!this.slots[6].isItemEqual(itemstack)) return false;
			//set the integer result as the stack size of the output added to the stack size of the input
			int result = slots[6].stackSize + itemstack.stackSize;
			
			//return that something can be smelted if the combined itemstack size is less than the 
			//item stack size limit and the inventory stack size limit.
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}else if(this.slots[2] != null){
			//identify the item that is in the slot 2
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[2]);
			//double check to see if the item is nothing then return that nothing can be smelted
			if(itemstack == null) return false;
			//if there is nothing in the output slot then return that something can smelt
			if(this.slots[6] == null) return true;
			//if there is an item in the output slot and it doesn't match the item in the input slot 
			//then return that nothing can be smelted
			if(!this.slots[6].isItemEqual(itemstack)) return false;
			//set the integer result as the stack size of the output added to the stack size of the input
			int result = slots[6].stackSize + itemstack.stackSize;
			
			//return that something can be smelted if the combined itemstack size is less than the 
			//item stack size limit and the inventory stack size limit.
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}else if(this.slots[3] != null){
			//identify the item that is in the slot 3
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[3]);
			//double check to see if the item is nothing then return that nothing can be smelted
			if(itemstack == null) return false;
			//if there is nothing in the output slot then return that something can smelt
			if(this.slots[6] == null) return true;
			//if there is an item in the output slot and it doesn't match the item in the input slot 
			//then return that nothing can be smelted
			if(!this.slots[6].isItemEqual(itemstack)) return false;
			//set the integer result as the stack size of the output added to the stack size of the input
			int result = slots[6].stackSize + itemstack.stackSize;
			
			//return that something can be smelted if the combined itemstack size is less than the 
			//item stack size limit and the inventory stack size limit.
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}else if(this.slots[4] != null){
			//identify the item that is in the slot 4
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[4]);
			//double check to see if the item is nothing then return that nothing can be smelted
			if(itemstack == null) return false;
			//if there is nothing in the output slot then return that something can smelt
			if(this.slots[6] == null) return true;
			//if there is an item in the output slot and it doesn't match the item in the input slot 
			//then return that nothing can be smelted
			if(!this.slots[6].isItemEqual(itemstack)) return false;
			//set the integer result as the stack size of the output added to the stack size of the input
			int result = slots[6].stackSize + itemstack.stackSize;
			
			//return that something can be smelted if the combined itemstack size is less than the 
			//item stack size limit and the inventory stack size limit.
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
		return false;
	}

	
	// Smelt the input item and put the result in the output slot
	public void smeltItem(){
		//staggered if statement to see if there is an item to smelt in any of the slots
		//if the slot 0 has an item
		if (this.slots[0] != null){
			//assign the item to the variable itemstack
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			//if the output slot is empty then make a copy of the item in the input and put it in the output
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			//if the item in the output equals the item in the input then increase the combined stack size
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			//reduce the stacksize of the input by one
			--this.slots[0].stackSize;
			//if the input slot hase nothing left then register it as empty
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
			}
		//if the slot 1 has an item
		}else if (this.slots[1] != null){
			//assign the item to the variable itemstack
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[1]);
			//if the output slot is empty then make a copy of the item in the input and put it in the output
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			//if the item in the output equals the item in the input then increase the combined stack size
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			//reduce the stacksize of the input by one
			--this.slots[1].stackSize;
			//if the input slot hase nothing left then register it as empty
			if(this.slots[1].stackSize <= 0){
				this.slots[1] = null;
			}	
		//if the slot 2 has an item
		}else if (this.slots[2] != null){
			//assign the item to the variable itemstack
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[2]);
			//if the output slot is empty then make a copy of the item in the input and put it in the output
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			//if the item in the output equals the item in the input then increase the combined stack size
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			//reduce the stacksize of the input by one
			--this.slots[2].stackSize;
			//if the input slot hase nothing left then register it as empty
			if(this.slots[2].stackSize <= 0){
				this.slots[2] = null;
			}	
		//if the slot 3 has an item	
		}else if (this.slots[3] != null){
			//assign the item to the variable itemstack
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[3]);
			//if the output slot is empty then make a copy of the item in the input and put it in the output
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			//if the item in the output equals the item in the input then increase the combined stack size
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			//reduce the stacksize of the input by one
			--this.slots[3].stackSize;
			//if the input slot hase nothing left then register it as empty
			if(this.slots[3].stackSize <= 0){
				this.slots[3] = null;
			}	
		//if the slot 4 has an item
		}else if (this.slots[4] != null){
			//assign the item to the variable itemstack
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[4]);
			//if the output slot is empty then make a copy of the item in the input and put it in the output
			if(this.slots[6] == null){
				this.slots[6] = itemstack.copy();
			//if the item in the output equals the item in the input then increase the combined stack size
			}else if(this.slots[6].getItem() == itemstack.getItem()){
				this.slots[6].stackSize += itemstack.stackSize;
			}
			//reduce the stacksize of the input by one
			--this.slots[4].stackSize;
			//if the input slot hase nothing left then register it as empty
			if(this.slots[4].stackSize <= 0){
				this.slots[4] = null;
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
		return i == 6 ? false : (i == 5 ? isItemFuel(itemstack) : true);
	}
	
	// What slots are accessible from the different sides
	public int[] getAccessibleSlotsFromSide(int i) {
		return (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) ? slots_bottom : (i == 5 ? slots_top : slots_side);
	}

	// Checks to see if hopper can insert item into specified slot
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	// Checks to see if a hopper can extract a certain item from specified slot
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		//yes as long as its not from slot 0, slot 1 or the item is a bucket 
		return j != 0 || j != 1 || j != 2 || j != 3 || j != 4 || i!= 1 || itemstack.getItem() == Items.bucket;
	}

	/**
	 * The next three are auto generated for the hopper code
	 */
	@Override
	public double getXPos() {
		return (double)this.xCoord;
	}

	@Override
	public double getYPos() {
		return (double)this.yCoord;
	}

	@Override
	public double getZPos() {
		return (double)this.zCoord;
	}
	
	/*
	 * everything after this comment is purely experimental, I am poking around to see if anything will work 
	 */
	
	public boolean func_145887_i()
	{
		
	    if (this.worldObj != null && !this.worldObj.isRemote)
	    {
	        if (!this.func_145888_j() && BlockHopper.func_149917_c(this.getBlockMetadata()))
	        {
	            boolean flag = false;

	            if (!this.isSlotEmpty())
	            {
	                flag = this.func_145883_k();
	            }
	
	            if (!this.func_152105_l())
	            {
	                flag = func_145891_a(this) || flag;
	            }
	
	            if (flag)
	            {
	                this.func_145896_c(8);
	                this.markDirty();
	                return true;
	            }
	        }
	
	        return false;
	    }
	    else
	    {
	        return false;
	    }
	}
	
	//I think that this is a check to see if there is an item in the slot and is the slot empty check renamed as such
	private boolean isSlotEmpty()
	{
	    ItemStack[] aitemstack = this.slots;
	    int i = aitemstack.length;
	
	    for (int j = 0; j < i; ++j)
	    {
	        ItemStack itemstack = aitemstack[j];
	
	        if (itemstack != null)
	        {
	            return false;
	        }
	    }
	
	    return true;
	}

	//this looks to be a check similar to isSlotEmpty but it looks to see if the slot is empty OR is the slot is NOT full
	private boolean func_152105_l()
	{
	    ItemStack[] aitemstack = this.slots;
	    int i = aitemstack.length;
	
	    for (int j = 0; j < i; ++j)
	    {
	        ItemStack itemstack = aitemstack[j];
	
	        if (itemstack == null || itemstack.stackSize != itemstack.getMaxStackSize())
	        {
	            return false;
	        }
	    }
	
	    return true;
	}

	private boolean func_145883_k()
	{
	    IInventory iinventory = this.func_145895_l();
	
	    if (iinventory == null)
	    {
	        return false;
	    }
	    else
	    {
	        int i = Facing.oppositeSide[BlockHopper.getDirectionFromMetadata(this.getBlockMetadata())];
	
	        if (this.func_152102_a(iinventory, i))
	        {
	            return false;
	        }
	        else
	        {
	            for (int j = 0; j < this.getSizeInventory(); ++j)
	            {
	                if (this.getStackInSlot(j) != null)
	                {
	                    ItemStack itemstack = this.getStackInSlot(j).copy();
	                    ItemStack itemstack1 = func_145889_a(iinventory, this.decrStackSize(j, 1), i);
	
	                    if (itemstack1 == null || itemstack1.stackSize == 0)
	                    {
	                        iinventory.markDirty();
	                        return true;
	                    }
	
	                    this.setInventorySlotContents(j, itemstack);
	                }
	            }
	
	            return false;
	        }
	    }
	}

	private boolean func_152102_a(IInventory inventory, int i)
	{
	    if (inventory instanceof ISidedInventory && i > -1)
	    {
	        ISidedInventory isidedinventory = (ISidedInventory)inventory;
	        int[] aint = isidedinventory.getAccessibleSlotsFromSide(i);
	
	        for (int l = 0; l < aint.length; ++l)
	        {
	            ItemStack itemstack1 = isidedinventory.getStackInSlot(aint[l]);
	
	            if (itemstack1 == null || itemstack1.stackSize != itemstack1.getMaxStackSize())
	            {
	                return false;
	            }
	        }
	    }
	    else
	    {
	        int j = inventory.getSizeInventory();
	
	        for (int k = 0; k < j; ++k)
	        {
	            ItemStack itemstack = inventory.getStackInSlot(k);
	
	            if (itemstack == null || itemstack.stackSize != itemstack.getMaxStackSize())
	            {
	                return false;
	            }
	        }
	    }
	
	    return true;
	}

	private static boolean func_152103_b(IInventory inventory, int i)
	{
	    if (inventory instanceof ISidedInventory && i > -1)
	    {
	        ISidedInventory isidedinventory = (ISidedInventory)inventory;
	        int[] aint = isidedinventory.getAccessibleSlotsFromSide(i);
	
	        for (int l = 0; l < aint.length; ++l)
	        {
	            if (isidedinventory.getStackInSlot(aint[l]) != null)
	            {
	                return false;
	            }
	        }
	    }
	    else
	    {
	        int j = inventory.getSizeInventory();
	
	        for (int k = 0; k < j; ++k)
	        {
	            if (inventory.getStackInSlot(k) != null)
	            {
	                return false;
	            }
	        }
	    }
	
	    return true;
	}

	public boolean func_145891_a(IHopper hopper)
	{
	    IInventory iinventory = func_145884_b(hopper);
	
	    if (iinventory != null)
	    {
	        byte b0 = 0;
	
	        if (func_152103_b(iinventory, b0))
	        {
	            return false;
	        }
	
	        if (iinventory instanceof ISidedInventory && b0 > -1)
	        {
	            ISidedInventory isidedinventory = (ISidedInventory)iinventory;
	            int[] aint = isidedinventory.getAccessibleSlotsFromSide(b0);
	
	            for (int k = 0; k < aint.length; ++k)
	            {
	                if (func_145892_a(hopper, iinventory, aint[k], b0))
	                {
	                    return true;
	                }
	            }
	        }
	        else
	        {
	            int i = iinventory.getSizeInventory();
	
	            for (int j = 0; j < i; ++j)
	            {
	                if (func_145892_a(hopper, iinventory, j, b0))
	                {
	                    return true;
	                }
	            }
	        }
	    }
	    else
	    {
	        EntityItem entityitem = func_145897_a(hopper.getWorldObj(), hopper.getXPos(), hopper.getYPos() + 1.0D, hopper.getZPos());
	
	        if (entityitem != null)
	        {
	            return func_145898_a(hopper, entityitem);
	        }
	    }
	
	    return false;
	}

	private boolean func_145892_a(IHopper hopper, IInventory inventory, int i, int j)
	{
	    ItemStack itemstack = inventory.getStackInSlot(i);
	
	    if (itemstack != null && canTakeFromInventory(inventory, itemstack, i, j))
	    {
	        ItemStack itemstack1 = itemstack.copy();
	        
	        //testing a theory, changing inventory.decrStackSize(i,1) to this.decreaseStackSize
	        ItemStack itemstack2 = func_145889_a(hopper, inventory.decrStackSize(i, 1), -1);
	
	        if (itemstack2 == null || itemstack2.stackSize == 0)
	        {
	        	inventory.markDirty();
	            return true;
	        }
	
	        inventory.setInventorySlotContents(i, itemstack1);
	    }
	
	    return false;
	}

	public boolean func_145898_a(IInventory inventory, EntityItem item)
	{
	    boolean flag = false;
	
	    if (item == null)
	    {
	        return false;
	    }
	    else
	    {
	        ItemStack itemstack = item.getEntityItem().copy();
	        ItemStack itemstack1 = func_145889_a(inventory, itemstack, -1);
	
	        if (itemstack1 != null && itemstack1.stackSize != 0)
	        {
	        	item.setEntityItemStack(itemstack1);
	        }
	        else
	        {
	            flag = true;
	            item.setDead();
	        }
	
	        return flag;
	    }
	}

	public  ItemStack func_145889_a(IInventory inventory, ItemStack itemstack, int i)
	{
	    if (inventory instanceof ISidedInventory && i > -1)
	    {
	        ISidedInventory isidedinventory = (ISidedInventory)inventory;
	        int[] aint = isidedinventory.getAccessibleSlotsFromSide(i);
	
	        for (int l = 0; l < aint.length && itemstack != null && itemstack.stackSize > 0; ++l)
	        {
	        	itemstack = func_145899_c(inventory, itemstack, aint[l], i);
	        }
	    }
	    else
	    {
	        int j = inventory.getSizeInventory();
	
	        for (int k = 0; k < j && itemstack != null && itemstack.stackSize > 0; ++k)
	        {
	        	itemstack = func_145899_c(inventory, itemstack, k, i);
	        }
	    }
	
	    if (itemstack != null && itemstack.stackSize == 0)
	    {
	    	itemstack = null;
	    }
	
	    return itemstack;
	}
	    
	private ItemStack func_145899_c(IInventory inventory, ItemStack item, int i, int j)
	{
		//CHANGED inventory.getStackInSlot(i); TO this.getStackInSlot(6) AS A TEST
	    ItemStack itemstack1 = inventory.getStackInSlot(i);

	    if (func_145885_a(inventory, item, i, j))
	    {
	        boolean flag = false;
	
	        if (itemstack1 == null)
	        {
	            //Forge: BUGFIX: Again, make things respect max stack sizes.
	            int max = Math.min(item.getMaxStackSize(), inventory.getInventoryStackLimit());
	            if (max >= item.stackSize)
	            {
	            	inventory.setInventorySlotContents(i, item);
	                item = null;
	            }
	            else
	            {
	            	inventory.setInventorySlotContents(i, item.splitStack(max));
	            }
	            flag = true;
	        }else if (canPutInChest(itemstack1, item)){
	           
	        		//Forge: BUGFIX: Again, make things respect max stack sizes.
		            int max = Math.min(item.getMaxStackSize(), inventory.getInventoryStackLimit());
		            if (max > itemstack1.stackSize)
		            {
		                int l = Math.min(item.stackSize, max - itemstack1.stackSize);
		                item.stackSize -= l;
		                itemstack1.stackSize += l;
		                flag = l > 0;
		            
	        	}
	        }
	
	        if (flag)
	        {
	            if (inventory instanceof TileEntityHopper)
	            {
	                ((TileEntityHopper)inventory).func_145896_c(8);
	                inventory.markDirty();
	            }
	
	            inventory.markDirty();
	        }
	    }
	
	    return item;
	}

	private IInventory func_145895_l()
	{
	    int i = BlockHopper.getDirectionFromMetadata(this.getBlockMetadata());
	    return func_145893_b(this.getWorldObj(), (double)(this.xCoord + Facing.offsetsXForSide[i]), (double)(this.yCoord + Facing.offsetsYForSide[i]), (double)(this.zCoord + Facing.offsetsZForSide[i]));
	}
	
	public static IInventory func_145884_b(IHopper hopper)
	{
	    return func_145893_b(hopper.getWorldObj(), hopper.getXPos(), hopper.getYPos() + 1.0D, hopper.getZPos());
	}
	
	public static EntityItem func_145897_a(World world, double x, double y, double z)
	{
	    List list = world.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.selectAnything);
	    return list.size() > 0 ? (EntityItem)list.get(0) : null;
	}
	
	public static IInventory func_145893_b(World world, double x, double y, double z)
	{
	    IInventory iinventory = null;
	    int i = MathHelper.floor_double(x);
	    int j = MathHelper.floor_double(y);
	    int k = MathHelper.floor_double(z);
	    TileEntity tileentity = world.getTileEntity(i, j, k);
	
	    if (tileentity != null && tileentity instanceof IInventory)
	    {
	        iinventory = (IInventory)tileentity;
	
	        if (iinventory instanceof TileEntityChest)
	        {
	            Block block = world.getBlock(i, j, k);
	
	            if (block instanceof BlockChest)
	            {
	                iinventory = ((BlockChest)block).func_149951_m(world, i, j, k);
	            }
	        }
	    }
	
	    if (iinventory == null)
	    {
	        List list = world.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.selectInventories);
	
	        if (list != null && list.size() > 0)
	        {
	            iinventory = (IInventory)list.get(world.rand.nextInt(list.size()));
	        }
	    }
	
	    return iinventory;
	}
	
	public void func_145896_c(int i)
	{
	    this.unknownInteger = i;
	}
	
	public boolean func_145888_j()
	{
	    return this.unknownInteger > 0;
	}
	/*
	 * More testing needed but this function appears to allow or block the transmission of items into and out of the furnace
	 * Straight false: Results in no items being passed in or out.
	 * Function unsure with StraightTrue test as the furnace acted almost indistinguishable from before.
	 */
	private boolean func_145885_a(IInventory inventory, ItemStack itemstack, int i, int j)
	{
		
		//changed inventory. to this.
	    return !this.isItemValidForSlot(i, itemstack) ? false : !(inventory instanceof ISidedInventory) || ((ISidedInventory)inventory).canInsertItem(i, itemstack, j);
	
		//return true;
	}
	
	/*
	 * Function: This piece of code allows for taking an item from internal inventory and placing it into a chest
	 * 
	 * Test:	Set the return to straight true and then straight false and measure response.
	 * Results: Straight True: Will increase a single item in the attached chest by the amount of 
	 * 				all items available in the internal inventory. EG a stack of gold ore and a stack 
	 * 				of iron ore in the internal inventory will yield a 2 stacks of gold or in the attached chest
	 *			Straight False:Will take one item from the stack and try and place it in a new spot.
	 *
	 *	Test: 	Set the slot it takes from to the output slot(slots[6])
	 */
	private boolean canPutInChest(ItemStack itemstack, ItemStack itemstackB){
		//original code
	    //return itemstack.getItem() != itemstackB.getItem() ? false : (itemstack.getItemDamage() != itemstackB.getItemDamage() ? false : (itemstack.stackSize > itemstack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(itemstack, itemstackB)));
			
	    /*
	     * Custom Code
	     */
		//itemstackB = this.getStackInSlot(6);
	    return itemstack.getItem() != itemstackB.getItem() ? false : (itemstack.getItemDamage() != itemstackB.getItemDamage() ? false : (itemstack.stackSize > itemstack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(itemstack, itemstackB)));

	}
	
	//This code, when altered to the custom code, controlled the ability for the furnace to absorb items from a chest
	//attached to the top. Items thrown on top would still behave as though this were a hopper
	//This code DOES NOT deal with taking from internal inventory and placing it in external inventory.
	private boolean canTakeFromInventory(IInventory inventory, ItemStack itemstack, int i, int j)
	{
		//original code
	    //return !(inventory instanceof ISidedInventory) || ((ISidedInventory)inventory).canExtractItem(i, itemstack, j);
		
		/*
		 * Custom code. if the statement asks the question, is the item fuel, and returns false then the 
		 * furnace will only allow cookable items into the input slots of the furnace, not items, not uncookables.
		 * Further testing confirms. Only items with a furnace recipe are accepted.
		 */
		//This allows only fuel items to be inputted
		if(!this.isItemFuel(itemstack)){
			return false;
		}
		return true;
		//return !(canExtractItem(i, itemstack, j) || (isItemValidForSlot(i, itemstack)));
	}
	
	
}