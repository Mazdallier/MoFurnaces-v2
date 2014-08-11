package io.github.mattkx4.morefurnaces.handler;

import cpw.mods.fml.common.registry.GameRegistry;

public class MFMHandler {

	/**
	 * Initialize and register items within the pre-initialization event
	 */
	public static void mainRegistry(){
		initializeHandler();
		registerHandler();
	}

	/**
	 * initialize and register items within the initialization event
	 */
	public static void secondaryRegistry(){
		registerhandler2();
	}
	
	/**
	 * No use ATM
	 */
	private static void initializeHandler() {
		// TODO Auto-generated method stub		
	}

	/**
	 * No use ATM
	 */
	private static void registerHandler() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Secondary Handler Registry
	 */
	private static void registerhandler2() {
		// Registers the fuel handler for possible future fuels
		GameRegistry.registerFuelHandler(new MFMFuelHandler());	
	}
	
}
