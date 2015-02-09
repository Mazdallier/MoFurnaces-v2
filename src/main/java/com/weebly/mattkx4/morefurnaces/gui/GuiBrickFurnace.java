package com.weebly.mattkx4.morefurnaces.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.weebly.mattkx4.morefurnaces.container.ContainerBrickFurnace;
import com.weebly.mattkx4.morefurnaces.items.MFMItems;
import com.weebly.mattkx4.morefurnaces.lib.FurnaceVariables;
import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;

public class GuiBrickFurnace extends GuiContainer {
	public static final ResourceLocation bground = new ResourceLocation(
			Strings.MODID + ":textures/gui/custom_furnace.png");

	public TileEntityBrickFurnace brickFurnace;

	private boolean inputTimer = false;

	public GuiBrickFurnace(InventoryPlayer inventoryPlayer,
			TileEntityBrickFurnace entity) {
		super(new ContainerBrickFurnace(inventoryPlayer, entity));

		this.brickFurnace = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j) {
		// Gets the name of the furnace and stores it String "name"
		String name = this.brickFurnace.hasCustomInventoryName() ? this.brickFurnace
				.getInventoryName() : I18n.format(
				this.brickFurnace.getInventoryName(), new Object[0]);
		// Displays the name and GUI of the furnace on the GUI Foreground Layer
		this.fontRendererObj.drawString(name, this.xSize / 2
				- this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.Inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);

		// Input Timer Code
		if (this.brickFurnace.getStackInSlot(3) != null
				&& this.brickFurnace.getStackInSlot(3).getItem() == MFMItems.UpgradeInputTimer) {
			if (!this.brickFurnace.canSmelt()) {
				this.fontRendererObj.drawString("0:00", 18, 39, 4210752);
			} else if (this.brickFurnace.canSmelt()) {
				int numOfItems = this.brickFurnace.getStackInSlot(0).stackSize;
				int numOfSeconds = numOfItems
						* FurnaceVariables.BRICK_FURNACE_SPEED_SECONDS;
				if (numOfSeconds > 59) {
					numOfSeconds = numOfSeconds % (60 * 60);
					int numOfMinutes = numOfSeconds / 60;
					numOfSeconds = numOfSeconds % 60;

					if (numOfSeconds < 10) {
						this.fontRendererObj.drawString(numOfMinutes + ":0"
								+ numOfSeconds, 18, 39, 4210752);
					} else {
						this.fontRendererObj.drawString(numOfMinutes + ":"
								+ numOfSeconds, 18, 39, 4210752);
					}
				} else {
					if (numOfSeconds < 10) {
						this.fontRendererObj.drawString("0:0" + numOfSeconds,
								18, 39, 4210752);
					} else {
						this.fontRendererObj.drawString("0:" + numOfSeconds,
								18, 39, 4210752);
					}
				}
			}
		}

		// Fuel Timer Code
		if (this.brickFurnace.getStackInSlot(3) != null
				&& this.brickFurnace.getStackInSlot(3).getItem() == MFMItems.UpgradeFuelTimer) {
			if (this.brickFurnace.getStackInSlot(2).getItem() == null) {
				this.fontRendererObj.drawString("0:00", 18, 39, 4210752);
			}
		}
	}

	/*
	 * Draws the Background Layer of the GUI
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float i, int j, int k) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		// Checks if the furnace is burning. If yes, then displays the Burn Time
		// Remaining (Fire)
		if (this.brickFurnace.isBurning()) {
			int m = this.brickFurnace.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 57, guiTop + 36 + l, 176, 17 + l,
					14, 14 - l);
		}

		// Draws the progress bar for the current item being cooked (Arrow)
		int m = this.brickFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, m + 1, 17);
	}
}
