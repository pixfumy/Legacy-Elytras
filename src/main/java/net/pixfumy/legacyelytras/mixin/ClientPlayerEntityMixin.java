package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.IPlayerEntity;
import net.pixfumy.legacyelytras.ItemElytra;
import net.pixfumy.legacyelytras.networking.FallFlyingC2SPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IPlayerEntity {
	@Shadow public Input input;
	@Shadow @Final public ClientPlayNetworkHandler networkHandler;
	public boolean isFallFlying;
	public boolean lastJumping = false;
	private int ticksSinceOnGround = 0;

	public ClientPlayerEntityMixin(World world, GameProfile gameProfile) {
		super(world, gameProfile);
	}

	@Inject(at = @At("HEAD"), method = "tickMovement")
	private void attemptStartFallFlying(CallbackInfo ci) {
		if (this.onGround) {
			this.ticksSinceOnGround = 0;
			this.stopFallFlying();
		} else {
			this.ticksSinceOnGround++;
		}
		if (this.input.jumping) {
			boolean hasUsableElytra = false;
			ItemStack chest = this.getArmorStacks()[2];
			if (chest != null && chest.getItem() instanceof ItemElytra && chest.getDamage() < chest.getMaxDamage()) {
				hasUsableElytra = true;
			}
			if (!this.lastJumping && this.ticksSinceOnGround > 3 && !this.isTouchingWater() &&
					!this.isClimbing() && !this.hasVehicle() && hasUsableElytra) {
				this.networkHandler.sendPacket(new FallFlyingC2SPacket((ClientPlayerEntity)(Object)this,
						FallFlyingC2SPacket.Type.START_FALL_FLYING));
			}
			this.lastJumping = true;
		} else {
			this.lastJumping = false;
		}
	}

	@Override
	public boolean isFallFlying() {
		return this.isFallFlying;
	}

	@Override
	public void startFallFlying() {
		this.isFallFlying = true;
	}

	@Override
	public void stopFallFlying() {
		this.isFallFlying = false;
	}
}
