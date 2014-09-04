package io.github.mattkx4.morefurnaces.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCobaltKatana extends EntityMob{

	public EntityCobaltKatana(World world){
		super(world);
		//add entity ai's here
		
		//add targeting here
	}
	
	protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        //set max health to 2500 hearts //set lower for testing
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        //set movement speed to 1.0 presumed to be regular walking speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
        //set base attack damage to 7.5 hearts
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0D);
        //set the resistance to knockback effect
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
    }
	
	public boolean isAIEnabled(){
		return true;
	}

	//set here what items to drop
	protected Item getItemDrop(){
		return null;
		
	}
	
	//add wearable armor and weapons
	protected void addRandomArmor(){
		super.addRandomArmor();
		this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));

	}
	
}
