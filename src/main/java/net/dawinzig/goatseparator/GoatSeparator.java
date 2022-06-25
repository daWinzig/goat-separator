package net.dawinzig.goatseparator;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.StickyKeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoatSeparator implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("goatseparator");

	public static KeyBinding keyBinding;
	@Override
	public void onInitializeClient() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
				"key.goatseparator.show", // The translation key of the keybinding's name
				GLFW.GLFW_KEY_LEFT_ALT, // The keycode of the key
				"category.goatseparator.main", // The translation key of the keybinding's category.
				() -> true
		));
	}
}
