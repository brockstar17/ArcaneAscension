package brockstar17.utility;

public class SpellIcons
{
	public static final int[] textX = { 70, 88, 70, 70, 70, 88, 106, 70 };
	public static final int[] textY = { 23, 23, 41, 59, 77, 77, 77, 95 };
	public static final int noSpellx = 24, nSpelly = 41;

	public static int getTextX(int id) {
		if (id != -1) {
			return textX[id];
		}
		return noSpellx;
	}

	public static int getTextY(int id) {
		if (id != -1) {
			return textY[id];
		}
		return nSpelly;
	}

	public static int getSpellId(int slot) {
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
}
