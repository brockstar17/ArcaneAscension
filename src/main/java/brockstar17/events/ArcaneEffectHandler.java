package brockstar17.events;

import brockstar17.ArcaneAscension;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArcaneEffectHandler
{
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
				return; // Nothing left to do, return
			}

			// Handle the target's motion
			target.setPosition(target.prevPosX, target.posY, target.prevPosZ); // Freezes the target
			target.setVelocity(0, 0, 0); // Prevents any other movement
		}

	}
}
