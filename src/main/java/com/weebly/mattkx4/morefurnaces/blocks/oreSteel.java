package com.weebly.mattkx4.morefurnaces.blocks;

import com.weebly.mattkx4.morefurnaces.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class oreSteel extends Block{

	protected oreSteel(Material material) {
		super(material);
		setBlockTextureName(Strings.MODID + ":Ore_Steel");
	}

}
