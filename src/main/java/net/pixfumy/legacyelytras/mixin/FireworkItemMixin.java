package net.pixfumy.legacyelytras.mixin;

import net.minecraft.entity.FireworkRocketEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.player.IPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(FireworkItem.class)
public class FireworkItemMixin extends Item {
    private Random random = new Random();

    @Override
    public ItemStack onStartUse(ItemStack stack, World world, PlayerEntity player) {
        if (((IPlayerEntity)player).isFallFlying()) {
            if (!world.isClient) {
                FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, player.x, player.y, player.z, stack);
                world.spawnEntity(fireworkRocketEntity);
            }
            if (!player.abilities.creativeMode) {
                --stack.count;
            }
            if (((IPlayerEntity)player).isFallFlying()) {
                Vec3d vec3d = player.getRotation();
                double d = 1.5D;
                double e = 0.1D;
                LivingEntity var10000 = player;
                for (int i = 0; i < 10; i++) {
                    var10000.velocityX += vec3d.x * e + (vec3d.x * d - var10000.velocityX);
                    var10000.velocityY += vec3d.y * e + (vec3d.y * d * 1.8 - var10000.velocityY);
                    var10000.velocityZ += vec3d.z * e + (vec3d.z * d - var10000.velocityZ);
                }
            }
        }
        return stack;
    }
}
