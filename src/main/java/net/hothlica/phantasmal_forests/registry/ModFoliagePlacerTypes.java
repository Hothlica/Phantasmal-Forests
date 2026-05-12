package net.hothlica.phantasmal_forests.registry;

import com.mojang.serialization.MapCodec;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.world.gen.feature.tree.foliage.MudwoodFoliagePlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<MudwoodFoliagePlacer> MUDWOOD = register("mudwood", MudwoodFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String id, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, PhantasmalForests.id(id), new FoliagePlacerType<>(codec));
    }

    public static void init() {}
}
