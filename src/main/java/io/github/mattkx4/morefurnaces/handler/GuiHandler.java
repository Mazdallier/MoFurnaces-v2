package io.github.mattkx4.morefurnaces.handler;


import io.github.mattkx4.morefurnaces.container.ContainerDiamondFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerObsidianFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerIronFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiDiamondFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiObsidianFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiIronFurnace;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;

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
			case MoFurnacesMod.guiIDObsidianFurnace:
				if(entity instanceof TileEntityObsidianFurnace) {
					return new ContainerObsidianFurnace(player.inventory, (TileEntityObsidianFurnace) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnace:
				if(entity instanceof TileEntityDiamondFurnace) {
					return new ContainerDiamondFurnace(player.inventory, (TileEntityDiamondFurnace) entity);
				}
			case MoFurnacesMod.guiIDIronFurnace:
				if(entity instanceof TileEntityIronFurnace) {
					return new ContainerIronFurnace(player.inventory, (TileEntityIronFurnace) entity);
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
			case MoFurnacesMod.guiIDObsidianFurnace:
				if(entity instanceof TileEntityObsidianFurnace) {
					return new GuiObsidianFurnace(player.inventory, (TileEntityObsidianFurnace) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnace:
				if(entity instanceof TileEntityDiamondFurnace) {
					return new GuiDiamondFurnace(player.inventory, (TileEntityDiamondFurnace) entity);
				}
			case MoFurnacesMod.guiIDIronFurnace:
				if(entity instanceof TileEntityIronFurnace) {
					return new GuiIronFurnace(player.inventory, (TileEntityIronFurnace) entity);
				}
				return null;
				}
			
			}
		return null;
	}

}
