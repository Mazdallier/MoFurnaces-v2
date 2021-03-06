package com.weebly.mattkx4.morefurnaces.container;

import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;

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

public class ContainerBrickFurnace extends Container {

	private TileEntityBrickFurnace brickFurnace;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerBrickFurnace(InventoryPlayer inventory,
			TileEntityBrickFurnace tileentity) {
		this.brickFurnace = tileentity;

		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				2, 116, 35));
		// add in a new slot to the container (last slot available)
		this.addSlotToContainer(new Slot(tileentity, 3, 20, 17));

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
		icrafting.sendProgressBarUpdate(this, 0, this.brickFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.brickFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.brickFurnace.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any
	 * changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.brickFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.brickFurnace.cookTime);
			}

			if (this.lastBurnTime != this.brickFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.brickFurnace.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.brickFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.brickFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.brickFurnace.cookTime;
		this.lastBurnTime = this.brickFurnace.burnTime;
		this.lastCurrentItemBurnTime = this.brickFurnace.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.brickFurnace.cookTime = par2;
		}

		if (par1 == 1) {
			this.brickFurnace.burnTime = par2;
		}

		if (par1 == 2) {
			this.brickFurnace.currentItemBurnTime = par2;
		}
	}

	/*
	 * Called when a player shift clicks a slot
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 != 1 && par2 != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityBrickFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 4 && par2 < 31) {
					if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
						return null;
					}
				} else if (par2 >= 331 && par2 < 40
						&& !this.mergeItemStack(itemstack1, 4, 31, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
				return null;
			}
			// added code for the upgrade, if the slot is the upgrade slot then
			// do nothing (ie, remove the ability to shiftclick out of that
			// slot)
			else if (par2 == 3) {
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
