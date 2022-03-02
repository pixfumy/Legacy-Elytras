package net.pixfumy.legacyelytras.networking;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class StartFallFlyingC2SPacket implements Packet<ServerPlayPacketListener> {
    public ClientPlayerEntity player;

    public StartFallFlyingC2SPacket(ClientPlayerEntity player) {
        this.player = player;
    }

    @Override
    public void read(PacketByteBuf buf) throws IOException {

    }

    @Override
    public void write(PacketByteBuf buf) throws IOException {

    }

    @Override
    public void apply(ServerPlayPacketListener listener) {
        ((IServerPlayNetworkHandler)listener).onStartFallFlying(this);
    }
}
