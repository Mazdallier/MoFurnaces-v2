package io.github.mattkx4.morefurnaces.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCobaltKatana extends EntityMob{

	public EntityCobaltKatana(World world){
		super(world);
		//add entity ai's here
        this.isImmuneToFire = true;

        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));


		
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
	
    protected boolean canDespawn()
    {
        return false;
    }
	
	/**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
	public void onLivingUpdate()
    {
        this.updateArmSwingProgress();
        float f = this.getBrightness(1.0F);

        if (f > 0.5F)
        {
            this.entityAge += 2;
        }

        super.onLivingUpdate();
    }
	
	//mob sounds
	protected String getSwimSound()
    {
        return "game.hostile.swim";
    }

    protected String getSplashSound()
    {
        return "game.hostile.swim.splash";
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "game.hostile.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "game.hostile.die";
    }
	
	//add wearable armor and weapons
	protected void addRandomArmor(){
		super.addRandomArmor();
		this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));

	}
	
}
