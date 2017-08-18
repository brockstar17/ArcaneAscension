package brockstar17.capability.spells;

import java.util.ArrayList;

import brockstar17.utility.Log;

public class ArcaneSpells implements IArcaneSpells
{
	private int[] initSpellArray = { 0, -1, -1, -1 };
	private int[] cooldowns = { 0, 0, 0, 0, 0, 0, 0, 0 };
	private ArrayList<Integer> activeCD = new ArrayList<Integer>();

	// Active spell slot = initSpellArray[0];
	// Icon 1 = initSpellArray[1];
	// Icon 2 = initSpellArray[2];
	// Icon 3 = initSpellArray[3];
	// SpellTargetId = initSpellArray[4];

	@Override
	public void initSpells(int[] arr) {
		this.initSpellArray = arr;
		if (arr.length != 4) {
			Log.error("Spell initialization array is i.nvalid, this is a bug");
		}

	}

	@Override
	public int[] getInitSpellsArray() {

		return this.initSpellArray;
	}

	@Override
	public int getActiveSlot() {

		return this.initSpellArray[0];
	}

	@Override
	public int getIcon(int icon) {
		switch (icon) {
		case 0:
			return this.initSpellArray[1];
		case 1:
			return this.initSpellArray[2];
		case 2:
			return this.initSpellArray[3];
		default:
			return -1;
		}

	}

}
