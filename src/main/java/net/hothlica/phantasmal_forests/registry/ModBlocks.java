package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.hothlica.phantasmal_forests.registry.helpers.BlockHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {

    // === Mudwood ===
    static BlockHelper MudwoodHelper = new BlockHelper(MapColor.CLAY, true);
    public static final Block MUDWOOD_LOG = register("mudwood_log", RotatedPillarBlock::new, MudwoodHelper.log(MapColor.COLOR_BROWN));
    public static final Block MUDWOOD_WOOD = register("mudwood_wood", RotatedPillarBlock::new, MudwoodHelper.wood(MapColor.COLOR_BROWN));
    public static final Block STRIPPED_MUDWOOD_LOG = register("stripped_mudwood_log", RotatedPillarBlock::new, MudwoodHelper.stripped());
    public static final Block STRIPPED_MUDWOOD_WOOD = register("stripped_mudwood_wood", RotatedPillarBlock::new, MudwoodHelper.stripped());


    public static void init() {}

    public static <B extends Block> B register(String id, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
        B block = registerWithoutItem(id, factory, properties);
        ModItems.registerBlockItem(block);
        return block;
    }

    public static <B extends Block> B registerWithoutItem(String id, Function<BlockBehaviour.Properties, B> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, PhantasmalForests.id(id));
        B block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
}
