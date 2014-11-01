package io.github.mattkx4.morefurnaces.main;

import io.github.mattkx4.morefurnaces.lib.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class MFMUpdateNotifier{

	//create a boolean flag to check if the mod is up to date
	public static boolean outOfDate = false;
	//create a string for the latest version and the download link
	public String newestVersion = "";
	public String downloadLink = "";
	EntityPlayer player;
	
	//this constructor is not really needed, but I'm keeping it in case deleting it has unintended consequences
	public MFMUpdateNotifier(){
		
		try{

		}catch (Exception e){
			//found an error
		}
	}
	
	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event) throws Exception {
		FileReader file = new FileReader("");
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(file);
		String text = "";
		String line = reader.readLine();
		
		while(line != null) {
			text += line;
			line = reader.readLine();
		}
		
		// System.out.println(text);
		if(text.matches("true")) {
			run();	
		} else if(text.matches("false")){
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] "+ "Update Notifier has been disabled."));
			return;
		} else {
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] "+ "Incorrect value in UpdateNotifierSettings.txt. UpdateNotifier cancelled."));
			return;
		}
	}
	
	public void run(){
		ArrayList<String> data = new ArrayList<String>();
		//Throw this for me
		try{
			URL accessedFile = new URL("https://raw.githubusercontent.com/Mattkx4/MoFurnaces-v2/master/update/update.txt.txt");
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(accessedFile.openStream()));
			for(int line = 0 ; line < 2 ; line++){
				data.add(fileReader.readLine());
			}
		//Here catch
		}catch(Exception e){
			//Ooh too bad
		}
		
		//Set link on his quest
		this.downloadLink = data.get(1);
		//Get all the versions
		String[] currentVersion = Strings.version.split("[.]");
		String[] latestVersion = data.get(0).split("[.]");
		//local boolean to tell if the mod is out of date
		//repeat for all the array locations
		for(int arrayLocation = 0; arrayLocation < 3; arrayLocation++){
			//Compare the Major versions, the first number.
			if(Integer.valueOf(currentVersion[arrayLocation]) < Integer.valueOf(latestVersion[arrayLocation])){
				//If the local version is not the same.
				this.newestVersion = data.get(0);
				this.outOfDate = true;
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.DARK_PURPLE + "A new update is available, Version: " + EnumChatFormatting.GREEN + newestVersion));
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.DARK_PURPLE + "Download here:"));
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(downloadLink));
				//outOfDate = true;
			}else if(Integer.valueOf(currentVersion[arrayLocation]) > Integer.valueOf(latestVersion[arrayLocation])){
				//Do nothing
				return;
			}
		}
		//called if the mod is up to date
		if(!this.outOfDate){
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[" + EnumChatFormatting.BLUE + "MoFurnacesMod" + EnumChatFormatting.RESET + "] " + EnumChatFormatting.DARK_PURPLE + "Mod is up to date."));

		}
	}
}
