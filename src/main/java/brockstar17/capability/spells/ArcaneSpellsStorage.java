package brockstar17.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArcaneSpellsStorage implements IStorage<IArcaneSpells>
{
	@Override
	public NBTBase writeNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side) {
		// Log.info("Slot 1: " + instance.getIcon1());
		return new NBTTagIntArray(instance.getInitSpellsArray());
	}

	@Override
	public void readNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side, NBTBase nbt) {

		instance.initSpells(((NBTTagIntArray) nbt).getIntArray());
		// Log.info("Slot 1: " + instance.getIcon1());
	}
}
