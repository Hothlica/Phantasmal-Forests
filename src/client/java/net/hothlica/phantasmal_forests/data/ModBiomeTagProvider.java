package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

import static net.hothlica.phantasmal_forests.registry.ModBiomes.*;

public class ModBiomeTagProvider extends FabricTagsProvider<Biome> {
    public ModBiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.BIOME, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Vanilla
        builder(BiomeTags.IS_FOREST).add(MUDWOOD_FOREST);
        builder(BiomeTags.IS_OVERWORLD).add(MUDWOOD_FOREST);
        builder(BiomeTags.HAS_TRIAL_CHAMBERS).add(MUDWOOD_FOREST);
        builder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(MUDWOOD_FOREST);

        builder(ConventionalBiomeTags.IS_CONIFEROUS_TREE).add(MUDWOOD_FOREST);
        builder(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD).add(MUDWOOD_FOREST);
    }
}
