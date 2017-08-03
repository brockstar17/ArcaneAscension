package brockstar17.capability;

/**
 * This is the interface for my custom player capability which allows for the storage of mana in the
 * player data.
 * 
 * @author Brockstar17
 */
public interface IArcaneMana
{

	// Set the player's mana to a certain amount
	public void setMana(int amount);

	// Return true if the player has mana greater than or equal to what is needed.
	public boolean hasEnoughMana(int needed);

	// Decrease the player's mana by a certain amount
	public void useMana(int amount);

	// Increase the player's mana by a certain amount
	public void gainMana(int amount);

	// Returns the amount of mana that the player has
	public int getMana();

	// Return the max mana the player can hold
	public int getMaxMana();
}
