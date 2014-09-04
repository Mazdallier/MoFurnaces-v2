package io.github.mattkx4.morefurnaces.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import io.github.mattkx4.morefurnaces.entity.EntityCobaltKatana;
import io.github.mattkx4.morefurnaces.lib.Strings;
import io.github.mattkx4.morefurnaces.model.ModelCobaltKatana;

public class RenderEntityCobaltKatana extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation(Strings.MODID + ":textures/model/cobalt_katana.png");
	
	protected ModelCobaltKatana modelEntity;
	
	public RenderEntityCobaltKatana(ModelBase modelBase, float f) {
		super(modelBase, f);
		
		modelEntity = ((ModelCobaltKatana) mainModel);
	}

	public void renderCobaltKatana(EntityCobaltKatana entity, double x, double y, double z, float u, float v){
		super.doRender(entity, x, y, z, u, v);
		
	}
	
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v){
		renderCobaltKatana((EntityCobaltKatana)entityLiving, x, y, z, u, v);
	}
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v){
		renderCobaltKatana((EntityCobaltKatana)entity, x, y, z, u, v);
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
