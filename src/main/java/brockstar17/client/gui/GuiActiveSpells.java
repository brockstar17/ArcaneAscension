package brockstar17.client.gui;

import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedProvider;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.utility.ArcaneConstants;
import brockstar17.utility.Log;
import brockstar17.utility.SpellIcons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class GuiActiveSpells extends Gui
{
	private static final ResourceLocation texture = new ResourceLocation("arcaneascension", "textures/gui/arcane_gui.png");

	private Minecraft mc;

	public GuiActiveSpells(Minecraft mc)
	{

		this.mc = mc;

		if (!mc.player.isSpectator()) {
			int xPos = 2;
			int yPos = 15;
			mc.getTextureManager().bindTexture(texture);

			// Add this block of code before you draw the section of your texture containing
			// transparency
			GlStateManager.pushAttrib();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableLighting();
			// alpha test and blend needed due to vanilla or Forge rendering bug
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			// Draw the background of the spell hotbar
			drawTexturedModalRect(xPos, yPos, 0, 20, 22, 64);
			// Draw the active slot
			drawActiveSlot(xPos, yPos, mc.player);

			GlStateManager.popAttrib();
		}
	}

	private void drawActiveSlot(int xPos, int yPos, EntityPlayer player) {
		xPos += 1;
		yPos += 1;
		int tx = 24, ty = 21, w = 20, h = 20;
		switch (getActiveSlot(player)) {
		case 0:
			yPos = 16;
			break;
		case 1:
			yPos = 37;
			break;
		case 2:
			yPos = 58;
			break;
		default:
			Log.error("Tried to render an invalid active spell slot");
		}

		drawTexturedModalRect(xPos, yPos, tx, ty, w, h);
		// Draw spell icons
		Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.SPELLS;
		IArcaneSpells slot = player.getCapability(cslot, null);

		int[] cooldowns = slot.getCooldowns();

		xPos += 2;
		// Slot 1
		drawTexturedModalRect(xPos, 18, SpellIcons.getTextX(slot.getIcon(0)), SpellIcons.getTextY(slot.getIcon(0)), 16, 16);

		// Slot 2
		drawTexturedModalRect(xPos, 39, SpellIcons.getTextX(slot.getIcon(1)), SpellIcons.getTextY(slot.getIcon(1)), 16, 16);

		// Slot 3
		drawTexturedModalRect(xPos, 60, SpellIcons.getTextX(slot.getIcon(2)), SpellIcons.getTextY(slot.getIcon(2)), 16, 16);

		drawPossibleLocks(xPos, player);

		// Cool 1
		if (slot.getIcon(0) != -1) {
			drawCooldownOverlay(cooldowns[slot.getIcon(0)], xPos, 18, slot, 0);
		}

		// Cool 2
		if (slot.getIcon(1) != -1) {
			drawCooldownOverlay(cooldowns[slot.getIcon(1)], xPos, 39, slot, 1);
		}

		// Cool 3
		if (slot.getIcon(2) != -1) {
			drawCooldownOverlay(cooldowns[slot.getIcon(2)], xPos, 60, slot, 2);
		}

	}

	private int getActiveSlot(EntityPlayer player) {
		Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.SPELLS;

		IArcaneSpells slot = player.getCapability(cslot, null);

		return slot.getActiveSlot();
	}

	private void drawCooldownOverlay(int dur, int x, int y, IArcaneSpells spells, int icon) {
		if (dur > 0) {

			int raise = (16 * dur) / ArcaneConstants.coolDowns[spells.getIcon(icon)];
			// TX = 236 TY = 22
			// Log.info("Cooldown = " + dur);

			drawTexturedModalRect(x, y, 237, 22 - (16 - raise), 16, 16);
		}

	}

	private void drawPossibleLocks(int xPos, EntityPlayer player) {

		if (!player.isCreative()) {
			IArcaneSpells slot = player.getCapability(ArcaneSpellsProvider.SPELLS, null);
			ILearnedSpells ls = player.getCapability(LearnedProvider.LEARNED, null);

			// Slot 1
			if (slot.getIcon(0) != -1 && !ls.isSpellLearned(slot.getIcon(0)))
				drawTexturedModalRect(xPos, 18, 70, 5, 16, 16);

			// Slot 2
			if (slot.getIcon(1) != -1 && !ls.isSpellLearned(slot.getIcon(1)))
				drawTexturedModalRect(xPos, 39, 70, 5, 16, 16);

			// Slot 3
			if (slot.getIcon(2) != -1 && !ls.isSpellLearned(slot.getIcon(2)))
				drawTexturedModalRect(xPos, 60, 70, 5, 16, 16);
		}

	}
}
