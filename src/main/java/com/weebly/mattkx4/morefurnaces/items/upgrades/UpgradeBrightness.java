package com.weebly.mattkx4.morefurnaces.items.upgrades;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.item.Item;

public class UpgradeBrightness extends Item {
	public UpgradeBrightness() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeBrightness");
		setTextureName(Strings.MODID + ":UpgradeBrightness");
	}
}
