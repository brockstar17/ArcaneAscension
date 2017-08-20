package brockstar17.events;

import brockstar17.ArcaneAscension;
import brockstar17.network.MessageSyncCooldown;
import brockstar17.network.NetworkHandler;
import brockstar17.utility.ArcaneUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArcaneEffectHandler
{

	private static ArcaneUtils au;

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent e) {

		// The target, must be EntityLivingBase because EntityPlayerSP and MP do not cast to
		// EntityLiving nicely
		EntityLivingBase target = e.getEntityLiving();
		if (target.isPotionActive(ArcaneAscension.freezeEffect)) { // Check if the freeze effect is
																	 // active on the entity
			// Check if duration is zero
			if (target.getActivePotionEffect(ArcaneAscension.freezeEffect).getDuration() == 0) {
				target.removePotionEffect(ArcaneAscension.freezeEffect); // It was so remove the
																		 // effect
			}

			// Handle the target's motion
			target.setPosition(target.prevPosX, target.posY, target.prevPosZ); // Freezes the target
			target.setVelocity(0, 0, 0); // Prevents any other movement
		}

		// The target is a player entity
		if (target instanceof EntityPlayer) {
			// Iterate through the cooldown effects
			for (int i = 0; i < au.pcd.length; i++) {
				Potion p = au.pcd[i];
				// Is the current cooldown effect active
				if (target.isPotionActive(p)) {
					// Is the duration zero
					if (target.getActivePotionEffect(p).getDuration() == 0) {
						// It was so remove the effect
						// NetworkHandler.sendTo(new MessageSyncCooldown(i, 0), (EntityPlayerMP)
						// target);

						target.removePotionEffect(p);
					}
					else {
						NetworkHandler.sendTo(new MessageSyncCooldown(i, target.getActivePotionEffect(p).getDuration() - 1), (EntityPlayerMP) target);

					}

				}
			}
		}

	}

}
