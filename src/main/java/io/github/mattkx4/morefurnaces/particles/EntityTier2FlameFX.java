package io.github.mattkx4.morefurnaces.particles;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.mattkx4.morefurnaces.lib.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import static org.lwjgl.opengl.GL11.*;


/**
 * Attempt at creating custom particles, it's bloody difficult
 * Dropping this part for now due to difficulty, will work on 'wacky' furnaces
 * 
 *  Problems:
 *  - Does not seem to want to render,
 *  	- the particle is called (currently commented out) in the T2 furnace block
 *  	and simply does not and to render
 */


/*public class EntityTier2FlameFX extends EntityFX{	

	// This attempt does not seem to work.
	  
	public ResourceLocation flame = new ResourceLocation(Strings.MODID + ":tier2_flame2.png");;
	
	//imitate what a regular furnace flame does
	public EntityTier2FlameFX(World world, double x, double y, double z, double i, double j, double k) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		setScale(200);
		setMaxAge(1000);
		setGravity(0);
	}
	
	//binds the texture and renders it
	@Override
	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator tessellator, float partialTicks, float i, float j, float k, float l, float m){
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(flame);

		
		glDepthMask(false);
		glEnableClientState(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		
		tessellator.startDrawingQuads();
	

		tessellator.setBrightness(200);
		tessellator.setColorOpaque_F(0.1F, 0.1F, 0.1F);
		float scale = 0.1F * particleScale;
		float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float)(prevPosX + (posZ - prevPosZ) * partialTicks - interpPosZ);

		tessellator.addVertexWithUV(x - i * scale - l * scale, y - j * scale, z - k * scale - m * scale, 0, 0);
		tessellator.addVertexWithUV(x - i * scale + l * scale, y - j * scale, z + k * scale - m * scale, 0.1, 0);
		tessellator.addVertexWithUV(x + i * scale + l * scale, y + j * scale, z + k * scale - m * scale, 0.1, 0.1);
		tessellator.addVertexWithUV(x + i * scale - l * scale, y + j * scale, z - k * scale - m * scale, 0, 0.1);
		
		tessellator.draw();
		
		glDisableClientState(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);	
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		
	}
	
	public int getFXLayer(){
		return 3;
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();//make sure to have this
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);// also important if you want your particle to move
		this.motionX = motionX * (1 - (worldObj.rand.nextFloat() / 10F));
		this.motionY = motionY * (1 - (worldObj.rand.nextFloat() / 10F));
		this.motionZ = motionZ * (1 - (worldObj.rand.nextFloat() / 10F));
		this.particleAlpha = (1F - ((float) this.particleAge / (float) this.particleMaxAge)) * 0.5F;
	}
		
		
	public EntityTier2FlameFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}
	
	public EntityTier2FlameFX setGravity(float gravity){
		particleGravity = gravity;
		return this;
	}
	
	public EntityTier2FlameFX setScale(float scale){
		particleScale = scale;
		return this;
	}
}*/
	
	
/**
 * Attempt at creating custom particles, it's bloody difficult
 * This is a carbon copy of the minecraft flame effect that recolours the texture to be a bluish green. 
 * problems persist in creating an original particle and calling it properly
 * as such, and this being the best I can do right now I am leaving this all as is
 * and moving onto wacky furnaces.
 * 
 */

public class EntityTier2FlameFX extends EntityFlameFX{	

	/* the scale of the flame FX */
    private float flameScale;
    private static final String __OBFID = "CL_00000907";

    public EntityTier2FlameFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_)
    {
        super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
        this.motionX = this.motionX * 0.009999999776482582D + p_i1209_8_;
        this.motionY = this.motionY * 0.009999999776482582D + p_i1209_10_;
        this.motionZ = this.motionZ * 0.009999999776482582D + p_i1209_12_;
        double d6 = p_i1209_2_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = p_i1209_4_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = p_i1209_6_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.flameScale = this.particleScale;
        this.particleRed = 1.0F;
		this.particleGreen = 0.1F;
		this.particleBlue = 1.0F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.particleAge + p_70539_2_) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f6 * f6 * 0.5F);
        super.renderParticle(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
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

    /*
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

    /*
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
