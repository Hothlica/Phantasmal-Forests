package net.hothlica.phantasmal_forests.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;


public class ModItems {
    //Make itemgroup (literally the tab thing you see at the top of your inventory)
    public static final ResourceKey<CreativeModeTab> PHANTASMAL_FORESTS_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, PhantasmalForests.id("phantasmal_forests_group"));
    public static final CreativeModeTab PHANTASMAL_FORESTS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PHANTASMAL_FORESTS_GROUP_KEY,
        FabricCreativeModeTab.builder()
            .title(Component.translatable("item_group.phantasmal_forests"))
            .icon(() -> new ItemStack(ModBlocks.MUDWOOD_LOG))
            .build()
        );

    //INITIALIZE ITEMS HERE
    public static final Item STEPPING_STONE = register("stepping_stone", new Item.Properties());

    public static void init() {}

    //General block item registration method
    public static Item registerBlockItem(Block block) {
        return registerBlockItem(block, p -> new BlockItem(block, p));
    }

    //For weird blocks
    public static Item registerBlockItem(Block block, Function<Item.Properties, Item> factory) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        return register(id, factory.apply(new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }

    //General item registration method
    public static Item register(String id, Item.Properties settings) {
        return register(id, Item::new, settings);
    }

    //For weird items
    public static Item register(String id, Function<Item.Properties, Item> factory, Item.Properties settings) {
        Identifier identifier = PhantasmalForests.id(id);
        return register(identifier, factory.apply(settings.setId(ResourceKey.create(Registries.ITEM, identifier))));
    }

    public static Item register(Identifier id, Item item) {
        Item returnItem = Registry.register(BuiltInRegistries.ITEM, id, item);
        CreativeModeTabEvents.modifyOutputEvent(PHANTASMAL_FORESTS_GROUP_KEY).register(itemGroup -> itemGroup.accept(returnItem));
        return returnItem;
    }
}
