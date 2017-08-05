package brockstar17.capability.spells;

public interface IArcaneSpells
{
	/**
	 * This will initialize all things in this class.<br>
	 * Such as...<br>
	 * •active slot<br>
	 * •active spell icons
	 * 
	 * @param arr
	 */
	public void initSpells(int[] arr);

	public int[] getInitSpellsArray();

	public int getActiveSlot();

	public int getIcon(int icon);

}
