package com.weebly.mattkx4.morefurnaces.tileentity;

import com.weebly.mattkx4.morefurnaces.blocks.PumpkinFurnace;
import com.weebly.mattkx4.morefurnaces.lib.FurnaceVariables;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityPumpkinFurnace extends TileEntity implements
		ISidedInventory {

	long oldTime;

	boolean otherSide;

	private String localizedName;

	private static final int[] slots_top = new int[] { 0 };
	private static final int[] slots_bottom = new int[] { 2, 1 };
	private static final int[] slots_side = new int[] { 1 };

	private ItemStack[] slots = new ItemStack[3];

	// Number of ticks the furnace will burn for
	public int burnTime;

	// Number of ticks a fresh piece of fuel will burn for
	public int currentItemBurnTime;

	// Number of ticks an item has been cooking for
	public int cookTime;

	// Gets the number of slots in the furnace, (example: [3])
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	// Gets the items via a number that corresponds to the slot
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}

	@Override
	// Decrease the number of items in specified slot
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	// Returns that itemstack that was in the slot before closing and clears the
	// slot
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.slots[i] != null) {
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

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	// Gets the custom inventory name
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName
				: "container.pumpkinFurnace";
	}

	// Checks to see if inventory has custom name
	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	// Sets GUI Display Name
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

		this.burnTime = (int) nbt.getShort("BurnTime");
		this.cookTime = (int) nbt.getShort("CookTime");
		this.currentItemBurnTime = (int) nbt.getShort("CurrentItemBurnTime");

		if (nbt.hasKey("CustomName")) {
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) this.burnTime);
		nbt.setShort("CookTime", (short) this.cookTime);
		nbt.setShort("CurrentBurnTime", (short) this.currentItemBurnTime);
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
	// Returns inventory stack limit
	public int getInventoryStackLimit() {
		return 64;
	}

	// Gets the cooking progress (Scaled)
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		return this.cookTime * i / FurnaceVariables.PUMPKIN_FURNACE_SPEED;
	}

	// Get the remaining burn time (Scaled)
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {

		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = FurnaceVariables.PUMPKIN_FURNACE_SPEED;
		}

		int result = this.burnTime * i / this.currentItemBurnTime;
		// below if statement is a fix to the gui rendering too much flame
		// basically caps the number of pixels to render
		if (result > i) {
			return 14;
		}
		return this.burnTime * i / this.currentItemBurnTime;
	}

	// Check if the furnace is burning
	public boolean isBurning() {
		return this.burnTime > 0;
	}

	// Update the Furnace
	public void updateEntity() {

		boolean flag = isBurning();
		boolean flag1 = false;

		if (this.burnTime > 0) {
			--this.burnTime;
		}
		if (!this.worldObj.isRemote) {

			long newTime = System.currentTimeMillis();
			if ((newTime - oldTime) > 10000) {
				PumpkinFurnace.spawnBat(this.worldObj, this.xCoord,
						this.yCoord, this.zCoord);
				oldTime = newTime;
			}

			// if the burnTime has reached zero and there is an item that can be
			// smelted
			if (this.burnTime == 0 && this.canSmelt()) {
				// set currentItemBurnTime and burnTime to the fuel item burn
				// time || add a '+1' after fuel efficiency to create an ever
				// lasting furnace
				this.currentItemBurnTime = this.burnTime = (int) (((double) getItemBurnTime(this.slots[1]) / FurnaceVariables.PUMPKIN_FURNACE_EFFICIENCY));

				if (this.isBurning()) {
					flag1 = true;

					if (this.slots[1] != null) {
						this.slots[1].stackSize--;

						if (this.slots[1].stackSize == 0) {
							this.slots[1] = this.slots[1].getItem()
									.getContainerItem(this.slots[1]);
						}
					}
				}
			}
			if (this.isBurning() && this.canSmelt()) {
				++this.cookTime;

				if (this.cookTime == FurnaceVariables.PUMPKIN_FURNACE_SPEED) {
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.cookTime = 0;
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				PumpkinFurnace.updatePumpkinFurnaceState(this.burnTime > 0,
						this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1) {
			this.markDirty();
		}
	}

	// Check to see if item can be smelted
	public boolean canSmelt() {
		if (this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
					this.slots[0]);

			if (itemstack == null)
				return false;
			if (this.slots[2] == null)
				return true;
			if (!this.slots[2].isItemEqual(itemstack))
				return false;

			int result = slots[2].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit()
					&& result <= itemstack.getMaxStackSize();
		}
	}

	// Smelt the input item and put the result in the output slot
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
					this.slots[0]);
			if (this.slots[2] == null) {
				this.slots[2] = itemstack.copy();
			} else if (this.slots[2].getItem() == itemstack.getItem()) {
				this.slots[2].stackSize += itemstack.stackSize;
			}

			--this.slots[0].stackSize;

			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	// Get fuel burn times
	public static int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();
			ItemStack itemStack = itemstack;

			if (item instanceof ItemBlock
					&& Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);
				if (block == Blocks.pumpkin)
					return 800;

			}

			// insert item based fuels
			if (item == Items.bone)
				return 200;
			if (item == Items.rotten_flesh)
				return 200;
			if (item == Items.gunpowder)
				return 200;
			if (item == Items.spider_eye)
				return 200;
			if (item == Items.fermented_spider_eye)
				return 400;
		}
		return GameRegistry.getFuelValue(itemstack);
	}

	// Checks if the specified item is a fuel
	public static boolean isItemFuel(ItemStack itemstack) {
		return getItemBurnTime(itemstack) > 0;
	}

	// Checks to see if the Player is in range of furnace
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	// Checks to see if item can go in specified slot
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
	}

	// What slots are accessible from the different sides
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}

	// Checks to see if hopper can insert item into specified slot
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	// Checks to see if a hopper can extract a certain item from specified slot
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// yes as long as its not from slot 0, slot 1 or the item is a bucket
		return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
	}
}
