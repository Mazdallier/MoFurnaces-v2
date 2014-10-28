package io.github.mattkx4.morefurnaces.lib;

public class FurnaceVariables {
	/*
	 * Tier 1 Furnaces
	 */
	// SPEEDS
	public static final int BRICK_FURNACE_SPEED = 133; // 1.5x Faster or 150% Faster
	public static final int IRON_FURNACE_SPEED = 100; // 2x Faster or 200% Faster
	public static final int GOLD_FURNACE_SPEED = 50; // 4x Faster or 400% Faster
	public static final int NETHERRACK_FURNACE_SPEED = 450; // 0.44X As Fast or 56% Slower
	public static final int QUARTZ_FURNACE_SPEED = 200; // Same Speed
	public static final int DIAMOND_FURNACE_SPEED = 66; // 3.03x Faster or 303% Faster
	public static final int OBSIDIAN_FURNACE_SPEED = 50; // 4x Faster or 400% Faster
	public static final int BONE_FURNACE_SPEED = 175; // 1.14x Faster or 114% Faster
	public static final int REDSTONE_FURNACE_SPEED = 200; // Same Speed
	public static final int ANVIL_FURNACE_SPEED = 250; // 0.8x As Fast or 20% Slower
	public static final int CACTUS_FURNACE_SPEED = 100; // 2x Faster or 200% Faster
	
	/*
	 * EFFICIENCIES
	 * - The efficiencies were calculate synonymously with the furnace speeds and were added
	 * to increase or decrease the total number of ticks that a piece of fuel would burn.
	 * - The baseline that everything was calculated off was the regular furnace and two (2) sticks
	 * - The regular furnace would require 200 ticks to cook any cookable item and two sticks would 
	 * burn for a total of 200 ticks. The efficiency is calculate as 
	 * 		Efficiency = OriginalFurnaceSpeed / PsuedoEfficiency / NewFurnaceSpeed
	 * - What is stated as the efficiency is really the psuedo efficiency used to maintain the 
	 * actual efficiency. The Brick Furnace was desired to be 1.5 times faster and 1.5 times more efficient.
	 * - To do this the speed was improved but the psuedo efficiency was kept at one. This had the effect of 
	 * improving the overall efficiency of the furnace.
	 * 
	 * So to get the actual efficiency of the furnace, follow the above equation
	 */
	public static final double BRICK_FURNACE_EFFICIENCY = 1.0D; // 1.5x More Efficient or 150% Regular Efficiency
	public static final double IRON_FURNACE_EFFICIENCY = 2.0D; // Same efficiency as the regular furnace
	public static final double GOLD_FURNACE_EFFICIENCY = 8.0D; // 0.5 Times as efficient as the regular furnace or 50% less efficient
	public static final double NETHERRACK_FURNACE_EFFICIENCY = 0.0D; // one infinite times more efficient, or two infinities if you want to be picky
	public static final double QUARTZ_FURNACE_EFFICIENCY = 0.5D; // 2x More Efficient or 200% As Efficient
	public static final double DIAMOND_FURNACE_EFFICIENCY = 3.0D; // As efficient as a regular furnace
	public static final double OBSIDIAN_FURNACE_EFFICIENCY = 8.0D; // 0.5 Times as efficient as the regular furnace or 50% less efficient
	public static final double BONE_FURNACE_EFFICIENCY = 2.0D; // 0.57 times as efficient as a regular furnace or 43% less efficient
	public static final double REDSTONE_FURNACE_EFFICIENCY = 1.0D; // Same Efficiency
	public static final double ANVIL_FURNACE_EFFICIENCY = 1.0D; // 0.8x as efficient or 20% less efficient as a regular furnace
	public static final double CACTUS_FURNACE_EFFICIENCY = 2.0D; // Same Efficiency
	
	// "DAMAGE FIXED" (For AnvilFurnace)
	public static final int ANVIL_FURNACE_DAMAGE_FIXED = 25; // This needs no description...
	
	/*
	 * Tier 2 Furnaces
	 */
	// SPEEDS
	public static final int BRICK_FURNACE_T2_SPEED = 133;
	public static final int IRON_FURNACE_T2_SPEED = 100;
	public static final int QUARTZ_FURNACE_T2_SPEED = 200;
	public static final int DIAMOND_FURNACE_T2_SPEED = 66;
	public static final int OBSIDIAN_FURNACE_T2_SPEED = 50;
	
	// EFFICIENCIES
	public static final double BRICK_FURNACE_T2_EFFICIENCY = 1.0D;
	public static final double IRON_FURNACE_T2_EFFICIENCY = 2.0D;
	public static final double QUARTZ_FURNACE_T2_EFFICIENCY = 0.5D;
	public static final double DIAMOND_FURNACE_T2_EFFICIENCY = 3.0D;
	public static final double OBSIDIAN_FURNACE_T2_EFFICIENCY = 8.0D;
	
	/*
	 * Teir 3 Furnaces
	 */
	// SPEEDS
	public static final int BRICK_FURNACE_T3_SPEED = 133;
	public static final int IRON_FURNACE_T3_SPEED = 100;
	public static final int QUARTZ_FURNACE_T3_SPEED = 200;
	public static final int DIAMOND_FURNACE_T3_SPEED = 66;
	public static final int OBSIDIAN_FURNACE_T3_SPEED = 50;
	
	// EFFICIENCIES
	public static final double BRICK_FURNACE_T3_EFFICIENCY = 1.0D;
	public static final double IRON_FURNACE_T3_EFFICIENCY = 2.0D;
	public static final double QUARTZ_FURNACE_T3_EFFICIENCY = 0.5D;
	public static final double DIAMOND_FURNACE_T3_EFFICIENCY = 3.0D;
	public static final double OBSIDIAN_FURNACE_T3_EFFICIENCY = 8.0D;
}
