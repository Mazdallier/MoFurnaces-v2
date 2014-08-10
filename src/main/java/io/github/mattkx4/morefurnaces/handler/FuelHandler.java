package io.github.mattkx4.morefurnaces.handler;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	/*
	 * This particular class will probably NEVER be utilized and may end up being removed.
	 * This entire class is for the handling of Minecraft fuels which this Mod may never
	 * add as a new item or feature.
	 */
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		//format if(fuel.getItem() == MFMItem.item'itemname') return fuel value
		return 0;
	}

}
