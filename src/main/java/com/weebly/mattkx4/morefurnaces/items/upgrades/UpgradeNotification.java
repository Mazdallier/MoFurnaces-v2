package com.weebly.mattkx4.morefurnaces.items.upgrades;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class UpgradeNotification extends Item {
	public UpgradeNotification() {
		maxStackSize = 16;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("UpgradeNotification");
		setTextureName(Strings.MODID + ":UpgradeNotification");
	}

	
	
	public static void notify(int xCoord, int yCoord, int zCoord,
			int messageId, String inventoryName) {
		
		inventoryName = I18n.format(inventoryName, new Object[0]);
		
		String message = "";
		
		switch(messageId){
			case 1:
				message = " is out of fuel! Please give the furnace more fuel to continue smelting!";	// Furnace out of fuel message
				break;
			case 2:
				message = " is done smelting! Go check out your new items!";	// No items left to smelt message
				break;
			case 3:
				message = " has a full output slot! Please go clear the output slot to continue smelting!";	// Full output slot message
				break;
			default:
				break;
		}
				
		
		Minecraft.getMinecraft().ingameGUI.getChatGUI()
		.printChatMessage(
				new ChatComponentText("["
						+ EnumChatFormatting.BLUE
						+ Strings.name
						+ EnumChatFormatting.RESET + "] "
						+ EnumChatFormatting.GOLD
						+ inventoryName
						+ EnumChatFormatting.RESET
						+ " at ("
						+ EnumChatFormatting.AQUA
						+ xCoord
						+ EnumChatFormatting.RESET + ", "
						+ EnumChatFormatting.AQUA
						+ yCoord
						+ EnumChatFormatting.RESET + ", "
						+ EnumChatFormatting.AQUA
						+ zCoord
						+ EnumChatFormatting.RESET + ") "
						+ message));
		
	}
}
