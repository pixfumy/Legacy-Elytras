package net.pixfumy.legacyelytras.mixin;

import net.minecraft.item.Item;
import net.pixfumy.legacyelytras.items.ItemElytra;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getRawId", at = @At("HEAD"), cancellable = true)
    private static void getElytraId(Item item, CallbackInfoReturnable<Integer> cir) {
        if (item instanceof ItemElytra) {
            cir.setReturnValue(9999);
        }
    }
}
