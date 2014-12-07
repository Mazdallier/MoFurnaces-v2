package com.weebly.mattkx4.morefurnaces.container;

import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityRedstoneFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;

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

public class ContainerRedstoneFurnace extends Container {

	private TileEntityRedstoneFurnace redstoneFurnace;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerRedstoneFurnace(InventoryPlayer inventory,
			TileEntityRedstoneFurnace tileentity) {
		this.redstoneFurnace = tileentity;

		/*
		 * slots 0-4 are inputs slot 5 is fuel input slot6 is the output
		 */
		this.addSlotToContainer(new Slot(tileentity, 0, 12, 16));
		this.addSlotToContainer(new Slot(tileentity, 1, 32, 16));
		this.addSlotToContainer(new Slot(tileentity, 2, 52, 16));
		this.addSlotToContainer(new Slot(tileentity, 3, 72, 16));
		this.addSlotToContainer(new Slot(tileentity, 4, 92, 16));
		this.addSlotToContainer(new Slot(tileentity, 5, 52, 51));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				6, 134, 41));

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
		icrafting.sendProgressBarUpdate(this, 0, this.redstoneFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.redstoneFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.redstoneFurnace.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any
	 * changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.redstoneFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.redstoneFurnace.cookTime);
			}

			if (this.lastBurnTime != this.redstoneFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.redstoneFurnace.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.redstoneFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.redstoneFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.redstoneFurnace.cookTime;
		this.lastBurnTime = this.redstoneFurnace.burnTime;
		this.lastCurrentItemBurnTime = this.redstoneFurnace.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.redstoneFurnace.cookTime = par2;
		}

		if (par1 == 1) {
			this.redstoneFurnace.burnTime = par2;
		}

		if (par1 == 2) {
			this.redstoneFurnace.currentItemBurnTime = par2;
		}
	}

	/**
	 * Called when a player shift clicks a slot
	 * 
	 * - int slots is the slot ID created at the beginning of the class - 0 =
	 * input 1, 1 = input 2, 2 = input 3, 3 = input 4, 4 = input 5, 5 = fuel
	 * input, 6 = output - 7-43 are the inventory slots
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer,
			int slots) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slots);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// if the slot is an output
			if (slots == 6) {
				if (!this.mergeItemStack(itemstack1, 7, 43, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
				/*
				 * else if the slots are not inputs (NOTE* the syntax works but
				 * doesn't make sense, it is essentially looking at all other
				 * slots including the inventory slots)
				 */
			} else if ((slots != 0 && slots != 1 && slots != 2 && slots != 3
					&& slots != 4 && slots != 5)) {
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
					if (!this.mergeItemStack(itemstack1, 0, 5, false)) {
						return null;
					}
					// If the item is fuel then the code tries to put the item
					// into the fuel slot (slot 2)
				} else if (TileEntityObsidianFurnaceT3.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 5, 6, false)) {
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