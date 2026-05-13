package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

public class ModEntityTypes {
    private static final EntityType.EntityFactory<Boat> boatFactory = (type, world) -> new Boat(type, world, () -> ModItems.MUDWOOD_BOAT);
    private static final EntityType.EntityFactory<ChestBoat> chestBoatFactory = (type, world) -> new ChestBoat(type, world, () -> ModItems.MUDWOOD_CHEST_BOAT);

    public static final EntityType<Boat> MUDWOOD_BOAT = register("mudwood_boat", EntityType.Builder.of(boatFactory, MobCategory.MISC)
            .noLootTable()
            .sized(1.375F, 0.5625F)
            .eyeHeight(0.5625F)
            .clientTrackingRange(10));
    public static final EntityType<ChestBoat> MUDWOOD_CHEST_BOAT = register("mudwood_chest_boat", EntityType.Builder.of(chestBoatFactory, MobCategory.MISC)
            .noLootTable()
            .sized(1.375F, 0.5625F)
            .eyeHeight(0.5625F)
            .clientTrackingRange(10));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, PhantasmalForests.id(id));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }
}
