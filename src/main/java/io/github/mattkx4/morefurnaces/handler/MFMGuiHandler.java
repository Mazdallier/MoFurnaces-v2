package io.github.mattkx4.morefurnaces.handler;

import io.github.mattkx4.morefurnaces.container.ContainerAnvilFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerBoneFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerBrickFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerDiamondFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerGoldFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerIronFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerNetherrackFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerObsidianFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerQuartzFurnace;
import io.github.mattkx4.morefurnaces.container.ContainerRedstoneFurnace;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerIronFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier2.ContainerQuartzFurnaceT2;
import io.github.mattkx4.morefurnaces.container.tier3.ContainerBrickFurnaceT3;
import io.github.mattkx4.morefurnaces.container.tier3.ContainerDiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.container.tier3.ContainerIronFurnaceT3;
import io.github.mattkx4.morefurnaces.container.tier3.ContainerObsidianFurnaceT3;
import io.github.mattkx4.morefurnaces.container.tier3.ContainerQuartzFurnaceT3;
import io.github.mattkx4.morefurnaces.gui.GuiAnvilFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiBoneFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiBrickFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiDiamondFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiGoldFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiIronFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiNetherrackFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiObsidianFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiQuartzFurnace;
import io.github.mattkx4.morefurnaces.gui.GuiRedstoneFurnace;
import io.github.mattkx4.morefurnaces.gui.tier2.GuiBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.tier2.GuiDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.tier2.GuiIronFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.tier2.GuiObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.tier2.GuiQuartzFurnaceT2;
import io.github.mattkx4.morefurnaces.gui.tier3.GuiBrickFurnaceT3;
import io.github.mattkx4.morefurnaces.gui.tier3.GuiDiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.gui.tier3.GuiIronFurnaceT3;
import io.github.mattkx4.morefurnaces.gui.tier3.GuiObsidianFurnaceT3;
import io.github.mattkx4.morefurnaces.gui.tier3.GuiQuartzFurnaceT3;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBoneFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityQuartzFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityRedstoneFurnace;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityIronFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityQuartzFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityBrickFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityDiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityIronFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityQuartzFurnaceT3;
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
			case MoFurnacesMod.guiIDObsidianFurnaceT3:
				if(entity instanceof TileEntityObsidianFurnaceT3) {
					return new ContainerObsidianFurnaceT3(player.inventory, (TileEntityObsidianFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDRedstoneFurnace:
				if(entity instanceof TileEntityRedstoneFurnace) {
					return new ContainerRedstoneFurnace(player.inventory, (TileEntityRedstoneFurnace) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnaceT2:
				if(entity instanceof TileEntityDiamondFurnaceT2) {
					return new ContainerDiamondFurnaceT2(player.inventory, (TileEntityDiamondFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnaceT3:
				if(entity instanceof TileEntityDiamondFurnaceT3) {
					return new ContainerDiamondFurnaceT3(player.inventory, (TileEntityDiamondFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDAnvilFurnace:
				if(entity instanceof TileEntityAnvilFurnace) {
					return new ContainerAnvilFurnace(player.inventory, (TileEntityAnvilFurnace) entity);
				}
			case MoFurnacesMod.guiIDIronFurnaceT2:
				if(entity instanceof TileEntityIronFurnaceT2) {
					return new ContainerIronFurnaceT2(player.inventory, (TileEntityIronFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDIronFurnaceT3:
				if(entity instanceof TileEntityIronFurnaceT3) {
					return new ContainerIronFurnaceT3(player.inventory, (TileEntityIronFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnaceT2:
				if(entity instanceof TileEntityBrickFurnaceT2) {
					return new ContainerBrickFurnaceT2(player.inventory, (TileEntityBrickFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnaceT3:
				if(entity instanceof TileEntityBrickFurnaceT3) {
					return new ContainerBrickFurnaceT3(player.inventory, (TileEntityBrickFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnaceT2:
				if(entity instanceof TileEntityQuartzFurnaceT2) {
					return new ContainerQuartzFurnaceT2(player.inventory, (TileEntityQuartzFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnaceT3:
				if(entity instanceof TileEntityQuartzFurnaceT3) {
					return new ContainerQuartzFurnaceT3(player.inventory, (TileEntityQuartzFurnaceT3) entity);
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
			case MoFurnacesMod.guiIDObsidianFurnaceT3:
				if(entity instanceof TileEntityObsidianFurnaceT3) {
					return new GuiObsidianFurnaceT3(player.inventory, (TileEntityObsidianFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDRedstoneFurnace:
				if(entity instanceof TileEntityRedstoneFurnace) {
					return new GuiRedstoneFurnace(player.inventory, (TileEntityRedstoneFurnace) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnaceT2:
				if(entity instanceof TileEntityDiamondFurnaceT2) {
					return new GuiDiamondFurnaceT2(player.inventory, (TileEntityDiamondFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDDiamondFurnaceT3:
				if(entity instanceof TileEntityDiamondFurnaceT3) {
					return new GuiDiamondFurnaceT3(player.inventory, (TileEntityDiamondFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDAnvilFurnace:
				if(entity instanceof TileEntityAnvilFurnace) {
					return new GuiAnvilFurnace(player.inventory, (TileEntityAnvilFurnace) entity);
				}
			case MoFurnacesMod.guiIDIronFurnaceT2:
				if(entity instanceof TileEntityIronFurnaceT2) {
					return new GuiIronFurnaceT2(player.inventory, (TileEntityIronFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDIronFurnaceT3:
				if(entity instanceof TileEntityIronFurnaceT3) {
					return new GuiIronFurnaceT3(player.inventory, (TileEntityIronFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnaceT2:
				if(entity instanceof TileEntityBrickFurnaceT2) {
					return new GuiBrickFurnaceT2(player.inventory, (TileEntityBrickFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDBrickFurnaceT3:
				if(entity instanceof TileEntityBrickFurnaceT3) {
					return new GuiBrickFurnaceT3(player.inventory, (TileEntityBrickFurnaceT3) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnaceT2:
				if(entity instanceof TileEntityQuartzFurnaceT2) {
					return new GuiQuartzFurnaceT2(player.inventory, (TileEntityQuartzFurnaceT2) entity);
				}
			case MoFurnacesMod.guiIDQuartzFurnaceT3:
				if(entity instanceof TileEntityQuartzFurnaceT3) {
					return new GuiQuartzFurnaceT3(player.inventory, (TileEntityQuartzFurnaceT3) entity);
				}
				return null;
				}			
			}
		return null;
	}

}
