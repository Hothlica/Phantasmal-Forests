package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Optional;
import java.util.function.Function;

public class WoodHelper {
    private final MapColor setMapColor;
    private final MapColor leafMapColor;
    private final MapColor barkMapColor;
    private final WoodType woodType;

    private static final BlockBehaviour.StatePredicate NEVER = (state, world, pos) -> false;

    public WoodHelper(MapColor setMapColor, MapColor leafMapColor, MapColor barkMapColor, WoodType woodType) {
        this.setMapColor = setMapColor;
        this.leafMapColor = leafMapColor;
        this.barkMapColor = barkMapColor;
        this.woodType = woodType;
    }

    public BlockBehaviour.Properties copy(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).mapColor(setMapColor);
    }

    public BlockPropertyBuilder make(Function<BlockBehaviour.Properties, Block> blockType, BlockBehaviour.Properties properties) {
        return new BlockPropertyBuilder(blockType, properties);
    }

    public BlockPropertyBuilder slab() {
        return make(SlabBlock::new, copy(Blocks.OAK_SLAB));
    }

    public BlockPropertyBuilder stairs() {
        return make(settings -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), settings), copy(Blocks.OAK_STAIRS));
    }

    public BlockPropertyBuilder fence() {
        return make(FenceBlock::new, copy(Blocks.OAK_FENCE));
    }

    public BlockPropertyBuilder fenceGate() {
        return make(settings -> new FenceGateBlock(WoodType.OAK, settings), copy(Blocks.OAK_FENCE_GATE));
    }

    public BlockPropertyBuilder trapdoor() {
        return make(settings -> new TrapDoorBlock(BlockSetType.OAK, settings), copy(Blocks.OAK_TRAPDOOR));
    }

    public BlockPropertyBuilder door() {
        return make(settings -> new DoorBlock(BlockSetType.OAK, settings), copy(Blocks.OAK_DOOR));
    }

    public BlockPropertyBuilder button() {
        return make(settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), copy(Blocks.OAK_BUTTON));
    }

    public BlockPropertyBuilder pressurePlate() {
        return make(settings -> new PressurePlateBlock(BlockSetType.OAK, settings), copy(Blocks.OAK_PRESSURE_PLATE));
    }

    public BlockPropertyBuilder sign() {
        return make(settings -> new StandingSignBlock(woodType, settings), copy(Blocks.OAK_SIGN)).blockEntity(BlockEntityType.SIGN);
    }

    public BlockPropertyBuilder wallSign() {
        return make(settings -> new WallSignBlock(woodType, settings), copy(Blocks.OAK_WALL_SIGN)).blockEntity(BlockEntityType.SIGN);
    }

    public BlockPropertyBuilder hangingSign() {
        return make(settings -> new CeilingHangingSignBlock(woodType, settings), copy(Blocks.OAK_HANGING_SIGN)).blockEntity(BlockEntityType.HANGING_SIGN);
    }

    public BlockPropertyBuilder wallHangingSign() {
        return make(settings -> new WallHangingSignBlock(woodType, settings), copy(Blocks.OAK_WALL_HANGING_SIGN)).blockEntity(BlockEntityType.HANGING_SIGN);
    }

    public BlockPropertyBuilder shelf() {
        return make(ShelfBlock::new, copy(Blocks.OAK_SHELF)).blockEntity(BlockEntityType.SHELF);
    }

    public BlockPropertyBuilder log() {
        return make(RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? setMapColor : barkMapColor));
    }

    public BlockPropertyBuilder wood() {
        return make(RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(barkMapColor));
    }

    public BlockPropertyBuilder stripped() {
        return make(RotatedPillarBlock::new, copy(Blocks.STRIPPED_OAK_WOOD));
    }

    public BlockPropertyBuilder planks() {
        return make(Block::new, copy(Blocks.OAK_PLANKS));
    }

    // Non-woody blocks

    public BlockPropertyBuilder leaves(int leafColor) {
        return make(settings -> new UntintedParticleLeavesBlock(0.01f, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, leafColor), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(leafMapColor).isValidSpawn((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating(NEVER).isViewBlocking(NEVER));
    }

    //TODO: make configured features later as well as potted sapling
//    public BlockPropertyBuilder sapling(MapColor leafMapColor) {
//        return make(settings -> new SaplingBlock(new TreeGrower("mudwood_tree", Optional.empty(), Optional.of(ModConfiguredFeatures.MUDWOOD_TREE), copy(Blocks.OAK_PLANKS)))
//                , BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).mapColor(leafMapColor));
//    }

}
