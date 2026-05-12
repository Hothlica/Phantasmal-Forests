package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class PhantasmalForestsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);

		pack.addProvider(ModRecipeProvider::new);

		var blockTagProvider = pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider((output, lookup) -> new ModItemTagProvider(output, lookup, blockTagProvider));

		pack.addProvider(ModDynamicRegistryProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		ModDynamicRegistryProvider.buildRegistry(registryBuilder);
	}
}
