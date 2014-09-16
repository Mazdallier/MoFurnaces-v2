package io.github.mattkx4.morefurnaces.entity;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EntityCobaltKatana extends EntityMob{
	
	/** Above zero if this Entity is Angry. */
    private int angerLevel;
    /** A random delay until this PigZombie next makes a sound. */
    private int randomSoundDelay;
    private Entity field_110191_bu;
    private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).setSaved(false);

	public EntityCobaltKatana(World world){
		super(world);
		//add entity ai's here
        this.isImmuneToFire = true;
        //set the entity to swim
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityCreeper.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityZombie.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntitySkeleton.class, 1.0D, true));
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, EntitySpider.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityWitch.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWitch.class, 0, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));


		//add targeting here
	}
	
	protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        //set the follow range of the entity. Set to 40 blocks
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        //set max health to 2500 hearts //set lower for testing
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        //set movement speed to 1.0 presumed to be regular walking speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        //set base attack damage to 7.5 hearts
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0D);
        //set the resistance to knockback effect
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3D);
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
    
    public boolean allowLeashing()
    {
        return false;
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();
        boolean flag = itemstack != null && itemstack.getItem() == Items.spawn_egg;

        if (!flag && this.isEntityAlive() && !this.isChild() && !player.isSneaking())
        {
            if (!this.worldObj.isRemote)
            {
            	//set the activation of the emerald furnace in here
            }

            return true;
        }
        else
        {
            return super.interact(player);
        }
    }
    
    
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setShort("Anger", (short)this.angerLevel);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.angerLevel = nbt.getShort("Anger");
    }
    
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }
    
    /**
     * Causes this CK to become angry at the supplied Entity (which will be a player).
     */
    private void becomeAngryAt(Entity entity)
    {
        this.entityToAttack = entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);
    }
	
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damage, float f1)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = damage.getEntity();

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof EntityCobaltKatana)
                    {
                        EntityCobaltKatana entitycobaltkatana = (EntityCobaltKatana)entity1;
                        entitycobaltkatana.becomeAngryAt(entity);
                    }
                }

                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(damage, f1);
        }
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            iattributeinstance.removeModifier(field_110190_br);

            if (this.entityToAttack != null)
            {
                iattributeinstance.applyModifier(field_110190_br);
            }
        }

        this.field_110191_bu = this.entityToAttack;

        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
        {
            this.playSound("mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.onUpdate();
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
		this.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
	}
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
		Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
        float f = this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        this.addRandomArmor();
        this.enchantEquipment();

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }


        return (IEntityLivingData)p_110161_1_1;
		
		
    }
	
	
}
