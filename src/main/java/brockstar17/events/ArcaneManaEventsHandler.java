package brockstar17.events;

import java.util.Random;

import brockstar17.capability.ArcaneManaProvider;
import brockstar17.capability.IArcaneMana;
import brockstar17.network.MessageManaChange;
import brockstar17.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class handles vanilla events involving Arcane Mana
 * 
 * @author Brockstar17
 */
public class ArcaneManaEventsHandler
{
	private Random r = new Random();
	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	@SubscribeEvent
	public void playerKillMob(LivingDeathEvent e) {
		if (e.getSource().getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			IArcaneMana mana = getInstance(player);
			mana.gainMana(r.nextInt(10) + 1);
			NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);
		}
	}

	/**
	 * Get an instance of IArcaneMana.
	 * 
	 * @param player
	 *            an instance of the player
	 * @return an instance of IArcaneMana
	 */
	private IArcaneMana getInstance(EntityPlayer player) {
		return player.getCapability(cmana, null);
	}
}
