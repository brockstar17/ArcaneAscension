package brockstar17.client.gui;

import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.mana.IArcaneMana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

/**
 * This is a gui. More specifically it is an in-game overlay similar to the experience bar.<br>
 * As most guis that are not containers are pretty similar, this will be the one only fully
 * documented.<br>
 * This gui will render a mana bar in the top left corner of the client's screen.<br>
 * Note: Most if not all non container gui's are client side only, when getting capability data as I
 * do, a packet system is required. I will document the packet handler for this gui.
 * 
 * @author Brockstar17
 */
public class GuiManaBar extends Gui
{
	// As I will be using the mana capability to draw how much mana the player has we need a default
	// instance of the mana capability
	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	// This is the resource location of my gui texture
	private static final ResourceLocation texture = new ResourceLocation("arcaneascension", "textures/gui/arcane_gui.png");

	// The constructor has a Minecraft parameter however you can get this if your gui is only ever
	// run client side with Minecraft.getMinecraft();
	public GuiManaBar(Minecraft mc)
	{
		if (!mc.player.isSpectator()) {
			// An instance of my mana capability
			IArcaneMana mana = mc.player.getCapability(cmana, null);
			// If the capability is equal to null or the max mana is zero then the capability has
			// not
			// been registered or correctly attached to the player. I return because continuing will
			// crash the game.
			if (mana == null || mana.getMaxMana() == 0) {
				return;
			}

			int xPos = 2; // I start the gui at 2 pixels from the left
			int yPos = 2; // I start the gui at 2 pixels from the top
			mc.getTextureManager().bindTexture(texture);

			/*
			 * The following open GL code and comments are in large part the work of CoolAlias I
			 * recommend checking out his tutorials and the mods he has made as they are great for
			 * learning java and Minecraft modding
			 */
			// Add this block of code before you draw the section of your texture containing
			// transparency
			GlStateManager.pushAttrib();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableLighting();
			// alpha test and blend needed due to vanilla or Forge rendering bug
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			// Here we draw the background bar which contains a transparent section; note the new
			// size
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

}
