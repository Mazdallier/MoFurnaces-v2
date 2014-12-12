package com.weebly.mattkx4.morefurnaces.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.weebly.mattkx4.morefurnaces.lib.Strings;
import com.weebly.mattkx4.morefurnaces.main.MoFurnacesMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class FurnaceBook extends Item {
	
	public static final ResourceLocation bground = new ResourceLocation(
			Strings.MODID + ":textures/gui/custom_furnace.png");
	/** The width of the screen object. */
    public int width;
    /** The height of the screen object. */
    public int height;
	
	
	public FurnaceBook() {
		maxStackSize = 1;
		setCreativeTab(MoFurnacesMod.MFM);
		setUnlocalizedName("FurnaceBook");
		setTextureName(Strings.MODID + ":furnaceBook");	
	}
	
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
    		int x = (int) entityPlayer.posX;
    		int y = (int) entityPlayer.posY;
    		int z = (int) entityPlayer.posZ;
    		entityPlayer.openGui(Strings.MODID, MoFurnacesMod.guiIDFurnaceBook, world, x, y, z);
    				
        return itemStack;
    }
	
}
