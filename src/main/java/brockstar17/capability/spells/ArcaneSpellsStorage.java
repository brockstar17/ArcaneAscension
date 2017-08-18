package brockstar17.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArcaneSpellsStorage implements IStorage<IArcaneSpells>
{
	@Override
	public NBTBase writeNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side) {

		NBTTagCompound tag = new NBTTagCompound();
		tag.setIntArray("initArray", instance.getInitSpellsArray());
		return tag;
	}

	@Override
	public void readNBT(Capability<IArcaneSpells> capability, IArcaneSpells instance, EnumFacing side, NBTBase nbt) {

		NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.initSpells(tag.getIntArray("initArray"));
	}
}
