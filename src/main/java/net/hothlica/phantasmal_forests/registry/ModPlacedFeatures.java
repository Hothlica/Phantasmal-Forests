package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MUDWOOD_TREE = resourceKey("mudwood_tree");



    private static ResourceKey<PlacedFeature> resourceKey(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, PhantasmalForests.id(id));
    }
}
