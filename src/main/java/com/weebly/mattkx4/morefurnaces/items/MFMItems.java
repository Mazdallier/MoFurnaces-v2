package com.weebly.mattkx4.morefurnaces.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.weebly.mattkx4.morefurnaces.blocks.MFMBlocks;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeBrightness;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeDoubleOutput;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeFuelSaver;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeFuelTimer;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeInputTimer;
import com.weebly.mattkx4.morefurnaces.items.upgrades.UpgradeNotification;

import cpw.mods.fml.common.registry.GameRegistry;

public class MFMItems {

	/*
	 * Add new items here format:public static Item 'NewItemName';
	 */
	// Tier Core
	public static Item TierCore;
	// Tier 2 Device
	public static Item Tier2Device;
	// Tier 3 Device
	public static Item Tier3Device;
	// Upgrades
	public static Item UpgradeBrightness; // Brightness upgrade
	public static Item UpgradeNotification; // Notification upgrade
	public static Item UpgradeDoubleOutput; // Double Output upgrade
	public static Item UpgradeFuelSaver; // Fuel Saver upgrade
	public static Item UpgradeInputTimer; // Input Timer upgrade
	public static Item UpgradeFuelTimer; // Fuel Timer upgrade
	// Ingots
	public static Item ingotSteel;
	// Bone Fragment
	public static Item boneFragment;
	// Information Book
	public static Item furnaceBook;

	/**
	 * Calls the registry methods for items
	 */
	public static void mainRegistry() {
		initializeItem();
		registerItem();
	}

	/**
	 * Calls the secondary registry for items
	 */
	public static void secondaryRegistry() {
		registerItem2();
	}

	/**
	 * Initialize the items
	 */
	public static void initializeItem() {
		// Initialize the Tier Core
		TierCore = new TierCore();
		// Initialize the Tier 2 Device
		Tier2Device = new Tier2Device();
		// Initialize the Tier 3 Device
		Tier3Device = new Tier3Device();
		// Initialize the upgrades
		UpgradeBrightness = new UpgradeBrightness(); // Brightness Upgrade
		UpgradeNotification = new UpgradeNotification(); // Notification Upgrade
		UpgradeDoubleOutput = new UpgradeDoubleOutput(); // Double Output
															// Upgrade
		UpgradeFuelSaver = new UpgradeFuelSaver(); // Fuel Saver Upgrade
		UpgradeInputTimer = new UpgradeInputTimer(); // Input Timer Upgrade
		UpgradeFuelTimer = new UpgradeFuelTimer();
		// Initialize the ingots
		ingotSteel = new SteelIngot();
		// Initialize the bone fragment
		boneFragment = new BoneFragment();
		// Initialize the furnace book
		furnaceBook = new FurnaceBook();

	}

	/**
	 * Register the items in the game registry format:
	 * GameRegistry.registerItem(newitemname, "newitemname");
	 */
	public static void registerItem() {
		// Register the Tier Core
		GameRegistry.registerItem(TierCore, "TierCore");
		// Register the Tier 2 Device
		GameRegistry.registerItem(Tier2Device, "Tier2Device");
		// Register the Tier 3 Device
		GameRegistry.registerItem(Tier3Device, "Tier3Device");
		// Register the upgrades
		GameRegistry.registerItem(UpgradeBrightness, "UpgradeBrightness"); // Brightness
																			// Upgrade
		GameRegistry.registerItem(UpgradeNotification, "UpgradeNotification"); // Notification
																				// Upgrade
		GameRegistry.registerItem(UpgradeDoubleOutput, "UpgradeDoubleOutput"); // Double
																				// Output
																				// Upgrade
		GameRegistry.registerItem(UpgradeFuelSaver, "UpgradeFuelSaver"); // Fuel
																			// Saver
																			// Upgrade
		GameRegistry.registerItem(UpgradeInputTimer, "UpgradeInputTimer"); // Input
																			// Timer
																			// Upgrade
		GameRegistry.registerItem(UpgradeFuelTimer, "UpgradeFuelTimer");
		// Register the ingots with game and ore registry
		GameRegistry.registerItem(ingotSteel, "SteelIngot");
		OreDictionary.registerOre("ingotSteel", ingotSteel);

		// Register the bone fragment
		GameRegistry.registerItem(boneFragment, "BoneFragment");

		// Register the furnace book
		GameRegistry.registerItem(furnaceBook, "FurnaceBook");
	}

	/**
	 * Register the Crafting Recipe
	 */
	public static void registerItem2() {
		// Register Crafting Recipe for the Tier Core
		GameRegistry.addRecipe(new ItemStack(TierCore), new Object[] { "zXz",
				"X X", "zXz", 'z', Items.diamond, 'X', Items.emerald });
		GameRegistry.addRecipe(new ItemStack(TierCore), new Object[] { "yWy",
				"W W", "yWy", 'y', Items.emerald, 'W', Items.diamond });
		// Register Crafting Recipe for the Tier 2 Device
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[] {
				"z z", "zXz", " y ", 'z', Items.iron_ingot, 'X',
				MFMItems.TierCore, 'y', Items.blaze_rod });
		// Register Crafting Recipe for the Tier 3 Device
		GameRegistry.addRecipe(new ItemStack(Tier3Device), new Object[] {
				"zXz", "zWz", " y ", 'z', Items.diamond, 'X', Items.ender_eye,
				'W', MFMItems.TierCore, 'y', Items.blaze_rod });
		// Add recipes for the upgrades here
		// Brightness Upgrade Recipe
		// Notification Upgrade Recipe
		// Fuel Saver Upgrade Recipe
		// Input Timer Upgrade Recipe
		// Fuel Timer Upgrade Recipe
		GameRegistry.addSmelting(MFMBlocks.oreSteel, new ItemStack(ingotSteel),
				0.5F);
		// Register Crafting Recipe for the new Bone Recipe
		GameRegistry.addRecipe(new ItemStack(Items.bone), new Object[] { "BBB",
				"BBB", "BBB", 'B', boneFragment });
		// Register crafting recipe for the furnace book
		GameRegistry.addShapelessRecipe(new ItemStack(furnaceBook),
				new Object[] { Items.book, Blocks.furnace });
	}
}
