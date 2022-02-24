package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
	@Shadow public Input input;
	public boolean isFlying;
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
		if (this.input.jumping && this.ticksSinceOnGround > 4) {
			System.out.println("icarus");
		}
	}
	//TODO: account for water and for holding space
}
