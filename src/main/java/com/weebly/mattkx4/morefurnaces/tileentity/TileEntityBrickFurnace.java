package com.weebly.mattkx4.morefurnaces.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.weebly.mattkx4.morefurnaces.blocks.BrickFurnace;
import com.weebly.mattkx4.morefurnaces.gui.GuiBrickFurnace;
import com.weebly.mattkx4.morefurnaces.items.MFMItems;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeBrightness;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeNotification;
import com.weebly.mattkx4.morefurnaces.lib.FurnaceVariables;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBrickFurnace extends TileEntity implements
		ISidedInventory {

	private String localizedName;

	private static final int[] slots_top = new int[] { 0 };
	private static final int[] slots_bottom = new int[] { 2, 1 };
	private static final int[] slots_side = new int[] { 1 };

	// changed the number of slots to 4 (3 for cooking plus 1 for upgrade)
	private ItemStack[] slots = new ItemStack[4];

	private boolean doubleOutput = false;

	private boolean notification = false;

	private boolean fuelSaver = false;

	private boolean inputTimer = false;

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
				: "container.brickFurnace";
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
		return this.cookTime * i / FurnaceVariables.BRICK_FURNACE_SPEED;
	}

	// Get the remaining burn time (Scaled)
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {

		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = FurnaceVariables.BRICK_FURNACE_SPEED;
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
			if (this.burnTime == 0 && notification && this.slots[0] != null) {
				if (this.slots[1] == null && this.slots[0] != null) {
					// Attempt to move to a simpler format
					UpgradeNotification.notify(this.xCoord, this.yCoord,
							this.zCoord, 1, this.getInventoryName());
				}
			}
		}
		if (!this.worldObj.isRemote) {
			// Assess which, if any, upgrade is being used
			try {
				if (slots[3].getItem() == null) {
					UpgradeBrightness.Inactive(worldObj, this.xCoord,
							this.yCoord, this.zCoord, this.isBurning());
					// Set the double output boolean to false
					doubleOutput = false;
					// Set the notification boolean to false
					notification = false;
					// Set the fuel saver boolean to false
					fuelSaver = false;
					// Set the input timer boolean to false
					inputTimer = false;
				}

				if (slots[3].getItem() == MFMItems.UpgradeBrightness) {
					// Activate the effect of the brightness upgrade
					UpgradeBrightness.Active(worldObj, this.xCoord,
							this.yCoord, this.zCoord, this.isBurning());
				} else if (slots[3].getItem() != MFMItems.UpgradeBrightness
						|| slots[3].getItem() == null) {
					// Cancel out the effect of the Brightness Upgrade
					UpgradeBrightness.Inactive(worldObj, this.xCoord,
							this.yCoord, this.zCoord, this.isBurning());
				}

				if (slots[3].getItem() == MFMItems.UpgradeDoubleOutput) {
					// Set the double output boolean to true
					doubleOutput = true;
				} else if (slots[3].getItem() != MFMItems.UpgradeDoubleOutput
						|| slots[3].getItem() == null) {
					// Set the double output boolean to false
					doubleOutput = false;
				}

				if (slots[3].getItem() == MFMItems.UpgradeNotification) {
					notification = true;
				} else if (slots[3].getItem() != MFMItems.UpgradeNotification
						|| slots[3].getItem() == null) {
					// Set the notification boolean to false
					notification = false;
				}

				if (slots[3].getItem() == MFMItems.UpgradeFuelSaver) {
					fuelSaver = true;
				} else if (slots[3].getItem() != MFMItems.UpgradeFuelSaver
						|| slots[3].getItem() == null) {
					// Set the fuel saver boolean to false
					fuelSaver = false;
				}

				if (slots[3].getItem() == MFMItems.UpgradeInputTimer) {
					inputTimer = true;
				} else if (slots[3].getItem() != MFMItems.UpgradeInputTimer
						|| slots[3].getItem() == null) {
					// Set the input timer boolean to false
					inputTimer = false;
				}

			} catch (Exception e) {

			}

			// if the burnTime has reached zero and there is an item that can be
			// smelted
			if (this.burnTime == 0 && this.canSmelt()) {
				// set currentItemBurnTime and burnTime to the fuel item burn
				// time || add a '+1' after fuel efficiency to create an ever
				// lasting furnace
				if (!fuelSaver) {
					this.currentItemBurnTime = this.burnTime = (int) (((double) getItemBurnTime(this.slots[1]) / FurnaceVariables.BRICK_FURNACE_EFFICIENCY) - 0.4D);
				} else {
					this.currentItemBurnTime = this.burnTime = (int) (((double) getItemBurnTime(this.slots[1]) / (FurnaceVariables.BRICK_FURNACE_EFFICIENCY - (FurnaceVariables.BRICK_FURNACE_EFFICIENCY * 0.33))) - 0.4D);
				}
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

				if (this.cookTime == FurnaceVariables.BRICK_FURNACE_SPEED) {
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.cookTime = 0;
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				BrickFurnace.updateBrickFurnaceState(this.burnTime > 0,
						this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1) {
			this.markDirty();
		}

		// Input Timer Stuff
		if (inputTimer) {
			if (!canSmelt()) {
				System.out.println("0:00");
			} else if (canSmelt()) {
				int numOfItems = this.slots[0].stackSize;
				int numOfSeconds = numOfItems
						* FurnaceVariables.BRICK_FURNACE_SPEED_SECONDS;
				if (numOfSeconds > 59) {
					numOfSeconds = numOfSeconds % (60 * 60);
					int numOfMinutes = numOfSeconds / 60;
					numOfSeconds = numOfSeconds % 60;

					if (numOfSeconds < 10) {
						System.out.println(numOfMinutes + ":0" + numOfSeconds);
					} else {
						System.out.println(numOfMinutes + ":" + numOfSeconds);
					}
				} else {
					if (numOfSeconds < 10) {
						System.out.println("0:0" + numOfSeconds);
					} else {
						System.out.println("0:" + numOfSeconds);
					}
				}
			}
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
			if (doubleOutput == true && this.slots[2].isItemEqual(itemstack)
					&& !(this.slots[2].stackSize < 63)) {
				return false;
			}

			int result = slots[2].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit()
					&& result <= itemstack.getMaxStackSize();
		}
	}

	// Smelt the input item and put the result in the output slot
	public void smeltItem() {
		// Get the current stack count in the input slot
		int oldInputItemCount = this.slots[0].stackSize;
		// Arbitrary integer for multiplying the output
		int outputMultiplier = 1;
		// If the double output boolean is true then set arbitrary integer to
		// double the output
		if (doubleOutput == true) {
			// Get the pseudo random chance that this will actually double the
			// output
			if (randInt(0, 100) < 34) {
				outputMultiplier = 2;
			} else if (randInt(0, 100) > 33) {
				outputMultiplier = 1;
			}
		} else if (doubleOutput == false) {
			outputMultiplier = 1;
		}
		// If there is an item to smelt
		if (this.canSmelt()) {
			// Get the result of the action of smelting said item
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
					this.slots[0]);
			itemstack.stackSize = 1 * outputMultiplier;
			// If the output slot is empty
			if (this.slots[2] == null) {
				// Copy over a single item of the smelting result multiplied by
				// the output multiplier
				this.slots[2] = itemstack.copy();
				// Otherwise if the two items are equal
			} else if (this.slots[2].getItem() == itemstack.getItem()) {
				// Check on the state of the doubleOutput
				if (doubleOutput == false) {
					this.slots[2].stackSize += itemstack.stackSize;
				} else if (doubleOutput == true) {
					// make sure that the stack size of the output stack is less
					// than 63 to accommodate the double output
					if (this.slots[2].stackSize < 63) {
						this.slots[2].stackSize += itemstack.stackSize;
					}
				}
			}

			--this.slots[0].stackSize;
			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
		if (notification) {
			// Check if the input stacks are completely done smelting.
			if (oldInputItemCount != 0 && this.slots[0] == null) {
				UpgradeNotification.notify(this.xCoord, this.yCoord,
						this.zCoord, 2, this.getInventoryName());
			}
			// Check if the output slot is full.
			if (this.slots[2].stackSize > 63 && this.slots[0].stackSize > 0) {
				UpgradeNotification.notify(this.xCoord, this.yCoord,
						this.zCoord, 3, this.getInventoryName());
			}
		}
	}

	// Get fuel burn times
	public static int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();

			if (item instanceof ItemBlock
					&& Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);

				// insert block based fuels
				if (block == Blocks.wooden_slab)
					return 150;
				if (block.getMaterial() == Material.wood)
					return 300;
				if (block == Blocks.coal_block)
					return 14400;
			}

			// insert item based fuels
			if (item instanceof ItemTool
					&& ((ItemTool) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemSword
					&& ((ItemSword) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemHoe
					&& ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item == Items.stick)
				return 100;
			if (item == Items.coal)
				return 1600;
			if (item == Items.lava_bucket)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.sapling))
				return 100;
			if (item == Items.blaze_rod)
				return 2400;
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

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
