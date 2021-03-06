package com.weebly.mattkx4.morefurnaces.container.tier2;

import com.weebly.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerObsidianFurnaceT2 extends Container {

	private TileEntityObsidianFurnaceT2 obsidianFurnaceT2;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime1;
	public int lastCookTime2;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerObsidianFurnaceT2(InventoryPlayer inventory,
			TileEntityObsidianFurnaceT2 tileentity) {
		this.obsidianFurnaceT2 = tileentity;

		/**
		 * - Slot is a new input/output slot - slots 0 and 1 are inputs for
		 * items, - slot 2 is the fuel input - SlotFurnace is a new furnace
		 * output slot - slots 3 and 4 are outputs for cooked items - The second
		 * last integer is the left most position of the slot where the item
		 * will go - The last integer is the location of the top most pixels of
		 * the slot - By slot I refer to the white square that is rendered when
		 * mousing over an item position on the GUI
		 */
		this.addSlotToContainer(new Slot(tileentity, 0, 20, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 2, 38, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				3, 116, 22));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				4, 116, 48));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	/*
	 * Sends progress bar updates
	 */
	public void addCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0,
				this.obsidianFurnaceT2.cookTime1);
		icrafting.sendProgressBarUpdate(this, 3,
				this.obsidianFurnaceT2.cookTime2);
		icrafting.sendProgressBarUpdate(this, 1,
				this.obsidianFurnaceT2.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.obsidianFurnaceT2.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any
	 * changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime1 != this.obsidianFurnaceT2.cookTime1) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.obsidianFurnaceT2.cookTime1);
			}

			if (this.lastCookTime2 != this.obsidianFurnaceT2.cookTime2) {
				icrafting.sendProgressBarUpdate(this, 3,
						this.obsidianFurnaceT2.cookTime2);
			}

			if (this.lastBurnTime != this.obsidianFurnaceT2.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.obsidianFurnaceT2.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.obsidianFurnaceT2.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.obsidianFurnaceT2.currentItemBurnTime);
			}
		}

		this.lastCookTime1 = this.obsidianFurnaceT2.cookTime1;
		this.lastCookTime2 = this.obsidianFurnaceT2.cookTime2;
		this.lastBurnTime = this.obsidianFurnaceT2.burnTime;
		this.lastCurrentItemBurnTime = this.obsidianFurnaceT2.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.obsidianFurnaceT2.cookTime1 = par2;
		}

		if (par1 == 3) {
			this.obsidianFurnaceT2.cookTime2 = par2;
		}

		if (par1 == 1) {
			this.obsidianFurnaceT2.burnTime = par2;
		}

		if (par1 == 2) {
			this.obsidianFurnaceT2.currentItemBurnTime = par2;
		}
	}

	/**
	 * Called when a player shift clicks a slot
	 * 
	 * - int slots is the slot ID created at the begining of the class - 0 =
	 * input 1, 1 = input 2, 2 = fuel input, 3 = output 1, 4 = output2 - 5-41
	 * are the inventory slots
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer,
			int slots) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slots);

		// if the slot is not null and the slot has a stack
		if (slot != null && slot.getHasStack()) {
			// set the itemstack to the slot itemstack and make a copy of it
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// if the slots are output
			if (slots == 3 || slots == 4) {
				// if there is a similar itemstack in inventory then move the
				// itemstack from output to the inventory
				if (!this.mergeItemStack(itemstack1, 5, 41, true)) {
					return null;
				}
				/*
				 * else if the slots are not inputs (NOTE* the syntax works but
				 * doesn't make sense, it is essentially looking at all other
				 * slots including the inventory slots)
				 */
			} else if ((slots != 1 && slots != 0 && slots != 2)) {
				// Find if the item has a furnace recipe and a corresponding
				// output
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					/*
					 * This is where it gets tricky, but basically the code
					 * tries to merge the right clicked item with the input
					 * slots (slot 0 or 1). If the item in the input slots
					 * matches the item in the right clicked slot then the stack
					 * is moved across. if the input slot is empty then the item
					 * is moved across.
					 * 
					 * It acts sort of like a for statement as in it starts by
					 * looking at 0, looks and 1, and then notices that the
					 * upper value of two has been reached and thus stops
					 * looking.
					 */
					if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
						return null;
					}
					// If the item is fuel then the code tries to put the item
					// into the fuel slot (slot 2)
				} else if (TileEntityObsidianFurnaceT2.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 2, 3, false)) {
						return null;
					}
				} else if (slots >= 5 && slots < 32) {
					if (!this.mergeItemStack(itemstack1, 32, 41, false)) {
						return null;
					}
				} else if (slots >= 32 && slots < 41
						&& !this.mergeItemStack(itemstack1, 5, 32, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 5, 41, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

	/*
	 * Makes it so a player can interact with it
	 */
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
