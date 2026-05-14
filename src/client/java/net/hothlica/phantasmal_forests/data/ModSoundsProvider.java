package net.hothlica.phantasmal_forests.data;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.hothlica.phantasmal_forests.registry.ModSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, SoundExporter soundExporter) {
        soundExporter.add(ModSounds.WEEPING_MUDWOOD_LOG_CHANGE, SoundTypeBuilder.of().sound(SoundTypeBuilder.RegistrationBuilder.ofEvent(SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM)).subtitle("subtitles.phantasmal-forests.block.weeping_mudwood_log_change"));
    }

    @Override
    public String getName() {
        return "Sounds";
    }
}
