package io.github.mattkx4.morefurnaces.handler;

import io.github.mattkx4.morefurnaces.container.ContainerBoneFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerBrickFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerDiamondFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerGoldFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerIronFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerNetherrackFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerObsidianFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerQuartzFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.GuiBoneFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiBrickFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiDiamondFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiGoldFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiIronFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiNetherrackFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiObsidianFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiQuartzFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBoneFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityQuartzFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnaceT2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class MFMGuiHandler implements IGuiHandler {

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
			case MoFurnacesMod.guiIDGoldFurnace:
				if(entity instanceof TileEntityGoldFurnace) {
					return new ContainerGoldFurnace(player.inventory, (TileEntityGoldFurnace) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnace:
				if(entity instanceof TileEntityBrickFurnace) {
					return new ContainerBrickFurnace(player.inventory, (TileEntityBrickFurnace) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnace:
				if(entity instanceof TileEntityQuartzFurnace) {
					return new ContainerQuartzFurnace(player.inventory, (TileEntityQuartzFurnace) entity);
				}
			case MoFurnacesMod.guiIDNetherrackFurnace:
				if(entity instanceof TileEntityNetherrackFurnace) {
					return new ContainerNetherrackFurnace(player.inventory, (TileEntityNetherrackFurnace) entity);
				}
			case MoFurnacesMod.guiIDBoneFurnace:
				if(entity instanceof TileEntityBoneFurnace) {
					return new ContainerBoneFurnace(player.inventory, (TileEntityBoneFurnace) entity);
				}
			case MoFurnacesMod.guiIDObsidianFurnaceT2:
				if(entity instanceof TileEntityObsidianFurnaceT2) {
					return new ContainerObsidianFurnaceT2(player.inventory, (TileEntityObsidianFurnaceT2) entity);
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
			case MoFurnacesMod.guiIDGoldFurnace:
				if(entity instanceof TileEntityGoldFurnace) {
					return new GuiGoldFurnace(player.inventory, (TileEntityGoldFurnace) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnace:
				if(entity instanceof TileEntityBrickFurnace) {
					return new GuiBrickFurnace(player.inventory, (TileEntityBrickFurnace) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnace:
				if(entity instanceof TileEntityQuartzFurnace) {
					return new GuiQuartzFurnace(player.inventory, (TileEntityQuartzFurnace) entity);
				}
			case MoFurnacesMod.guiIDNetherrackFurnace:
				if(entity instanceof TileEntityNetherrackFurnace) {
					return new GuiNetherrackFurnace(player.inventory, (TileEntityNetherrackFurnace) entity);
				}
			case MoFurnacesMod.guiIDBoneFurnace:
				if(entity instanceof TileEntityBoneFurnace) {
					return new GuiBoneFurnace(player.inventory, (TileEntityBoneFurnace) entity);
				}
			case MoFurnacesMod.guiIDObsidianFurnaceT2:
				if(entity instanceof TileEntityObsidianFurnaceT2) {
					return new GuiObsidianFurnaceT2(player.inventory, (TileEntityObsidianFurnaceT2) entity);
				}
				return null;
				}
			
			}
		return null;
	}

}
