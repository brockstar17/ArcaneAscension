package brockstar17.client.gui;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import brockstar17.Reference;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.network.MessageAssignSpell;
import brockstar17.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

public class GuiSpellSelect extends GuiScreen
{
	private Minecraft mc;
	private boolean isGuiOpen;
	private String[] elements = { "Air", "Earth", "Fire", "Spirit", "Water" };
	private int[] colors = { 4387918, 7159569, 15870985, 5178163, 4524018 };

	private ArrayList<Point> slotCorners;
	private int slotToHighlight = -1;

	private Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.ACTIVESPELL;

	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/arcane_gui.png");

	public GuiSpellSelect(Minecraft mc)
	{
		this.mc = mc;
		this.slotCorners = new ArrayList<Point>();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// Draw the default background
		this.drawDefaultBackground();
		// Draw highlight

		// Draw the actual gui
		drawSpellSelect(mouseX, mouseY);

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
		switch (key) {
		case Keyboard.KEY_T:
			NetworkHandler.sendToServer(new MessageAssignSpell(getSpellIcon(slotToHighlight), spell.getIcon2(), spell.getIcon3()));
			break;
		case Keyboard.KEY_G:
			NetworkHandler.sendToServer(new MessageAssignSpell(spell.getIcon1(), getSpellIcon(slotToHighlight), spell.getIcon3()));
			break;
		case Keyboard.KEY_V:
			NetworkHandler.sendToServer(new MessageAssignSpell(spell.getIcon1(), spell.getIcon2(), getSpellIcon(slotToHighlight)));
			break;
		case Keyboard.KEY_Y:
			this.mc.displayGuiScreen(null);
			break;
		case Keyboard.KEY_E:
			this.mc.displayGuiScreen(null);
			break;
		default:

			break;
		}
		super.keyTyped(typedChar, key);
	}

	private int getSpellIcon(int slot) {
		switch (slot) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 5:
			return 2;
		case 10:
			return 3;
		case 15:
			return 4;
		case 16:
			return 5;
		case 17:
			return 6;
		case 20:
			return 7;
		default:
			return -1;
		}

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
		sx += 3;
		sy += 3;
		int rx = sx;
		int tsx = 70, tsy = 23, rtx = tsx;
		for (int r = 0; r < 5; r++) {
			sx = rx;
			for (int c = 0; c < 5; c++) {

				drawTexturedModalRect(sx, sy, tsx + (18 * c), tsy + (18 * r), 16, 16);
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

	public boolean isGuiOpen() {
		return this.isGuiOpen;
	}

	public void setGuiOpen(boolean value) {
		this.isGuiOpen = value;
	}
}
