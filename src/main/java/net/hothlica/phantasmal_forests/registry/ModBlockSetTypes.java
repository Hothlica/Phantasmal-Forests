package net.hothlica.phantasmal_forests.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType MUDWOOD = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(PhantasmalForests.id("mudwood"));
}
