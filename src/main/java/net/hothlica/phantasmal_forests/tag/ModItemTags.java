package net.hothlica.phantasmal_forests.tag;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> MUDWOOD_LOGS = of("mudwood_logs");

    private static TagKey<Item> of(String id) {
        return TagKey.create(Registries.ITEM, PhantasmalForests.id(id));
    }
}
