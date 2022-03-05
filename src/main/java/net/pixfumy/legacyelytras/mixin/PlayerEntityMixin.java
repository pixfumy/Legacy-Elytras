package net.pixfumy.legacyelytras.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.IPlayerEntity;
import net.pixfumy.legacyelytras.items.ItemElytra;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerEntity {
    protected boolean isFallFlying;
    private int ticksFallFlying = 0;

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;tickMovement()V"))
    private void doTickFallFlying(CallbackInfo ci) {
        this.tickFallFlying();
    }

    @Override
    public boolean isFallFlying() {
        return this.isFallFlying;
    }

    @Override
    public boolean checkFallFlying() {
        ItemStack itemStack;
        if (!((LivingEntity)(Object)this).onGround && !((LivingEntity)(Object)this).isTouchingWater()) {
            boolean hasUsableElytra = false;
            ItemStack chest = ((PlayerEntity)(Object)this).getArmorStacks()[2];
            if (chest != null && chest.getItem() instanceof ItemElytra && chest.getDamage() < chest.getMaxDamage()) {
                hasUsableElytra = true;
            }
            if (hasUsableElytra) {
                this.startFallFlying();
                return true;
            }
        }
        this.stopFallFlying();
        return false;
    }

    @Override
    public void startFallFlying() {
        this.isFallFlying = true;
    }

    @Override
    public void stopFallFlying() {
        this.isFallFlying = false;
    }

    @Override
    public void tickFallFlying() {
        boolean bl = this.isFallFlying();
        if (bl && !((PlayerEntity)(Object)this).onGround && !((PlayerEntity)(Object)this).hasVehicle()) {
            boolean hasUsableElytra = false;
            ItemStack chest = ((PlayerEntity)(Object)this).getArmorStacks()[2];
            if (chest != null && chest.getItem() instanceof ItemElytra && chest.getDamage() < chest.getMaxDamage()) {
                hasUsableElytra = true;
            }
            if (hasUsableElytra) {
                bl = true;
            }
        } else {
            bl = false;
        }
        if (!((PlayerEntity)(Object)this).world.isClient) {
            if (bl) {
                this.startFallFlying();
            } else {
                this.stopFallFlying();
            }
        }
    }

    public int getTicksFallFlying() {
        return this.ticksFallFlying;
    }
}
