package com.weebly.mattkx4.morefurnaces.items.upgrades;

import net.minecraft.item.Item;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

public class UpgradeFuelSaver extends Item {
	public UpgradeFuelSaver() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeFuelSaver");
		setTextureName(Strings.MODID + ":UpgradeFuelSaver");
	}
}
