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
	//Add the Tier 2 Device
	public static Item Tier2Device;
	
	
	public static void mainRegistry(){
		initializeItem();
		registerItem();
	}

	public static void secondaryRegistry(){
		registerItem2();
	}
	
	
	public static void initializeItem() {
		//Initialize the Tier 2 Device
		Tier2Device = new Tier2Device();
		
	}
	
	public static void registerItem() {
		//Register the Tier 2 Device
		GameRegistry.registerItem(Tier2Device, "Tier2Device");
		
	}

	public static void registerItem2() {
		//register recipes for the Tier 2 Device
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"zXz", "X X", "zXz", 'z', Items.diamond, 'X', Items.emerald});
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"yWy", "W W", "yWy", 'y', Items.emerald, 'W', Items.diamond});		
	}
	
}
