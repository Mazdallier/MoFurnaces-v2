package io.github.mattkx4.morefurnaces.blocks;

import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityGoldFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public class MFMBlock {
	
	/**
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
    
	public static void mainRegistry(){
		initializeBlock();
		registerBlock();
	}
	
	public static void secondaryRegistry(){
		registerBlock2();
	}
	
	/**
	 * Add new blocks here
	 * format:public static Block 'newblockname';
	 */
	//Obsidian furnace active and idle blocks
	public static Block ObsidianFurnaceIdle;
	public static Block ObsidianFurnaceActive;
	//Diamond furnace active and idle blocks
	public static Block DiamondFurnaceIdle;
	public static Block DiamondFurnaceActive;
	//Iron furnace active and idle states
	public static Block IronFurnaceIdle;
	public static Block IronFurnaceActive;
	//Gold furnace active and idle states
	public static Block GoldFurnaceIdle;
	public static Block GoldFurnaceActive;
	
	//Initialize new block and include settings
	public static void initializeBlock(){
		/**
		 * Initialize the active and idle blocks and set attributes
		 */
		//initialize the Obsidian furnace
		ObsidianFurnaceIdle = new ObsidianFurnace(false).setBlockName("ObsidianFurnaceIdle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoFurnacesMod.MFM);
		ObsidianFurnaceActive = new ObsidianFurnace(true).setBlockName("ObsidianFurnaceActive").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Diamond furnace
		DiamondFurnaceIdle = new DiamondFurnace(false).setBlockName("DiamondFurnaceIdle").setHardness(5.0F).setResistance(50.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoFurnacesMod.MFM);
		DiamondFurnaceActive = new DiamondFurnace(true).setBlockName("DiamondFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
		//initialize the Iron Furnace
		IronFurnaceIdle = new IronFurnace(false).setBlockName("IronFurnaceIdle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.MFM);
		IronFurnaceActive = new IronFurnace(true).setBlockName("IronFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
		//initialize the Gold Furnace
		GoldFurnaceIdle = new GoldFurnace(false).setBlockName("GoldFurnaceIdle").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setCreativeTab(MoFurnacesMod.MFM);
		GoldFurnaceActive = new GoldFurnace(true).setBlockName("GoldFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeIron).setLightLevel(0.625F);
				
	}
	
	//Register new blocks here with game registry
	public static void registerBlock(){
		
		/**
		 * Register the blocks in the game registry
		 * format: GameRegistry.registerBlock('newblockname',"newblockname");
		 */
		//register Obsidian furnace blocks
		GameRegistry.registerBlock(ObsidianFurnaceIdle, "ObsidianFurnaceIdle");
		GameRegistry.registerBlock(ObsidianFurnaceActive, "ObsidianFurnaceActive");
		//register Diamond furnace blocks
		GameRegistry.registerBlock(DiamondFurnaceIdle, "DiamondFurnaceIdle");
		GameRegistry.registerBlock(DiamondFurnaceActive, "DiamondFurnaceActive");
		//register Iron furnace
		GameRegistry.registerBlock(IronFurnaceIdle,  "IronFurnaceIdle");
		GameRegistry.registerBlock(IronFurnaceActive, "IronFurnaceActive");
		//register Gold furnace
		GameRegistry.registerBlock(GoldFurnaceIdle,  "GoldFurnaceIdle");
		GameRegistry.registerBlock(GoldFurnaceActive, "GoldFurnaceActive");
	}
	
	//registry for initialization event
	public static void registerBlock2(){
		
		/**
		 * Register the tile entity and crafting recipe
		 */
		//registry for Obsidian furnace
		GameRegistry.registerTileEntity(TileEntityObsidianFurnace.class, "Obsidian Furnace");
		GameRegistry.addRecipe(new ItemStack(ObsidianFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Blocks.obsidian});
		//register for Diamond furnace
		GameRegistry.registerTileEntity(TileEntityDiamondFurnace.class, "Diamond Furnace");
		GameRegistry.addRecipe(new ItemStack(DiamondFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.diamond});
		//register for Iron furnace
		GameRegistry.registerTileEntity(TileEntityIronFurnace.class, "Iron Furnace");
		GameRegistry.addRecipe(new ItemStack(IronFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.iron_ingot});
		//register for Iron furnace
		GameRegistry.registerTileEntity(TileEntityGoldFurnace.class, "Gold Furnace");
		GameRegistry.addRecipe(new ItemStack(GoldFurnaceIdle), new Object[]{"OOO", "O O", "OOO", 'O', Items.gold_ingot});
	}


}
