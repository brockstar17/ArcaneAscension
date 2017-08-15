package brockstar17.capability.learned;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * This is the capability provider. This implements the interface ICapabilitySerializable in order
 * to save the NBT data that the storage class has written to the entity that the capabiliyt is
 * attached to. <br>
 * This is also the class that is used when determining if a player has your capability.
 * 
 * @author Brockstar17
 */
public class LearnedProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(ILearnedSpells.class)
	public static final Capability<ILearnedSpells> LEARNED = null;

	// The default instance of the capability
	private ILearnedSpells instance = LEARNED.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// Return true if the player has the LEARNED capability
		return capability == LEARNED;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// Check if the capability is LEARNED if it is return it, otherwise returns null
		return capability == LEARNED ? LEARNED.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		// This gets the NBT data that the storage class wrote
		return LEARNED.getStorage().writeNBT(LEARNED, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		// This reads the NBT data that was saved to the player to the storage class
		LEARNED.getStorage().readNBT(LEARNED, this.instance, null, nbt);
	}

}
