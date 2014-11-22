package io.github.mattkx4.morefurnaces.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnvilFurnace extends ModelBase {
	// fields
	ModelRenderer furnaceBase;
	ModelRenderer anvilBase;
	ModelRenderer anvilTop;

	public ModelAnvilFurnace() {
		textureWidth = 64;
		textureHeight = 64;

		furnaceBase = new ModelRenderer(this, 0, 0);
		furnaceBase.addBox(0F, 0F, 0F, 16, 8, 16);
		furnaceBase.setRotationPoint(-8F, 16F, -8F);
		furnaceBase.setTextureSize(64, 64);
		furnaceBase.mirror = true;
		setRotation(furnaceBase, 0F, 0F, 0F);
		anvilBase = new ModelRenderer(this, 40, 24);
		anvilBase.addBox(0F, 0F, 0F, 4, 2, 6);
		anvilBase.setRotationPoint(-2F, 14F, -3F);
		anvilBase.setTextureSize(64, 64);
		anvilBase.mirror = true;
		setRotation(anvilBase, 0F, 0F, 0F);
		anvilTop = new ModelRenderer(this, 0, 24);
		anvilTop.addBox(0F, 0F, 0F, 10, 6, 16);
		anvilTop.setRotationPoint(-5F, 8F, -8F);
		anvilTop.setTextureSize(64, 64);
		anvilTop.mirror = true;
		setRotation(anvilTop, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		furnaceBase.render(f5);
		anvilBase.render(f5);
		anvilTop.render(f5);
	}

	public void renderModel(float f) {
		furnaceBase.render(f);
		anvilBase.render(f);
		anvilTop.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}