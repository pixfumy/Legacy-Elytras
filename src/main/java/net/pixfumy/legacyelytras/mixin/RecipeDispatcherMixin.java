package net.pixfumy.legacyelytras.mixin;

import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeDispatcher;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipeType;
import net.minecraft.recipe.ShapelessRecipeType;
import net.pixfumy.legacyelytras.LegacyElytras;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Mixin(RecipeDispatcher.class)
public abstract class RecipeDispatcherMixin {
    @Final
    @Shadow
    private final List<RecipeType> recipes = Lists.newArrayList();

    @Shadow public abstract ShapedRecipeType registerShapedRecipe(ItemStack stack, Object... args);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void registerElytraRecipe(CallbackInfo ci) {
        this.registerShapedRecipe(new ItemStack(LegacyElytras.ELYTRA, 1), "X#X", "WXW", "X X", '#', Blocks.DRAGON_EGG, 'X', Items.LEATHER, 'W', Items.FEATHER);
        this.registerShapedRecipe(new ItemStack(LegacyElytras.ELYTRA, 1), "X#X", "WXW", "X X", '#', Items.NETHER_STAR, 'X', Items.LEATHER, 'W', Items.FEATHER);
        Collections.sort(this.recipes, new Comparator<RecipeType>() {
            public int compare(RecipeType recipeType, RecipeType recipeType2) {
                if (recipeType instanceof ShapelessRecipeType && recipeType2 instanceof ShapedRecipeType) {
                    return 1;
                } else if (recipeType2 instanceof ShapelessRecipeType && recipeType instanceof ShapedRecipeType) {
                    return -1;
                } else if (recipeType2.getSize() < recipeType.getSize()) {
                    return -1;
                } else {
                    return recipeType2.getSize() > recipeType.getSize() ? 1 : 0;
                }
            }
        });
    }
}
