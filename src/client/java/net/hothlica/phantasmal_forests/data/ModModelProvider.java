package net.hothlica.phantasmal_forests.data;

import com.mojang.math.Quadrant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.hothlica.phantasmal_forests.registry.ModBlockFamilies;
import net.hothlica.phantasmal_forests.registry.ModBlockProperties;
import net.hothlica.phantasmal_forests.registry.ModBlocks;
import net.hothlica.phantasmal_forests.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class ModModelProvider extends FabricModelProvider {
    public static final VariantMutator X_ROT_90 = VariantMutator.X_ROT.withValue(Quadrant.R90);
    public static final VariantMutator X_ROT_180 = VariantMutator.X_ROT.withValue(Quadrant.R180);
    public static final VariantMutator X_ROT_270 = VariantMutator.X_ROT.withValue(Quadrant.R270);
    public static final VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    public static final VariantMutator Y_ROT_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    public static final VariantMutator Y_ROT_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING).select(Direction.EAST, Y_ROT_90).select(Direction.SOUTH, Y_ROT_180).select(Direction.WEST, Y_ROT_270).select(Direction.NORTH, (v) -> v);

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
        // === Mudwood ===
        blockModelGenerator.woodProvider(ModBlocks.MUDWOOD_LOG).logWithHorizontal(ModBlocks.MUDWOOD_LOG).wood(ModBlocks.MUDWOOD_WOOD);
        blockModelGenerator.woodProvider(ModBlocks.STRIPPED_MUDWOOD_LOG).logWithHorizontal(ModBlocks.STRIPPED_MUDWOOD_LOG).wood(ModBlocks.STRIPPED_MUDWOOD_WOOD);
        blockModelGenerator.family(ModBlockFamilies.MUDWOOD.getBaseBlock()).generateFor(ModBlockFamilies.MUDWOOD);
        blockModelGenerator.createHangingSign(ModBlocks.STRIPPED_MUDWOOD_LOG, ModBlocks.MUDWOOD_HANGING_SIGN, ModBlocks.MUDWOOD_WALL_HANGING_SIGN);
        blockModelGenerator.createShelf(ModBlocks.MUDWOOD_SHELF, ModBlocks.STRIPPED_MUDWOOD_LOG);
        blockModelGenerator.createTrivialBlock(ModBlocks.MUDWOOD_LEAVES, TexturedModel.LEAVES);
        blockModelGenerator.createPlantWithDefaultItem(ModBlocks.MUDWOOD_SAPLING, ModBlocks.POTTED_MUDWOOD_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        registerWeepingLog(blockModelGenerator, ModBlocks.WEEPING_MUDWOOD_LOG, ModBlocks.MUDWOOD_LOG);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.STEPPING_STONE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MUDWOOD_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUDWOOD_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }

    public final void registerWeepingLog(BlockModelGenerators blockModelGenerator, Block block, Block borrowTextureBlock) {
        TextureMapping weepingLogDormantTextures = (new TextureMapping()).put(TextureSlot.SIDE, getBlockTexture(borrowTextureBlock)).put(TextureSlot.FRONT, getBlockTexture(borrowTextureBlock)).put(TextureSlot.TOP, getBlockTexture(borrowTextureBlock, "_top"));
        MultiVariant dayModel = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE.createWithSuffix(block, "_dormant", weepingLogDormantTextures, blockModelGenerator.modelOutput));
        MultiVariant nightModel = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block));
        blockModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(BlockModelGenerators.createBooleanModelDispatch(ModBlockProperties.NIGHT, nightModel, dayModel)).with(ROTATION_HORIZONTAL_FACING));
        //blockModelGenerator.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
    }
}
