package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

import static net.hothlica.phantasmal_forests.registry.ModBlocks.*;
import static net.hothlica.phantasmal_forests.tag.ModBlockTags.*;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider{

    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(MUDWOOD_LOGS).add(MUDWOOD_LOG, STRIPPED_MUDWOOD_LOG, MUDWOOD_WOOD, STRIPPED_MUDWOOD_WOOD, WEEPING_MUDWOOD_LOG);

        // Vanilla

        //valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD).add();
        //valueLookupBuilder(BlockTags.STONE_ORE_REPLACEABLES).add();

        // Stone
        //valueLookupBuilder(BlockTags.SLABS).add();
        //valueLookupBuilder(BlockTags.STAIRS).add();
        //valueLookupBuilder(BlockTags.WALLS).add();

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN).addTag(MUDWOOD_LOGS);
        valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(MUDWOOD_LOG, WEEPING_MUDWOOD_LOG);
        valueLookupBuilder(BlockTags.PLANKS).add(MUDWOOD_PLANKS);
        valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add(MUDWOOD_BUTTON);
        valueLookupBuilder(BlockTags.WOODEN_DOORS).add(MUDWOOD_DOOR);
        valueLookupBuilder(BlockTags.WOODEN_FENCES).add(MUDWOOD_FENCE);
        valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(MUDWOOD_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.WOODEN_SLABS).add(MUDWOOD_SLAB);
        valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(MUDWOOD_STAIRS);
        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(MUDWOOD_TRAPDOOR);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(MUDWOOD_FENCE_GATE);
        valueLookupBuilder(BlockTags.STANDING_SIGNS).add(MUDWOOD_SIGN);
        valueLookupBuilder(BlockTags.WALL_SIGNS).add(MUDWOOD_WALL_SIGN);
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(MUDWOOD_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(MUDWOOD_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WOODEN_SHELVES).add(MUDWOOD_SHELF);

        valueLookupBuilder(BlockTags.LEAVES).add(MUDWOOD_LEAVES);
        valueLookupBuilder(BlockTags.SAPLINGS).add(MUDWOOD_SAPLING);
        valueLookupBuilder(BlockTags.FLOWER_POTS).add(POTTED_MUDWOOD_SAPLING);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).add(MUDWOOD_LEAVES);

        valueLookupBuilder(ConventionalBlockTags.WOODEN_FENCES).add(MUDWOOD_FENCE);
        valueLookupBuilder(ConventionalBlockTags.FENCE_GATES).add(MUDWOOD_FENCE_GATE);
        valueLookupBuilder(ConventionalBlockTags.WOODEN_FENCE_GATES).add(MUDWOOD_FENCE_GATE);
        valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(STRIPPED_MUDWOOD_LOG);
        valueLookupBuilder(ConventionalBlockTags.STRIPPED_WOODS).add(STRIPPED_MUDWOOD_WOOD);
    }
}
