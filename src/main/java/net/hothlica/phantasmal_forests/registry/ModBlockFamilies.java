package net.hothlica.phantasmal_forests.registry;

import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;

public class ModBlockFamilies {
    // === Mudwood ===
    public static final BlockFamily MUDWOOD = BlockFamilies.familyBuilder(ModBlocks.MUDWOOD_PLANKS)
            .button(ModBlocks.MUDWOOD_BUTTON)
            .fence(ModBlocks.MUDWOOD_FENCE)
            .fenceGate(ModBlocks.MUDWOOD_FENCE_GATE)
            .pressurePlate(ModBlocks.MUDWOOD_PRESSURE_PLATE)
            .sign(ModBlocks.MUDWOOD_SIGN, ModBlocks.MUDWOOD_WALL_SIGN)
            .slab(ModBlocks.MUDWOOD_SLAB)
            .stairs(ModBlocks.MUDWOOD_STAIRS)
            .door(ModBlocks.MUDWOOD_DOOR)
            .trapdoor(ModBlocks.MUDWOOD_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
}
