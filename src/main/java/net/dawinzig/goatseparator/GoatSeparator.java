package net.dawinzig.goatseparator;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.StickyKeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoatSeparator implements ClientModInitializer {
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LoggerFactory.getLogger("goatseparator");

	public static KeyBinding keyBinding;
	private static boolean wasPressed = false;
	@Override
	public void onInitializeClient() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding(
				"key.goatseparator.show",
				GLFW.GLFW_KEY_LEFT_ALT,
				"category.goatseparator.main",
				() -> true
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keyBinding.isPressed() && !wasPressed) {
				assert client.player != null; // should never happen
				client.player.sendMessage(Text.translatable("text.goatseparator.toggled.on"), true);
				wasPressed = keyBinding.isPressed();
			} else if (!keyBinding.isPressed() && wasPressed) {
				assert client.player != null; // should never happen
				client.player.sendMessage(Text.translatable("text.goatseparator.toggled.off"), true);
				wasPressed = keyBinding.isPressed();
			}
		});
	}
}
