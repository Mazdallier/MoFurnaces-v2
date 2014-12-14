package com.weebly.mattkx4.morefurnaces.items.upgrades;

import java.util.List;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class UpgradeBrightness extends Item {
	
	// The number of blocks in any direction that the effect will occur in
	static double burnRadius = 8;
	static int lightRadius = 4; 
	
	public UpgradeBrightness() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeBrightness");
		setTextureName(Strings.MODID + ":UpgradeBrightness");

	}

	/**
	 * Custom method that will burn the mob within the area without having to add code to
	 * every single block class
	 * 
	 * @param worldObj : The world that this sits in
	 * @param xCoord : The x position of the tile entity
	 * @param yCoord : The y position of the tile entity
	 * @param zCoord : the z position of the tile entity
	 * @param burning : boolean check to determine of the furnace is running or not
	 */
	public static void Active(World worldObj, int xCoord, int yCoord,
			int zCoord, boolean burning) {

		// Create the boundary for the effect
		AxisAlignedBB boundary = AxisAlignedBB.getBoundingBox(xCoord - burnRadius,
		yCoord - burnRadius, zCoord - burnRadius, xCoord + burnRadius, yCoord + burnRadius, zCoord + 8);
		
		// Create a list of entities with all the EntityMob's within the boundary
		List entityList = worldObj.getEntitiesWithinAABB(EntityMob.class,
				boundary);
		
		// Check to see if the furnace is still burning
		if(burning == true) {
			// Set the light level of the block to maximum
			worldObj.getBlock(xCoord, yCoord, zCoord).setLightLevel(1);
			
			// Cycle through all of the mobs within the entityList
			for (int i = 0; i < entityList.size(); i++) {
				// Assign each mob
				EntityMob entity = (EntityMob) entityList.get(i);
				// Set the mob on fire for 10 seconds
				entity.setFire(10);
			}
		}		
	}

	/**
	 * This method is used to deactivate the effects of activating the effects of the brightness upgrade
	 * 
	 * @param worldObj : The world that this sits in
	 * @param xCoord : The x position of the tile entity
	 * @param yCoord : The y position of the tile entity
	 * @param zCoord : the z position of the tile entity
	 * @param burning 
	 */
	public static void Inactive(World worldObj, int xCoord, int yCoord,
			int zCoord, boolean burning) {
		// Get the block at the tile entity
		Block furnace = worldObj.getBlock(xCoord, yCoord, zCoord);

		// Check if the furnace is burning or not
		if (burning == true) {
			furnace.setLightLevel(0.625F);	// Set the light level to default

		} else if (burning == false) {
			furnace.setLightLevel(0);	// Set light level to zero
		}
		
		
	}
}
