package net.hothlica.phantasmal_forests.registry.helpers;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockPropertyBuilder {
    private final Function<BlockBehaviour.Properties, Block> blockType;
    private final BlockBehaviour.Properties settings;
    private final ArrayList<Consumer<Block>> postRegisterMethods = new ArrayList<>();
    private final ArrayList<Consumer<BlockItem>> postRegisterItemMethods = new ArrayList<>();

    public BlockPropertyBuilder(Function<BlockBehaviour.Properties, Block> blockType, BlockBehaviour.Properties settings) {
        this.blockType = blockType;
        this.settings = settings;
    }

    @SafeVarargs
    public final BlockPropertyBuilder postRegister(Consumer<Block>... methods) {
        this.postRegisterMethods.addAll(Arrays.asList(methods));
        return this;
    }

    @SafeVarargs
    public final BlockPropertyBuilder postRegisterItem(Consumer<BlockItem>... methods) {
        this.postRegisterItemMethods.addAll(Arrays.asList(methods));
        return this;
    }

    public void runPostRegisterMethods(Block block) {
        postRegisterMethods.forEach(method -> method.accept(block));
    }

    public void runPostRegisterItemMethods(BlockItem blockItem) {
        postRegisterItemMethods.forEach(method -> method.accept(blockItem));
    }

    public Function<BlockBehaviour.Properties, Block> getBlockType() {return blockType;}
    public BlockBehaviour.Properties getProperties() {return settings;}
}
