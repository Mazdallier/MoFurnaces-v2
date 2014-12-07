package com.weebly.mattkx4.morefurnaces.blocks.tier3;

import com.weebly.mattkx4.morefurnaces.blocks.MFMBlocks;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityBrickFurnaceT3;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityDiamondFurnaceT3;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntityQuartzFurnaceT3;
import com.weebly.mattkx4.morefurnaces.tileentity.tier3.TileEntitySteelFurnaceT3;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFMT3Blocks {
	/**
	 * Calls the registry methods for blocks
	 */
	public static void mainRegistry() {
		initializeBlock();
		registerBlock();
	}

	/**
	 * Calls secondary registry for blocks
	 */
	public static void secondaryRegistry() {
		registerBlock2();
	}

	/*
	 * Add new blocks here format:public static Block 'NewBlockName';
	 */
	// Tier 3 Brick Furnace Active and Idle blocks
	public static Block BrickFurnaceT3Idle;
	public static Block BrickFurnaceT3Active;

	// Tier 3 Iron Furnace Active and Idle blocks
	public static Block SteelFurnaceT3Idle;
	public static Block SteelFurnaceT3Active;

	// Tier 3 Quartz Furnace Active and Idle blocks
	public static Block QuartzFurnaceT3Idle;
	public static Block QuartzFurnaceT3Active;

	// Tier 3 Diamond Furnace Active and Idle blocks
	public static Block DiamondFurnaceT3Idle;
	public static Block DiamondFurnaceT3Active;

	// Tier 3 Obsidian Furnace Active and Idle blocks
	public static Block ObsidianFurnaceT3Idle;
	public static Block ObsidianFurnaceT3Active;

	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock() {
		// Initialize the Tier 3 Brick Furnace
		BrickFurnaceT3Idle = new BrickFurnaceT3(false)
				.setBlockName("BrickFurnaceT3Idle").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeBrick)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT3Active = new BrickFurnaceT3(true)
				.setBlockName("BrickFurnaceT3Active").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeBrick)
				.setLightLevel(0.625F);

		// Initialize the Tier 3 Iron Furnace
		SteelFurnaceT3Idle = new SteelFurnaceT3(false)
				.setBlockName("SteelFurnaceT3Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeIron)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		SteelFurnaceT3Active = new SteelFurnaceT3(true)
				.setBlockName("SteelFurnaceT3Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeIron)
				.setLightLevel(0.625F);

		// Initialize the Tier 3 Quartz Furnace
		QuartzFurnaceT3Idle = new QuartzFurnaceT3(false)
				.setBlockName("QuartzFurnaceT3Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeQuartz)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		QuartzFurnaceT3Active = new QuartzFurnaceT3(true)
				.setBlockName("QuartzFurnaceT3Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeQuartz)
				.setLightLevel(0.625F);

		// Initialize the Tier 3 Diamond Furnace
		DiamondFurnaceT3Idle = new DiamondFurnaceT3(false)
				.setBlockName("DiamondFurnaceT3Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeDiamond)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT3Active = new DiamondFurnaceT3(true)
				.setBlockName("DiamondFurnaceT3Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeDiamond)
				.setLightLevel(0.625F);

		// Initialize the Tier 3 Obsidian Furnace
		ObsidianFurnaceT3Idle = new ObsidianFurnaceT3(false)
				.setBlockName("ObsidianFurnaceT3Idle").setHardness(50.0F)
				.setResistance(2000.0F)
				.setStepSound(MFMBlocks.soundTypeObsidian)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT3Active = new ObsidianFurnaceT3(true)
				.setBlockName("ObsidianFurnaceT3Active").setHardness(50.0F)
				.setResistance(2000.0F)
				.setStepSound(MFMBlocks.soundTypeObsidian)
				.setLightLevel(0.625F);
	}

	/**
	 * Register the blocks in the game registry format:
	 * GameRegistry.registerBlock(newblockname, "newblockname");
	 */
	public static void registerBlock() {
		// Register Tier 3 Brick Furnace
		GameRegistry.registerBlock(BrickFurnaceT3Idle, "BrickFurnaceT3Idle");
		GameRegistry
				.registerBlock(BrickFurnaceT3Active, "BrickFurnaceT3Active");

		// Register Tier 3 Iron Furnace
		GameRegistry.registerBlock(SteelFurnaceT3Idle, "SteelFurnaceT3Idle");
		GameRegistry.registerBlock(SteelFurnaceT3Active, "SteelFurnaceT3Active");

		// Register Tier 3 Quartz Furnace
		GameRegistry.registerBlock(QuartzFurnaceT3Idle, "QuartzFurnaceT3Idle");
		GameRegistry.registerBlock(QuartzFurnaceT3Active,
				"QuartzFurnaceT3Active");

		// Register Tier 3 Diamond Furnace
		GameRegistry
				.registerBlock(DiamondFurnaceT3Idle, "DiamondFurnaceT3Idle");
		GameRegistry.registerBlock(DiamondFurnaceT3Active,
				"DiamondFurnaceT3Active");

		// Register Tier 3 Obsidian Furnace
		GameRegistry.registerBlock(ObsidianFurnaceT3Idle,
				"ObsidianFurnaceT3Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT3Active,
				"ObsidianFurnaceT3Active");
	}

	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2() {
		// TileEntity Registry for Tier 3 Brick Furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT3.class,
				"Brick Furnace T3");

		// TileEntity Registry for Tier 3 Iron Furnace
		GameRegistry.registerTileEntity(TileEntitySteelFurnaceT3.class,
				"Steel Furnace T3");

		// TileEntity Registry for Tier 3 Quartz Furnace
		GameRegistry.registerTileEntity(TileEntityQuartzFurnaceT3.class,
				"Quartz Furnace T3");

		// TileEntity Registry for Tier 3 Diamond Furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT3.class,
				"Diamond Furnace T3");

		// TileEntity Registry for Tier 3 Obsidian Furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT3.class,
				"Obsidian Furnace T3");
	}
}
