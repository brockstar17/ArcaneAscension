package brockstar17.client;

import brockstar17.ArcaneAscension;
import brockstar17.events.ArcaneGuiHandler;
import brockstar17.network.MessageActiveSlotChange;
import brockstar17.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyInputHandler
{
	private Keybindings getPressedKey() {
		for (Keybindings key : Keybindings.values()) {
			if (key.isPressed())
				return key;
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
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
			case SPELLGUI:
				EntityPlayer player = Minecraft.getMinecraft().player;
				World world = player.world;
				BlockPos pos = player.getPosition();
				player.openGui(ArcaneAscension.instance, ArcaneGuiHandler.SPELL_GUI, world, pos.getX(), pos.getY(), pos.getZ());

				break;
			default:
				break;
			}
		}
	}

}
