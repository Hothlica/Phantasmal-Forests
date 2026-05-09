package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.registry.helpers.WoodHelper;
import net.hothlica.phantasmal_forests.registry.helpers.BlockPropertyBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {

    // === Mudwood ===
    private static final WoodHelper MudwoodHelper = new WoodHelper(MapColor.CLAY, MapColor.COLOR_GREEN, MapColor.COLOR_BROWN, ModWoodTypes.MUDWOOD);

    public static final Block MUDWOOD_LOG = register("mudwood_log", MudwoodHelper.log());
    public static final Block MUDWOOD_WOOD = register("mudwood_wood", MudwoodHelper.wood());
    public static final Block STRIPPED_MUDWOOD_LOG = register("stripped_mudwood_log", MudwoodHelper.stripped());
    public static final Block STRIPPED_MUDWOOD_WOOD = register("stripped_mudwood_wood", MudwoodHelper.stripped());
    public static final Block MUDWOOD_PLANKS = register("mudwood_planks", MudwoodHelper.planks());
    public static final Block MUDWOOD_SLAB = register("mudwood_slab", MudwoodHelper.slab());
    public static final Block MUDWOOD_STAIRS = register("mudwood_stairs", MudwoodHelper.stairs());
    public static final Block MUDWOOD_FENCE = register("mudwood_fence", MudwoodHelper.fence());
    public static final Block MUDWOOD_FENCE_GATE = register("mudwood_fence_gate", MudwoodHelper.fenceGate());
    public static final Block MUDWOOD_TRAPDOOR = register("mudwood_trapdoor", MudwoodHelper.trapdoor());
    public static final Block MUDWOOD_DOOR = register("mudwood_door", MudwoodHelper.door());
    public static final Block MUDWOOD_BUTTON = register("mudwood_button", MudwoodHelper.button());
    public static final Block MUDWOOD_PRESSURE_PLATE = register("mudwood_pressure_plate", MudwoodHelper.pressurePlate());
    public static final Block MUDWOOD_SIGN = register("mudwood_sign", MudwoodHelper.sign());
    public static final Block MUDWOOD_WALL_SIGN = register("mudwood_wall_sign", MudwoodHelper.wallSign());
    public static final Block MUDWOOD_HANGING_SIGN = register("mudwood_hanging_sign", MudwoodHelper.hangingSign());
    public static final Block MUDWOOD_WALL_HANGING_SIGN = register("mudwood_wall_hanging_sign", MudwoodHelper.wallHangingSign());
    public static final Block MUDWOOD_SHELF = register("mudwood_shelf", MudwoodHelper.shelf());

    public static final Block MUDWOOD_LEAVES = register("mudwood_leaves", MudwoodHelper.leaves(0x4A461E));

    //public static final SaplingBlock MUDWOOD_SAPLING = register("mudwood_sapling",);
    //public static FlowerPotBlock POTTED_MUDWOOD_SAPLING;



    public static void init() {}

    public static Block register(String id, BlockPropertyBuilder properties) {
        Block block = registerWithoutItem(id, properties.getBlockType(), properties.getProperties());
        ModItems.registerBlockItem(block);
        return block;
    }

    public static <B extends Block> B registerWithoutItem(String id, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, PhantasmalForests.id(id));
        B block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
}
