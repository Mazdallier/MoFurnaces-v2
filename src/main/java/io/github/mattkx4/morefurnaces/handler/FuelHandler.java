package io.github.mattkx4.morefurnaces.handler;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	//This particular class will probably NEVER be utilized and may end up being removed
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		
		//format if(fuel.getItem() == MFMItem.item'itemname') return fuel value
		
		return 0;
	}

}
