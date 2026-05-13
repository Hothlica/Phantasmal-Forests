package net.hothlica.phantasmal_forests.client.render.entity;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;

public class ModEntityModelLayers {
    public static final ModelLayerLocation MUDWOOD_BOAT = new ModelLayerLocation(PhantasmalForests.id("boat/mudwood"), "main");
    public static final ModelLayerLocation MUDWOOD_CHEST_BOAT = new ModelLayerLocation(PhantasmalForests.id("chest_boat/mudwood"), "main");

    public static void register() {
        LayerDefinition boatModel = BoatModel.createBoatModel();
        LayerDefinition chestBoatModel = BoatModel.createChestBoatModel();
        ModelLayerRegistry.registerModelLayer(MUDWOOD_BOAT, () -> boatModel);
        ModelLayerRegistry.registerModelLayer(MUDWOOD_CHEST_BOAT, () -> chestBoatModel);
    }
}
