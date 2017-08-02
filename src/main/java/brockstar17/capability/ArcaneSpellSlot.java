package brockstar17.capability;

public class ArcaneSpellSlot implements IArcaneSpellSlot
{

	private int activeSpellSlot = 0;

	@Override
	public void setActiveSpellSlot(int slot) {
		this.activeSpellSlot = slot;
	}

	@Override
	public int getActiveSpellSlot() {

		return this.activeSpellSlot;
	}

}
