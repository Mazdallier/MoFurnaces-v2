package io.github.mattkx4.morefurnaces.handler;

import cpw.mods.fml.common.registry.GameRegistry;

public class MFMHandler {

	//register attached to the main class
	//initialize and register items within the pre-initialization event
	public static void mainRegistry(){
		initializeHandler();
		registerHandler();
	}

	//initialize and register items within the initialization event
	public static void secondaryRegistry(){
		registerhandler2();
	}
	
	private static void initializeHandler() {
		// TODO Auto-generated method stub
		
	}

	private static void registerHandler() {
		// TODO Auto-generated method stub
		
	}

	private static void registerhandler2() {
		//register the fuel handler for any possible future fuels
		GameRegistry.registerFuelHandler(new FuelHandler());
		
	}
	
}
