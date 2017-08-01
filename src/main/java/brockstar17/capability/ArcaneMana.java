package brockstar17.capability;

/**
 * This is the implementation of the mana interface. <br>
 * It defines the methods that are declared in the interface.
 * 
 * @author Brockstar17
 */
public class ArcaneMana implements IArcaneMana
{

	private int mana;
	private int maxMana = 100;

	@Override
	public void setMana(int amount) {
		this.mana = amount;
	}

	@Override
	public boolean hasEnoughMana(int needed) {
		if (this.mana >= needed)
			return true;
		return false;
	}

	@Override
	public void useMana(int amount) {
		this.mana -= amount;
	}

	@Override
	public void gainMana(int amount) {
		this.mana += amount;
	}

	@Override
	public int getMana() {
		return this.mana;
	}

	@Override
	public int getMaxMana() {

		return this.maxMana;
	}

}
