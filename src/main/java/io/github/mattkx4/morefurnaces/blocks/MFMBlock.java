package io.github.mattkx4.morefurnaces.blocks;

import io.github.mattkx4.morefurnaces.blocks.tier2.BrickFurnaceT2;
import io.github.mattkx4.morefurnaces.blocks.tier2.DiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.blocks.tier2.IronFurnaceT2;
import io.github.mattkx4.morefurnaces.blocks.tier2.ObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.blocks.tier3.BrickFurnaceT3;
import io.github.mattkx4.morefurnaces.blocks.tier3.DiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.blocks.tier3.IronFurnaceT3;
import io.github.mattkx4.morefurnaces.blocks.tier3.ObsidianFurnaceT3;
import io.github.mattkx4.morefurnaces.items.Tier2Device;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBoneFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityQuartzFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityRedstoneFurnace;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityBrickFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityDiamondFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityIronFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier2.TileEntityObsidianFurnaceT2;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityBrickFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityDiamondFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityIronFurnaceT3;
import io.github.mattkx4.morefurnaces.tileentity.tier3.TileEntityObsidianFurnaceT3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFMBlock {
	
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
    //Bone Furnace step sound
    public static final Block.SoundType soundTypeBone = new Block.SoundType("stone", 1.0F, 1.0F);
    //Redstone Furnace step sound
    public static final Block.SoundType soundTypeRedstone = new Block.SoundType("stone", 1.0F, 1.5F);
    //Anvil Furnace step sound
    public static final Block.SoundType soundTypeAnvil = new Block.SoundType("anvil", 0.3F, 1.0F);
    
	public static void mainRegistry(){
		initializeBlock();
		registerBlock();
		initializeItem();
		registerItem();
	}

	public static void secondaryRegistry(){
		registerBlock2();
		registerItem2();
	}
	
	/*
	 * Add new blocks here
	 * format:public static Block 'NewBlockName';
	 */
	//Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceIdle;
	public static Block ObsidianFurnaceActive;
	//Tier 2 Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceT2Idle;
	public static Block ObsidianFurnaceT2Active;
	//Tier 3 Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceT3Idle;
	public static Block ObsidianFurnaceT3Active;
	//Diamond furnace active and idle blocks
	public static Block DiamondFurnaceIdle;
	public static Block DiamondFurnaceActive;
	//Tier 2 Diamond furnace active and idle blocks
	public static Block DiamondFurnaceT2Idle;
	public static Block DiamondFurnaceT2Active;
	//Tier 3 Diamond furnace active and idle blocks
	public static Block DiamondFurnaceT3Idle;
	public static Block DiamondFurnaceT3Active;
	//Iron furnace active and idle states
	public static Block IronFurnaceIdle;
	public static Block IronFurnaceActive;
	//Tier 2 Iron furnace active and idle blocks
	public static Block IronFurnaceT2Idle;
	public static Block IronFurnaceT2Active;
	//Tier 3 Iron furnace active and idle blocks
	public static Block IronFurnaceT3Idle;
	public static Block IronFurnaceT3Active;
	//Gold furnace active and idle states
	public static Block GoldFurnaceIdle;
	public static Block GoldFurnaceActive;
	//Brick furnace active and idle states
	public static Block BrickFurnaceIdle;
	public static Block BrickFurnaceActive;
	//Tier 2 Brick furnace active and idle states
	public static Block BrickFurnaceT2Idle;
	public static Block BrickFurnaceT2Active;
	//Tier 3 Brick furnace active and idle states
	public static Block BrickFurnaceT3Idle;
	public static Block BrickFurnaceT3Active;
	//Quartz furnace active and idle states
	public static Block QuartzFurnaceIdle;
	public static Block QuartzFurnaceActive;
	//Netherrack furnace active and idle states
	public static Block NetherrackFurnaceIdle;
	public static Block NetherrackFurnaceActive;
	//Bone furnace active and idle states
	public static Block BoneFurnaceIdle;
	public static Block BoneFurnaceActive;
	//Redstone furnace active and idle states
	public static Block RedstoneFurnaceIdle;
	public static Block RedstoneFurnaceActive;
	//Anvil furnace active and idle states
	public static Block AnvilFurnaceIdle;
	public static Block AnvilFurnaceActive;
	
	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock(){
		//initialize the Obsidian furnace
		ObsidianFurnaceIdle = new ObsidianFurnace(false).setBlockName("ObsidianFurnaceIdle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.MFM);
		ObsidianFurnaceActive = new ObsidianFurnace(true).setBlockName("ObsidianFurnaceActive").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Tier 2 Obsidian furnace
		ObsidianFurnaceT2Idle = new ObsidianFurnaceT2(false).setBlockName("ObsidianFurnaceT2Idle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT2Active = new ObsidianFurnaceT2(true).setBlockName("ObsidianFurnaceT2Active").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Tier 3 Obsidian furnace
		ObsidianFurnaceT3Idle = new ObsidianFurnaceT3(false).setBlockName("ObsidianFurnaceT3Idle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.TieredMFM);
		ObsidianFurnaceT3Active = new ObsidianFurnaceT3(true).setBlockName("ObsidianFurnaceT3Active").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Diamond furnace
		DiamondFurnaceIdle = new DiamondFurnace(false).setBlockName("DiamondFurnaceIdle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.MFM);
		DiamondFurnaceActive = new DiamondFurnace(true).setBlockName("DiamondFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Tier 2 Diamond furnace
		DiamondFurnaceT2Idle = new DiamondFurnaceT2(false).setBlockName("DiamondFurnaceT2Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT2Active = new DiamondFurnaceT2(true).setBlockName("DiamondFurnaceT2Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Tier 3 Diamond furnace
		DiamondFurnaceT3Idle = new DiamondFurnaceT3(false).setBlockName("DiamondFurnaceT3Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.TieredMFM);
		DiamondFurnaceT3Active = new DiamondFurnaceT3(true).setBlockName("DiamondFurnaceT3Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Iron furnace
		IronFurnaceIdle = new IronFurnace(false).setBlockName("IronFurnaceIdle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.MFM);
		IronFurnaceActive = new IronFurnace(true).setBlockName("IronFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Tier 2 Iron furnace
		IronFurnaceT2Idle = new IronFurnaceT2(false).setBlockName("IronFurnaceT2Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.TieredMFM);
		IronFurnaceT2Active = new IronFurnaceT2(true).setBlockName("IronFurnaceT2Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Tier 3 Iron furnace
		IronFurnaceT3Idle = new IronFurnaceT3(false).setBlockName("IronFurnaceT3Idle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.TieredMFM);
		IronFurnaceT3Active = new IronFurnaceT3(true).setBlockName("IronFurnaceT3Active").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Gold furnace
		GoldFurnaceIdle = new GoldFurnace(false).setBlockName("GoldFurnaceIdle").setHardness(3.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.MFM);
		GoldFurnaceActive = new GoldFurnace(true).setBlockName("GoldFurnaceActive").setHardness(3.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Brick furnace
		BrickFurnaceIdle = new BrickFurnace(false).setBlockName("BrickFurnaceIdle").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setCreativeTab(MoFurnacesMod.MFM);
		BrickFurnaceActive = new BrickFurnace(true).setBlockName("BrickFurnaceActive").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setLightLevel(0.625F);
		//initialize the Tier 2 Brick furnace
		BrickFurnaceT2Idle = new BrickFurnaceT2(false).setBlockName("BrickFurnaceT2Idle").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT2Active = new BrickFurnaceT2(true).setBlockName("BrickFurnaceT2Active").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setLightLevel(0.625F);
		//initialize the Tier 3 Brick furnace
		BrickFurnaceT3Idle = new BrickFurnaceT3(false).setBlockName("BrickFurnaceT3Idle").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setCreativeTab(MoFurnacesMod.TieredMFM);
		BrickFurnaceT3Active = new BrickFurnaceT3(true).setBlockName("BrickFurnaceT3Active").setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypeBrick).setLightLevel(0.625F);
		//initialize the Quartz furnace
		QuartzFurnaceIdle = new QuartzFurnace(false).setBlockName("QuartzFurnaceIdle").setHardness(0.8F).setResistance(4.0F).setStepSound(soundTypeQuartz).setCreativeTab(MoFurnacesMod.MFM);
		QuartzFurnaceActive = new QuartzFurnace(true).setBlockName("QuartzFurnaceActive").setHardness(0.8F).setResistance(4.0F).setStepSound(soundTypeQuartz).setLightLevel(0.625F);
		//initialize the Netherrack furnace
		NetherrackFurnaceIdle = new NetherrackFurnace(false).setBlockName("NetherrackFurnaceIdle").setHardness(0.4F).setResistance(4.0F).setStepSound(soundTypeNetherrack).setCreativeTab(MoFurnacesMod.MFM);
		NetherrackFurnaceActive = new NetherrackFurnace(true).setBlockName("NetherrackFurnaceActive").setHardness(0.4F).setResistance(4.0F).setStepSound(soundTypeNetherrack).setLightLevel(0.625F);
		//initialize the Bone furnace
		BoneFurnaceIdle = new BoneFurnace(false).setBlockName("BoneFurnaceIdle").setHardness(0.5F).setResistance(2.0F).setStepSound(soundTypeBone).setCreativeTab(MoFurnacesMod.MFM);
		BoneFurnaceActive = new BoneFurnace(true).setBlockName("BoneFurnaceActive").setHardness(0.5F).setResistance(2.0F).setStepSound(soundTypeBone).setLightLevel(0.625F);
		//initialize the Redstone furnace
		RedstoneFurnaceIdle = new RedstoneFurnace(false).setBlockName("RedstoneFurnaceIdle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeRedstone).setCreativeTab(MoFurnacesMod.MFM);
		RedstoneFurnaceActive = new RedstoneFurnace(true).setBlockName("RedstoneFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeRedstone).setLightLevel(0.625F);
		//initialize the Redstone furnace
		AnvilFurnaceIdle = new AnvilFurnace(false).setBlockName("AnvilFurnaceIdle").setHardness(5.0F).setResistance(2000.0F).setStepSound(soundTypeAnvil).setCreativeTab(MoFurnacesMod.MFM);
		AnvilFurnaceActive = new AnvilFurnace(true).setBlockName("AnvilFurnaceActive").setHardness(5.0F).setResistance(2000.0F).setStepSound(soundTypeAnvil).setLightLevel(0.625F);
	}
	
	/**
	 * Register the blocks in the game registry
	 * format: GameRegistry.registerBlock(newblockname, "newblockname"); 
	 */
	public static void registerBlock(){
		//register Obsidian furnace
		GameRegistry.registerBlock(ObsidianFurnaceIdle, "ObsidianFurnaceIdle");
		GameRegistry.registerBlock(ObsidianFurnaceActive, "ObsidianFurnaceActive");
		//register Tier 2 Obsidian furnace
		GameRegistry.registerBlock(ObsidianFurnaceT2Idle, "ObsidianFurnaceT2Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT2Active, "ObsidianFurnaceT2Active");
		//register Tier 3 Obsidian furnace
		GameRegistry.registerBlock(ObsidianFurnaceT3Idle, "ObsidianFurnaceT3Idle");
		GameRegistry.registerBlock(ObsidianFurnaceT3Active, "ObsidianFurnaceT3Active");
		//register Diamond furnace
		GameRegistry.registerBlock(DiamondFurnaceIdle, "DiamondFurnaceIdle");
		GameRegistry.registerBlock(DiamondFurnaceActive, "DiamondFurnaceActive");
		//register Tier 2 Diamond furnace
		GameRegistry.registerBlock(DiamondFurnaceT2Idle, "DiamondFurnaceT2Idle");
		GameRegistry.registerBlock(DiamondFurnaceT2Active, "DiamondFurnaceT2Active");
		//register Tier 3 Diamond furnace
		GameRegistry.registerBlock(DiamondFurnaceT3Idle, "DiamondFurnaceT3Idle");
		GameRegistry.registerBlock(DiamondFurnaceT3Active, "DiamondFurnaceT3Active");
		//register Iron furnace
		GameRegistry.registerBlock(IronFurnaceIdle,  "IronFurnaceIdle");
		GameRegistry.registerBlock(IronFurnaceActive, "IronFurnaceActive");
		//register Tier 2 Iron furnace
		GameRegistry.registerBlock(IronFurnaceT2Idle, "IronFurnaceT2Idle");
		GameRegistry.registerBlock(IronFurnaceT2Active, "IronFurnaceT2Active");
		//register Tier 3 Iron Furnace
		GameRegistry.registerBlock(IronFurnaceT3Idle, "IronFurnaceT3Idle");
		GameRegistry.registerBlock(IronFurnaceT3Active, "IronFurnaceT3Active");
		//register Gold furnace
		GameRegistry.registerBlock(GoldFurnaceIdle,  "GoldFurnaceIdle");
		GameRegistry.registerBlock(GoldFurnaceActive, "GoldFurnaceActive");
		//register Brick furnace
		GameRegistry.registerBlock(BrickFurnaceIdle, "BrickFurnaceIdle");
		GameRegistry.registerBlock(BrickFurnaceActive, "BrickFurnaceActive");
		//register Tier 2 Brick furnace
		GameRegistry.registerBlock(BrickFurnaceT2Idle, "BrickFurnaceT2Idle");
		GameRegistry.registerBlock(BrickFurnaceT2Active, "BrickFurnaceT2Active");
		//register Tier 3 Brick furnace
		GameRegistry.registerBlock(BrickFurnaceT3Idle, "BrickFurnaceT3Idle");
		GameRegistry.registerBlock(BrickFurnaceT3Active, "BrickFurnaceT3Active");
		//register Quartz furnace
		GameRegistry.registerBlock(QuartzFurnaceIdle, "QuartzFurnaceIdle");
		GameRegistry.registerBlock(QuartzFurnaceActive, "QuartzFurnaceActive");
		//register Netherrack furnace
		GameRegistry.registerBlock(NetherrackFurnaceIdle, "NetherrackFurnaceIdle");
		GameRegistry.registerBlock(NetherrackFurnaceActive, "NetherrackFurnaceActive");
		//register Bone furnace
		GameRegistry.registerBlock(BoneFurnaceIdle, "BoneFurnaceIdle");
		GameRegistry.registerBlock(BoneFurnaceActive, "BoneFurnaceActive");
		//register Redstone furnace
		GameRegistry.registerBlock(RedstoneFurnaceIdle, "RedstoneFurnaceIdle");
		GameRegistry.registerBlock(RedstoneFurnaceActive, "RedstoneFurnaceActive");
		//register Anvil furnace
		GameRegistry.registerBlock(AnvilFurnaceIdle, "AnvilFurnaceIdle");
		GameRegistry.registerBlock(AnvilFurnaceActive, "AnvilFurnaceActive");
	}
	
	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2(){
		//registry for Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnace.class, "Obsidian Furnace");
		//GameRegistry.addRecipe(new ItemStack(ObsidianFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Blocks.obsidian});
		GameRegistry.addRecipe(new ItemStack(ObsidianFurnaceIdle), new Object[]{"ooo", "oDo", "ooo", 'o', Blocks.obsidian, 'D', DiamondFurnaceIdle});
		//registry for Tier 2 Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT2.class, "Obsidian Furnace T2");
		//registry for Tier 3 Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnaceT3.class, "Obsidian Furnace T3");
		//GameRegistry.addRecipe(new ItemStack(ObsidianFurnaceT3Idle), new Object[]{"ofo", "dOd", "ooo", 'd', Items.diamond, 'o', Blocks.obsidian, 'O', ObsidianFurnaceT2Idle, 'f', ObsidianFurnaceIdle});
		//registry for Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace.class, "Diamond Furnace");
		//GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle), new Object[]{"ddd", "dQd", "ddd", 'd', Items.diamond, 'Q', QuartzFurnaceIdle});
		GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle), new Object[]{"ddd", "dNd", "ddd", 'd', Items.diamond, 'N', NetherrackFurnaceIdle});
		//registry for Tier 2 Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT2.class, "Diamond Furnace T2");
		//registry for Tier 3 Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnaceT3.class, "Diamond Furnace T3");
		//registry for Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnace.class, "Iron Furnace");
		//GameRegistry.addRecipe(new ItemStack(IronFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(IronFurnaceIdle), new Object[]{"iii", "iBi", "iii", 'i', Items.iron_ingot, 'B', BrickFurnaceIdle});
		//registry for Tier 2 Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnaceT2.class, "Iron Furnace T2");
		//registry for Tier 3 Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnaceT3.class, "Iron Furnace T3");
		//registry for Gold furnace
		GameRegistry.registerTileEntity(TileEntityGoldFurnace.class, "Gold Furnace");
		//GameRegistry.addRecipe(new ItemStack(GoldFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(GoldFurnaceIdle), new Object[]{"ggg", "gBg", "ggg", 'g', Items.gold_ingot, 'B', BrickFurnaceIdle});
		//registry for Brick furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, "Brick Furnace");
		//GameRegistry.addRecipe(new ItemStack(BrickFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Blocks.brick_block});
		GameRegistry.addRecipe(new ItemStack(BrickFurnaceIdle), new Object[]{"bbb", "bFb", "bbb", 'b', Blocks.brick_block, 'F', Blocks.furnace});
		//registry for Tier 2 Brick furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT2.class, "Brick Furnace T2");
		//registry for Tier 3 Brick furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnaceT3.class, "Brick Furnace T3");
		//registry for Quartz furnace
		GameRegistry.registerTileEntity(TileEntityQuartzFurnace.class, "Quartz Furnace");
		//GameRegistry.addRecipe(new ItemStack(QuartzFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.quartz});
		GameRegistry.addRecipe(new ItemStack(QuartzFurnaceIdle), new Object[]{"qqq", "qIq", "qqq", 'q', Blocks.quartz_block, 'I', IronFurnaceIdle});
		GameRegistry.addRecipe(new ItemStack(QuartzFurnaceIdle), new Object[]{"qqq", "qGq", "qqq", 'q', Blocks.quartz_block, 'G', GoldFurnaceIdle});
		//registry for Netherrack furnace
		GameRegistry.registerTileEntity(TileEntityNetherrackFurnace.class, "Netherrack Furnace");
		//GameRegistry.addRecipe(new ItemStack(NetherrackFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Blocks.netherrack});
		GameRegistry.addRecipe(new ItemStack(NetherrackFurnaceIdle), new Object[]{"nnn", "nIn", "nnn", 'n', Blocks.netherrack, 'I', IronFurnaceIdle});
		GameRegistry.addRecipe(new ItemStack(NetherrackFurnaceIdle), new Object[]{"nnn", "nGn", "nnn", 'n', Blocks.netherrack, 'G', GoldFurnaceIdle});
		//registry for Bone furnace
		GameRegistry.registerTileEntity(TileEntityBoneFurnace.class, "Bone Furnace");
		//GameRegistry.addRecipe(new ItemStack(BoneFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.bone});
		//GameRegistry.addRecipe(new ItemStack(BoneFurnaceIdle), new Object[]{"OOO", "OIO", "OOO", 'O', Items.bone, 'I', Blocks.furnace});
		GameRegistry.addRecipe(new ItemStack(BoneFurnaceIdle), new Object[]{"sss", "sTs", "sss", 's', Items.bone, 'T', Blocks.furnace});
		
		//registry for Redstone Furnace
		GameRegistry.registerTileEntity(TileEntityRedstoneFurnace.class, "Redstone Furnace");
		GameRegistry.addRecipe(new ItemStack(RedstoneFurnaceIdle), new Object[]{"aaa", "aCa", "aBa", 'a', Blocks.redstone_block, 'C', Blocks.furnace, 'B', Blocks.hopper});
		//registry for Anvil Furnace
		GameRegistry.registerTileEntity(TileEntityAnvilFurnace.class, "Anvil Furnace");
		GameRegistry.addRecipe(new ItemStack(AnvilFurnaceIdle), new Object[]{"jjj", "jIj", "jUj", 'j', Items.iron_ingot, 'I', Blocks.furnace, 'U', Blocks.anvil});
	}
	
	//initialize obsidian tiering device
	public static Item Tier2Device;
	
	public static void initializeItem() {
		Tier2Device = new Tier2Device();
	}
	
	public static void registerItem() {
		GameRegistry.registerItem(Tier2Device, "Tier2Device");
	}
	
	public static void registerItem2() {
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"zXz", "X X", "zXz", 'z', Items.diamond, 'X', Items.emerald});
		GameRegistry.addRecipe(new ItemStack(Tier2Device), new Object[]{"yWy", "W W", "yWy", 'y', Items.emerald, 'W', Items.diamond});
	}
}
