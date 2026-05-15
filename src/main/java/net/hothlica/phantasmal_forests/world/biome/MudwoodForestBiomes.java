package net.hothlica.phantasmal_forests.world.biome;

import net.hothlica.phantasmal_forests.registry.helpers.DefaultBiomeHelper;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.hothlica.phantasmal_forests.registry.helpers.DefaultBiomeHelper.*;

public class MudwoodForestBiomes {

    public static final Climate.ParameterPoint DEFAULT_MUDWOOD_FOREST_HYPERCUBE =
        new Climate.ParameterPoint(
            Climate.Parameter.span(-1.0f, -0.15f),   // temperature
            Climate.Parameter.span(-1.0f, -0.35f),   // humidity
            Climate.Parameter.span(-0.11f, 0.3f),    // continentalness [Near inland, mid inland]
            Climate.Parameter.span(-0.375f, 0.05f),  // erosion
            Climate.Parameter.point(0.0f),      // depth (Surface biomes don't need it)
            Climate.Parameter.span(-1.0f, 0.2f),     // weirdness [Valleys, mid]
            0L                                       // offset
        );

    public static Biome create(BootstrapContext<Biome> context) {
        return new Biome.BiomeBuilder()
            .generationSettings(createGenerationSettings(context))
            .mobSpawnSettings(createSpawnSettings())
            .hasPrecipitation(true)
            .temperature(0.7F)
            .downfall(0.8F)
            .specialEffects(DefaultBiomeHelper.createDefaultBiomeEffects()
                .waterColor(0x3f76e4)
                .grassColorOverride(0x7ecc41)
                .foliageColorOverride(10931465)
                .build()
            )
            .putAttributes(DefaultBiomeHelper.createDefaultEnvironmentAttributes()
                .set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
                .set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
                .build()
            )
            .build();
    }

    private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
        addBasicFeatures(builder, true);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addForestGrass(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addBushes(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        return builder.build();
    }

    private static MobSpawnSettings createSpawnSettings() {
        net.minecraft.world.level.biome.MobSpawnSettings.Builder builder = DefaultBiomeHelper.createDefaultSpawnSettings();
        return builder.build();
    }
}
