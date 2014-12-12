package com.weebly.mattkx4.morefurnaces.gui;

import org.lwjgl.opengl.GL11;

import com.weebly.mattkx4.morefurnaces.container.ContainerPumpkinFurnace;
import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityPumpkinFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFurnaceBook extends GuiScreen {
	
	// TODO this needs to change to the proper gui
	public static final ResourceLocation bground = new ResourceLocation(
			Strings.MODID + ":textures/gui/furnace_book.png");
	private int xSize;
	private int ySize;
	
	
	public GuiFurnaceBook(EntityPlayer entityPlayer) {
		this.xSize = 200;
		this.ySize = 200;
	}
	
	/**
     * Draws the screen and all the components in it.
     */
	@Override
    public void drawScreen(int x, int y, float f)
    {
		// Draw the default background
		drawDefaultBackground();
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		// Bind the gui
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		
		
		// Draw the main GUI background
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		
		// Draw the internals for the Gui
		drawTexturedModalRect(posX+11,posY+41,201,0,32,32);
		drawTexturedModalRect(posX+110,posY+41,201,0,32,32);
		
		String title = "The Crafter's Guide to Mo Furnaces";
		
		this.fontRendererObj.drawString(title, this.xSize / 2
				- this.fontRendererObj.getStringWidth(title) / 2, 6, 4210752);
		
		

		
		super.drawScreen(x,  y,  f);
    }
    
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	   
}
