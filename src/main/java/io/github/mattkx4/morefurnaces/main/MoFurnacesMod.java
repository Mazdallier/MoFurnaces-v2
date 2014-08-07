package io.github.mattkx4.morefurnaces.main;

import io.github.mattkx4.morefurnaces.blocks.MFMBlock;
import io.github.mattkx4.morefurnaces.gui.MFMGui;
import io.github.mattkx4.morefurnaces.handler.GuiHandler;
import io.github.mattkx4.morefurnaces.handler.MFMHandler;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.proxy.ServerProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid = Strings.MODID, name = Strings.name, version = Strings.version)
public class MoFurnacesMod {
	
	//new creative tab for all new physical aspects of the mod
	public static CreativeTabs MFM;
	
	/**
	 * Create constants for the individual furnace ID's
	 */
	//Obsidian furnace gui ID
	public static final int guiIDObsidianFurnace = 0;
	//Diamond furnace gui ID
	public static final int guiIDDiamondFurnace = 1;
	//Iron furnace gui ID
	public static final int guiIDIronFurnace = 2;
	
	
	
	@Instance(Strings.MODID)
	public static MoFurnacesMod instance;
	
	@SidedProxy (clientSide = "io.github.mattkx4.morefurnaces.proxy.ClientProxy",serverSide = "io.github.mattkx4.morefurnaces.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	//Loads before
	@EventHandler
	public static void preload(FMLPreInitializationEvent PreEvent){
		
		//create new creative tab using obsidian furnace texture
		MFM = new CreativeTabs("mfm"){
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem(){
				return Item.getItemFromBlock(MFMBlock.ObsidianFurnaceActive);
			}
		};
		
		//Input mainRegisterys
		MFMBlock.mainRegistry();		
		
		proxy.registerRenderThings();	
	}
	
	//Loads during
	@EventHandler
	public void load(FMLInitializationEvent Event){
		
		//Input secondaryRegistrys
		MFMBlock.secondaryRegistry();
		MFMGui.secondaryRegistry();
		MFMHandler.secondaryRegistry();
		
		//register for gui handler, cannot place in MFMGui due to restrictions surrounding statics
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	//Loads After
	@EventHandler
	public static void postload(FMLPostInitializationEvent PostEvent){
		
	}

}
