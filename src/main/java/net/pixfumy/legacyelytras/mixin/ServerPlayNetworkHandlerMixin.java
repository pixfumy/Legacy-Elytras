package net.pixfumy.legacyelytras.mixin;

import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pixfumy.legacyelytras.IPlayerEntity;
import net.pixfumy.legacyelytras.networking.FallFlyingC2SPacket;
import net.pixfumy.legacyelytras.networking.IServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin implements IServerPlayNetworkHandler {
    @Shadow
    public ServerPlayerEntity player;

    @Override
    public void onFallFlying(FallFlyingC2SPacket packet) {
        NetworkThreadUtils.forceMainThread(packet, (ServerPlayNetworkHandler)(Object)this, this.player.getServerWorld());
        FallFlyingC2SPacket.Type type = packet.type;
        if (type == FallFlyingC2SPacket.Type.START_FALL_FLYING) {
            ((IPlayerEntity)this.player).startFallFlying();
        } else if (type == FallFlyingC2SPacket.Type.STOP_FALL_FLYING) {
            ((IPlayerEntity)this.player).stopFallFlying();
        }
    }
}
