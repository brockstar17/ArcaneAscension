package brockstar17.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderArcaneGui
{
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		// Only draw when rendering experience bar
		if (event.getType() != ElementType.EXPERIENCE)
			return;

		new GuiManaBar(Minecraft.getMinecraft());
		new GuiActiveSpells(Minecraft.getMinecraft());
	}
}
