package net.pixfumy.legacyelytras.mixin;

import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeDispatcher;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipeType;
import net.pixfumy.legacyelytras.LegacyElytras;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeDispatcher.class)
public abstract class RecipeDispatcherMixin {
    private boolean elytraRecipeRegistered = false;
    @Shadow
    private final List<RecipeType> recipes = Lists.newArrayList();

    @Shadow
    abstract ShapedRecipeType registerShapedRecipe(ItemStack stack, Object... args);

    @Inject(method = "registerShapelessRecipe", at = @At("HEAD"))
    private void registerElytraRecipe(CallbackInfo ci) {
        if (!elytraRecipeRegistered) {
            this.registerShapedRecipe(new ItemStack(LegacyElytras.ELYTRA, 1), "X#X", "WXW", "X X", '#', Blocks.DRAGON_EGG, 'X', Items.LEATHER, 'W', Items.FEATHER);
            this.registerShapedRecipe(new ItemStack(LegacyElytras.ELYTRA, 1), "X#X", "WXW", "X X", '#', Items.NETHER_STAR, 'X', Items.LEATHER, 'W', Items.FEATHER);
            this.elytraRecipeRegistered = true;
        }
    }
}
