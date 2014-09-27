package io.github.mattkx4.morefurnaces.updater;

import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MFMUpdateNotifier extends Thread{

	//create a boolean flag to check if the mod is up to date
	public boolean outOfDate = false;
	//create a string for the latest version and the download link
	public String newestVersion = "";
	public String downloadLink = "";
	
	
	public MFMUpdateNotifier(){
		
		try{
			System.out.println("Debug got to here 1");

			run();
		}catch (Exception e){
			//found an error
		}
	}
	
	@Override
	public void run(){
		System.out.println("Debug got to here 2");
		ArrayList<String> data = new ArrayList<String>();
		//Throw this for me
		try{
			System.out.println("Debug got to here 3");

			URL accessedFile = new URL("https://raw.githubusercontent.com/Mattkx4/MoFurnaces-v2/11d91f857d92aa7f1fd90c2ed95ece4f3ebcb57b/update/update.txt.txt");
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
		
		//repeat for all the array locations
		for(int arrayLocation = 0; arrayLocation < 3; arrayLocation++){
			System.out.println("This has run for i = "+arrayLocation);
			//Compare the Major versions, the first number.
			if(Integer.valueOf(currentVersion[arrayLocation]) < Integer.valueOf(latestVersion[arrayLocation])){
				//If the local version is not the same.
				this.newestVersion = data.get(0);
				this.outOfDate = true;
				MoFurnacesMod.instance.logInfo("There is a new update available: " + this.newestVersion);
				MoFurnacesMod.instance.updateFound();
			}else if(Integer.valueOf(currentVersion[arrayLocation]) >= Integer.valueOf(latestVersion[arrayLocation])){
				//DO NOTHING, you have the latest update
				MoFurnacesMod.instance.logInfo("There is no new update for MoFurnacesMod, current version:" + Strings.version);
				MoFurnacesMod.instance.versionCurrent();
				System.out.println("version is current");
				return;
			}
		}


		
	}
	
	
}
