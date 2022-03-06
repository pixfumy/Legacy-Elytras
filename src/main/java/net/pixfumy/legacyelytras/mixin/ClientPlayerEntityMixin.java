package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.items.ItemElytra;
import net.pixfumy.legacyelytras.networking.StartFallFlyingC2SPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Shadow public Input input;
	@Shadow protected MinecraftClient client;
	public boolean lastJumping;

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;method_1302()V"), method = "tickMovement")
	private void setLastJumping(CallbackInfo ci) {
		this.lastJumping = this.input.jumping;
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;method_1302()V", shift = At.Shift.AFTER), method = "tickMovement")
	private void attemptStartFallFlying(CallbackInfo ci) {
		ClientPlayerEntity thisPlayer = (ClientPlayerEntity) (Object) this;
		if (this.input.jumping) {
			boolean hasUsableElytra = false;
			ItemStack chest = thisPlayer.getArmorStacks()[2];
			if (chest != null && chest.getItem() instanceof ItemElytra && chest.getDamage() < chest.getMaxDamage()) {
				hasUsableElytra = true;
			}
			if (!this.lastJumping && !thisPlayer.isTouchingWater() && !thisPlayer.onGround &&
					!thisPlayer.isClimbing() && !thisPlayer.hasVehicle() && hasUsableElytra) {
				this.client.getNetworkHandler().sendPacket(new StartFallFlyingC2SPacket((ClientPlayerEntity)(Object)thisPlayer));
			}
		}
	}
}
