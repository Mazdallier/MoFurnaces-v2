package com.weebly.mattkx4.morefurnaces.items;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.item.Item;

public class SteelIngot extends Item{
	public SteelIngot() {
		maxStackSize = 64;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("SteelIngot");
		setTextureName(Strings.MODID + ":Steel_Ingot");
	}
}
