package brockstar17.client;

import brockstar17.network.MessageActiveSlotChange;
import brockstar17.network.NetworkHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler
{
	private Keybindings getPressedKey() {
		for (Keybindings key : Keybindings.values()) {
			if (key.isPressed())
				return key;
		}
		return null;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		Keybindings key = getPressedKey();
		if (key != null) {
			switch (key) {
			case SAS1:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(0));
				break;
			case SAS2:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(1));
				break;
			case SAS3:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(2));
				break;
			}
		}
	}
}
