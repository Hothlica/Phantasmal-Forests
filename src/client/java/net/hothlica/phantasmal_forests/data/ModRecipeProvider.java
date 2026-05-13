package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hothlica.phantasmal_forests.registry.ModBlockFamilies;
import net.hothlica.phantasmal_forests.registry.ModItems;
import net.hothlica.phantasmal_forests.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;

import java.util.concurrent.CompletableFuture;

import static net.hothlica.phantasmal_forests.registry.ModBlocks.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput){
            @Override
            public void buildRecipes() {
                // === Mudwood ===
                generateRecipes(ModBlockFamilies.MUDWOOD, FeatureFlagSet.of(FeatureFlags.VANILLA));
                this.planksFromLogs(MUDWOOD_PLANKS, ModItemTags.MUDWOOD_LOGS, 4);
                this.woodFromLogs(MUDWOOD_WOOD, MUDWOOD_LOG);
                this.woodFromLogs(STRIPPED_MUDWOOD_WOOD, STRIPPED_MUDWOOD_LOG);
                this.hangingSign(MUDWOOD_HANGING_SIGN, STRIPPED_MUDWOOD_LOG);
                this.shelf(MUDWOOD_SHELF, STRIPPED_MUDWOOD_LOG);

                this.woodenBoat(ModItems.MUDWOOD_BOAT, MUDWOOD_PLANKS);
                this.chestBoat(ModItems.MUDWOOD_CHEST_BOAT, ModItems.MUDWOOD_BOAT);

            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
