package brockstar17.capability;

public interface IArcaneSpells
{
	/**
	 * Set the active spell slot.
	 * 
	 * @param slot
	 *            the slot number to set to
	 */
	public void setActiveSpellSlot(int slot);

	/**
	 * Return the current active spell slot.
	 * 
	 * @return the active spell slot
	 */
	public int getActiveSpellSlot();

	public int getIcon1();

	public void setIcon1(int icon);

	public int getIcon2();

	public void setIcon2(int icon);

	public int getIcon3();

	public void setIcon3(int icon);
}
