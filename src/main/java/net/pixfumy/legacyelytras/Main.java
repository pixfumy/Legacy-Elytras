package net.pixfumy.legacyelytras;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.Item;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		// too lazy to mixin to Item, let's just re-register the item
		Item.REGISTRY.add(401, new Identifier("fireworks"), (new FireworkItem()).setTranslationKey("fireworks").setItemGroup(ItemGroup.MISC));
	}
}
