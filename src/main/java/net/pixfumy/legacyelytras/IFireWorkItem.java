package net.pixfumy.legacyelytras;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFireWorkItem {
    public ItemStack onStartUse(ItemStack stack, World world, PlayerEntity player);
}