package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;

public class TierCore extends Item {
	public TierCore() {
		maxStackSize = 64;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("TierCore");
		setTextureName(Strings.MODID + ":TierCore");
	}
}
