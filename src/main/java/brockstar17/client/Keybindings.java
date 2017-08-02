package brockstar17.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

public enum Keybindings {

	SAS1("key.arcaneascension.activeslot1", Keyboard.KEY_T), SAS2("key.arcaneascension.activeslot2", Keyboard.KEY_G), SAS3("key.arcaneascension.activeslot3", Keyboard.KEY_V);

	private final KeyBinding keybinding;

	private Keybindings(String keyName, int defaultKeyCode)
	{
		keybinding = new KeyBinding(keyName, defaultKeyCode, "key.categories.arcaneascension");
	}

	public KeyBinding getKeybind() {
		return keybinding;
	}

	public boolean isPressed() {
		return keybinding.isPressed();
	}

}
