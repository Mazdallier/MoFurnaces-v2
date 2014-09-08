package io.github.mattkx4.morefurnaces.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MFMItems {

	/*
	 * Add new items here
	 * format:public static Item 'NewItemName';
	 */
	// Tier 2 Device
	public static Item Tier2Device;
	//Tier 3 Device
	public static Item Tier3Device;
	
	/**
	 * Calls the registry methods for items
	 */
	public static void mainRegistry(){
		initializeItem();
		registerItem();
	}

	/**
	 * Calls the secondary registry for items
	 */
	public static void secondaryRegistry(){
		registerItem2();
	}
	
	/**
	 * Initialize the items
	 */
	public static void initializeItem() {
		// Initialize the Tier 2 Device
		Tier2Device = new Tier2Device();
		// Initialize the Tier 3 Device
		Tier3Device = new Tier3Device();
	}
	
	/**
	 * Register the items in the game registry
	 * format: GameRegistry.registerItem(newitemname, "newitemname"); 
	 */
	public static void registerItem() {
		// Register the Tier 2 Device
		GameRegistry.registerItem(Tier2Device, "Tier2Device");
		// Register the Tier 3 Device
		GameRegistry.registerItem(Tier3Device, "Tier3Device");
	}

	/**
	 * Register the Crafting Recipe
	 */
	public static void registerItem2() {
		// Register Crafting Recipe for the Tier 2 Device
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"zXz", "X X", "zXz", 'z', Items.diamond, 'X', Items.emerald});
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"yWy", "W W", "yWy", 'y', Items.emerald, 'W', Items.diamond});		
	}
}
