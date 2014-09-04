package io.github.mattkx4.morefurnaces.blocks.tier3;

import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityBrickFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityDiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityIronFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class MFMT3Blocks {

	/*
	 * Set block step sounds
	 */
	//Obsidian furnace step sound
    public static final Block.SoundType soundTypeObsidian = new Block.SoundType("stone", 1.0F, 1.0F);
    //Diamond furnace step sound
    public static final Block.SoundType soundTypeDiamond = new Block.SoundType("stone", 1.0F, 1.5F);
	//Iron furnace step sound
    public static final Block.SoundType soundTypeIron = new Block.SoundType("stone", 1.0F,  1.5F);
    //Gold furnace step sound
    public static final Block.SoundType soundTypeGold = new Block.SoundType("stone", 1.0F, 1.5F);
    //Brick furnace step sound
    public static final Block.SoundType soundTypeBrick = new Block.SoundType("stone", 1.0F, 1.0F);
    //Quartz Furnace step sound
    public static final Block.SoundType soundTypeQuartz = new Block.SoundType("stone", 1.0F, 1.0F);
    //Netherrack Furnace step sound
    public static final Block.SoundType soundTypeNetherrack = new Block.SoundType("stone", 1.0F, 1.0F);
    
    public static void mainRegistry(){
		initializeBlock();
		registerBlock();
	}

	public static void secondaryRegistry(){
		registerBlock2();
	}
	
	/*
	 * Add new blocks here
	 * format:public static Block 'NewBlockName';
	 */
	//Tier 3 Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceT3Idle;
	public static Block ObsidianFurnaceT3Active;
	//Tier 3 Diamond furnace active and idle blocks
	public static Block DiamondFurnaceT3Idle;
	public static Block DiamondFurnaceT3Active;
	//Tier 3 Iron furnace active and idle blocks
	public static Block IronFurnaceT3Idle;
	public static Block IronFurnaceT3Active;
	//Tier 3 Brick furnace active and idle states
	public static Block BrickFurnaceT3Idle;
	public static Block BrickFurnaceT3Active;

	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock(){
		//initialize the Tier 3 Obsidian furnace
		ObsidianFurnaceT3Idle = new ObsidianFurnaceT3(false).setBlockName("ObsidianFurnaceT3Idle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT3Active = new ObsidianFurnaceT3(true).setBlockName("ObsidianFurnaceT3Active").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Tier 3 Diamond furnace
		DiamondFurnaceT3Idle = new DiamondFurnaceT3(false).setBlockName("DiamondFurnaceT3Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT3Active = new DiamondFurnaceT3(true).setBlockName("DiamondFurnaceT3Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Tier 3 Iron furnace
		IronFurnaceT3Idle = new IronFurnaceT3(false).setBlockName("IronFurnaceT3Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.TieredMFM);
		IronFurnaceT3Active = new IronFurnaceT3(true).setBlockName("IronFurnaceT3Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Tier 3 Brick furnace
		BrickFurnaceT3Idle = new BrickFurnaceT3(false).setBlockName("BrickFurnaceT3Idle").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT3Active = new BrickFurnaceT3(true).setBlockName("BrickFurnaceT3Active").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setLightLevel(0.625F);

	}
	
	/**
	 * Register the blocks in the game registry
	 * format: GameRegistry.registerBlock(newblockname, "newblockname"); 
	 */
	public static void registerBlock(){
		//register Tier 3 Obsidian furnace
		GameRegistry.registerBlock(ObsidianFurnaceT3Idle, "ObsidianFurnaceT3Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT3Active, "ObsidianFurnaceT3Active");
		//register Tier 3 Diamond furnace
		GameRegistry.registerBlock(DiamondFurnaceT3Idle, "DiamondFurnaceT3Idle");
		GameRegistry.registerBlock(DiamondFurnaceT3Active, "DiamondFurnaceT3Active");
		//register Tier 3 Iron Furnace
		GameRegistry.registerBlock(IronFurnaceT3Idle, "IronFurnaceT3Idle");
		GameRegistry.registerBlock(IronFurnaceT3Active, "IronFurnaceT3Active");
		//register Tier 3 Brick furnace
		GameRegistry.registerBlock(BrickFurnaceT3Idle, "BrickFurnaceT3Idle");
		GameRegistry.registerBlock(BrickFurnaceT3Active, "BrickFurnaceT3Active");

	}

	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2(){
		//registry for Tier 3 Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT3.class, "Obsidian Furnace T3");
		//registry for Tier 3 Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT3.class, "Diamond Furnace T3");
		//registry for Tier 3 Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnaceT3.class, "Iron Furnace T3");
		//registry for Tier 3 Brick furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT3.class, "Brick Furnace T3");

	}
}
