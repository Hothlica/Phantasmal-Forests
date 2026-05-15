package net.hothlica.phantasmal_forests.block;

import com.mojang.serialization.MapCodec;
import net.hothlica.phantasmal_forests.registry.ModBlockProperties;
import net.hothlica.phantasmal_forests.registry.ModBlocks;
import net.hothlica.phantasmal_forests.registry.ModItems;
import net.hothlica.phantasmal_forests.registry.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class WeepingMudwoodLogBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<WeepingMudwoodLogBlock> CODEC = simpleCodec(WeepingMudwoodLogBlock::new);
    public static final BooleanProperty NIGHT = ModBlockProperties.NIGHT;

    public WeepingMudwoodLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(NIGHT, false));
    }

    @Override
    public MapCodec<WeepingMudwoodLogBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, NIGHT);
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof AxeItem) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            BlockState NewLogState;
            if (state.getValue(NIGHT)) {
                NewLogState = ModBlocks.MUDWOOD_LOG.defaultBlockState();
                //TODO: Change MUDWOOD_BOAT to the supposed resource
                Block.popResource(level, pos, new ItemStack(ModItems.MUDWOOD_BOAT));
            }
            else {
                NewLogState = ModBlocks.STRIPPED_MUDWOOD_LOG.defaultBlockState();
            }
            level.setBlock(pos, NewLogState,  11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, NewLogState));
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
            }
            stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }

    @Override
    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        boolean isNight = level.isDarkOutside();
        if (state.getValue(NIGHT) != isNight) {
            level.setBlock(pos, state.setValue(NIGHT, isNight), 3);
            level.playSound(null, pos, ModSounds.WEEPING_MUDWOOD_LOG_CHANGE, SoundSource.BLOCKS, 1.0F, 1.0F);
            Vec3 particleLoc = Vec3.atCenterOf(pos).relative(state.getValue(FACING), 0.5);
            level.sendParticles(ParticleTypes.GLOW, particleLoc.x, particleLoc.y, particleLoc.z, 10, 0.3, 0.3, 0.3, 0.00001);
        }
    }
}
