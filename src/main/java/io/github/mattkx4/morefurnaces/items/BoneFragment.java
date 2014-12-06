package io.github.mattkx4.morefurnaces.items;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.item.Item;

public class BoneFragment extends Item {
	public BoneFragment() {
		maxStackSize = 64;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("BoneFragment");
		setTextureName(Strings.MODID + ":BoneFragment");
	}
}
