package brockstar17.capability.spells;

public class ArcaneSpells implements IArcaneSpells
{

	private int activeSpellSlot = 0;
	private int icon1 = -1, icon2 = -1, icon3 = -1;

	@Override
	public void setActiveSpellSlot(int slot) {
		this.activeSpellSlot = slot;
	}

	@Override
	public int getActiveSpellSlot() {

		return this.activeSpellSlot;
	}

	@Override
	public int getIcon1() {

		return this.icon1;
	}

	@Override
	public void setIcon1(int icon) {
		this.icon1 = icon;
	}

	@Override
	public int getIcon2() {

		return this.icon2;
	}

	@Override
	public void setIcon2(int icon) {
		this.icon2 = icon;
	}

	@Override
	public int getIcon3() {

		return this.icon3;
	}

	@Override
	public void setIcon3(int icon) {
		this.icon3 = icon;
	}

}
