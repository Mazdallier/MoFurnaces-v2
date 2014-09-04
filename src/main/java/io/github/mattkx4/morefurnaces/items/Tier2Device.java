package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.items.MFMItems;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tier2Device extends Item {
	public Tier2Device() {
		maxStackSize = 1;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("Tier2Device");
		setTextureName(Strings.MODID + ":Tier2Device");
	}
}