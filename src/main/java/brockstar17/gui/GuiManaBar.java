package brockstar17.gui;

import brockstar17.capability.ArcaneManaProvider;
import brockstar17.capability.IArcaneMana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class GuiManaBar extends Gui
{
	// private Minecraft mc;
	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	private static final ResourceLocation texture = new ResourceLocation("arcaneascension", "textures/gui/mana_bar.png");

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
		int manabarwidth = (int) (((float) mana.getMana() / mana.getMaxMana()) * 49);
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, manabarwidth, 3);
		String s = "Mana " + mana.getMana() + "/" + mana.getMaxMana();
		yPos += 10;
		mc.fontRendererObj.drawString(s, xPos + 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos - 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos + 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos - 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos, 8453920);
		GlStateManager.popAttrib();
	}

}
