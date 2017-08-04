package brockstar17.client;

import brockstar17.ArcaneAscension;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.events.ArcaneGuiHandler;
import brockstar17.network.MessageActiveSlotChange;
import brockstar17.network.NetworkHandler;
import brockstar17.utility.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
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

		Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.ACTIVESPELL;
		EntityPlayer player = Minecraft.getMinecraft().player;

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
				World world = player.world;
				BlockPos pos = player.getPosition();
				player.openGui(ArcaneAscension.instance, ArcaneGuiHandler.SPELL_GUI, world, pos.getX(), pos.getY(), pos.getZ());
				break;
			case USESPELL:
				IArcaneSpells spells = player.getCapability(cspells, null);
				Log.info("Spell " + getCurrentSpell(spells.getActiveSpellSlot(), spells));
				break;
			default:
				break;
			}
		}
	}

	public int getCurrentSpell(int slot, IArcaneSpells spell) {
		switch (slot) {
		case 0:
			return spell.getIcon1();
		case 1:
			return spell.getIcon2();
		case 2:
			return spell.getIcon3();
		default:
			Log.error("Invalid slot number");
			return -1;
		}
	}
}
