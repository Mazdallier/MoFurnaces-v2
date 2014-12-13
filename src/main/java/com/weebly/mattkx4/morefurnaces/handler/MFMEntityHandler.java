package com.weebly.mattkx4.morefurnaces.handler;

import java.util.Random;

import com.weebly.mattkx4.morefurnaces.entity.EntityCobaltKatana;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

public class MFMEntityHandler {

	public static void mainRegistry() {
		registerMonsters(EntityCobaltKatana.class, "Cobalt Katana");
	}

	public static void registerMonsters(Class entityClass, String name) {
		// get a unique mob ID
		// int entityID = EntityRegistry.findGlobalUniqueEntityId();
		/*
		 * The above line does not seem to be grabbing an actual unique ID. Thus
		 * I am setting the ID to an insanely high number
		 */
		int entityID = 1024;
		// create mob seed
		long x = name.hashCode();
		// create a random number from the seed of x
		Random random = new Random(x);
		// set a primary and secondary colour based off the random
		// of the seed.
		int mainColour = random.nextInt() * 16777215;
		int subColour = random.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		// spawn CK into the world
		if (MoFurnacesMod.spawnCK) {
			EntityRegistry.addSpawn(entityClass, 1, 1, 1,
					EnumCreatureType.creature, BiomeGenBase.desert,
					BiomeGenBase.extremeHills, BiomeGenBase.roofedForest);
		}
		EntityRegistry.registerModEntity(entityClass, name, entityID,
				MoFurnacesMod.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID),
				new EntityList.EntityEggInfo(entityID, mainColour, subColour));
	}
}
