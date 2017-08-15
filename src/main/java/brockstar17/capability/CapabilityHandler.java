package brockstar17.capability;

import brockstar17.Reference;
import brockstar17.capability.learned.LearnedProvider;
import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class is responsible for attaching all my capabilities to the entity that I want to attach
 * each capability to.<br>
 * You can attach a capability to any entity. However there may be a preferred method of storing
 * data with other entities that I do not yet know of.
 * 
 * @author Brockstar17
 */
public class CapabilityHandler
{
	// Each capability needs a resource location, I'm not totally sure why, I'm guessing it's just
	// so forge knows where the capability came from
	public static final ResourceLocation MANA = new ResourceLocation(Reference.MODID, "mana");
	public static final ResourceLocation ACTIVESPELL = new ResourceLocation(Reference.MODID, "activespell");
	public static final ResourceLocation LEARNED = new ResourceLocation(Reference.MODID, "learned");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity e) {

		// I only want to attach the following capabilities to the player so I return if the target
		// entity is not an instance of an EntityPlayer
		if (!(e.getEntity() instanceof EntityPlayer))
			return;

		// Now that I know the target of the event is an EntityPlayer I can attach all my player
		// capabilities to it
		e.addCapability(MANA, new ArcaneManaProvider());
		e.addCapability(ACTIVESPELL, new ArcaneSpellsProvider());
		e.addCapability(LEARNED, new LearnedProvider());
	}
}
