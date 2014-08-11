package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerGoldFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiGoldFurnace extends GuiContainer{
public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/gold_furnace.png");
	
	public TileEntityGoldFurnace goldFurnace;
	
	public GuiGoldFurnace(InventoryPlayer inventoryPlayer, TileEntityGoldFurnace entity) {
		super(new ContainerGoldFurnace(inventoryPlayer, entity));

		this.goldFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	//get the name and display on the Gui
	public void drawGuiContainerForegroundLayer(int i, int j){
		//get the name of the furnace
		String name = this.goldFurnace.hasCustomInventoryName() ? this.goldFurnace.getInventoryName() : I18n.format(this.goldFurnace.getInventoryName(), new Object[0]);
		//display the name of the furnace and the inventory on the Gui
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.Inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float i, int j, int k) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	
		//draw progress bar if the furnace is burning
		if(this.goldFurnace.isBurning()){
			int m = this.goldFurnace.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 57, guiTop + 36 + l, 176, 0 + l, 14, 14 - l);
		}
		
		//draws the progress bar dor the item being cooked
		int m = this.goldFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, m + 1, 17);
		
	}
}
