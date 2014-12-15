package com.weebly.mattkx4.morefurnaces.gui;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiFurnaceBook extends GuiScreen {
	
	// TODO this needs to change to the proper gui
	public static final ResourceLocation bground = new ResourceLocation(
			Strings.MODID + ":textures/gui/furnace_book.png");
	private int xSize;
	private int ySize;
	/** A list of all the buttons in this container. */
    protected List buttonList = new ArrayList();

    /** The button that was just pressed. */
    private GuiButton selectedButton;
    
    // Button for closing the book
    private GuiButton buttonClose = new GuiButton(1, 200, 200, 100, 20, "Close");
    // Main menu buttons
    private GuiButton buttonItems = new GuiButton(2, 100, 10, 20, 20, "Items");
    private GuiButton buttonFurnaceBase = new GuiButton(3, 100, 30, 20, 20, "Base Furnaces");
    private GuiButton buttonFurnaceTier = new GuiButton(4, 100, 50, 20, 20, "Tiered Furnaces");
    private GuiButton buttonFurnaceSpecial = new GuiButton(5, 100, 70, 20, 20, "Special Furnaces");
    private GuiButton buttonMobs = new GuiButton(6, 100, 90, 20, 20, "Mobs");

	
	
	public GuiFurnaceBook(EntityPlayer entityPlayer) {
		this.xSize = 200;
		this.ySize = 200;
		buttonList.add(buttonClose);
		buttonList.add(buttonItems);
		buttonList.add(buttonFurnaceBase);
		buttonList.add(buttonFurnaceTier);
		buttonList.add(buttonFurnaceSpecial);
		buttonList.add(buttonMobs);

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
		
		this.fontRendererObj.drawString(title, posX + this.xSize / 2
				- this.fontRendererObj.getStringWidth(title) / 2, posY + 2, 4210752);
		
		buttonClose.drawButton(this.mc, 200,  200);
		int k;
		for (k = 1; k < buttonList.size(); k++) {
			((GuiButton) buttonList.get(k)).drawButton(this.mc, 100, 10 + (20 * (k -1)));
		}
		
		super.drawScreen(x,  y,  f);
    }
    
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	/**
     * Called when the mouse is clicked.
     */
	@Override
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        if (p_73864_3_ == 0) {
            for (int l = 0; l < this.buttonList.size(); ++l) {
                GuiButton guibutton = (GuiButton)this.buttonList.get(l);

                if (guibutton.mousePressed(this.mc, p_73864_1_, p_73864_2_)) {
                    ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
                    if (MinecraftForge.EVENT_BUS.post(event))
                        break;
                    this.selectedButton = event.button;
                    event.button.func_146113_a(this.mc.getSoundHandler());
                    this.actionPerformed(event.button);
                    if (this.equals(this.mc.currentScreen))
                        MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonList));
                    
                    if (selectedButton == buttonClose) {
                    	this.mc.displayGuiScreen((GuiScreen)null);
                    	this.mc.setIngameFocus();
                    }
                }
            }
        }
    }
}
