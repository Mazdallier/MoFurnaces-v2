package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerRedstoneFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityRedstoneFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiRedstoneFurnace extends GuiContainer{

	public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/redstone_furnace.png");
	
	public TileEntityRedstoneFurnace redstoneFurnace;
	
	public GuiRedstoneFurnace(InventoryPlayer inventoryPlayer, TileEntityRedstoneFurnace entity) {
		super(new ContainerRedstoneFurnace(inventoryPlayer, entity));

		this.redstoneFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j){
		// Gets the name of the furnace and stores it String "name"
		String name = this.redstoneFurnace.hasCustomInventoryName() ? this.redstoneFurnace.getInventoryName() : I18n.format(this.redstoneFurnace.getInventoryName(), new Object[0]);
		// Displays the name and GUI of the furnace on the GUI Foreground Layer
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 4, 4210752);
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
		if(this.redstoneFurnace.isBurning()){
			int m = this.redstoneFurnace.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 50, guiTop + 32 + l, 176, 17 + l, 14, 14 - l);
		}
		
		// Draws the progress bar for the current item being cooked (Arrow)
		int m = this.redstoneFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 94, guiTop + 38, 176, 0, m + 1, 17);
		
	}	
}