package net.pixfumy.legacyelytras.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.pixfumy.legacyelytras.player.IPlayerEntity;
import net.pixfumy.legacyelytras.networking.FallFlyingS2CPacket;
import net.pixfumy.legacyelytras.networking.IClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin implements IClientPlayNetworkHandler {

    @Shadow private MinecraftClient client;

    @Override
    public void onFallFlying(FallFlyingS2CPacket packet) {
        ClientPlayerEntity player = this.client.player;
        boolean fallFlying = packet.fallFlying;
        if (fallFlying) {
            ((IPlayerEntity)player).startFallFlying();
        } else {
            ((IPlayerEntity)player).stopFallFlying();
        }
    }
}
