package net.hothlica.phantasmal_forests.client;

import net.fabricmc.api.ClientModInitializer;
import net.hothlica.phantasmal_forests.client.render.entity.ModEntityModelLayers;
import net.hothlica.phantasmal_forests.client.render.entity.ModEntityRenderers;

public class PhantasmalForestsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModEntityModelLayers.register();
		ModEntityRenderers.register();
	}
}