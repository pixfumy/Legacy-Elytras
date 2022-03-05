package net.pixfumy.legacyelytras.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.slot.Slot;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.pixfumy.legacyelytras.items.ItemElytra;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin {
    @Shadow @Final public Inventory inventory;
    @Shadow @Final private int invSlot;

    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void allowElytra(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

    }
}
