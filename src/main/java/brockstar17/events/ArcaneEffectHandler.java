package brockstar17.events;

import brockstar17.ArcaneAscension;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArcaneEffectHandler
{
	private static ArcaneAscension aa;
	private Potion[] pcd = { aa.entombCD, aa.fireballCD, aa.freezeCD, aa.gatewayCD, aa.healCD, aa.lightningCD, aa.rHealCD, aa.whirlwindCD };

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
			for (int i = 0; i < pcd.length; i++) {
				Potion p = pcd[i];
				// Is the current cooldown effect active
				if (target.isPotionActive(p)) {
					// Is the duration zero
					if (target.getActivePotionEffect(p).getDuration() == 0) {
						// It was so remove the effect
						target.removePotionEffect(p);
					}

				}
			}
		}

	}

}
