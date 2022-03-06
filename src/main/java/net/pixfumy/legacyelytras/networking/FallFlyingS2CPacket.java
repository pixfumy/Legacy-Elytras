package net.pixfumy.legacyelytras.networking;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class FallFlyingS2CPacket implements Packet {
    public boolean fallFlying;

    public FallFlyingS2CPacket(boolean fallFlying) {
        this.fallFlying = fallFlying;
    }

    @Override
    public void read(PacketByteBuf buf) {

    }

    @Override
    public void write(PacketByteBuf buf) {

    }

    @Override
    public void apply(PacketListener listener) {
        ((IClientPlayNetworkHandler) listener).onFallFlying(this);
    }
}