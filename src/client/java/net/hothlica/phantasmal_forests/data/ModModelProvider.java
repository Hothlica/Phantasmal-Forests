package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.hothlica.phantasmal_forests.registry.ModBlockFamilies;
import net.hothlica.phantasmal_forests.registry.ModBlocks;
import net.hothlica.phantasmal_forests.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
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
        //blockModelGenerator.createPlantWithDefaultItem(ModBlocks.MUDWOOD_SAPLING, ModBlocks.MUDWOOD_POTTED_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.STEPPING_STONE, ModelTemplates.FLAT_ITEM);
    }

    private void registerBlockItemModel(BlockModelGenerators blockModelGenerators, Block block) {
        blockModelGenerators.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
    }
}
