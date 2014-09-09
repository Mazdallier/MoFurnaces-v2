package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerObsidianFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiObsidianFurnace extends GuiContainer{

	public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/custom_furnace.png");
	
	public TileEntityObsidianFurnace obsidianFurnace;
	
	public GuiObsidianFurnace(InventoryPlayer inventoryPlayer, TileEntityObsidianFurnace entity) {
		super(new ContainerObsidianFurnace(inventoryPlayer, entity));

		this.obsidianFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j){
		// Gets the name of the furnace and stores it String "name"
		String name = this.obsidianFurnace.hasCustomInventoryName() ? this.obsidianFurnace.getInventoryName() : I18n.format(this.obsidianFurnace.getInventoryName(), new Object[0]);
		// Displays the name and GUI of the furnace on the GUI Foreground Layer
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.Inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	
	/*
	 * Draws the Background Layer of the GUI
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float i, int j, int k) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	
		// Checks if the furnace is burning. If yes, then displays the Burn Time Remaining (Fire)
		if(this.obsidianFurnace.isBurning()){
			int m = this.obsidianFurnace.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 57, guiTop + 36 + l, 176, 87 + l, 14, 14 - l);
		}
		
		// Draws the progress bar for the current item being cooked (Arrow)
		int m = this.obsidianFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, m + 1, 17);
		
	}
	
	
	
}
