package net.pixfumy.legacyelytras.mixin;

import net.minecraft.entity.FireworkRocketEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.items.IFireWorkItem;
import net.pixfumy.legacyelytras.IPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireworkItem.class)
public class FireworkItemMixin implements IFireWorkItem {
    @Override
    public ItemStack onStartUse(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isClient && ((IPlayerEntity)player).checkFallFlying()) {
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, player.x, player.y, player.z, stack);
            world.spawnEntity(fireworkRocketEntity);
            if (!player.abilities.creativeMode) {
                --stack.count;
            }
        }
        return stack;
    }
}
