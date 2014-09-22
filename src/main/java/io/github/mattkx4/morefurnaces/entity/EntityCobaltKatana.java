package io.github.mattkx4.morefurnaces.entity;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import io.github.mattkx4.morefurnaces.blocks.IronFurnace;
import io.github.mattkx4.morefurnaces.main.MoFurnacesMod;
import io.github.mattkx4.morefurnaces.tileentity.TileEntityIronFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCobaltKatana extends EntityMob{

	//an integer for the number of attempts
    int attempts;
    String text;
    //flag to check if this mob has greeted this player
    boolean greeted;
    //get the number of attempts to add to the total number of attempts
    int newAttempts;
    //new itemstack for the currently held item
    ItemStack heldItem;
    //new itemstack for the result of cooking the held item
    ItemStack cookingResult;
	
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
        this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCreeper.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityZombie.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySkeleton.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySpider.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityWitch.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
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
    
    /**
     * quick check to see if the item that will be right-clicked onto CK Mob has a cooking result
     * @param item
     * @param slot
     * @return
     */
    public boolean canSmelt(ItemStack item, int slot){
    	if(item == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(item);
		
			if(itemstack == null) return false;
		}
    	
    	return true;
    }
    /**
     * This is called to display a conversational message for the player
     * 
     * @param branch : Which piece of text should the player receive
     * @param player : The player entity
     */
    public void chatTree(int branch, EntityPlayer player){
    	switch(branch){
    	//greet the player and set the greeting as true
    	case 1:{
            player.addChatMessage(new ChatComponentText("Hello "+player.getDisplayName()+" it's nice to finnaly meet you."));
            greeted = true;
            return;
    	}
        //notify the player of the number of attempts added
    	case 2:{
            player.addChatMessage(new ChatComponentText("You may use my furnace another "+newAttempts+" times."));
            return;
    	}
        //notify the player that they have nothing in hand
    	case 3:{
            player.addChatMessage(new ChatComponentText("I'm sorry "+player.getDisplayName()+" you have nothing to cook."));
            return;
    	}
        //notify the player of what they will smelt and what they will receive
    	case 4:{
    		player.addChatMessage(new ChatComponentText("I will turn " + heldItem.stackSize + " of your " + heldItem.getDisplayName() + " into " + cookingResult.getDisplayName() + "."));
    		return;
    	}
		//notify the player of how many attempts they have left
    	case 5:{
    		if(attempts == 0){
                player.addChatMessage(new ChatComponentText("You have no more attempts for cooking, " + player.getDisplayName() + "."));
                return;
    		}else if(attempts == 1){
                player.addChatMessage(new ChatComponentText(player.getDisplayName() + ", I can cook " + attempts + " more stack of items for you."));
                return;
    		}else{
    			player.addChatMessage(new ChatComponentText(player.getDisplayName() + ", I can cook " + attempts + " more stacks of items for you."));
    			return;
    		}
    	}
		//notify the player that the item cannot be smelted/cooked
    	case 6:{
            player.addChatMessage(new ChatComponentText("I'm sorry " + player.getDisplayName() + " I can't cook that for you."));
            return;
    	}
    	default:{
    		return;
    	}
    	}
    }
        
    @Override
    @SideOnly(Side.CLIENT)
    public boolean interact(EntityPlayer player)
    {    	
    	if(!this.worldObj.isRemote){
    		//make sure that the mob is not angry
	    	if(angerLevel == 0){
		    	//get the item currently being held
	    		heldItem = player.inventory.getCurrentItem();
		    	//set the current slot ID
		    	int slot = player.inventory.currentItem;
		        //if the item is a diamond then set the flag to true
		        boolean flag = heldItem != null && (heldItem.getItem() == Items.diamond || heldItem.getItem() == Items.emerald);
		        //create a ready flag for smelting 
		        boolean smeltIt = canSmelt(heldItem, slot);   
		        
		        //if the player has not been greeted, greet them
		        if(greeted == false)chatTree(1,player);
		        
		        //make sure the mob is first ready for smelting
		        if (flag && this.isEntityAlive() && !this.isChild() && !player.isSneaking()){
		        	//play the tamed animation (hearts above the mob)
		        	for (int i = 0; i < 7; ++i){
		                double d0 = this.rand.nextGaussian() * 0.02D;
		                double d1 = this.rand.nextGaussian() * 0.02D;
		                double d2 = this.rand.nextGaussian() * 0.02D;
		                this.worldObj.spawnParticle("flame", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
		            }
		            //remove one item from the stack
		            --heldItem.stackSize;
		            
		            //set the number of attempts to 3
		            if(heldItem.getItem() == Items.diamond)newAttempts = 2;
		            if(heldItem.getItem() == Items.emerald)newAttempts = 5;
		            
		            //add the new attempts to the current counter
		            attempts = attempts + newAttempts;
		            //tell the player how many more attempts they gainer
		            chatTree(2, player);
		            
		            return true;
		        }
		        //smelt the item held immediately
		        //if the mob is ready and there is an item to smelt
		        else if (smeltIt && attempts > 0){
		        	System.out.println("smelted");
		        	//set a new itemstack for the smelting result
		        	cookingResult = FurnaceRecipes.smelting().getSmeltingResult(heldItem);
		        	//set the correct itemstack size
		        	cookingResult.stackSize = heldItem.stackSize;
		        	//delete current itemstack
		        	player.inventory.setInventorySlotContents(slot, null);
		        	//replace the old itemstack with the new one   	
		        	player.inventory.setInventorySlotContents(slot, cookingResult);
		        	//reduce the number of attempts by one
		        	--attempts;
		        	//tell the player what is being cooked into what, and how much
		        	chatTree(4, player);
		        	//tell the player how many attempts they have left
		        	chatTree(5, player);
		        	return true;
		        }else if(heldItem == null){
		        	//tell the player that there is nothing being held
		        	chatTree(3, player);
		        	//tell the player how many attempts they have left
		        	chatTree(5, player);
		        	return true;
		        }else
		        	//tell the player that they can't smelt that item
		        	chatTree(6, player);
		        	//tell the player how many attempts they have left
		        	chatTree(5, player);
		        	
	            return true;
	    	}
    	}
        return true;
    }
    
    
    /**
     * save variables to the nbt
     */
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        //set the anger level of the current mob
        nbt.setShort("Anger", (short)this.angerLevel);
        //set the number of cooking attempts left for the player
        nbt.setShort("attempts", (short)this.attempts);
        //set whether or not this particular mob has been greeted or not
        nbt.setBoolean("greeted", (boolean)this.greeted);
    }

    /**
     * Read variable from the saved nbt
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        //grab the anger level of this mob
        this.angerLevel = nbt.getShort("Anger");
        //grab the number of cooking attempts granted by this mob
        this.attempts = nbt.getShort("attempts");
        //get whether or not this mob has greeted the player
        this.greeted = nbt.getBoolean("greeted");
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
		this.setCurrentItemOrArmor(1, new ItemStack(Items.diamond_helmet));
		this.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_chestplate));
		this.setCurrentItemOrArmor(3, new ItemStack(Items.diamond_leggings));
		this.setCurrentItemOrArmor(4, new ItemStack(Items.diamond_boots));
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
