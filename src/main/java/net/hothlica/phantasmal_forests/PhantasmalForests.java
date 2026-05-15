package net.hothlica.phantasmal_forests;

import net.fabricmc.api.ModInitializer;

import net.hothlica.phantasmal_forests.registry.*;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhantasmalForests implements ModInitializer {
	public static final String MOD_ID = "phantasmal-forests";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModSounds.init();
		ModFoliagePlacerTypes.init();
		ModTrunkPlacerTypes.init();
		ModBiomes.init();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}