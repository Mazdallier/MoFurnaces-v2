package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;

public class SteelIngot extends Item{
	public SteelIngot() {
		maxStackSize = 64;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("Steel Ingot");
		setTextureName(Strings.MODID + ":ingotSteel");
	}
}
