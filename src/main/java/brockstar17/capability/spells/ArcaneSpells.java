package brockstar17.capability.spells;

import brockstar17.utility.Log;

public class ArcaneSpells implements IArcaneSpells
{
	private int[] initSpellArray = { 0, -1, -1, -1 };

	// Active spell slot = initSpellArray[0];
	// Icon 1 = initSpellArray[1];
	// Icon 2 = initSpellArray[2];
	// Icon 3 = initSpellArray[3];

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
	public int getIcon1() {

		return this.initSpellArray[1];
	}

	@Override
	public int getIcon2() {

		return this.initSpellArray[2];
	}

	@Override
	public int getIcon3() {

		return this.initSpellArray[3];
	}

}
