package brockstar17.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class implements IStorage and reads and writes the capability from and to the player.
 * 
 * @author Brockstar17
 */
public class ArcaneManaStorage implements IStorage<IArcaneMana>
{

	@Override
	public NBTBase writeNBT(Capability<IArcaneMana> capability, IArcaneMana instance, EnumFacing side) {

		return new NBTTagInt(instance.getMana());
	}

	@Override
	public void readNBT(Capability<IArcaneMana> capability, IArcaneMana instance, EnumFacing side, NBTBase nbt) {

		instance.setMana(((NBTTagInt) nbt).getInt());
	}

}
