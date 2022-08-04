package net.pixfumy.legacyelytras;

import net.fabricmc.api.ModInitializer;
import net.legacyfabric.fabric.api.registry.v1.RegistryHelper;
import net.legacyfabric.fabric.api.util.Identifier;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.itemgroup.ItemGroup;
import net.pixfumy.legacyelytras.items.ItemElytra;

public class LegacyElytras implements ModInitializer {
	public static final FireworkItem FIREWORK_ITEM = (FireworkItem) (new FireworkItem()).setTranslationKey("fireworks").setItemGroup(ItemGroup.MISC);
	public static final ItemElytra ELYTRA = new ItemElytra(ArmorMaterial.CLOTH, 0, 1);

	@Override
	public void onInitialize() {
		register();
	}

	private static boolean registered = false;
	public static void register() {
		if (!registered) {
			registered = true;
			RegistryHelper.registerItem(ELYTRA, new Identifier("legacyelytras", "elytra"));
		}
	}
}
