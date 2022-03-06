package net.pixfumy.legacyelytras.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkState;
import net.minecraft.network.Packet;
import net.pixfumy.legacyelytras.networking.FallFlyingS2CPacket;
import net.pixfumy.legacyelytras.networking.StartFallFlyingC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Redirect(method = "method_7401", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkState;getPacketHandlerState(Lnet/minecraft/network/Packet;)Lnet/minecraft/network/NetworkState;"))
    private NetworkState getPacketState(Packet packet) {
        if (packet instanceof StartFallFlyingC2SPacket || packet instanceof FallFlyingS2CPacket) {
            return NetworkState.PLAY;
        } else {
            return NetworkState.getPacketHandlerState(packet);
        }
    }
}
