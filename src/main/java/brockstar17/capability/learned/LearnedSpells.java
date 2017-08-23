package brockstar17.capability.learned;

import brockstar17.utility.ArcaneUtils;

public class LearnedSpells implements ILearnedSpells
{

	private boolean[] learned = { false, false, false, false, false, false, false, false };

	@Override
	public boolean isSpellLearned(int spell) {
		if (spell != -1) {
			return this.learned[spell];
		}
		return false;
	}

	@Override
	public void setSpellLearned(int spell) {
		this.learned[spell] = true;
	}

	@Override
	public int[] getLearnedArray() {
		return ArcaneUtils.boolArrIntArr(learned);
	}

	@Override
	public void setLearnedArray(int[] arr) {
		this.learned = ArcaneUtils.intArrBoolArr(arr);
	}

}
