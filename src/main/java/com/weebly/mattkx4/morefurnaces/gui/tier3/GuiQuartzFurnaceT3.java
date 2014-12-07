package com.weebly.mattkx4.morefurnaces.gui.tier3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.weebly.mattkx4.morefurnaces.container.tier3.ContainerQuartzFurnaceT3;
import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityQuartzFurnaceT3;

public class GuiQuartzFurnaceT3 extends GuiContainer {
	public static final ResourceLocation bground = new ResourceLocation(
			Strings.MODID + ":textures/gui/custom_furnace_tier3.png");

	public TileEntityQuartzFurnaceT3 quartzFurnaceT3;

	public GuiQuartzFurnaceT3(InventoryPlayer inventoryPlayer,
			TileEntityQuartzFurnaceT3 entity) {
		super(new ContainerQuartzFurnaceT3(inventoryPlayer, entity));

		this.quartzFurnaceT3 = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j) {
		// Gets the name of the furnace and stores it String "name"
		String name = this.quartzFurnaceT3.hasCustomInventoryName() ? this.quartzFurnaceT3
				.getInventoryName() : I18n.format(
				this.quartzFurnaceT3.getInventoryName(), new Object[0]);
		// Displays the name and GUI of the furnace on the GUI Foreground Layer
		this.fontRendererObj.drawString(name, this.xSize / 2
				- this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.Inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
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
		if (this.quartzFurnaceT3.isBurning()) {
			int m = this.quartzFurnaceT3.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 38, guiTop + 36 + l, 176, 73 + l,
					14, 14 - l);
		}

		// Draws the progress bar for the current item 1 being cooked (Arrow)
		int n = this.quartzFurnaceT3.getCookProgressScaled1(24);
		drawTexturedModalRect(guiLeft + 84, guiTop + 17, 176, 0, n + 1, 17);

		// Draws the progress bar for the current item 2 being cooked (Arrow)
		int o = this.quartzFurnaceT3.getCookProgressScaled2(24);
		drawTexturedModalRect(guiLeft + 84, guiTop + 37, 176, 0, o + 1, 17);

		// Draws the progress bar for the current item 2 being cooked (Arrow)
		int x = this.quartzFurnaceT3.getCookProgressScaled3(24);
		drawTexturedModalRect(guiLeft + 84, guiTop + 57, 176, 0, x + 1, 17);

	}
}
