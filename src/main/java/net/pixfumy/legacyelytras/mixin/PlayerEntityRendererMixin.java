package net.pixfumy.legacyelytras.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.pixfumy.legacyelytras.player.IPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin extends LivingEntityRenderer {
    @Shadow private BiPedModel field_2133;
    @Shadow private BiPedModel field_2134;
    @Shadow private BiPedModel field_2135;

    public PlayerEntityRendererMixin(EntityModel entityModel, float f) {
        super(entityModel, f);
    }

    @Inject(method = "method_5777(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFF)V", at = @At("HEAD"), cancellable = true)
    private void renderFlyingPlayer(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, CallbackInfo ci) {
        if (((IPlayerEntity)abstractClientPlayerEntity).isFallFlying()) {
            super.method_5777(abstractClientPlayerEntity, f, g, h);
            float i = (float)(((IPlayerEntity)abstractClientPlayerEntity).getTicksFallFlying()) + h;
            float j = MathHelper.clamp(i * i / 100.0F, 0.0F, 1.0F);
            GL11.glRotatef(j * (-90.0F - abstractClientPlayerEntity.pitch), 1.0F, 0.0F, 0.0F);
            Vec3d vec3d = abstractClientPlayerEntity.getRotation();
            double d = abstractClientPlayerEntity.velocityX * abstractClientPlayerEntity.velocityX + abstractClientPlayerEntity.velocityZ * abstractClientPlayerEntity.velocityZ;
            double e = vec3d.x * vec3d.x + vec3d.z * vec3d.z;
            if (d > 0.0D && e > 0.0D) {
                double k = (abstractClientPlayerEntity.velocityX * vec3d.x + abstractClientPlayerEntity.velocityZ * vec3d.z) / (Math.sqrt(d) * Math.sqrt(e));
                double l = abstractClientPlayerEntity.velocityX * vec3d.z - abstractClientPlayerEntity.velocityZ * vec3d.x;
                GL11.glRotatef((float) (Math.signum(l) * Math.acos(k)) * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
            }
            ci.cancel();
        }
    }

    @Override
    public Identifier getTexture(Entity entity) {
        if (!(entity instanceof AbstractClientPlayerEntity)){
            return null;
        }
        return ((AbstractClientPlayerEntity)entity).getSkinTexture();
    }

    @Override
    public void render(Entity entity, double d, double e, double f, float g, float h) {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack var10 = ((AbstractClientPlayerEntity)entity).inventory.getMainHandStack();
        this.field_2134.field_1483 = this.field_2135.field_1483 = this.field_2133.field_1483 = var10 != null ? 1 : 0;
        if (var10 != null && ((AbstractClientPlayerEntity)entity).method_3192() > 0) {
            UseAction var11 = var10.getUseAction();
            if (var11 == UseAction.BLOCK) {
                this.field_2134.field_1483 = this.field_2135.field_1483 = this.field_2133.field_1483 = 3;
            } else if (var11 == UseAction.BOW) {
                this.field_2134.field_1485 = this.field_2135.field_1485 = this.field_2133.field_1485 = true;
            }
        }

        this.field_2134.field_1484 = this.field_2135.field_1484 = this.field_2133.field_1484 = entity.isSneaking();
        double var13 = e - (double)entity.heightOffset;
        if (entity.isSneaking() && !(entity instanceof ClientPlayerEntity)) {
            var13 -= 0.125D;
        }

        super.render((LivingEntity)entity, d, var13, f, g, h);
        this.field_2134.field_1485 = this.field_2135.field_1485 = this.field_2133.field_1485 = false;
        this.field_2134.field_1484 = this.field_2135.field_1484 = this.field_2133.field_1484 = false;
        this.field_2134.field_1483 = this.field_2135.field_1483 = this.field_2133.field_1483 = 0;
    }
}
