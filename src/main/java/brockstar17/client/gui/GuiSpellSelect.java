package brockstar17.client.gui;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import brockstar17.Reference;
import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedProvider;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.network.MessageAssignSpell;
import brockstar17.network.NetworkHandler;
import brockstar17.utility.SpellIcons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;

public class GuiSpellSelect extends GuiScreen
{
	private Minecraft mc;
	private EntityPlayer player;
	private String[] elements = { "Air", "Earth", "Fire", "Spirit", "Water" };
	private int[] colors = { 4387918, 7159569, 15870985, 5178163, 4524018 };

	private ArrayList<Point> slotCorners;
	private int slotToHighlight = -1;

	private Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.SPELLS;
	private Capability<ILearnedSpells> cls = LearnedProvider.LEARNED;

	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/arcane_gui.png");

	public GuiSpellSelect(Minecraft mc)
	{
		this.mc = mc;
		this.player = mc.player;
		this.slotCorners = new ArrayList<Point>();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// Draw the default background
		this.drawDefaultBackground();

		mc.getTextureManager().bindTexture(texture);

		// Add this block of code before you draw the section of your texture containing
		// transparency
		GlStateManager.pushAttrib();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		// alpha test and blend needed due to vanilla or Forge rendering bug
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();

		// Draw the actual gui
		drawSpellSelect(mouseX, mouseY);

		GlStateManager.popAttrib();

		// Call to super
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {

		return false;
	}

	private void drawHighlight(int x, int y) {
		drawTexturedModalRect(x, y, 24, 21, 22, 22);

	}

	@Override
	protected void keyTyped(char typedChar, int key) throws IOException {
		EntityPlayer player = Minecraft.getMinecraft().player;
		IArcaneSpells spell = player.getCapability(cslot, null);
		ILearnedSpells ls = player.getCapability(cls, null);

		// Assign to first slot
		if (key == Keyboard.KEY_T || key == Keyboard.KEY_1) {
			if (!spellAlreadyActive(slotToHighlight, spell)) {
				NetworkHandler.sendToServer(new MessageAssignSpell(SpellIcons.getSpellId(slotToHighlight), spell.getIcon(1), spell.getIcon(2)));

			}
			// Spell is already assigned to a slot
			else {

				player.sendMessage(new TextComponentString("That spell is already active." + TextFormatting.BOLD));

			}
		}
		// Assign to second slot
		else if (key == Keyboard.KEY_G || key == Keyboard.KEY_2) {
			if (!spellAlreadyActive(slotToHighlight, spell)) {
				NetworkHandler.sendToServer(new MessageAssignSpell(spell.getIcon(0), SpellIcons.getSpellId(slotToHighlight), spell.getIcon(2)));

			}
			// Spell is already assigned to a slot
			else {

				player.sendMessage(new TextComponentString("That spell is already active." + TextFormatting.BOLD));

			}
		}
		// Assign to third slot
		else if (key == Keyboard.KEY_V || key == Keyboard.KEY_3) {
			if (!spellAlreadyActive(slotToHighlight, spell)) {
				NetworkHandler.sendToServer(new MessageAssignSpell(spell.getIcon(0), spell.getIcon(1), SpellIcons.getSpellId(slotToHighlight)));

			}
			// Spell is already assigned to a slot
			else {

				player.sendMessage(new TextComponentString("That spell is already active." + TextFormatting.BOLD));

			}
		}
		else if (key == Keyboard.KEY_Y || key == Keyboard.KEY_E) {
			this.mc.displayGuiScreen(null);
		}
		super.keyTyped(typedChar, key);
	}

	private void drawSpellSelect(int mouseX, int mouseY) {

		int sx = 44, rx = sx;
		int sy = 15;

		for (int r = 0; r < 5; r++) {
			sx = rx;
			for (int c = 0; c < 5; c++) {

				drawTexturedModalRect(sx, sy, 46, 20, 22, 22);
				slotCorners.add(new Point(sx, sy));
				sx += 22;
			}

			sy += 21;

		}

		slotToHighlight = getSlotClicked(mouseX, mouseY);

		if (slotToHighlight != -1) {
			Point p = slotCorners.get(slotToHighlight);
			drawHighlight(p.x + 1, p.y + 1);
			updateScreen();
		}

		sy = 15;
		drawSpellIcons(44, sy);

		sx += 22;

		for (int r = 0; r < 5; r++) {
			drawColoredString(elements[r], sx, sy + 6, colors[r]);
			sy += 22;

		}

	}

	private void drawSpellIcons(int sx, int sy) {
		ILearnedSpells ls = player.getCapability(cls, null);

		sx += 3;
		sy += 3;
		int rx = sx;
		int tsx = 70, tsy = 23, rtx = tsx;
		for (int r = 0; r < 5; r++) {
			sx = rx;
			for (int c = 0; c < 5; c++) {
				drawTexturedModalRect(sx, sy, tsx + (18 * c), tsy + (18 * r), 16, 16);

				if (!player.isCreative() && SpellIcons.getSpellId(getSlotClicked(sx, sy)) != -1) {
					if (!ls.isSpellLearned(SpellIcons.getSpellId(getSlotClicked(sx, sy))))
						drawTexturedModalRect(sx, sy, 70, 5, 16, 16);
				}

				sx += 22;

			}
			sy += 21;

		}
	}

	private void drawColoredString(String s, int xPos, int yPos, int color) {
		mc.fontRendererObj.drawString(s, xPos + 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos - 1, yPos, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos + 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos - 1, 0);
		mc.fontRendererObj.drawString(s, xPos, yPos, color);
	}

	private int getSlotClicked(int x, int y) {
		int num = 0;
		for (Point p : slotCorners) {
			if (p.x < x && x < p.x + 22) {
				if (p.y < y && y < p.y + 22) {
					return num;
				}
			}
			num++;
		}
		return -1;
	}

	private boolean spellAlreadyActive(int spell, IArcaneSpells spells) {
		if (SpellIcons.getSpellId(spell) != -1 && (spells.getIcon(0) == SpellIcons.getSpellId(spell) || spells.getIcon(1) == SpellIcons.getSpellId(spell) || spells.getIcon(2) == SpellIcons.getSpellId(spell))) {
			return true;
		}
		return false;
	}

	private boolean isSpellLearned(int spell, ILearnedSpells ls, EntityPlayer player) {

		if (player.isCreative()) {
			return true;
		}

		if (spell != -1) {
			if (ls.isSpellLearned(spell)) {
				return true;
			}
		}

		return false;
	}

}
