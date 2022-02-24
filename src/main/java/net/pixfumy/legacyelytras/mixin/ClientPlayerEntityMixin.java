package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pixfumy.legacyelytras.IPlayerEntity;
import net.pixfumy.legacyelytras.ItemElytra;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IPlayerEntity {
	@Shadow public Input input;
	public boolean isFlying;
	public boolean lastJumping = false;
	private int ticksSinceOnGround = 0;

	public ClientPlayerEntityMixin(World world, GameProfile gameProfile) {
		super(world, gameProfile);
	}

	@Inject(at = @At("HEAD"), method = "tickMovement")
	private void attemptStartFallFlying(CallbackInfo ci) {
		if (this.onGround) {
			this.ticksSinceOnGround = 0;
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
				this.startFallFlying();
			}
			this.lastJumping = true;
		} else {
			this.lastJumping = false;
		}
	}

	@Override
	public boolean isFallFlying() {
		return this.isFlying;
	}

	@Override
	public void startFallFlying() {
		this.isFlying = true;
	}

	@Override
	public void stopFallFlying() {
		this.isFlying = false;
	}
}
