package io.github.mattkx4.morefurnaces.container;

import io.github.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
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

public class ContainerNetherrackFurnace extends Container{

	private TileEntityNetherrackFurnace netherrackFurnace;

	public int lastCookTime;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerNetherrackFurnace(InventoryPlayer inventory, TileEntityNetherrackFurnace tileentity) {
		this.netherrackFurnace = tileentity;

		//this is the input slot
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));
		//this is the output slot
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 1, 116, 35));

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
		icrafting.sendProgressBarUpdate(this, 0, this.netherrackFurnace.cookTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if(this.lastCookTime != this.netherrackFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.netherrackFurnace.cookTime);
			}
		}

		this.lastCookTime = this.netherrackFurnace.cookTime;
	}

	/*
	 * Updates Progress Bar
	 */
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0) {
            this.netherrackFurnace.cookTime = par2;
        }
    }

	/*
	 * Called when a player shift clicks a slot | NEEDS TO BE CORRECTED
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //this here deals shift clicking the output slot which has been ID'd as slot 1
            //thus this needs to be par2 == 1
            //mergeItemStack needs to go from slot 2(the first inventory slot) to slot 38(the last inventory slot)
            if (par2 == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            //this should only be for inputs, as there is only one input (0) this needs to say
            // par2 != 0
            else if (par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                
                //removal of the condition for if the itemstack is a fuel (as no fuel is needed)
                /*else if (TileEntityNetherrackFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                */
                //otherwise if the shift clicked slot is any of the inventory slots (2 to 29 not 3 to 30)
                //changed all values down by one to properly reflect the number of slots
                else if (par2 >= 2 && par2 < 29){
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
