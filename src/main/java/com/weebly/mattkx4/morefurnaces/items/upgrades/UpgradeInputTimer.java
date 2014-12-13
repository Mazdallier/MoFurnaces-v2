package com.weebly.mattkx4.morefurnaces.items.upgrades;

import net.minecraft.item.Item;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

public class UpgradeInputTimer extends Item {
	public UpgradeInputTimer() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeInputTimer");
		setTextureName(Strings.MODID + ":UpgradeInputTimer");
	}
}
