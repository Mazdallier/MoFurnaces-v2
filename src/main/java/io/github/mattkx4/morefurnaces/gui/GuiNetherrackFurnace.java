package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerNetherrackFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiNetherrackFurnace extends GuiContainer{
public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/netherrack_furnace.png");
	
	public TileEntityNetherrackFurnace netherrackFurnace;
	
	public GuiNetherrackFurnace(InventoryPlayer inventoryPlayer, TileEntityNetherrackFurnace entity) {
		super(new ContainerNetherrackFurnace(inventoryPlayer, entity));

		this.netherrackFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j){
		// Gets the name of the furnace and stores it String "name"
		String name = this.netherrackFurnace.hasCustomInventoryName() ? this.netherrackFurnace.getInventoryName() : I18n.format(this.netherrackFurnace.getInventoryName(), new Object[0]);
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
		if(this.netherrackFurnace.isBurning()){
			int m = this.netherrackFurnace.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 57, guiTop + 36 + l, 176, 0 + l, 14, 14 - l);
		}
		
		// Draws the progress bar for the current item being cooked (Arrow)
		int m = this.netherrackFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, m + 1, 17);
		
		/*
		 * The following code SHOULD render a a gray box over the fuel slot. This code is still being
		 * tested and for the moment is commented out. Should be available in future updates.
		 */
		//Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		//drawTexturedModalRect(guiLeft + 55, guiTop + 52, 176, 31, 18, 18);
		
	}
}
