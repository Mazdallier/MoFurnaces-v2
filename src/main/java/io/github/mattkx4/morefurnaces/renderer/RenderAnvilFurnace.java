package io.github.mattkx4.morefurnaces.renderer;

import org.lwjgl.opengl.GL11;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.model.ModelAnvilFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderAnvilFurnace extends TileEntitySpecialRenderer {

	private TileEntityAnvilFurnace anvilFurnace;

	public static final ResourceLocation texture = new ResourceLocation(
			Strings.MODID + ":textures/model/anvil_furnace.png");

	private ModelAnvilFurnace model;

	public RenderAnvilFurnace() {
		this.model = new ModelAnvilFurnace();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float f) {

		int i;
		if (tileentity.getWorldObj() == null) {
			i = 0;
		} else {
			Block block = tileentity.getBlockType();
			i = tileentity.getBlockMetadata();
			if (block != null && i == 0) {
				i = tileentity.getBlockMetadata();
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		int j = 0;

		if (i == 5) {
			j = 360;
		}
		if (i == 4) {
			j = 180;
		}
		if (i == 3) {
			j = 270;
		}
		if (i == 2) {
			j = 90;
		}
		GL11.glRotatef(j, 0.0F, 1.0F, 0F);
		this.model
				.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
