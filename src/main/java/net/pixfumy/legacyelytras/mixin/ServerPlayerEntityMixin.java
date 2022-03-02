package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.IPlayerEntity;
import net.pixfumy.legacyelytras.networking.FallFlyingS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Shadow public ServerPlayNetworkHandler networkHandler;

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void sendFallFlyingPacket(CallbackInfo ci) {
        this.networkHandler.sendPacket(new FallFlyingS2CPacket(((IPlayerEntity)this).isFallFlying()));
    }
}
