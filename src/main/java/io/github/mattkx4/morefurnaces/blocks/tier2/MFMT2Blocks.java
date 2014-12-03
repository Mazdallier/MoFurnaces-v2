package io.github.mattkx4.morefurnaces.blocks.tier2;

import io.github.mattkx4.morefurnaces.blocks.MFMBlocks;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityIronFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityQuartzFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFMT2Blocks {
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
	// Tier 2 Brick Furnace Active and Idle blocks
	public static Block BrickFurnaceT2Idle;
	public static Block BrickFurnaceT2Active;

	// Tier 2 Iron Furnace Active and Idle blocks
	public static Block IronFurnaceT2Idle;
	public static Block IronFurnaceT2Active;

	// Tier 2 Quartz Furnace Active and Idle blocks
	public static Block QuartzFurnaceT2Idle;
	public static Block QuartzFurnaceT2Active;

	// Tier 2 Diamond Furnace Active and Idle blocks
	public static Block DiamondFurnaceT2Idle;
	public static Block DiamondFurnaceT2Active;

	// Tier 2 Obsidian Furnace Active and Idle blocks
	public static Block ObsidianFurnaceT2Idle;
	public static Block ObsidianFurnaceT2Active;

	// Tier 2 Netherrack Furnace Active and Idle blocks
	public static Block NetherrackFurnaceT2Idle;
	public static Block NetherrackFurnaceT2Active;

	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock() {
		// Initialize the Tier 2 Brick Furnace
		BrickFurnaceT2Idle = new BrickFurnaceT2(false)
				.setBlockName("BrickFurnaceT2Idle").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeBrick)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT2Active = new BrickFurnaceT2(true)
				.setBlockName("BrickFurnaceT2Active").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeBrick)
				.setLightLevel(0.625F);

		// Initialize the Tier 2 Iron Furnace
		IronFurnaceT2Idle = new IronFurnaceT2(false)
				.setBlockName("IronFurnaceT2Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeIron)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		IronFurnaceT2Active = new IronFurnaceT2(true)
				.setBlockName("IronFurnaceT2Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeIron)
				.setLightLevel(0.625F);

		// Initialize the Tier 2 Quartz Furnace
		QuartzFurnaceT2Idle = new QuartzFurnaceT2(false)
				.setBlockName("QuartzFurnaceT2Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeQuartz)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		QuartzFurnaceT2Active = new QuartzFurnaceT2(true)
				.setBlockName("QuartzFurnaceT2Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeQuartz)
				.setLightLevel(0.625F);

		// Initialize the Tier 2 Diamond Furnace
		DiamondFurnaceT2Idle = new DiamondFurnaceT2(false)
				.setBlockName("DiamondFurnaceT2Idle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeDiamond)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT2Active = new DiamondFurnaceT2(true)
				.setBlockName("DiamondFurnaceT2Active").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(MFMBlocks.soundTypeDiamond)
				.setLightLevel(0.625F);

		// Initialize the Tier 2 Obsidian Furnace
		ObsidianFurnaceT2Idle = new ObsidianFurnaceT2(false)
				.setBlockName("ObsidianFurnaceT2Idle").setHardness(50.0F)
				.setResistance(2000.0F)
				.setStepSound(MFMBlocks.soundTypeObsidian)
				.setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT2Active = new ObsidianFurnaceT2(true)
				.setBlockName("ObsidianFurnaceT2Active").setHardness(50.0F)
				.setResistance(2000.0F)
				.setStepSound(MFMBlocks.soundTypeObsidian)
				.setLightLevel(0.625F);

		// INSERT NETHERRACK SOUNDS HERE
	}

	/**
	 * Register the blocks in the game registry format:
	 * GameRegistry.registerBlock(newblockname, "newblockname");
	 */
	public static void registerBlock() {
		// Register Tier 2 Brick Furnace
		GameRegistry.registerBlock(BrickFurnaceT2Idle, "BrickFurnaceT2Idle");
		GameRegistry
				.registerBlock(BrickFurnaceT2Active, "BrickFurnaceT2Active");

		// Register Tier 2 Iron Furnace
		GameRegistry.registerBlock(IronFurnaceT2Idle, "IronFurnaceT2Idle");
		GameRegistry.registerBlock(IronFurnaceT2Active, "IronFurnaceT2Active");

		// Register Tier 2 Quartz Furnace
		GameRegistry.registerBlock(QuartzFurnaceT2Idle, "QuartzFurnaceT2Idle");
		GameRegistry.registerBlock(QuartzFurnaceT2Active,
				"QuartzFurnaceT2Active");

		// Register Tier 2 Diamond Furnace
		GameRegistry
				.registerBlock(DiamondFurnaceT2Idle, "DiamondFurnaceT2Idle");
		GameRegistry.registerBlock(DiamondFurnaceT2Active,
				"DiamondFurnaceT2Active");

		// Register Tier 2 Obsidian Furnace
		GameRegistry.registerBlock(ObsidianFurnaceT2Idle,
				"ObsidianFurnaceT2Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT2Active,
				"ObsidianFurnaceT2Active");

		// Register Tier 2 Netherrack Furnace
		GameRegistry.registerBlock(NetherrackFurnaceT2Idle,
				"NetherrackFurnaceT2Idle");
		GameRegistry.registerBlock(NetherrackFurnaceT2Active,
				"NetherrackFurnaceT2Active");
	}

	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2() {
		// TileEntity Registry for Tier 2 Brick Furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT2.class,
				"Brick Furnace T2");

		// TileEntity Registry for Tier 2 Iron Furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnaceT2.class,
				"Iron Furnace T2");

		// TileEntity Registry for Tier 2 Quartz Furnace
		GameRegistry.registerTileEntity(TileEntityQuartzFurnaceT2.class,
				"Quartz Furnace T2");

		// TileEntity Registry for Tier 2 Diamond Furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT2.class,
				"Diamond Furnace T2");

		// TileEntity Registry for Tier 2 Obsidian Furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT2.class,
				"Obsidian Furnace T2");

		// TileEntity Registry for Tier 2 Netherrack Furnace
		GameRegistry.registerTileEntity(TileEntityNetherrackFurnaceT2.class,
				"Netherrack Furnace T2");
	}
}
