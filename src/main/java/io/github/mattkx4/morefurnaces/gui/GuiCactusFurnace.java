package io.github.mattkx4.morefurnaces.gui;

import io.github.mattkx4.morefurnaces.container.ContainerCactusFurnace;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityCactusFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCactusFurnace extends GuiContainer{
public static final ResourceLocation bground = new ResourceLocation(Strings.MODID + ":textures/gui/cactus_furnace.png");
	
	public TileEntityCactusFurnace cactusFurnace;
	
	public GuiCactusFurnace(InventoryPlayer inventoryPlayer, TileEntityCactusFurnace entity) {
		super(new ContainerCactusFurnace(inventoryPlayer, entity));

		this.cactusFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	/*
	 * Draw the Foreground Layer (everything that is in front of the items)
	 */
	 protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	    {
	        String s = this.cactusFurnace.hasCustomInventoryName() ? this.cactusFurnace.getInventoryName() : I18n.format(this.cactusFurnace.getInventoryName(), new Object[0]);
	        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
	        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	    }

	    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	    {
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(bground);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	        if (this.cactusFurnace.isBurning())
	        {
	            int i1 = this.cactusFurnace.getBurnTimeRemainingScaled(13);
	            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
	            i1 = this.cactusFurnace.getCookProgressScaled(24);
	            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	        }
	    }
}
