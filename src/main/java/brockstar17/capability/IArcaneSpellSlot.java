package brockstar17.capability;

public interface IArcaneSpellSlot
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
}
