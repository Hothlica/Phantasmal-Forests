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
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.hothlica.phantasmal_forests.registry.helpers.DefaultBiomeHelper.*;

public class MudwoodForestBiomes {

    public static Biome create(BootstrapContext<Biome> context) {
        return new Biome.BiomeBuilder()
            .generationSettings(createGenerationSettings(context))
            .mobSpawnSettings(createSpawnSettings())
            .hasPrecipitation(true)
            .temperature(0.7F)
            .downfall(0.8F)
            .specialEffects(DefaultBiomeHelper.createDefaultBiomeEffects()
                .waterColor(0x294E6A)
                .grassColorOverride(0x97A156)
                .foliageColorOverride(0x454E22)
                .build()
            )
            .putAttributes(DefaultBiomeHelper.createDefaultEnvironmentAttributes()
                .set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
                .set(EnvironmentAttributes.WATER_FOG_COLOR, 0x294E6A)
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
