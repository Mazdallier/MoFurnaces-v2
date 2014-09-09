package io.github.mattkx4.morefurnaces.gui.tier2;

import io.github.mattkx4.morefurnaces.container.tier2.ContainerObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiObsidianFurnaceT2 extends GuiContainer{
	public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/custom_furnace_tier2.png");
	
	public TileEntityObsidianFurnaceT2 obsidianFurnaceT2;
	
	public GuiObsidianFurnaceT2(InventoryPlayer inventoryPlayer, TileEntityObsidianFurnaceT2 entity) {
		super(new ContainerObsidianFurnaceT2(inventoryPlayer, entity));

		this.obsidianFurnaceT2 = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j){
		// Gets the name of the furnace and stores it String "name"
		String name = this.obsidianFurnaceT2.hasCustomInventoryName() ? this.obsidianFurnaceT2.getInventoryName() : I18n.format(this.obsidianFurnaceT2.getInventoryName(), new Object[0]);
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
		if(this.obsidianFurnaceT2.isBurning()){
			int m = this.obsidianFurnaceT2.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 38, guiTop + 36 + l, 176, 59 + l, 14, 14 - l);
		}
		
		// Draws the progress bar for the current item 1 being cooked (Arrow)
		int n = this.obsidianFurnaceT2.getCookProgressScaled1(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 22, 176, 0, n + 1, 17);
		
		// Draws the progress bar for the current item 2 being cooked (Arrow)
		int o = this.obsidianFurnaceT2.getCookProgressScaled2(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 48, 176, 0, o + 1, 17);
		
	}
}
