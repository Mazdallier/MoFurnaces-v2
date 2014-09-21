package io.github.mattkx4.morefurnaces.achievements;

import io.github.mattkx4.morefurnaces.blocks.MFMBlocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MFMAchievements {
	//public static Achievement gettingAnUpgradeII;
	
	public static void mainRegistry() {
		initializeAchivement();
		registerAchivement();
	}
	
	public static void initializeAchivement() {
		//gettingAnUpgradeII = new Achievement("achievement.gettingAnUpgradeII", "gettingAnUpgradeII", 5, 5, MFMBlocks.BrickFurnaceIdle, AchievementList.buildFurnace).registerStat();
	}
	
	public static void registerAchivement() {
	}
}
