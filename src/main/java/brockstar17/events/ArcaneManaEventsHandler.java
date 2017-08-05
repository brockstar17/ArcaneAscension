package brockstar17.events;

import java.util.Random;

import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.mana.IArcaneMana;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.network.MessageAssignSpell;
import brockstar17.network.MessageManaChange;
import brockstar17.network.NetworkHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
	private Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;

	@SubscribeEvent
	public void playerKillMob(LivingDeathEvent e) {
		if (e.getSource().getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			EntityLiving target = (EntityLiving) e.getEntity();
			IArcaneMana mana = getInstance(player);
			mana.gainMana(r.nextInt((int) target.getMaxHealth() / 2) + 1);
			NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);
		}
	}

	@SubscribeEvent
	public void removeManaOnPlayerDeath(LivingDeathEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			IArcaneMana mana = getInstance(player);
			IArcaneSpells spells = player.getCapability(cspells, null);
			mana.setMana(0);
			NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);

			NetworkHandler.sendTo(new MessageAssignSpell(spells.getIcon(0), spells.getIcon(1), spells.getIcon(2)), (EntityPlayerMP) player);
		}
	}

	@SubscribeEvent()
	public void onPlayerJoinWorld(EntityJoinWorldEvent e) {

		if (e.getEntity() instanceof EntityPlayerMP) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			IArcaneMana mana = getInstance(player);
			IArcaneSpells spells = player.getCapability(cspells, null);
			if (mana.getMana() != 0)
				NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);

			NetworkHandler.sendTo(new MessageAssignSpell(spells.getIcon(0), spells.getIcon(1), spells.getIcon(2)), (EntityPlayerMP) player);

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
