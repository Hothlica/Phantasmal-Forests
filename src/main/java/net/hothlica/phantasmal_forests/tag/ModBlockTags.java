package net.hothlica.phantasmal_forests.tag;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> MUDWOOD_LOGS = of("mudwood_logs");

    public static TagKey<Block> of(String id) {
        return TagKey.create(Registries.BLOCK, PhantasmalForests.id(id));
    }

}
