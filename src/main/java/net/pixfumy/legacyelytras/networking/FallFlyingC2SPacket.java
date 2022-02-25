package net.pixfumy.legacyelytras.networking;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class FallFlyingC2SPacket implements Packet<ServerPlayPacketListener> {
    public PlayerEntity player;
    public FallFlyingC2SPacket.Type type;

    public FallFlyingC2SPacket(PlayerEntity player, FallFlyingC2SPacket.Type type) {
        this.player = player;
        this.type = type;
    }

    @Override
    public void read(PacketByteBuf buf) throws IOException {

    }

    @Override
    public void write(PacketByteBuf buf) throws IOException {

    }

    @Override
    public void apply(ServerPlayPacketListener listener) {
        ((IServerPlayNetworkHandler)listener).onFallFlying(this);
    }

    public static enum Type {
        START_FALL_FLYING,
        STOP_FALL_FLYING
    }
}