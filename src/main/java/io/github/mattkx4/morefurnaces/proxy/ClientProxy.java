package io.github.mattkx4.morefurnaces.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import io.github.mattkx4.morefurnaces.blocks.AnvilFurnace;
import io.github.mattkx4.morefurnaces.blocks.MFMBlock;
import io.github.mattkx4.morefurnaces.renderer.ItemRenderAnvilFurnace;
import io.github.mattkx4.morefurnaces.renderer.RenderAnvilFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy{
	
	public void registerRenderThings(){
		//rendering anvil furnace
		TileEntitySpecialRenderer renderAF = new RenderAnvilFurnace();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnvilFurnace.class, renderAF);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MFMBlock.AnvilFurnaceIdle), new ItemRenderAnvilFurnace(renderAF, new TileEntityAnvilFurnace()));
		
	
	}
	
	public void registerTileEntitySpecialRenderer(){
		
	}
	
}
