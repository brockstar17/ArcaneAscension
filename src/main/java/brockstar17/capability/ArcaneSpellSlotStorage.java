package brockstar17.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArcaneSpellSlotStorage implements IStorage<IArcaneSpellSlot>
{
	@Override
	public NBTBase writeNBT(Capability<IArcaneSpellSlot> capability, IArcaneSpellSlot instance, EnumFacing side) {

		return new NBTTagInt(instance.getActiveSpellSlot());
	}

	@Override
	public void readNBT(Capability<IArcaneSpellSlot> capability, IArcaneSpellSlot instance, EnumFacing side, NBTBase nbt) {

		instance.setActiveSpellSlot(((NBTTagInt) nbt).getInt());
	}
}
