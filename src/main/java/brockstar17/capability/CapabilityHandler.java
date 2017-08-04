package brockstar17.capability;

import brockstar17.Reference;
import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
	public static final ResourceLocation MANA = new ResourceLocation(Reference.MODID, "mana");
	public static final ResourceLocation ACTIVESPELL = new ResourceLocation(Reference.MODID, "activespell");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity e) {

		if (!(e.getEntity() instanceof EntityPlayer))
			return;

		e.addCapability(MANA, new ArcaneManaProvider());
		e.addCapability(ACTIVESPELL, new ArcaneSpellsProvider());
	}
}
