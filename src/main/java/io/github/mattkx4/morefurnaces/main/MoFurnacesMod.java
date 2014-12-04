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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Strings.MODID, name = Strings.name, version = Strings.version)
public class MoFurnacesMod {
	
	/*
	 * New Creative Tabs for all New Physical Aspects of the Mod
	 */
	public static CreativeTabs MFM;
	public static CreativeTabs TieredMFM;

	// TODO this boolean needs to be set to true within the update notifier
	// changes in order
	// for the halloween changes to come into effect, if false regular operation
	// resume,s
	public static boolean isHalloween;

	/*
	 * Create constants for the individual furnace GUI ID's
	 */
	// Obsidian furnace gui ID
	public static final int guiIDObsidianFurnace = 0;
	// Diamond furnace gui ID
	public static final int guiIDDiamondFurnace = 1;
	// Iron furnace gui ID
	public static final int guiIDSteelFurnace = 2;
	// Gold furnace gui ID
	public static final int guiIDGoldFurnace = 3;
	// Brick Furnace gui ID
	public static final int guiIDBrickFurnace = 4;
	// Quartz Furnace gui ID
	public static final int guiIDQuartzFurnace = 5;
	// Netherrack Furnace gui ID
	public static final int guiIDNetherrackFurnace = 6;
	// Bone Furnace gui ID
	public static final int guiIDBoneFurnace = 7;
	// Redstone Furnace gui ID
	public static final int guiIDRedstoneFurnace = 8;
	// Anvil Furnace gui ID
	public static final int guiIDAnvilFurnace = 9;
	// Cactus Furnace gui ID
	public static final int guiIDCactusFurnace = 10;
	// Cactus Furnace gui ID
	public static final int guiIDPumpkinFurnace = 11;
	// Tier 2 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT2 = 20;
	// Tier 3 Obsidian Furnace gui ID
	public static final int guiIDObsidianFurnaceT3 = 30;
	// Tier 2 Diamond Furnace gui ID
	public static final int guiIDDiamondFurnaceT2 = 21;
	// Tier 3 Diamond Furnace gui ID
	public static final int guiIDDiamondFurnaceT3 = 31;
	// Tier 2 Iron Furnace gui ID
	public static final int guiIDSteelFurnaceT2 = 22;
	// Tier 3 Iron Furnace gui ID
	public static final int guiIDSteelFurnaceT3 = 32;
	// Tier 2 Brick Furnace gui ID
	public static final int guiIDBrickFurnaceT2 = 23;
	// Tier 3 Brick Furnace gui ID
	public static final int guiIDBrickFurnaceT3 = 33;
	// Tier 2 Quartz Furnace gui ID
	public static final int guiIDQuartzFurnaceT2 = 24;
	// Tier 3 Quartz Furnace gui ID
	public static final int guiIDQuartzFurnaceT3 = 34;

	@Instance(Strings.MODID)
	public static MoFurnacesMod instance;

	@SidedProxy(clientSide = "io.github.mattkx4.morefurnaces.proxy.ClientProxy", serverSide = "io.github.mattkx4.morefurnaces.proxy.ServerProxy")
	public static ServerProxy proxy;

	static MFMUpdateNotifier updateNotifier = new MFMUpdateNotifier();
	static MFMHalloweenNotifier halloweenUpdateNotifier = new MFMHalloweenNotifier();

	/**
	 * 'Things' that will load before
	 */
	@EventHandler
	public static void preload(FMLPreInitializationEvent preEvent) {

		FMLCommonHandler.instance().bus().register(updateNotifier);
		FMLCommonHandler.instance().bus().register(halloweenUpdateNotifier);
		MinecraftForge.EVENT_BUS.register(updateNotifier);
		MinecraftForge.EVENT_BUS.register(halloweenUpdateNotifier);

		// Creates a new Creative Tab using the Diamond Furnace Block.
		MFM = new CreativeTabs("mfm") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(MFMBlocks.DiamondFurnaceActive);
			}
		};

		TieredMFM = new CreativeTabs("tieredmfm") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item
						.getItemFromBlock(MFMT3Blocks.ObsidianFurnaceT3Active);
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
	}

	/**
	 * 'Things' that will load during
	 */
	@EventHandler
	public void load(FMLInitializationEvent Event) {

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

	/**
	 * 'Things' that will load after
	 */
	@EventHandler
	public static void postload(FMLPostInitializationEvent PostEvent) {

	}
}
