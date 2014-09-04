package io.github.mattkx4.morefurnaces.blocks.tier2;

import cpw.mods.fml.common.registry.GameRegistry;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityIronFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import net.minecraft.block.Block;

public class MFMT2Blocks {

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
	//Tier 2 Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceT2Idle;
	public static Block ObsidianFurnaceT2Active;
	//Tier 2 Diamond furnace active and idle blocks
	public static Block DiamondFurnaceT2Idle;
	public static Block DiamondFurnaceT2Active;
	//Tier 2 Iron furnace active and idle blocks
	public static Block IronFurnaceT2Idle;
	public static Block IronFurnaceT2Active;
	//Tier 2 Brick furnace active and idle states
	public static Block BrickFurnaceT2Idle;
	public static Block BrickFurnaceT2Active;

	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock() {
		//initialize the Tier 2 Obsidian furnace
		ObsidianFurnaceT2Idle = new ObsidianFurnaceT2(false).setBlockName("ObsidianFurnaceT2Idle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT2Active = new ObsidianFurnaceT2(true).setBlockName("ObsidianFurnaceT2Active").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Tier 2 Diamond furnace
		DiamondFurnaceT2Idle = new DiamondFurnaceT2(false).setBlockName("DiamondFurnaceT2Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT2Active = new DiamondFurnaceT2(true).setBlockName("DiamondFurnaceT2Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Tier 2 Iron furnace
		IronFurnaceT2Idle = new IronFurnaceT2(false).setBlockName("IronFurnaceT2Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.TieredMFM);
		IronFurnaceT2Active = new IronFurnaceT2(true).setBlockName("IronFurnaceT2Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Tier 2 Brick furnace
		BrickFurnaceT2Idle = new BrickFurnaceT2(false).setBlockName("BrickFurnaceT2Idle").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT2Active = new BrickFurnaceT2(true).setBlockName("BrickFurnaceT2Active").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setLightLevel(0.625F);
	}
	
	/**
	 * Register the blocks in the game registry
	 * format: GameRegistry.registerBlock(newblockname, "newblockname"); 
	 */
	public static void registerBlock(){
		//register Tier 2 Obsidian furnace
		GameRegistry.registerBlock(ObsidianFurnaceT2Idle, "ObsidianFurnaceT2Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT2Active, "ObsidianFurnaceT2Active");
		//register Tier 2 Diamond furnace
		GameRegistry.registerBlock(DiamondFurnaceT2Idle, "DiamondFurnaceT2Idle");
		GameRegistry.registerBlock(DiamondFurnaceT2Active, "DiamondFurnaceT2Active");
		//register Tier 2 Iron furnace
		GameRegistry.registerBlock(IronFurnaceT2Idle, "IronFurnaceT2Idle");
		GameRegistry.registerBlock(IronFurnaceT2Active, "IronFurnaceT2Active");
		//register Tier 2 Brick furnace
		GameRegistry.registerBlock(BrickFurnaceT2Idle, "BrickFurnaceT2Idle");
		GameRegistry.registerBlock(BrickFurnaceT2Active, "BrickFurnaceT2Active");

	}
	
	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2(){
		//registry for Tier 2 Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT2.class, "Obsidian Furnace T2");
		//registry for Tier 2 Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT2.class, "Diamond Furnace T2");
		//registry for Tier 2 Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnaceT2.class, "Iron Furnace T2");
		//registry for Tier 2 Brick furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT2.class, "Brick Furnace T2");
		
	}
}
