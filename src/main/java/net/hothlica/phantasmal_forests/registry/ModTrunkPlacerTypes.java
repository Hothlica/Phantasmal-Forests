package net.hothlica.phantasmal_forests.registry;

import com.mojang.serialization.MapCodec;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.world.gen.feature.tree.trunk.MudwoodTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<MudwoodTrunkPlacer> MUDWOOD = register("mudwood", MudwoodTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String id, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, PhantasmalForests.id(id), new TrunkPlacerType<>(codec));
    }

    public static void init() {}
}
