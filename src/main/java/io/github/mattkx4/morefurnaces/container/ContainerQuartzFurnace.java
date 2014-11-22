package io.github.mattkx4.morefurnaces.container;

import io.github.mattkx4.morefurnaces.tileentity.TileEntityQuartzFurnace;
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

public class ContainerQuartzFurnace extends Container {

	private TileEntityQuartzFurnace quartzFurnace;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerQuartzFurnace(InventoryPlayer inventory,
			TileEntityQuartzFurnace tileentity) {
		this.quartzFurnace = tileentity;

		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				2, 116, 35));

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
		icrafting.sendProgressBarUpdate(this, 0, this.quartzFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.quartzFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.quartzFurnace.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any
	 * changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.quartzFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.quartzFurnace.cookTime);
			}

			if (this.lastBurnTime != this.quartzFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.quartzFurnace.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.quartzFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.quartzFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.quartzFurnace.cookTime;
		this.lastBurnTime = this.quartzFurnace.burnTime;
		this.lastCurrentItemBurnTime = this.quartzFurnace.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.quartzFurnace.cookTime = par2;
		}

		if (par1 == 1) {
			this.quartzFurnace.burnTime = par2;
		}

		if (par1 == 2) {
			this.quartzFurnace.currentItemBurnTime = par2;
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
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 != 1 && par2 != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityQuartzFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39
						&& !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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
