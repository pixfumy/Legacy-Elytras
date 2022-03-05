package net.pixfumy.legacyelytras;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.Item;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.util.Identifier;
import net.pixfumy.legacyelytras.items.ItemElytra;

public class LegacyElytras implements ModInitializer {

	@Override
	public void onInitialize() {
		// too lazy to mixin to Item, let's just re-register the item
		Item.REGISTRY.add(401, new Identifier("fireworks"), (new FireworkItem()).setTranslationKey("fireworks").setItemGroup(ItemGroup.MISC));
		Item.REGISTRY.add(9999, new Identifier("elytra"), new ItemElytra(ArmorItem.Material.CHAIN, 0, 1));
	}
}
