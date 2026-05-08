package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockHelper {
    //The helper assumes calls only to the designated methods of a block type (EX: Stone assumed not becoming a fence)
    private final MapColor setMapColor;
    private final boolean isWood;

    public BlockHelper(MapColor setMapColor, boolean isWood) {
        this.setMapColor = setMapColor;
        this.isWood = isWood;
    }

    public BlockBehaviour.Properties copy(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).mapColor(setMapColor);
    }

    public BlockBehaviour.Properties slab () {
        return copy(isWood ? Blocks.OAK_SLAB : Blocks.STONE_SLAB);
    }

    public BlockBehaviour.Properties stairs () {
        return copy(isWood ? Blocks.OAK_STAIRS : Blocks.STONE_STAIRS);
    }

    public BlockBehaviour.Properties wall () {
        return copy(Blocks.STONE_BRICK_WALL);
    }

    public BlockBehaviour.Properties fence () {
        return copy(Blocks.OAK_FENCE);
    }

    public BlockBehaviour.Properties fenceGate () {
        return copy(Blocks.OAK_FENCE);
    }

    public BlockBehaviour.Properties trapdoor () {
        return copy(Blocks.OAK_TRAPDOOR).noOcclusion();
    }

    public BlockBehaviour.Properties door () {
        return copy(Blocks.OAK_DOOR).noOcclusion();
    }

    public BlockBehaviour.Properties log (MapColor barkColor) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? setMapColor : barkColor);
    }

    public BlockBehaviour.Properties wood (MapColor barkColor) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(barkColor);
    }

    public BlockBehaviour.Properties stripped () {
        return copy(Blocks.STRIPPED_OAK_WOOD);
    }

    public BlockBehaviour.Properties planks () {
        return copy(Blocks.OAK_PLANKS);
    }

}
