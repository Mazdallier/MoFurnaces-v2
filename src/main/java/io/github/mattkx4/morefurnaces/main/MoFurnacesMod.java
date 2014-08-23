package io.github.mattkx4.morefurnaces.main;

import io.github.mattkx4.morefurnaces.blocks.MFMBlock;
import io.github.mattkx4.morefurnaces.gui.MFMGui;
import io.github.mattkx4.morefurnaces.handler.MFMGuiHandler;
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
	
	/*
	 * new creative tab for all new physical aspects of the mod
	 */
	public static CreativeTabs MFM;
	
	/*
	 * Create constants for the individual furnace GUI ID's
	 */
	//Obsidian furnace gui ID
	public static final int guiIDObsidianFurnace = 0;
	//Diamond furnace gui ID
	public static final int guiIDDiamondFurnace = 1;
	//Iron furnace gui ID
	public static final int guiIDIronFurnace = 2;
	//Gold furnace gui ID
	public static final int guiIDGoldFurnace = 3;
	//Brick Furnace gui ID
	public static final int guiIDBrickFurnace = 4;
	//Quartz Furnace gui ID
	public static final int guiIDQuartzFurnace = 5;
	//Netherrack Furnace gui ID
	public static final int guiIDNetherrackFurnace = 6;
	//Bone Furnace gui ID
	public static final int guiIDBoneFurnace = 7;
	//Tier 2 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT2 = 20;
	//Tier 3 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT3 = 30;
	//Redstone Furnace gui ID
	public static final int guiIDRedstoneFurnace = 8;
	
	@Instance(Strings.MODID)
	public static MoFurnacesMod instance;
	
	@SidedProxy (clientSide = "io.github.mattkx4.morefurnaces.proxy.ClientProxy",serverSide = "io.github.mattkx4.morefurnaces.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	/*
	 * 'Things' that will load before
	 */
	@EventHandler
	public static void preload(FMLPreInitializationEvent preEvent){
		// Creates a new Creative Tab using the Diamond Furnace Block.
		MFM = new CreativeTabs("mfm"){
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem(){
				return Item.getItemFromBlock(MFMBlock.DiamondFurnaceActive);
			}
		};
		
		// Calls the mainRegistry() method
		MFMBlock.mainRegistry();		
		
		proxy.registerRenderThings();	
	}
	
	/*
	 * 'Things' that will load during
	 */
	@EventHandler
	public void load(FMLInitializationEvent Event){
		// Calls all secondaryRegistry() methods.
		MFMBlock.secondaryRegistry();
		MFMGui.secondaryRegistry();
		MFMHandler.secondaryRegistry();
		
		// Registers the mods GUI Handler class
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MFMGuiHandler());
	}
	
	/*
	 * 'Things' that will load after
	 */
	@EventHandler
	public static void postload(FMLPostInitializationEvent PostEvent){
		
	}

}
