package net.hothlica.phantasmal_forests.registry.helpers;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Consumer;

public class RegistryCallbacks {
    // === Post registration helper methods ===
    public static Consumer<Block> ignite(int burnChance, int spreadChance) {
        return block -> FlammableBlockRegistry.getDefaultInstance().add(block, burnChance, spreadChance);
    }

    public static Consumer<Block> blockEntity(BlockEntityType<?> blockEntity) {
        return blockEntity::addValidBlock;
    }

    // Only need to attach this to non stripped logs
    public static Consumer<Block> strip(Block stripped) {
        return notStripped -> StrippableBlockRegistry.register(notStripped, stripped);
    }

    // === Block item post registration helper methods ===
    public static Consumer<BlockItem> compost(float compostChance) {
        return blockItem -> CompostableRegistry.INSTANCE.add(blockItem, compostChance);
    }

    public static Consumer<BlockItem> fuel(int duration) {
        return blockItem -> FuelValueEvents.BUILD.register((builder, context) -> builder.add(blockItem, duration));
    }
}
