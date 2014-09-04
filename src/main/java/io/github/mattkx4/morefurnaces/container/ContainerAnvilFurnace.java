package io.github.mattkx4.morefurnaces.container;

import io.github.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
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

public class ContainerAnvilFurnace extends Container{

	private TileEntityAnvilFurnace anvilFurnace;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerAnvilFurnace(InventoryPlayer inventory, TileEntityAnvilFurnace tileentity) {
		this.anvilFurnace = tileentity;

		//setting this from SlotFurnace to simply Slot fixes the slots inability to accept items from a click
		this.addSlotToContainer(new Slot(tileentity, 0, 80, 21));
		this.addSlotToContainer(new Slot(tileentity, 1, 80, 61));

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	/*
	 * Sends progress bar updates
	 */
	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.anvilFurnace.repairTime);
		icrafting.sendProgressBarUpdate(this, 1, this.anvilFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.anvilFurnace.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if(this.lastCookTime != this.anvilFurnace.repairTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.anvilFurnace.repairTime);
			}

			if(this.lastBurnTime != this.anvilFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.anvilFurnace.burnTime);
			}

			if(this.lastCurrentItemBurnTime != this.anvilFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.anvilFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.anvilFurnace.repairTime;
		this.lastBurnTime = this.anvilFurnace.burnTime;
		this.lastCurrentItemBurnTime = this.anvilFurnace.currentItemBurnTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0) {
            this.anvilFurnace.repairTime = par2;
        }

        if (par1 == 1) {
            this.anvilFurnace.burnTime = par2;
        }

        if (par1 == 2) {
            this.anvilFurnace.currentItemBurnTime = par2;
        }
    }

	/*
	 * Called when a player shift clicks a slot
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0) {
                if (itemstack1.getItemDamage() < itemstack1.getMaxDamage()) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }else if (TileEntityAnvilFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }else if (par2 >= 2 && par2 < 29){
                    if (!this.mergeItemStack(itemstack1, 29, 38, false)){
                        return null;
                    }
                }else if (par2 >= 29 && par2 < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
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
