package com.MoreFurnacesMod.mod;

import com.MoreFurnacesMod.mod.blocks.MFMBlock;
import com.MoreFurnacesMod.mod.gui.MFMGui;
import com.MoreFurnacesMod.mod.handler.GuiHandler;
import com.MoreFurnacesMod.mod.handler.MFMHandler;
import com.MoreFurnacesMod.mod.lib.Strings;
import com.MoreFurnacesMod.mod.proxy.ServerProxy;

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


@Mod(modid = Strings.MODID,name = Strings.name,version = Strings.version)
public class MoreFurnacesMod {
	/*
	 * testing repo thingy
	 */
	
	//new creative tab for all new physical aspects of the mod
	public static CreativeTabs MFM;
	
	/**
	 * Create constants for the individual furnace ID's
	 */
	//Obsidian furnace gui ID
	public static final int guiIDObsidianFurnace = 0;
	//Diamond furnace gui ID
	public static final int guiIDDiamondFurnace = 1;
	
	
	
	@Instance(Strings.MODID)
	public static MoreFurnacesMod instance;
	
	@SidedProxy (clientSide = "com.MoreFurnacesMod.mod.proxy.ClientProxy",serverSide = "com.MoreFurnacesMod.mod.proxy.ServerProxy")
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
