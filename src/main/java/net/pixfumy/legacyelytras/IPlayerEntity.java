package net.pixfumy.legacyelytras;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public interface IPlayerEntity {
    public boolean isFallFlying();
    public boolean checkFallFlying();
    public void startFallFlying();
    public void stopFallFlying();
    public void tickFallFlying();
    public int getTicksFallFlying();
}
