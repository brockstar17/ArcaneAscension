package brockstar17.capability.learned;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This is the storage class for the capability. It is used to write the data to NBT so that it can
 * be stored in the desired entity.<br>
 * This is the storage parameter when registering the capability.
 * 
 * @author Brockstar17
 */

public class LearnedStorage implements IStorage<ILearnedSpells>
{
	@Override
	public NBTBase writeNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side) {

		// If you only have one piece of data that needs to be stored then it is easiest to do
		// something like this...
		// ex: return new NBTTagIntArray(instance.getLearnedArray());
		// In this example I am storing an integer array in the player data

		// Here however I am using a method that allows for the storage of as much data as you want
		// with one capability.
		// I start by creating an NBTTagCompound
		NBTTagCompound tag = new NBTTagCompound();
		// Then I set whatever data I want to it with a string key that I can reference later
		tag.setIntArray("learnedArray", instance.getLearnedArray());

		// Now I return the tag and the provider will store the NBTTagCompound in the player data
		return tag;
	}

	@Override
	public void readNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side, NBTBase nbt) {
		// The comment below is how you would set up the reading for the method of only storing one
		// piece of data
		// instance.setLearnedArray(((NBTTagIntArray) nbt).getIntArray());

		// This is the method for reading from the NBTTagCompound
		// I create an NBTTagCompound from the NBTBase parameter in this method
		NBTTagCompound tag = (NBTTagCompound) nbt;
		// Then I can read the data associated with the key that I set to the tag earlier when I
		// wrote the data to the nbt tag
		instance.setLearnedArray(tag.getIntArray("learnedArray"));

	}
}
