package io.github.mattkx4.morefurnaces.gui.tier2;

import io.github.mattkx4.morefurnaces.container.tier2.ContainerDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiDiamondFurnaceT2 extends GuiContainer{
public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/custom_furnace_tier2.png");
	
	public TileEntityDiamondFurnaceT2 diamondFurnaceT2;
	
	public GuiDiamondFurnaceT2(InventoryPlayer inventoryPlayer, TileEntityDiamondFurnaceT2 entity) {
		super(new ContainerDiamondFurnaceT2(inventoryPlayer, entity));

		this.diamondFurnaceT2 = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(int i, int j){
		// Gets the name of the furnace and stores it String "name"
		String name = this.diamondFurnaceT2.hasCustomInventoryName() ? this.diamondFurnaceT2.getInventoryName() : I18n.format(this.diamondFurnaceT2.getInventoryName(), new Object[0]);
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
		if(this.diamondFurnaceT2.isBurning()){
			int m = this.diamondFurnaceT2.getBurnTimeRemainingScaled(14);
			int l = 14 - m;
			drawTexturedModalRect(guiLeft + 38, guiTop + 36 + l, 176, 31 + l, 14, 14 - l);
		}
		
		// Draws the progress bar for the current item 1 being cooked (Arrow)
		int n = this.diamondFurnaceT2.getCookProgressScaled1(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 22, 176, 0, n + 1, 17);
		
		// Draws the progress bar for the current item 2 being cooked (Arrow)
		int o = this.diamondFurnaceT2.getCookProgressScaled2(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 48, 176, 0, o + 1, 17);
		
	}
}
