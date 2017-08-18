package brockstar17.events;

import java.util.Random;

import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedProvider;
import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.mana.IArcaneMana;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.items.ArcaneItems;
import brockstar17.network.MessageActiveSlotChange;
import brockstar17.network.MessageAssignSpell;
import brockstar17.network.MessageLearnSpell;
import brockstar17.network.MessageManaChange;
import brockstar17.network.NetworkHandler;
import brockstar17.utility.ArcaneUtils;
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
	private Capability<ILearnedSpells> cls = LearnedProvider.LEARNED;

	@SubscribeEvent
	public void entityDied(LivingDeathEvent e) {
		// If mob killed by player
		if (e.getSource().getEntity() instanceof EntityPlayer) {
			// Player instance
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			// The mob killed
			EntityLiving target = (EntityLiving) e.getEntity();
			// Instance of mana capability
			IArcaneMana mana = player.getCapability(cmana, null);

			// Gain 0-2 mana if the mob was not a boss
			if (target.isNonBoss()) {
				mana.gainMana(r.nextInt(3));
			}
			// If it is a boss, gain 1/4 to 1/2 of the boss' health
			else {
				mana.gainMana((int) (target.getMaxHealth() / 4 + (r.nextInt((int) (target.getMaxHealth() / 4)))));
			}

			NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);
		}

		// The mob has died of fire damage
		if (e.getSource().isFireDamage()) {
			// The Random from the world instance
			Random r = e.getEntity().world.rand;
			// 1 in five chance to drop arcane ash
			if (r.nextInt(5) == 3) {
				// A cool conditional resulting in a 1 in 9 chance of dropping between 1 and 5 ash
				// otherwise drop 1 ash
				e.getEntityLiving().dropItem(ArcaneItems.arcane_ash, r.nextInt(9) == 5 ? r.nextInt(5) + 1 : 1);
			}
		}

		// The entity that died is the player handle all data transfer that needs to be saved or
		// updated on death
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity(); // Player instance
			IArcaneMana mana = player.getCapability(cmana, null); // An instance of mana capabilty

			mana.setMana(0); // Set the player's mana to zero
			// Update the mana value on the client side, needed for rendering mana bar correctly
			NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);

			// An instance of learned spells capability
			ILearnedSpells ls = player.getCapability(cls, null);
			// Persist the spells that the player has learned
			NetworkHandler.sendToServer(new MessageLearnSpell(ArcaneUtils.intArrBoolArr(ls.getLearnedArray())));

			// An instance of the spells capabilty
			IArcaneSpells spells = player.getCapability(cspells, null);
			// Persist the assigned spells
			NetworkHandler.sendToServer(new MessageAssignSpell(spells.getIcon(0), spells.getIcon(1), spells.getIcon(2)));

		}
	}

	@SubscribeEvent()
	public void onPlayerJoinWorld(EntityJoinWorldEvent e) {
		// Check if the event's entity object is an instance of a player entity
		if (e.getEntity() instanceof EntityPlayerMP) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			// Get an instance of the mana capability
			IArcaneMana mana = player.getCapability(cmana, null);
			// Get an instance of the spells capability
			IArcaneSpells spells = player.getCapability(cspells, null);
			// Get an instance of the learned spells capability
			ILearnedSpells ls = player.getCapability(cls, null);
			// There is no need to update client side mana as the default value is zero anyway
			if (mana.getMana() != 0)
				// Load the player's mana to client side, this is for rendering purposes
				NetworkHandler.sendTo(new MessageManaChange(mana.getMana()), (EntityPlayerMP) player);

			// Load the active spells when player joins the world
			NetworkHandler.sendTo(new MessageAssignSpell(spells.getIcon(0), spells.getIcon(1), spells.getIcon(2)), (EntityPlayerMP) player);
			// Load the active spell slot, this should fix the bug of the wrong spell firing after
			// joining world
			NetworkHandler.sendTo(new MessageActiveSlotChange(spells.getActiveSlot()), (EntityPlayerMP) player);
			// Load the spells that the player has learned
			NetworkHandler.sendTo(new MessageLearnSpell(ArcaneUtils.intArrBoolArr(ls.getLearnedArray())), (EntityPlayerMP) player);
		}

	}

}
