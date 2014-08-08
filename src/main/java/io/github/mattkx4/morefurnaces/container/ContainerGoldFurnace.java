package io.github.mattkx4.morefurnaces.container;

import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;

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

public class ContainerGoldFurnace extends Container{
	private TileEntityGoldFurnace goldFurnace;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	public ContainerGoldFurnace(InventoryPlayer inventory, TileEntityGoldFurnace tileentity) {
		this.goldFurnace = tileentity;

		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 35));

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.goldFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.goldFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.goldFurnace.currentItemBurnTime);
	}

	//This updates the fuel burn and item cook progress bars
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if(this.lastCookTime != this.goldFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.goldFurnace.cookTime);
			}

			if(this.lastBurnTime != this.goldFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.goldFurnace.burnTime);
			}

			if(this.lastCurrentItemBurnTime != this.goldFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.goldFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.goldFurnace.cookTime;
		this.lastBurnTime = this.goldFurnace.burnTime;
		this.lastCurrentItemBurnTime = this.goldFurnace.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0) {
            this.goldFurnace.cookTime = par2;
        }

        if (par1 == 1) {
            this.goldFurnace.burnTime = par2;
        }

        if (par1 == 2) {
            this.goldFurnace.currentItemBurnTime = par2;
        }
    }

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }else if (TileEntityGoldFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }else if (par2 >= 3 && par2 < 30){
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)){
                        return null;
                    }
                }else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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

	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
