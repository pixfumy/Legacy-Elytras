package net.pixfumy.legacyelytras;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Material;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.Item;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.util.Identifier;
import net.pixfumy.legacyelytras.items.ItemElytra;

public class LegacyElytras implements ModInitializer {
	public static final FireworkItem FIREWORK_ITEM = (FireworkItem) (new FireworkItem()).setTranslationKey("fireworks").setItemGroup(ItemGroup.MISC);
	public static final ItemElytra ELYTRA = new ItemElytra(ArmorMaterial.CLOTH, 0, 1);

	@Override
	public void onInitialize() {
		Item.REGISTRY.method_7327(9999, "elytra", ELYTRA);
	}
}
