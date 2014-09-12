package io.github.mattkx4.morefurnaces.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFuelLessFurnace extends TileEntity implements ISidedInventory{
	private String localizedName;
	
	private static final int[] slots_input = new int[]{0};
	private static final int[] slots_output = new int[]{1};
	
	private ItemStack[] slots = new ItemStack[2];
	
	public int furnaceSpeed = 200;
	public int cookTime;
	
	public int getSizeInventory() {
		return this.slots.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(this.slots[i] != null) {
			ItemStack itemstack;
			
			if(this.slots[i].stackSize <= j) {
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);
				
				if(this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container.fuelLessFurnace";
	}
	
	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			
			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.cookTime = (int) nbt.getShort("CookTime");
		
		if (nbt.hasKey("CustomKey")) {
			this.localizedName = nbt.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("CookTime", (short) this.cookTime);
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("Items", list);
		
		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.localizedName);
		}
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void updateEntity() {
		boolean flag = false;
		
		if(this.canSmelt()) {
			++this.cookTime;
			
			if(this.cookTime == this.furnaceSpeed) {
				this.cookTime = 0;
				this.smeltItem();
				flag = true;
			}
		} else {
			this.cookTime = 0;
		}
		
		if (flag) {
			this.markDirty();
		}
	}
	
	public boolean canSmelt() {
		if(this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(itemstack == null) return false;
			if(this.slots[1] == null) return true;
			if(!this.slots[1].isItemEqual(itemstack)) return false;
			
			int result = slots[1].stackSize + itemstack.stackSize;
			
			return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			if(this.slots[1] == null) {
				this.slots[1] = itemstack.copy();
			} else if(this.slots[1].getItem() == itemstack.getItem()) {
				this.slots[1].stackSize += itemstack.stackSize;
			}
			
			--this.slots[0].stackSize;
			
			if(this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public void openInventory() {}
	
	public void closeInventory() {}
	
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int[] getAccessibleSlotsFromSide(int i) {
		if(i == 0) {
			return slots_input;
		} else if(i == 1) {
			return slots_output;
		}
		return null;
	}
	
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}
	
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return itemstack.getItem() == Items.bucket || j != 0;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i){
		return this.cookTime * i / this.furnaceSpeed;
	}
}