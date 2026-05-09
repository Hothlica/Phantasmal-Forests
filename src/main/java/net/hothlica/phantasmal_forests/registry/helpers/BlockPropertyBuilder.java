package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlockPropertyBuilder {
    private Function<BlockBehaviour.Properties, Block> blockType = Block::new;
    private BlockBehaviour.Properties settings;

    public BlockPropertyBuilder(Function<BlockBehaviour.Properties, Block> blockType, BlockBehaviour.Properties settings) {
        this.blockType = blockType;
        this.settings = settings;
    }

    public Function<BlockBehaviour.Properties, Block> getBlockType() {
        return blockType;
    }

    public BlockBehaviour.Properties getProperties() {
        return settings;
    }
}
