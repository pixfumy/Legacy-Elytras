package net.pixfumy.legacyelytras.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.pixfumy.legacyelytras.player.IPlayerEntity;
import net.pixfumy.legacyelytras.networking.StartFallFlyingC2SPacket;
import net.pixfumy.legacyelytras.networking.IServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin implements IServerPlayNetworkHandler {
    @Shadow
    public ServerPlayerEntity player;

    @Override
    public void onStartFallFlying(StartFallFlyingC2SPacket packet) {
        ((IPlayerEntity)player).checkFallFlying();
    }
}
