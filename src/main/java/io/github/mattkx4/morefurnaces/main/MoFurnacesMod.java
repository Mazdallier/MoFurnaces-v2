package io.github.mattkx4.morefurnaces.main;

import io.github.mattkx4.morefurnaces.achievements.MFMAchievements;
import io.github.mattkx4.morefurnaces.blocks.MFMBlocks;
import io.github.mattkx4.morefurnaces.blocks.tier2.MFMT2Blocks;
import io.github.mattkx4.morefurnaces.blocks.tier3.MFMT3Blocks;
import io.github.mattkx4.morefurnaces.gui.MFMGui;
import io.github.mattkx4.morefurnaces.handler.MFMEntityHandler;
import io.github.mattkx4.morefurnaces.handler.MFMGuiHandler;
import io.github.mattkx4.morefurnaces.handler.MFMHandler;
import io.github.mattkx4.morefurnaces.items.MFMItems;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.proxy.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.client.FMLClientHandler;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Strings.MODID, name = Strings.name, version = Strings.version)
public class MoFurnacesMod {
	
	/*
	 * New Creative Tabs for all New Physical Aspects of the Mod
	 */
	public static CreativeTabs MFM;
	public static CreativeTabs TieredMFM;
	
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
	//Redstone Furnace gui ID
	public static final int guiIDRedstoneFurnace = 8;
	//Anvil Furnace gui ID
	public static final int guiIDAnvilFurnace = 9;
	//Cactus Furnace gui ID
	public static final int guiIDCactusFurnace = 10;
	//Tier 2 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT2 = 20;
	//Tier 3 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT3 = 30;
	//Tier 2 Diamond Furnace gui ID
	public static final int guiIDDiamondFurnaceT2 = 21; 
	//Tier 3 Diamond Furnace gui ID
	public static final int guiIDDiamondFurnaceT3 = 31;
	//Tier 2 Iron Furnace gui ID
	public static final int guiIDIronFurnaceT2 = 22;
	//Tier 3 Iron Furnace gui ID
	public static final int guiIDIronFurnaceT3 = 32;
	//Tier 2 Brick Furnace gui ID
	public static final int guiIDBrickFurnaceT2 = 23;
	//Tier 3 Brick Furnace gui ID
	public static final int guiIDBrickFurnaceT3 = 33;
	//Tier 2 Quartz Furnace gui ID
	public static final int guiIDQuartzFurnaceT2 = 24;
	//Tier 3 Quartz Furnace gui ID
	public static final int guiIDQuartzFurnaceT3 = 34;
	
	@Instance(Strings.MODID)
	public static MoFurnacesMod instance;
	
	@SidedProxy (clientSide = "io.github.mattkx4.morefurnaces.proxy.ClientProxy",serverSide = "io.github.mattkx4.morefurnaces.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	//Things for the updater
	private MFMUpdateNotifier updateNotifier;
	public Logger LOGGER;
    private boolean updateMessageQueued;

	
	
	/*
	 * 'Things' that will load before
	 */
	@EventHandler
	public static void preload(FMLPreInitializationEvent preEvent){
		// Creates a new Creative Tab using the Diamond Furnace Block.
		MFM = new CreativeTabs("mfm"){
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem(){
				return Item.getItemFromBlock(MFMBlocks.DiamondFurnaceActive);
			}
		};
		
		TieredMFM = new CreativeTabs("tieredmfm"){
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(MFMT3Blocks.ObsidianFurnaceT3Active);
			}
		};
		
		// Calls the mainRegistry() methods
		MFMBlocks.mainRegistry();
		MFMT2Blocks.mainRegistry();
		MFMT3Blocks.mainRegistry();
		MFMItems.mainRegistry();
		MFMEntityHandler.mainRegistry();
		MFMAchievements.mainRegistry();
		
		proxy.registerRenderThings();	

			instance.updateNotifier = new MFMUpdateNotifier();
		
	}
	
	/*
	 * 'Things' that will load during
	 */
	@EventHandler
	public void load(FMLInitializationEvent Event){
		// Calls all secondaryRegistry() methods.
		MFMBlocks.secondaryRegistry();
		MFMT2Blocks.secondaryRegistry();
		MFMT3Blocks.secondaryRegistry();
		MFMItems.secondaryRegistry();
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
	
	public static MoFurnacesMod instance() {
        return instance;
    }
	
	public void logWarn(String s) {
        this.LOGGER.warn(s);
    }
	
	public void logInfo(String s) {
        this.LOGGER.info(s);
    }
	
	//activate in the case that an update is found
	public void updateFound() {
        try {
            if(FMLClientHandler.instance().getClientPlayerEntity() != null) {
                FMLClientHandler.instance().getClientPlayerEntity().addChatComponentMessage(new ChatComponentText("[" + EnumChatFormatting.RED + "HudPixel" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.DARK_PURPLE + "Update available: " + EnumChatFormatting.GREEN + this.updateNotifier.newestVersion));
                FMLClientHandler.instance().getClientPlayerEntity().addChatMessage(new ChatComponentText("[" + EnumChatFormatting.RED + "HudPixel" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.DARK_PURPLE + "Download here: " + EnumChatFormatting.YELLOW + this.updateNotifier.downloadLink));
                this.updateMessageQueued = false;
            } else {
                // make this being called from onTick()
                this.updateMessageQueued = true;
            }
        } catch(Exception e) {
            this.logWarn("An exception occured in updateFound(). Stacktrace below.");
            e.printStackTrace();
        }
    }

}
