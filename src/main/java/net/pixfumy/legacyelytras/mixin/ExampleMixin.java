package net.pixfumy.legacyelytras.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ExampleMixin extends AbstractClientPlayerEntity {
	@Shadow public Input input;
	private int ticksSinceOnGround = 0;

	public ExampleMixin(World world, GameProfile gameProfile) {
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
}
