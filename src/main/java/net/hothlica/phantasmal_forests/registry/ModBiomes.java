package net.hothlica.phantasmal_forests.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.world.biome.MudwoodForestBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> MUDWOOD_FOREST = create("mudwood_forest");

    private static ResourceKey<Biome> create(String id) {
        return ResourceKey.create(Registries.BIOME, PhantasmalForests.id(id));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(MUDWOOD_FOREST, MudwoodForestBiomes.create(context));
    }

    public static void init() {
        BiomePlacement.addOverworld(MUDWOOD_FOREST, MudwoodForestBiomes.DEFAULT_MUDWOOD_FOREST_HYPERCUBE);
    }
}
