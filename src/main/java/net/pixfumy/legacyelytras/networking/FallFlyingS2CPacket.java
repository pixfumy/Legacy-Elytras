package net.pixfumy.legacyelytras.networking;

import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.util.PacketByteBuf;

public class FallFlyingS2CPacket extends Packet {
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