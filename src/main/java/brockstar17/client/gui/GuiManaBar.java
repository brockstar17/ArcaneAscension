package brockstar17.client.gui;

import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.mana.IArcaneMana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class GuiManaBar extends Gui
{

	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	private static final ResourceLocation texture = new ResourceLocation("arcaneascension", "textures/gui/arcane_gui.png");

	public GuiManaBar(Minecraft mc)
	{
		IArcaneMana mana = mc.player.getCapability(cmana, null);
		if (mana == null || mana.getMaxMana() == 0) {
			return;
		}

		int xPos = 2;
		int yPos = 2;
		mc.getTextureManager().bindTexture(texture);

		// Add this block of code before you draw the section of your texture containing
		// transparency
		GlStateManager.pushAttrib();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		// alpha test and blend needed due to vanilla or Forge rendering bug
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		// Here we draw the background bar which contains a transparent section; note the new size
		drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);
		// You can keep drawing without changing anything
		int manabarwidth = (int) (((float) mana.getMana() / mana.getMaxMana()) * 53);
		drawTexturedModalRect(xPos + 1, yPos + 1, 0, 9, manabarwidth, 9);
		String s = "Mana " + mana.getMana() + "/" + mana.getMaxMana();
		xPos += 60;
		mc.fontRendererObj.drawString(s, xPos + 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos - 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos + 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos - 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos, 4353012);
		GlStateManager.popAttrib();
	}

}
