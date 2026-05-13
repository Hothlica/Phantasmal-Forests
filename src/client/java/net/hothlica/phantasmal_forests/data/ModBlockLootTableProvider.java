package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.hothlica.phantasmal_forests.registry.ModBlocks.*;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    protected ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        // Saplings
        dropSelf(MUDWOOD_SAPLING);

        // Potted plants
        dropPottedContents(POTTED_MUDWOOD_SAPLING);

        // Leaves
        add(MUDWOOD_LEAVES, createLeavesDrops(MUDWOOD_LEAVES, MUDWOOD_SAPLING, SAPLING_DROP_CHANCE));

        // === Mudwood ===
        dropSelf(STRIPPED_MUDWOOD_LOG);
        dropSelf(MUDWOOD_LOG);
        dropSelf(STRIPPED_MUDWOOD_WOOD);
        dropSelf(MUDWOOD_WOOD);
        dropSelf(MUDWOOD_PLANKS);
        dropSelf(MUDWOOD_STAIRS);
        add(MUDWOOD_SLAB, this::createSlabItemTable);
        dropSelf(MUDWOOD_FENCE);
        dropSelf(MUDWOOD_FENCE_GATE);
        add(MUDWOOD_DOOR, this::createDoorTable);
        dropSelf(MUDWOOD_TRAPDOOR);
        dropSelf(MUDWOOD_BUTTON);
        dropSelf(MUDWOOD_PRESSURE_PLATE);
        dropSelf(MUDWOOD_SIGN);
        dropSelf(MUDWOOD_HANGING_SIGN);
        dropSelf(MUDWOOD_WALL_SIGN);
        dropSelf(MUDWOOD_WALL_HANGING_SIGN);
        dropSelf(MUDWOOD_SHELF);
    }
}
