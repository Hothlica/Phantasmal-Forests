package net.hothlica.phantasmal_forests.client.render.entity;

import net.hothlica.phantasmal_forests.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

public class ModEntityRenderers {
    public static void register() {
        registerBoat(ModEntityTypes.MUDWOOD_BOAT, ModEntityModelLayers.MUDWOOD_BOAT);
        registerBoat(ModEntityTypes.MUDWOOD_CHEST_BOAT, ModEntityModelLayers.MUDWOOD_CHEST_BOAT);
    }

    private static void registerBoat(EntityType<? extends AbstractBoat> type, ModelLayerLocation modelId) {
        EntityRenderers.register(type, context -> new BoatRenderer(context, modelId));
    }
}
