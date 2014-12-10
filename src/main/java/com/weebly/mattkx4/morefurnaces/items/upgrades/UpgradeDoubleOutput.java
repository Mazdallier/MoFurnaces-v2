package com.weebly.mattkx4.morefurnaces.items.upgrades;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.item.Item;

public class UpgradeDoubleOutput extends Item{
	
	public UpgradeDoubleOutput() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeDoubleOutput");
		setTextureName(Strings.MODID + ":UpgradeDoubleOutput");
	}

}
