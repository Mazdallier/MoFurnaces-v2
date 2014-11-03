package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;

public class UpgradeBrightness extends Item{
	public UpgradeBrightness() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeBrightness");
		setTextureName(Strings.MODID + ":UpgradeBrightness");
	}
}
