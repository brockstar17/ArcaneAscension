package brockstar17.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArcaneSpellsStorage implements IStorage<IArcaneSpells>
{
	@Override
	public NBTBase writeNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side) {

		return new NBTTagInt(instance.getActiveSpellSlot());
	}

	@Override
	public void readNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side, NBTBase nbt) {

		instance.setActiveSpellSlot(((NBTTagInt) nbt).getInt());
	}
}
