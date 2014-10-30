package io.github.mattkx4.morefurnaces.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class MFMHalloweenNotifier {
	// Create a string to hold the value
	public String bool = "";
	// Create a string to hold the message
	public String message = "";
	
	public MFMHalloweenNotifier() {
		// Pointless for the time being
	}
	
	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event) {
		run();
	}
	
	public void run() {
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			URL accessedFile = new URL("");
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(accessedFile.openStream()));
			for(int line = 0; line < 2; line++) {
				data.add(fileReader.readLine());
			}
		} catch(Exception e) {
			// Nope
		}
		
		message = data.get(1);
		bool = data.get(0);
		
		if(bool == "true") {
			MoFurnacesMod.isHalloween = true;
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.GOLD + message));
		} else if(bool == "false") {
			MoFurnacesMod.isHalloween = false;
		} else {
			System.out.println("Something went wrong.");
		}
	}
}
