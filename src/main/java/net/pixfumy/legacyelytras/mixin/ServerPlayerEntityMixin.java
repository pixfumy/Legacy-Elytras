package net.pixfumy.legacyelytras.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.pixfumy.legacyelytras.IPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements IPlayerEntity {
    boolean isFlying;

    @Override
    public boolean isFallFlying() {
        return this.isFlying;
    }

    @Override
    public void startFallFlying() {
        this.isFlying = true;
    }

    @Override
    public void stopFallFlying() {
        this.isFlying = false;
    }
}
