package brockstar17.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

/**
 * This enum contains all the keybindings for my mod
 * 
 * @author Brockstar17
 */
public enum Keybindings {

	/*
	 * SAS: short for Set Active Spell, used to change the active spell. USESPELL: should be self
	 * explanatory, but use the active spell SPELLGUI: open the spell select gui
	 */
	SAS1("key.arcaneascension.activeslot1", Keyboard.KEY_T),
	SAS2("key.arcaneascension.activeslot2", Keyboard.KEY_G),
	SAS3("key.arcaneascension.activeslot3", Keyboard.KEY_V),
	USESPELL("key.arcaneascension.usespell", Keyboard.KEY_F),
	SPELLGUI("key.arcaneascension.spellgui", Keyboard.KEY_Y);

	private final KeyBinding keybinding; // This is an instance of minecraft's keybinding

	// Adds keybindings to the keybinding gui in options
	private Keybindings(String keyName, int defaultKeyCode)
	{
		keybinding = new KeyBinding(keyName, defaultKeyCode, "key.categories.arcaneascension");
	}

	// Return keybinding
	public KeyBinding getKeybind() {
		return keybinding;
	}

	// Return true if the keybinding is being pressed
	public boolean isPressed() {
		return keybinding.isPressed();
	}

}
