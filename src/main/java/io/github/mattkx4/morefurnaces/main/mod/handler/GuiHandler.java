package com.MoreFurnacesMod.mod.handler;


import com.MoreFurnacesMod.mod.MoreFurnacesMod;
import com.MoreFurnacesMod.mod.container.ContainerDiamondFurnace;
import com.MoreFurnacesMod.mod.container.ContainerObsidianFurnace;
import com.MoreFurnacesMod.mod.gui.GuiObsidianFurnace;
import com.MoreFurnacesMod.mod.gui.GuiDiamondFurnace;
import com.MoreFurnacesMod.mod.tileentity.TileEntityObsidianFurnace;
import com.MoreFurnacesMod.mod.tileentity.TileEntityDiamondFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			switch(ID){
			case MoreFurnacesMod.guiIDObsidianFurnace:
				if(entity instanceof TileEntityObsidianFurnace) {
					return new ContainerObsidianFurnace(player.inventory, (TileEntityObsidianFurnace) entity);
				}
			case MoreFurnacesMod.guiIDDiamondFurnace:
				if(entity instanceof TileEntityDiamondFurnace) {
					return new ContainerDiamondFurnace(player.inventory, (TileEntityDiamondFurnace) entity);
				}
				return null;
				}
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			switch(ID){
			case MoreFurnacesMod.guiIDObsidianFurnace:
				if(entity instanceof TileEntityObsidianFurnace) {
					return new GuiObsidianFurnace(player.inventory, (TileEntityObsidianFurnace) entity);
				}
			case MoreFurnacesMod.guiIDDiamondFurnace:
				if(entity instanceof TileEntityDiamondFurnace) {
					return new GuiDiamondFurnace(player.inventory, (TileEntityDiamondFurnace) entity);
				}
				return null;
				}
			
			}
		return null;
	}

}
