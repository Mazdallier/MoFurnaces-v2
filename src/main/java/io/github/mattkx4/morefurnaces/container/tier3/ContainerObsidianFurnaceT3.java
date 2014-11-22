package io.github.mattkx4.morefurnaces.container.tier3;

import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerObsidianFurnaceT3 extends Container {
	private TileEntityObsidianFurnaceT3 obsidianFurnaceT3;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime1;
	public int lastCookTime2;
	public int lastCookTime3;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerObsidianFurnaceT3(InventoryPlayer inventory,
			TileEntityObsidianFurnaceT3 tileentity) {
		this.obsidianFurnaceT3 = tileentity;

		/**
		 * - Slot is a new input/output slot - slots 0, 1 and 2 are inputs for
		 * items, - slot 3 is the fuel input - SlotFurnace is a new furnace
		 * output slot - slots 4, 5 and 6 are outputs for cooked items - The
		 * second last integer is the left most position of the slot where the
		 * item will go - The last integer is the location of the top most
		 * pixels of the slot - By slot I refer to the white square that is
		 * rendered when mousing over an item position on the GUI
		 */
		this.addSlotToContainer(new Slot(tileentity, 0, 18, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 38, 17));
		this.addSlotToContainer(new Slot(tileentity, 2, 58, 17));
		this.addSlotToContainer(new Slot(tileentity, 3, 38, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				4, 117, 17));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				5, 117, 37));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				6, 117, 57));

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
				this.obsidianFurnaceT3.cookTime1);
		icrafting.sendProgressBarUpdate(this, 1,
				this.obsidianFurnaceT3.cookTime2);
		icrafting.sendProgressBarUpdate(this, 2,
				this.obsidianFurnaceT3.cookTime3);
		icrafting.sendProgressBarUpdate(this, 3,
				this.obsidianFurnaceT3.burnTime);
		icrafting.sendProgressBarUpdate(this, 4,
				this.obsidianFurnaceT3.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any
	 * changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime1 != this.obsidianFurnaceT3.cookTime1) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.obsidianFurnaceT3.cookTime1);
			}

			if (this.lastCookTime2 != this.obsidianFurnaceT3.cookTime2) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.obsidianFurnaceT3.cookTime2);
			}

			if (this.lastCookTime3 != this.obsidianFurnaceT3.cookTime3) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.obsidianFurnaceT3.cookTime3);
			}

			if (this.lastBurnTime != this.obsidianFurnaceT3.burnTime) {
				icrafting.sendProgressBarUpdate(this, 3,
						this.obsidianFurnaceT3.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.obsidianFurnaceT3.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 4,
						this.obsidianFurnaceT3.currentItemBurnTime);
			}
		}

		this.lastCookTime1 = this.obsidianFurnaceT3.cookTime1;
		this.lastCookTime2 = this.obsidianFurnaceT3.cookTime2;
		this.lastCookTime3 = this.obsidianFurnaceT3.cookTime3;
		this.lastBurnTime = this.obsidianFurnaceT3.burnTime;
		this.lastCurrentItemBurnTime = this.obsidianFurnaceT3.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.obsidianFurnaceT3.cookTime1 = par2;
		}

		if (par1 == 1) {
			this.obsidianFurnaceT3.cookTime2 = par2;
		}

		if (par1 == 2) {
			this.obsidianFurnaceT3.cookTime3 = par2;
		}

		if (par1 == 3) {
			this.obsidianFurnaceT3.burnTime = par2;
		}

		if (par1 == 4) {
			this.obsidianFurnaceT3.currentItemBurnTime = par2;
		}
	}

	/**
	 * Called when a player shift clicks a slot
	 * 
	 * - int slots is the slot ID created at the beginning of the class - 0 =
	 * input 1, 1 = input 2, 2 = input 3, 3 = fuel input, 4 = output 1, 5 =
	 * output 2, 6 = output 3 - 7-43 are the inventory slots
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
			if (slots == 4 || slots == 5 || slots == 6) {
				// if there is a similar itemstack in inventory then move the
				// itemstack from output to the inventory
				if (!this.mergeItemStack(itemstack1, 7, 43, true)) {
					return null;
				}
				/*
				 * else if the slots are not inputs (NOTE* the syntax works but
				 * doesn't make sense, it is essentially looking at all other
				 * slots including the inventory slots)
				 */
			} else if ((slots != 0 && slots != 1 && slots != 2 && slots != 3)) {
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
					if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
						return null;
					}
					// If the item is fuel then the code tries to put the item
					// into the fuel slot (slot 2)
				} else if (TileEntityObsidianFurnaceT3.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 3, 4, false)) {
						return null;
					}
				} else if (slots >= 7 && slots < 34) {
					if (!this.mergeItemStack(itemstack1, 34, 43, false)) {
						return null;
					}
				} else if (slots >= 34 && slots < 43
						&& !this.mergeItemStack(itemstack1, 7, 34, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 7, 43, false)) {
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
