package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.blocks.MFMBlock;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ObsidianTieringDevice extends Item {
	public ObsidianTieringDevice() {
		maxStackSize = 1;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("ObsidianTieringDevice");
		setTextureName(Strings.MODID + ":ObsidianTieringDevice");
	}
}