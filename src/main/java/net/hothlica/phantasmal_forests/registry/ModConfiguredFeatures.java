package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.world.gen.feature.tree.foliage.MudwoodFoliagePlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> MUDWOOD_TREE = registerKey("mudwood_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, MUDWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(ModBlocks.MUDWOOD_LOG),
                new StraightTrunkPlacer(7, 3, 0),
                SimpleStateProvider.simple(ModBlocks.MUDWOOD_LEAVES),
                new MudwoodFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, PhantasmalForests.id(id));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        FeatureUtils.register(registerable, key, feature, config);
    }
}
