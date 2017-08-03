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
}
