package net.pixfumy.legacyelytras.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.world.World;

public class ItemElytra extends ArmorItem {
    public ItemElytra(ArmorMaterial material, int materialId, int slot) {
        super(material, materialId, slot);
        this.maxCount = 1;
        this.damageable = true;
        this.setMaxDamage(432);
        this.setTranslationKey("elytra");
        this.setItemGroup(ItemGroup.MISC);
    }

    @Override
    public ItemStack onStartUse(ItemStack stack, World world, PlayerEntity player) {
        ItemStack itemStack = player.method_4485(2);
        if (itemStack == null) {
            player.setArmorSlot(2, stack.copy());
            stack.count = 0;
        }
        return stack;
    }

    @Override
    public String getDisplayName(ItemStack stack) {
        return "Elytra";
    }
}
