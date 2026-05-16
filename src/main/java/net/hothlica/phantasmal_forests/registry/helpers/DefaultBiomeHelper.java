package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class DefaultBiomeHelper {
    public static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings, boolean lavaSprings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        if (lavaSprings) {
            BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        }
        else {
            generationSettings.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);
        }
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }

    public static MobSpawnSettings.Builder createDefaultSpawnSettings() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.caveSpawns(spawns);
        BiomeDefaultFeatures.monsters(spawns, 95, 5, 0, 100, false);
        return spawns;
    }

    public static net.minecraft.world.level.biome.BiomeSpecialEffects.Builder createDefaultBiomeEffects() {
        return new net.minecraft.world.level.biome.BiomeSpecialEffects.Builder().waterColor(0x3F76E4);
    }

    public static EnvironmentAttributeMap.Builder createDefaultEnvironmentAttributes() {
        return EnvironmentAttributeMap.builder()
            .set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
            .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0.2F))
            .set(EnvironmentAttributes.FOG_COLOR, 0xC0D8FF);
    }
}
