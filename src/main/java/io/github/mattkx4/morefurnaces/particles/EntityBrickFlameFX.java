package io.github.mattkx4.morefurnaces.particles;

import io.github.mattkx4.morefurnaces.lib.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrickFlameFX extends EntityFlameFX{

	public static final ResourceLocation flame = new ResourceLocation(Strings.MODID + ":textures/particles/custom_flames.png");

	
    /** the scale of the flame FX */
    private float flameScale;
    private static final String __OBFID = "CL_00000907";

    public EntityBrickFlameFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_)
    {
        super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
        this.motionX = this.motionX * 0.009999999776482582D + p_i1209_8_;
        this.motionY = this.motionY * 0.009999999776482582D + p_i1209_10_;
        this.motionZ = this.motionZ * 0.009999999776482582D + p_i1209_12_;
        double d6 = p_i1209_2_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = p_i1209_4_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = p_i1209_6_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.flameScale = this.particleScale;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(6);
    }

    public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7){
    	tessellator.draw();
    	Minecraft.getMinecraft().getTextureManager().bindTexture(flame);
    	tessellator.startDrawingQuads();
    	tessellator.setBrightness(200);//make sure you have this!!
    	super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
    	tessellator.draw();
    	Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
    	tessellator.startDrawingQuads();

    }

    public int getBrightnessForRender(float p_70070_1_)
    {
        float f1 = ((float)this.particleAge + p_70070_1_) / (float)this.particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender(p_70070_1_);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f1 * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        float f1 = ((float)this.particleAge + p_70013_1_) / (float)this.particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        float f2 = super.getBrightness(p_70013_1_);
        return f2 * f1 + (1.0F - f1);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
	

}
