package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerFuelLessFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityFuelLessFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiFuelLessFurnace extends GuiContainer {
	public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/fuelless_furnace.png");
	
	public TileEntityFuelLessFurnace fuelLessFurnace;
	
	public GuiFuelLessFurnace(InventoryPlayer inventoryPlayer, TileEntityFuelLessFurnace entity) {
		super(new ContainerFuelLessFurnace(inventoryPlayer, entity));
		
		this.fuelLessFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.fuelLessFurnace.hasCustomInventoryName() ? this.fuelLessFurnace.getInventoryName() : I18n.format(this.fuelLessFurnace.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.Inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float i, int j, int k) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int m = this.fuelLessFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, m + 1, 17);
		
	}
}
