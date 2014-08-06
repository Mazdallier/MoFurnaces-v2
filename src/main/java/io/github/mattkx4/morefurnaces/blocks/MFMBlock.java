package io.github.mattkx4.morefurnaces.blocks;

import io.github.mattkx4.morefurnaces.main.MoreFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityDiamondFurnace;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityObsidianFurnace;
import net.minecraft.block.Block;
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
	
	//Initialize new block and include settings
	public static void initializeBlock(){
		/**
		 * Initialize the active and idle blocks and set attributes
		 */
		//initialize the Obsidian furnace
		ObsidianFurnaceIdle = new ObsidianFurnace(false).setBlockName("ObsidianFurnaceIdle").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setCreativeTab(MoreFurnacesMod.MFM);
		ObsidianFurnaceActive = new ObsidianFurnace(true).setBlockName("ObsidianFurnaceActive").setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypeObsidian).setLightLevel(0.625F);
		//initialize the Diamond furnace
		DiamondFurnaceIdle = new DiamondFurnace(false).setBlockName("DiamondFurnaceIdle").setHardness(5.0F).setResistance(50.0F).setStepSound(soundTypeDiamond).setCreativeTab(MoreFurnacesMod.MFM);
		DiamondFurnaceActive = new DiamondFurnace(true).setBlockName("DiamondFurnaceActive").setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeDiamond).setLightLevel(0.625F);
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
		
	}


}
