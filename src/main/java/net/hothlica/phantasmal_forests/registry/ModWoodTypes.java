package net.hothlica.phantasmal_forests.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType MUDWOOD = WoodTypeBuilder.copyOf(WoodType.OAK).register(PhantasmalForests.id("mudwood"), ModBlockSetTypes.MUDWOOD);
}
