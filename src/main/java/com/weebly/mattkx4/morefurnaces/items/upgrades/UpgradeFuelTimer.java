package com.weebly.mattkx4.morefurnaces.items.upgrades;

import net.minecraft.item.Item;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

public class UpgradeFuelTimer extends Item {
	public UpgradeFuelTimer() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeFuelTimer");
		setTextureName(Strings.MODID + ":UpgradeFuelTimer");
	}
}
