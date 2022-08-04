package net.pixfumy.legacyelytras.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.pixfumy.legacyelytras.player.IPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean canMoveVoluntarily();

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void travelFallFlying(float f, float g, CallbackInfo ci) {
        if (this.canMoveVoluntarily() && ((LivingEntity)(Object)this) instanceof PlayerEntity) {
            if (((IPlayerEntity)(Object)this).isFallFlying()
                    && !((PlayerEntity)(Object)this).abilities.creativeMode) {
                PlayerEntity thisPlayer = (PlayerEntity)(Object)this;
                if (thisPlayer.velocityY > -0.5D) {
                    thisPlayer.fallDistance = 1.0F;
                }

                Vec3d vec3d = thisPlayer.getRotation();
                float l = thisPlayer.pitch * 0.017453292F;
                double m = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
                double n = Math.sqrt(thisPlayer.velocityX * thisPlayer.velocityX + thisPlayer.velocityZ * thisPlayer.velocityZ);
                double o = vec3d.length();
                float p = MathHelper.cos(l);
                p = (float)((double)p * (double)p * Math.min(1.0D, o / 0.4D));
                thisPlayer.velocityY += -0.08D + (double)p * 0.06D;
                double s;
                if (thisPlayer.velocityY < 0.0D && m > 0.0D) {
                    s = thisPlayer.velocityY * -0.1D * (double)p;
                    thisPlayer.velocityY += s;
                    thisPlayer.velocityX += vec3d.x * s / m;
                    thisPlayer.velocityZ += vec3d.z * s / m;
                }

                if (l < 0.0F) {
                    s = n * (double)(-MathHelper.sin(l)) * 0.04D;
                    thisPlayer.velocityY += s * 3.2D;
                    thisPlayer.velocityX -= vec3d.x * s / m;
                    thisPlayer.velocityZ -= vec3d.z * s / m;
                }

                if (m > 0.0D) {
                    thisPlayer.velocityX += (vec3d.x / m * n - thisPlayer.velocityX) * 0.1D;
                    thisPlayer.velocityZ += (vec3d.z / m * n - thisPlayer.velocityZ) * 0.1D;
                }

                thisPlayer.velocityX *= 0.9900000095367432D;
                thisPlayer.velocityY *= 0.9800000190734863D;
                thisPlayer.velocityZ *= 0.9900000095367432D;
                thisPlayer.move(thisPlayer.velocityX, thisPlayer.velocityY, thisPlayer.velocityZ);
                if (thisPlayer.horizontalCollision && !thisPlayer.world.isClient) {
                    s = Math.sqrt(thisPlayer.velocityX * thisPlayer.velocityX + thisPlayer.velocityZ * thisPlayer.velocityZ);
                    double t = n - s;
                    float u = (float)(t * 10.0D - 3.0D);
                    if (u > 0.0F) {
                        thisPlayer.damage(DamageSource.FALL, u);
                    }
                }

                if (thisPlayer.onGround && !thisPlayer.world.isClient) {
                    ((IPlayerEntity)thisPlayer).stopFallFlying();
                }
                ci.cancel();
            }
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readFlying(NbtCompound tag, CallbackInfo ci) {
        if ((LivingEntity)(Object)this instanceof PlayerEntity && tag.getBoolean("fallFlying")) {
            ((IPlayerEntity)this).startFallFlying();
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeFlying(NbtCompound tag, CallbackInfo ci) {
        if ((LivingEntity)(Object)this instanceof PlayerEntity) {
            tag.putBoolean("fallFlying", ((IPlayerEntity) this).isFallFlying());
        }
    }
}
