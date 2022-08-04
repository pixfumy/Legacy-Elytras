package net.pixfumy.legacyelytras.networking;

import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class StartFallFlyingC2SPacket extends Packet {
    public ClientPlayerEntity player;

    public StartFallFlyingC2SPacket(ClientPlayerEntity player) {
        this.player = player;
    }

    @Override
    public void read(PacketByteBuf buf) {

    }

    @Override
    public void write(PacketByteBuf buf) {

    }
    @Override
    public void apply(PacketListener listener) {
        ((IServerPlayNetworkHandler)(ServerPlayNetworkHandler)listener).onStartFallFlying(this);
    }
}
