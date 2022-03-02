package net.pixfumy.legacyelytras.mixin;

import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pixfumy.legacyelytras.IPlayerEntity;
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
        NetworkThreadUtils.forceMainThread(packet, (ServerPlayNetworkHandler)(Object)this, this.player.getServerWorld());
        ((IPlayerEntity)player).checkFallFlying();
    }
}
