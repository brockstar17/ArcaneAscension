package brockstar17.capability.spells;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

	@SideOnly(Side.CLIENT)
	public int[] getCooldowns();

	@SideOnly(Side.CLIENT)
	public void setCooldowns(int[] arr);

	@SideOnly(Side.CLIENT)
	public int getCooldown(int id);

	@SideOnly(Side.CLIENT)
	public void setCooldown(int id, int duration);

}
