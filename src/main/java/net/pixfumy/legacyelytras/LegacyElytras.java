package net.pixfumy.legacyelytras;

import net.fabricmc.api.ModInitializer;
import net.legacyfabric.fabric.api.registry.v1.RegistryHelper;
import net.legacyfabric.fabric.api.util.Identifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.itemgroup.ItemGroup;
import net.pixfumy.legacyelytras.items.ItemElytra;

public class LegacyElytras implements ModInitializer {
	public static final FireworkItem FIREWORK_ITEM = (FireworkItem) (new FireworkItem()).setTranslationKey("fireworks").setItemGroup(ItemGroup.MISC);
	public static final ItemElytra ELYTRA = new ItemElytra(ArmorItem.Material.LEATHER, 0, 1);

	@Override
	public void onInitialize() {
		RegistryHelper.registerItem(ELYTRA, new Identifier("legacyelytras", "elytra"));
	}
}
