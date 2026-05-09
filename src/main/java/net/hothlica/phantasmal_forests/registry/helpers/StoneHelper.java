package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class StoneHelper {
    private final MapColor setMapColor;

    public StoneHelper(MapColor setMapColor) {
        this.setMapColor = setMapColor;
    }

    public BlockBehaviour.Properties copy(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).mapColor(setMapColor);
    }

    public BlockPropertyBuilder make(Function<BlockBehaviour.Properties, Block> blockType, BlockBehaviour.Properties properties) {
        return new BlockPropertyBuilder(blockType, properties);
    }

    public BlockPropertyBuilder slab() {
        return make(SlabBlock::new, copy(Blocks.STONE_SLAB));
    }

    public BlockPropertyBuilder stairs() {
        return make(settings -> new StairBlock(Blocks.STONE.defaultBlockState(), settings), copy(Blocks.STONE_STAIRS));
    }

    public BlockPropertyBuilder button() {
        return make(settings -> new ButtonBlock(BlockSetType.STONE, 20, settings), copy(Blocks.STONE_BUTTON));
    }

    public BlockPropertyBuilder pressurePlate() {
        return make(settings -> new PressurePlateBlock(BlockSetType.STONE, settings), copy(Blocks.STONE_PRESSURE_PLATE));
    }
}
