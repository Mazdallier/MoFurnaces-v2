package com.weebly.mattkx4.morefurnaces.blocks;

import com.weebly.mattkx4.morefurnaces.items.MFMItems;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityAnvilFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityBoneFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityBrickFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityCactusFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityNetherrackFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityPumpkinFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityQuartzFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntityRedstoneFurnace;
import com.weebly.mattkx4.morefurnaces.tileentity.TileEntitySteelFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFMBlocks {

	/*
	 * Set block step sounds
	 */
	// Brick Furnace step sound
	public static final Block.SoundType soundTypeBrick = new Block.SoundType(
			"stone", 1.0F, 1.0F);

	// Iron Furnace step sound
	public static final Block.SoundType soundTypeIron = new Block.SoundType(
			"stone", 1.0F, 1.5F);

	// Gold Furnace step sound
	public static final Block.SoundType soundTypeGold = new Block.SoundType(
			"stone", 1.0F, 1.5F);

	// Netherrack Furnace step sound
	public static final Block.SoundType soundTypeNetherrack = new Block.SoundType(
			"stone", 1.0F, 1.0F);

	// Quartz Furnace step sound
	public static final Block.SoundType soundTypeQuartz = new Block.SoundType(
			"stone", 1.0F, 1.0F);

	// Diamond Furnace step sound
	public static final Block.SoundType soundTypeDiamond = new Block.SoundType(
			"stone", 1.0F, 1.5F);

	// Obsidian Furnace step sound
	public static final Block.SoundType soundTypeObsidian = new Block.SoundType(
			"stone", 1.0F, 1.0F);

	// Bone Furnace step sound
	public static final Block.SoundType soundTypeBone = new Block.SoundType(
			"stone", 1.0F, 1.0F);

	// Redstone Furnace step sound
	public static final Block.SoundType soundTypeRedstone = new Block.SoundType(
			"stone", 1.0F, 1.5F);

	// Anvil Furnace step sound
	public static final Block.SoundType soundTypeAnvil = new Block.SoundType(
			"anvil", 0.3F, 1.0F);

	// Cactus Furnace step sound
	public static final Block.SoundType soundTypeCactus = new Block.SoundType(
			"cloth", 1.0F, 1.0F);

	// pumpkin furnace step sounds
	public static final Block.SoundType soundTypePumpkin = new Block.SoundType(
			"wood", 1.0F, 1.0F);

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
	//  Steel ore block
	public static Block oreSteel;
	
	// Brick Furnace Active and Idle blocks
	public static Block BrickFurnaceIdle;
	public static Block BrickFurnaceActive;

	// Iron Furnace Active and Idle blocks
	public static Block SteelFurnaceIdle;
	public static Block SteelFurnaceActive;

	// Gold Furnace Active and Idle blocks
	public static Block GoldFurnaceIdle;
	public static Block GoldFurnaceActive;

	// Netherrack Furnace Active blocks
	public static Block NetherrackFurnaceActive;

	// Quartz Furnace Active and Idle blocks
	public static Block QuartzFurnaceIdle;
	public static Block QuartzFurnaceActive;

	// Diamond Furnace Active and Idle blocks
	public static Block DiamondFurnaceIdle;
	public static Block DiamondFurnaceActive;

	// Obsidian Furnace Active and Idle blocks
	public static Block ObsidianFurnaceIdle;
	public static Block ObsidianFurnaceActive;

	// Bone Furnace Active and Idle blocks
	public static Block BoneFurnaceIdle;
	public static Block BoneFurnaceActive;

	// Redstone Furnace Active and Idle blocks
	public static Block RedstoneFurnaceIdle;
	public static Block RedstoneFurnaceActive;

	// Anvil Furnace Active and Idle blocks
	public static Block AnvilFurnaceIdle;
	public static Block AnvilFurnaceActive;

	// Cactus Furnace Active and Idle blocks
	public static Block CactusFurnaceIdle;
	public static Block CactusFurnaceActive;

	// Pumpkin Block Active and Idle Blocks
	public static Block PumpkinFurnaceIdle;
	public static Block PumpkinFurnaceActive;

	/**
	 * Initialize the active and idle blocks and set attributes
	 */
	public static void initializeBlock() {
		// Initialize the ores
		oreSteel = new oreSteel(Material.rock)
				.setBlockName("oreSteel").setHardness(4.0F)
				.setResistance(7.5F).setStepSound(soundTypeIron)
				.setCreativeTab(MoFurnacesMod.MFM);
		
		// Initialize the Brick Furnace
		BrickFurnaceIdle = new BrickFurnace(false)
				.setBlockName("BrickFurnaceIdle").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(soundTypeBrick)
				.setCreativeTab(MoFurnacesMod.MFM);
		BrickFurnaceActive = new BrickFurnace(true)
				.setBlockName("BrickFurnaceActive").setHardness(2.0F)
				.setResistance(10.0F).setStepSound(soundTypeBrick)
				.setLightLevel(0.625F);

		// Initialize the Iron Furnace
		SteelFurnaceIdle = new SteelFurnace(false)
				.setBlockName("SteelFurnaceIdle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeIron)
				.setCreativeTab(MoFurnacesMod.MFM);
		SteelFurnaceActive = new SteelFurnace(true)
				.setBlockName("SteelFurnaceActive").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeIron)
				.setLightLevel(0.625F);

		// Initialize the Gold Furnace
		GoldFurnaceIdle = new GoldFurnace(false)
				.setBlockName("GoldFurnaceIdle").setHardness(3.0F)
				.setResistance(10.0F).setStepSound(soundTypeIron)
				.setCreativeTab(MoFurnacesMod.MFM);
		GoldFurnaceActive = new GoldFurnace(true)
				.setBlockName("GoldFurnaceActive").setHardness(3.0F)
				.setResistance(10.0F).setStepSound(soundTypeIron)
				.setLightLevel(0.625F);

		// Initialize the Netherrack Furnace
		NetherrackFurnaceActive = new NetherrackFurnace(true)
				.setBlockName("NetherrackFurnaceActive").setHardness(0.4F)
				.setResistance(4.0F).setStepSound(soundTypeNetherrack)
				.setLightLevel(0.625F).setCreativeTab(MoFurnacesMod.MFM);

		// Initialize the Quartz Furnace
		QuartzFurnaceIdle = new QuartzFurnace(false)
				.setBlockName("QuartzFurnaceIdle").setHardness(0.8F)
				.setResistance(4.0F).setStepSound(soundTypeQuartz)
				.setCreativeTab(MoFurnacesMod.MFM);
		QuartzFurnaceActive = new QuartzFurnace(true)
				.setBlockName("QuartzFurnaceActive").setHardness(0.8F)
				.setResistance(4.0F).setStepSound(soundTypeQuartz)
				.setLightLevel(0.625F);

		// Initialize the Diamond Furnace
		DiamondFurnaceIdle = new DiamondFurnace(false)
				.setBlockName("DiamondFurnaceIdle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeDiamond)
				.setCreativeTab(MoFurnacesMod.MFM);
		DiamondFurnaceActive = new DiamondFurnace(true)
				.setBlockName("DiamondFurnaceActive").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeDiamond)
				.setLightLevel(0.625F);

		// Initialize the Obsidian Furnace
		ObsidianFurnaceIdle = new ObsidianFurnace(false)
				.setBlockName("ObsidianFurnaceIdle").setHardness(50.0F)
				.setResistance(2000.0F).setStepSound(soundTypeObsidian)
				.setCreativeTab(MoFurnacesMod.MFM);
		ObsidianFurnaceActive = new ObsidianFurnace(true)
				.setBlockName("ObsidianFurnaceActive").setHardness(50.0F)
				.setResistance(2000.0F).setStepSound(soundTypeObsidian)
				.setLightLevel(0.625F);

		// Initialize the Bone Furnace
		BoneFurnaceIdle = new BoneFurnace(false)
				.setBlockName("BoneFurnaceIdle").setHardness(0.5F)
				.setResistance(2.0F).setStepSound(soundTypeBone)
				.setCreativeTab(MoFurnacesMod.MFM);
		BoneFurnaceActive = new BoneFurnace(true)
				.setBlockName("BoneFurnaceActive").setHardness(0.5F)
				.setResistance(2.0F).setStepSound(soundTypeBone)
				.setLightLevel(0.625F);

		// Initialize the Redstone Furnace
		RedstoneFurnaceIdle = new RedstoneFurnace(false)
				.setBlockName("RedstoneFurnaceIdle").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeRedstone)
				.setCreativeTab(MoFurnacesMod.MFM);
		RedstoneFurnaceActive = new RedstoneFurnace(true)
				.setBlockName("RedstoneFurnaceActive").setHardness(5.0F)
				.setResistance(10.0F).setStepSound(soundTypeRedstone)
				.setLightLevel(0.625F);

		// Initialize the Anvil Furnace
		AnvilFurnaceIdle = new AnvilFurnace(false)
				.setBlockName("AnvilFurnaceIdle").setHardness(5.0F)
				.setResistance(2000.0F).setStepSound(soundTypeAnvil)
				.setCreativeTab(MoFurnacesMod.MFM);
		AnvilFurnaceActive = new AnvilFurnace(true)
				.setBlockName("AnvilFurnaceActive").setHardness(5.0F)
				.setResistance(2000.0F).setStepSound(soundTypeAnvil)
				.setLightLevel(0.625F);

		// Initialize the Cactus Furnace
		CactusFurnaceIdle = new CactusFurnace(false)
				.setBlockName("CactusFurnaceIdle").setHardness(0.4F)
				.setResistance(0.1F).setStepSound(soundTypeCactus)
				.setCreativeTab(MoFurnacesMod.MFM);
		CactusFurnaceActive = new CactusFurnace(true)
				.setBlockName("CactusFurnaceActive").setHardness(0.4F)
				.setResistance(0.1F).setStepSound(soundTypeCactus)
				.setLightLevel(0.625F);

		// Initialize the Pumpkin Furnace
		PumpkinFurnaceIdle = new PumpkinFurnace(false)
				.setBlockName("PumpkinFurnaceIdle").setHardness(0.4F)
				.setResistance(0.1F).setStepSound(soundTypePumpkin)
				.setCreativeTab(MoFurnacesMod.MFM);
		PumpkinFurnaceActive = new PumpkinFurnace(true)
				.setBlockName("PumpkinFurnaceActive").setHardness(0.4F)
				.setResistance(0.1F).setStepSound(soundTypePumpkin)
				.setLightLevel(1.0F);
	}

	/**
	 * Register the blocks in the game registry format:
	 * GameRegistry.registerBlock(newblockname, "newblockname");
	 */
	public static void registerBlock() {
		// Register ores with oreDictionary and game registry
		GameRegistry.registerBlock(oreSteel, "oreSteel");
		OreDictionary.registerOre("oreSteel", oreSteel);
		
		// Register Brick Furnace
		GameRegistry.registerBlock(BrickFurnaceIdle, "BrickFurnaceIdle");
		GameRegistry.registerBlock(BrickFurnaceActive, "BrickFurnaceActive");

		// Register Iron Furnace
		GameRegistry.registerBlock(SteelFurnaceIdle, "SteelFurnaceIdle");
		GameRegistry
				.registerBlock(SteelFurnaceActive, "SteelFurnaceActive");

		// Register Gold Furnace
		GameRegistry.registerBlock(GoldFurnaceIdle, "GoldFurnaceIdle");
		GameRegistry.registerBlock(GoldFurnaceActive, "GoldFurnaceActive");

		// Register Netherrack Furnace
		GameRegistry.registerBlock(NetherrackFurnaceActive,
				"NetherrackFurnaceActive");

		// Register Quartz Furnace
		GameRegistry.registerBlock(QuartzFurnaceIdle, "QuartzFurnaceIdle");
		GameRegistry.registerBlock(QuartzFurnaceActive, "QuartzFurnaceActive");

		// Register Diamond Furnace
		GameRegistry.registerBlock(DiamondFurnaceIdle, "DiamondFurnaceIdle");
		GameRegistry
				.registerBlock(DiamondFurnaceActive, "DiamondFurnaceActive");

		// Register Obsidian Furnace
		GameRegistry.registerBlock(ObsidianFurnaceIdle, "ObsidianFurnaceIdle");
		GameRegistry.registerBlock(ObsidianFurnaceActive,
				"ObsidianFurnaceActive");

		// Register Bone Furnace
		GameRegistry.registerBlock(BoneFurnaceIdle, "BoneFurnaceIdle");
		GameRegistry.registerBlock(BoneFurnaceActive, "BoneFurnaceActive");

		// Register Redstone Furnace
		GameRegistry.registerBlock(RedstoneFurnaceIdle, "RedstoneFurnaceIdle");
		GameRegistry.registerBlock(RedstoneFurnaceActive,
				"RedstoneFurnaceActive");

		// Register Anvil Furnace
		GameRegistry.registerBlock(AnvilFurnaceIdle, "AnvilFurnaceIdle");
		GameRegistry.registerBlock(AnvilFurnaceActive, "AnvilFurnaceActive");

		// Register Cactus Furnace
		GameRegistry.registerBlock(CactusFurnaceIdle, "CactusFurnaceIdle");
		GameRegistry.registerBlock(CactusFurnaceActive, "CactusFurnaceActive");

		// Register Cactus Furnace
		GameRegistry.registerBlock(PumpkinFurnaceIdle, "PumpkinFurnaceIdle");
		GameRegistry
				.registerBlock(PumpkinFurnaceActive, "PumpkinFurnaceActive");
	}

	/**
	 * Register the tile entity and crafting recipe
	 */
	public static void registerBlock2() {
		// Create a crafting recipe for the ore
		GameRegistry.addShapelessRecipe(new ItemStack(oreSteel), new Object[]{
				Blocks.iron_ore, Items.coal});
		
		// TileEntity and Crafting Recipe Registry for Brick Furnace
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class,
				"Brick Furnace");
		GameRegistry.addRecipe(new ItemStack(BrickFurnaceIdle), new Object[] {
				"bbb", "bFb", "bbb", 'b', Blocks.brick_block, 'F',
				Blocks.furnace });

		// TileEntity and Crafting Recipe Registry for Iron Furnace
		GameRegistry.registerTileEntity(TileEntitySteelFurnace.class,
				"SteelFurnace");
		GameRegistry.addRecipe(new ItemStack(SteelFurnaceIdle), new Object[] {
				"iii", "iBi", "iii", 'i', MFMItems.ingotSteel, 'B',
				BrickFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Gold Furnace
		GameRegistry.registerTileEntity(TileEntityGoldFurnace.class,
				"Gold Furnace");
		GameRegistry.addRecipe(new ItemStack(GoldFurnaceIdle), new Object[] {
				"ggg", "gBg", "ggg", 'g', Items.gold_ingot, 'B',
				BrickFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Netherrack Furnace
		GameRegistry.registerTileEntity(TileEntityNetherrackFurnace.class,
				"Netherrack Furnace");
		GameRegistry.addRecipe(new ItemStack(NetherrackFurnaceActive),
				new Object[] { "nnn", "nIn", "nnn", 'n', Blocks.netherrack,
						'I', SteelFurnaceIdle });
		GameRegistry.addRecipe(new ItemStack(NetherrackFurnaceActive),
				new Object[] { "nnn", "nGn", "nnn", 'n', Blocks.netherrack,
						'G', GoldFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Quartz Furnace
		GameRegistry.registerTileEntity(TileEntityQuartzFurnace.class,
				"Quartz Furnace");
		GameRegistry.addRecipe(new ItemStack(QuartzFurnaceIdle), new Object[] {
				"qqq", "qIq", "qqq", 'q', Blocks.quartz_block, 'I',
				SteelFurnaceIdle });
		GameRegistry.addRecipe(new ItemStack(QuartzFurnaceIdle), new Object[] {
				"qqq", "qGq", "qqq", 'q', Blocks.quartz_block, 'G',
				GoldFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Diamond Furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace.class,
				"Diamond Furnace");
		GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle),
				new Object[] { "ddd", "dQd", "ddd", 'd', Items.diamond, 'Q',
						QuartzFurnaceIdle });
		GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle), new Object[] {
				"ddd", "dNd", "ddd", 'd', Items.diamond, 'N',
				NetherrackFurnaceActive });

		// TileEntity and Crafting Recipe Registry for Obsidian Furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnace.class,
				"Obsidian Furnace");
		GameRegistry.addRecipe(new ItemStack(ObsidianFurnaceIdle),
				new Object[] { "ooo", "oDo", "ooo", 'o', Blocks.obsidian, 'D',
						DiamondFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Bone Furnace
		GameRegistry.registerTileEntity(TileEntityBoneFurnace.class,
				"Bone Furnace");
		GameRegistry.addRecipe(new ItemStack(BoneFurnaceIdle), new Object[] {
				"sss", "sTs", "sss", 's', Items.bone, 'T',
				MFMBlocks.BrickFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Redstone Furnace
		GameRegistry.registerTileEntity(TileEntityRedstoneFurnace.class,
				"Redstone Furnace");
		GameRegistry.addRecipe(new ItemStack(RedstoneFurnaceIdle),
				new Object[] { "aBa", "aCB", "aaa", 'a', Blocks.redstone_block,
						'C', MFMBlocks.BrickFurnaceIdle, 'B', Blocks.hopper });

		// TileEntity and Crafting Recipe Registry for Anvil Furnace
		GameRegistry.registerTileEntity(TileEntityAnvilFurnace.class,
				"Anvil Furnace");
		GameRegistry.addRecipe(new ItemStack(AnvilFurnaceIdle), new Object[] {
				"dUd", "jIj", "lll", 'l', Blocks.iron_block, 'j',
				Items.iron_ingot, 'I', MFMBlocks.BrickFurnaceIdle, 'U',
				Blocks.anvil, 'd', Items.diamond });

		// TileEntity and Crafting Recipe Registry for Cactus Furnace
		GameRegistry.registerTileEntity(TileEntityCactusFurnace.class,
				"Cactus Furnace");
		GameRegistry.addRecipe(new ItemStack(CactusFurnaceIdle), new Object[] {
				"yyy", "yBy", "yyy", 'y', Blocks.cactus, 'B',
				MFMBlocks.BrickFurnaceIdle });

		// TileEntity and Crafting Recipe Registry for Pumpkin Furnace
		GameRegistry.registerTileEntity(TileEntityPumpkinFurnace.class,
				"Pumpkin Furnace");
		GameRegistry.addRecipe(new ItemStack(PumpkinFurnaceIdle), new Object[] {
				"yyy", "yBy", "yyy", 'y', Blocks.pumpkin, 'B',
				MFMBlocks.BrickFurnaceIdle });
	}
}