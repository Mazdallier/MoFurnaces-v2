package io.github.mattkx4.morefurnaces.container;

import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnaceT2;
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

public class ContainerObsidianFurnaceT2 extends Container{
	
	private TileEntityObsidianFurnaceT2 obsidianFurnaceT2;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime1;
	public int lastCookTime2;

	/*
	 * Class constructor that adds all the slots to the Furnace GUI
	 */
	public ContainerObsidianFurnaceT2(InventoryPlayer inventory, TileEntityObsidianFurnaceT2 tileentity) {
		this.obsidianFurnaceT2 = tileentity;

		this.addSlotToContainer(new Slot(tileentity, 0, 20, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 38, 53));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 22));
		this.addSlotToContainer(new Slot(tileentity, 3, 56, 17));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 4, 116, 48));

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
		icrafting.sendProgressBarUpdate(this, 0, this.obsidianFurnaceT2.cookTime1);
		icrafting.sendProgressBarUpdate(this, 3, this.obsidianFurnaceT2.cookTime2);
		icrafting.sendProgressBarUpdate(this, 1, this.obsidianFurnaceT2.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.obsidianFurnaceT2.currentItemBurnTime);
	}

	/*
	 * Updates the Fuel and Progess Bar (Arrow and Fire) if there is any changes.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if(this.lastCookTime1 != this.obsidianFurnaceT2.cookTime1) {
				icrafting.sendProgressBarUpdate(this, 0, this.obsidianFurnaceT2.cookTime1);
			}
			
			if(this.lastCookTime2 != this.obsidianFurnaceT2.cookTime2) {
				icrafting.sendProgressBarUpdate(this, 3, this.obsidianFurnaceT2.cookTime2);
			}

			if(this.lastBurnTime != this.obsidianFurnaceT2.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.obsidianFurnaceT2.burnTime);
			}

			if(this.lastCurrentItemBurnTime != this.obsidianFurnaceT2.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.obsidianFurnaceT2.currentItemBurnTime);
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
    public void updateProgressBar(int par1, int par2)
    {
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
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                
            }else if (par2 == 4) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            
            }else if (par2 != 1 && par2 != 0 && par2 != 3) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }else if (TileEntityObsidianFurnaceT2.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }else if (par2 >= 5 && par2 < 30){
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)){
                        return null;
                    }
                }else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 5, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 5, 39, false)) {
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
