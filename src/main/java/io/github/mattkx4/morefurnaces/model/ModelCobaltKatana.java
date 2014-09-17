package io.github.mattkx4.morefurnaces.model;

import io.github.mattkx4.morefurnaces.entity.EntityCobaltKatana;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;

public class ModelCobaltKatana extends ModelBase{
	//fields
	ModelRenderer head;
	ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer emeraldfurnace;
    ModelRenderer strapback2;
    ModelRenderer strapback1;
    ModelRenderer strapfront1;
    ModelRenderer strapfront2;
    ModelRenderer strapfronthorizontal;
    
    /**
     * Records whether the model should be rendered holding an item in the right hand, and if that item is a block.
     */
    public int heldItemRight;
  
    public ModelCobaltKatana(){
    	textureWidth = 64;
    	textureHeight = 64;
    
    	head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
    	setRotation(head, 0F, 0F, 0F);
    	body = new ModelRenderer(this, 16, 16);
    	body.addBox(-4F, 0F, -2F, 8, 12, 4);
    	body.setRotationPoint(0F, 0F, 0F);
    	body.setTextureSize(64, 64);
    	body.mirror = true;
    	setRotation(body, 0F, 0F, 0F);
	    rightarm = new ModelRenderer(this, 40, 16);
	    rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
	    rightarm.setRotationPoint(-5F, 2F, 0F);
	    rightarm.setTextureSize(64, 64);
	    rightarm.mirror = true;
	    setRotation(rightarm, 0F, 0F, 0F);
	    leftarm = new ModelRenderer(this, 40, 16);
	    leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
	    leftarm.setRotationPoint(5F, 2F, 0F);
	    leftarm.setTextureSize(64, 64);
	    leftarm.mirror = true;
	    setRotation(leftarm, 0F, 0F, 0F);
	    rightleg = new ModelRenderer(this, 0, 16);
	    rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	    rightleg.setRotationPoint(-2F, 12F, 0F);
	    rightleg.setTextureSize(64, 64);
	    rightleg.mirror = true;
	    setRotation(rightleg, 0F, 0F, 0F);
	    leftleg = new ModelRenderer(this, 0, 16);
	    leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	    leftleg.setRotationPoint(2F, 12F, 0F);
	    leftleg.setTextureSize(64, 64);
	    leftleg.mirror = true;
	    setRotation(leftleg, 0F, 0F, 0F);
    	head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.mirror = true;
        leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        rightleg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.mirror = true;
        leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        leftleg.setRotationPoint(1.9F, 12.0F, 0.0F);
	    emeraldfurnace = new ModelRenderer(this, 0, 32);
	    emeraldfurnace.addBox(-4F, 2F, 2F, 8, 8, 4);
	    emeraldfurnace.setRotationPoint(0F, 0F, 0F);
	    emeraldfurnace.setTextureSize(64, 64);
	    emeraldfurnace.mirror = true;
	    setRotation(emeraldfurnace, 0F, 0F, 0F);
	    strapback2 = new ModelRenderer(this, 24, 32);
	    strapback2.addBox(-3F, 0F, 2F, 1, 2, 1);
	    strapback2.setRotationPoint(0F, 0F, 0F);
	    strapback2.setTextureSize(64, 64);
	    strapback2.mirror = true;
	    setRotation(strapback2, 0F, 0F, 0F);
	    strapback1 = new ModelRenderer(this, 28, 32);
	    strapback1.addBox(2F, 0F, 2F, 1, 2, 1);
	    strapback1.setRotationPoint(0F, 0F, 0F);
	    strapback1.setTextureSize(64, 64);
	    strapback1.mirror = true;
	    setRotation(strapback1, 0F, 0F, 0F);
	    strapfront1 = new ModelRenderer(this, 28, 32);
	    strapfront1.addBox(2F, 0F, -3F, 1, 7, 1);
	    strapfront1.setRotationPoint(0F, 0F, 0F);
	    strapfront1.setTextureSize(64, 64);
	    strapfront1.mirror = true;
	    setRotation(strapfront1, 0F, 0F, 0F);
	    strapfront2 = new ModelRenderer(this, 28, 32);
	    strapfront2.addBox(-3F, 0F, -3F, 1, 7, 1);
	    strapfront2.setRotationPoint(0F, 0F, 0F);
	    strapfront2.setTextureSize(64, 64);
	    strapfront2.mirror = true;
	    setRotation(strapfront2, 0F, 0F, 0F);
	    strapfronthorizontal = new ModelRenderer(this, 32, 42);
	    strapfronthorizontal.addBox(-4F, 7F, -3F, 8, 1, 1);
	    strapfronthorizontal.setRotationPoint(0F, 0F, 0F);
	    strapfronthorizontal.setTextureSize(64, 64);
	    strapfronthorizontal.mirror = true;
	    setRotation(strapfronthorizontal, 0F, 0F, 0F);
    }
  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    rightarm.render(f5);
	    leftarm.render(f5);
	    rightleg.render(f5);
	    leftleg.render(f5);
	    emeraldfurnace.render(f5);
	    strapback2.render(f5);
	    strapback1.render(f5);
	    strapfront1.render(f5);
	    strapfront2.render(f5);
	    strapfronthorizontal.render(f5);
	}
  
	public void renderModel(float f){
	  	head.render(f);
	    body.render(f);
	    rightarm.render(f);
	    leftarm.render(f);
	    rightleg.render(f);
	    leftleg.render(f);
	    emeraldfurnace.render(f);
	    strapback2.render(f);
	    strapback1.render(f);
	    strapfront1.render(f);
	    strapfront2.render(f);
	    strapfronthorizontal.render(f);
	}
  
	private void setRotation(ModelRenderer model, float x, float y, float z){
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	}
  
	
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		if (this.heldItemRight != 0)
        {
            this.rightarm.rotateAngleX = this.rightarm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }
        
		this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
        this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
	}
		
	protected void renderEquippedItems(EntityLiving entity, float f)
    {
        this.renderEquippedItems((EntityCobaltKatana)entity, f);
    }
	
}
