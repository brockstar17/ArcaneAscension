package brockstar17.capability.learned;

/**
 * This interface lays out the methods that will be needed to get and set all data regarding the
 * spells that the player has learned.<br>
 * In registering the capability this is the type parameter.<br>
 * This must be implemented by what the Forge Docs call the factory.
 * 
 * @author Brockstar17
 */
public interface ILearnedSpells
{
	// Return whether or not the specified spell has been learned
	public boolean isSpellLearned(int spell);

	// Set that the specified spell has been learned
	public void setSpellLearned(int spell);

	// This is a method that is used for the storage class
	public int[] getLearnedArray();

	// This is a method that is used in the storage class
	public void setLearnedArray(int[] arr);

}
