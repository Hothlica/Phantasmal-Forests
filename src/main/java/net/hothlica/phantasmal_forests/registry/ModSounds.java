package net.hothlica.phantasmal_forests.registry;

import net.hothlica.phantasmal_forests.PhantasmalForests;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.Optional;

public class ModSounds {

    public static final SoundEvent WEEPING_MUDWOOD_LOG_CHANGE = register("weeping_mudwood_log_changes");

    public static void init() {}

    public static SoundEvent register(String path) {
        Identifier id = PhantasmalForests.id(path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, new SoundEvent(id, Optional.empty()));
    }
}
