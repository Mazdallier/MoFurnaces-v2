package io.github.mattkx4.morefurnaces.main;

import java.io.BufferedReader;
import java.io.FileReader;
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

		try {

		} catch (Exception e) {
			// found an error
		}
	}

	/*
	 * @SubscribeEvent public void checkUpdate(PlayerEvent.PlayerLoggedInEvent
	 * event) throws Exception { FileReader file = new FileReader("");
	 * 
	 * @SuppressWarnings("resource") BufferedReader reader = new
	 * BufferedReader(file); String text = ""; String line = reader.readLine();
	 * 
	 * while(line != null) { text += line; line = reader.readLine(); }
	 * 
	 * // System.out.println(text); if(text.matches("true")) { run(); } else
	 * if(text.matches("false")) {
	 * Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new
	 * ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" +
	 * EnumChatFormatting.RESET + "] "+
	 * "Halloween Notifier has been disabled.")); return; } else {
	 * Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new
	 * ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" +
	 * EnumChatFormatting.RESET + "] "+
	 * "Incorrect value in HalloweenNotifierSettings.txt. HalloweenNotifier cancelled."
	 * )); return; } }
	 */

	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event)
			throws Exception {
		run();
	}

	public void run() {
		ArrayList<String> data = new ArrayList<String>();

		try {
			URL accessedFile = new URL(
					"https://raw.githubusercontent.com/Mattkx4/MoFurnaces-v2/master/update/halloween%20update.txt");
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(accessedFile.openStream()));
			for (int line = 0; line < 2; line++) {
				data.add(fileReader.readLine());
			}
		} catch (Exception e) {
			return;
		}

		this.message = data.get(1);
		this.bool = data.get(0);

		if (this.bool.matches("true")) {
			MoFurnacesMod.isHalloween = true;
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(
					new ChatComponentText("[" + EnumChatFormatting.BLUE
							+ "MoFurnacesMod" + EnumChatFormatting.RESET + "] "
							+ this.message));
		} else if (this.bool.matches("false")) {
			MoFurnacesMod.isHalloween = false;
		} else {
			System.out.println("[MoFurnacesMod] Um, something went wrong.");
		}
	}
}
