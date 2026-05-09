package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlockPropertyBuilder {
    private final Function<BlockBehaviour.Properties, Block> blockType;
    private final BlockBehaviour.Properties settings;
    private BlockEntityType<?> blockEntityType = null;

    public BlockPropertyBuilder(Function<BlockBehaviour.Properties, Block> blockType, BlockBehaviour.Properties settings) {
        this.blockType = blockType;
        this.settings = settings;
    }

    public BlockPropertyBuilder blockEntity(BlockEntityType<?> blockEntityType) {
        this.blockEntityType = blockEntityType;
        return this;
    }

    public Function<BlockBehaviour.Properties, Block> getBlockType() {
        return blockType;
    }

    public BlockBehaviour.Properties getProperties() {
        return settings;
    }

    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }
}
