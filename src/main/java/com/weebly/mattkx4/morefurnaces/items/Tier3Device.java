package com.weebly.mattkx4.morefurnaces.items;

import com.weebly.mattkx4.morefurnaces.items.MFMItems;
import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tier3Device extends Item {
	public Tier3Device() {
		maxStackSize = 1;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("Tier3Device");
		setTextureName(Strings.MODID + ":Tier3Device");
	}
}