package net.hothlica.phantasmal_forests.registry.helpers;

import net.hothlica.phantasmal_forests.registry.ModConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.hothlica.phantasmal_forests.registry.helpers.RegistryCallbacks.*;

public class WoodHelper {
    private final MapColor setMapColor;
    private final MapColor leafMapColor;
    private final MapColor barkMapColor;
    private final WoodType woodType;

    private static final BlockBehaviour.StatePredicate NEVER = (state, world, pos) -> false;

    private final Consumer<Block> logIgnite = ignite(5, 5);
    private final Consumer<Block> craftedIgnite = ignite(5, 20);
    private final Consumer<BlockItem> compost = compost(0.3F);

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
        return make(SlabBlock::new, copy(Blocks.OAK_SLAB).ignitedByLava()).postRegister(craftedIgnite);
    }

    public BlockPropertyBuilder stairs() {
        return make(settings -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), settings), copy(Blocks.OAK_STAIRS).ignitedByLava()).postRegister(craftedIgnite);
    }

    public BlockPropertyBuilder fence() {
        return make(FenceBlock::new, copy(Blocks.OAK_FENCE).ignitedByLava()).postRegister(craftedIgnite);
    }

    public BlockPropertyBuilder fenceGate() {
        return make(settings -> new FenceGateBlock(WoodType.OAK, settings), copy(Blocks.OAK_FENCE_GATE).ignitedByLava()).postRegister(craftedIgnite);
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
        return make(settings -> new StandingSignBlock(woodType, settings), copy(Blocks.OAK_SIGN)).postRegister(blockEntity(BlockEntityType.SIGN));
    }

    public BlockPropertyBuilder wallSign() {
        return make(settings -> new WallSignBlock(woodType, settings), copy(Blocks.OAK_WALL_SIGN)).postRegister(blockEntity(BlockEntityType.SIGN));
    }

    public BlockPropertyBuilder hangingSign() {
        return make(settings -> new CeilingHangingSignBlock(woodType, settings), copy(Blocks.OAK_HANGING_SIGN)).postRegister(blockEntity(BlockEntityType.HANGING_SIGN));
    }

    public BlockPropertyBuilder wallHangingSign() {
        return make(settings -> new WallHangingSignBlock(woodType, settings), copy(Blocks.OAK_WALL_HANGING_SIGN)).postRegister(blockEntity(BlockEntityType.HANGING_SIGN));
    }

    public BlockPropertyBuilder shelf() {
        return make(ShelfBlock::new, copy(Blocks.OAK_SHELF)).postRegister(blockEntity(BlockEntityType.SHELF));
    }

    public BlockPropertyBuilder log(Block strippedVariant) {
        return make(RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? setMapColor : barkMapColor).ignitedByLava()).postRegister(logIgnite, strip(strippedVariant));
    }

    public BlockPropertyBuilder wood(Block strippedVariant) {
        return make(RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(barkMapColor).ignitedByLava()).postRegister(logIgnite, strip(strippedVariant));
    }

    public BlockPropertyBuilder stripped() {
        return make(RotatedPillarBlock::new, copy(Blocks.STRIPPED_OAK_WOOD).ignitedByLava()).postRegister(logIgnite);
    }

    public BlockPropertyBuilder planks() {
        return make(Block::new, copy(Blocks.OAK_PLANKS).ignitedByLava()).postRegister(craftedIgnite);
    }

    // Non-woody blocks

    public BlockPropertyBuilder leaves(int leafColor) {
        return make(settings -> new UntintedParticleLeavesBlock(0.01f, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, leafColor), settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(leafMapColor).isValidSpawn((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
            .isSuffocating(NEVER).isViewBlocking(NEVER).ignitedByLava()).postRegister(ignite(30, 60)).postRegisterItem(compost);
    }

    //TODO: make configured features later as well as potted sapling

    public BlockPropertyBuilder sapling(ResourceKey<ConfiguredFeature<?,?>> feature) {
        return make(settings -> new SaplingBlock(new TreeGrower("mudwood", Optional.empty(), Optional.of(feature), Optional.empty()), settings), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).mapColor(leafMapColor));
    }
}
