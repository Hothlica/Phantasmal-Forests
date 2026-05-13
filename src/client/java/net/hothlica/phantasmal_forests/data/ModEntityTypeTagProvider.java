package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

import static net.hothlica.phantasmal_forests.registry.ModEntityTypes.*;

public class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider{

    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(EntityTypeTags.BOAT).add(MUDWOOD_BOAT);
    }
}
