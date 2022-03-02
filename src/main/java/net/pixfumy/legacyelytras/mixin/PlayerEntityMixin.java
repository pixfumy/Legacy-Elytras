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

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerEntity {
    protected boolean isFallFlying;

    @Override
    public boolean isFallFlying() {
        return this.isFallFlying;
    }

    @Override
    public boolean checkFallFlying() {
        ItemStack itemStack;
        if (!((LivingEntity)(Object)this).onGround && !this.isFallFlying && !((LivingEntity)(Object)this).isTouchingWater()) {
            boolean hasUsableElytra = false;
            ItemStack chest = ((PlayerEntity)(Object)this).getArmorStacks()[2];
            if (chest != null && chest.getItem() instanceof ItemElytra && chest.getDamage() < chest.getMaxDamage()) {
                hasUsableElytra = true;
            }
            this.startFallFlying();
            return true;
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
}
