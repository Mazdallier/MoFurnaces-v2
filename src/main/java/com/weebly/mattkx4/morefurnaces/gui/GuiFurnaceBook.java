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
    protected List buttonListMenu = new ArrayList();
    
    // A list of the Item sub menu buttons
    protected List buttonListItems = new ArrayList();

    /** The button that was just pressed. */
    private GuiButton selectedButton;
    
    // Variables for the x, y and f values
    private int xStored = 0;
    private int yStored = 0;
    private float fStored = 0;
    private int index = 0;
        
    // Button for closing the book
    private GuiButton buttonClose = new GuiButton(1, 200, 200, 100, 20, "Close");
    // Main menu buttons
    private GuiButton buttonItems = new GuiButton(2, 100, 10, 50, 20, "Items");
    private GuiButton buttonFurnaceBase = new GuiButton(3, 150, 10, 50, 20, "Base Furnaces");
    private GuiButton buttonFurnaceTier = new GuiButton(4, 200, 10, 50, 20, "Tiered Furnaces");
    private GuiButton buttonFurnaceSpecial = new GuiButton(5, 250, 10, 50, 20, "Special Furnaces");
    private GuiButton buttonMobs = new GuiButton(6, 300, 10, 50, 20, "Mobs");
    
    // Menu buttons for the items
    private GuiButton buttonItemsTier2 = new GuiButton(21, 100, 10, 20, 20, "Tier 2 Device");
    private GuiButton buttonItemsTier3 = new GuiButton(22, 100, 30, 20, 20, "Tier 3 Device");
    private GuiButton buttonItemsGuide = new GuiButton(23, 100, 50, 20, 20, "Furnace Guide");
    private GuiButton buttonItemsSteelIng = new GuiButton(24, 100, 70, 20, 20, "Steel Ingot");
    private GuiButton buttonItemsTierCore = new GuiButton(25, 100, 90, 20, 20, "Tier Core");
    private GuiButton buttonItemsBoneFrag = new GuiButton(26, 100, 110, 20, 20, "Bone Fragment");
    private GuiButton buttonItemsUpBri = new GuiButton(27, 100, 130, 10, 20, "Upgrade: Brightness");
    private GuiButton buttonItemsUpDou = new GuiButton(28, 100, 150, 20, 20, "Upgrade: Double Output");
    private GuiButton buttonItemsUpFue = new GuiButton(29, 100, 170, 20, 20, "Upgrade: Fuel Saver");
    private GuiButton buttonItemsUpNot = new GuiButton(30, 100, 190, 20, 20, "Upgrade: Notification");
    private GuiButton buttonItemsUpInp = new GuiButton(31, 100, 210, 20, 20, "Upgrade: Input Timer");



	
	
	public GuiFurnaceBook(EntityPlayer entityPlayer) {
		this.xSize = 200;
		this.ySize = 200;
		// Add the main menu buttons
		buttonListMenu.add(buttonClose);
		buttonListMenu.add(buttonItems);
		buttonListMenu.add(buttonFurnaceBase);
		buttonListMenu.add(buttonFurnaceTier);
		buttonListMenu.add(buttonFurnaceSpecial);
		buttonListMenu.add(buttonMobs);
		
		// Add the Item sub-menu buttons
		buttonListItems.add(buttonItemsTier2);
		buttonListItems.add(buttonItemsTier3);
		buttonListItems.add(buttonItemsGuide);
		buttonListItems.add(buttonItemsSteelIng);
		buttonListItems.add(buttonItemsTierCore);
		buttonListItems.add(buttonItemsBoneFrag);
		buttonListItems.add(buttonItemsUpBri);
		buttonListItems.add(buttonItemsUpDou);
		buttonListItems.add(buttonItemsUpFue);
		buttonListItems.add(buttonItemsUpNot);
		buttonListItems.add(buttonItemsUpInp);
		
	}
	
	/**
	 * This method overrides the default draw screen method from the GuiScreen class
	 * and calls the custom draw screen method below.
	 */
	@Override
	public void drawScreen(int x, int y, float f) {
		// Store the x, y and f values
		xStored = x;
		yStored = y;
		fStored = f;
		drawScreen(x, y, f, index);
		return;
	}
	
	/**
     * Draws the screen and all the components in it.
	 * @param index 
     */
	public void drawScreen(int x, int y, float f, int index)
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
		for (k = 1; k < buttonListMenu.size(); k++) {
			((GuiButton) buttonListMenu.get(k)).drawButton(this.mc, 100 + (50 * (k - 1)), 10);
		}
		
		switch (index) {
			case 1:
			{
				int j;
				for (j = 0; j < buttonListItems.size(); j++) {
					((GuiButton) buttonListItems.get(j)).drawButton(this.mc, 100, 10 + (20 * j));
				}
				break;
			}
			default:
				break;
		}
		index = 0;
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
		GuiButton lastButton = null;
        if (p_73864_3_ == 0) {
            for (int l = 0; l < this.buttonListMenu.size(); ++l) {
                GuiButton guibutton = (GuiButton)this.buttonListMenu.get(l);

                if (guibutton.mousePressed(this.mc, p_73864_1_, p_73864_2_)) {
                    ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, this.buttonListMenu);
                    if (MinecraftForge.EVENT_BUS.post(event))
                        break;
                    this.selectedButton = event.button;
                    event.button.func_146113_a(this.mc.getSoundHandler());
                    this.actionPerformed(event.button);
                    if (this.equals(this.mc.currentScreen))
                        MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonListMenu));
                    
                    if (lastButton != null && lastButton == selectedButton){
                    	System.out.println("buttons equal the same, returning with no action");
                    	return;
                    }
                    if (selectedButton == buttonClose) {
                    	this.mc.displayGuiScreen((GuiScreen)null);
                    	this.mc.setIngameFocus();
                    }
                    
                    if (selectedButton == buttonItems) {
                    	System.out.println("selected button is equal to last button");
                    		lastButton = selectedButton;
                        	index = 1;
                        	this.drawScreen(xStored, yStored, fStored);

                    } else if (selectedButton != buttonItems && selectedButton != buttonClose) {
                    	System.out.println("selected button is not equal to the last button");
                    	index = 0;
                    	this.drawScreen(xStored, yStored, fStored);
                    }
                }
            }
        }
    }
}
