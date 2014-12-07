package com.weebly.mattkx4.morefurnaces.proxy;

import com.weebly.mattkx4.morefurnaces.blocks.AnvilFurnace;
import com.weebly.mattkx4.morefurnaces.blocks.MFMBlocks;
import com.weebly.mattkx4.morefurnaces.entity.EntityCobaltKatana;
import com.weebly.mattkx4.morefurnaces.model.ModelCobaltKatana;
import com.weebly.mattkx4.morefurnaces.renderer.ItemRenderAnvilFurnace;
import com.weebly.mattkx4.morefurnaces.renderer.RenderAnvilFurnace;
import com.weebly.mattkx4.morefurnaces.renderer.RenderEntityCobaltKatana;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {

	public void registerRenderThings() {
		// rendering anvil furnace // follow this layout for blocks
		TileEntitySpecialRenderer renderAF = new RenderAnvilFurnace();
		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityAnvilFurnace.class, renderAF);
		MinecraftForgeClient.registerItemRenderer(Item
				.getItemFromBlock(MFMBlocks.AnvilFurnaceIdle),
				new ItemRenderAnvilFurnace(renderAF,
						new TileEntityAnvilFurnace()));

		// rendering CK
		RenderingRegistry.registerEntityRenderingHandler(
				EntityCobaltKatana.class, new RenderEntityCobaltKatana(
						new ModelCobaltKatana(), 0.3F));
	}

	public void registerTileEntitySpecialRenderer() {

	}

}
