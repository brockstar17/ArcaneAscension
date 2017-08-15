package brockstar17.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is the event handler that handles the drawing of the mana bar and my active spells gui.<br>
 * I have it set to fire when the experience bar is drawn.
 * 
 * @author Brockstar17
 */
public class RenderArcaneGui
{
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		// Only draw when rendering experience bar
		if (event.getType() != ElementType.EXPERIENCE)
			return;
		// Draw the guis that need to be drawn at this time
		new GuiManaBar(Minecraft.getMinecraft());
		new GuiActiveSpells(Minecraft.getMinecraft());
	}
}
