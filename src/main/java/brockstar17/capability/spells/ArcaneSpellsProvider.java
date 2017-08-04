package brockstar17.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ArcaneSpellsProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IArcaneSpells.class)
	public static final Capability<IArcaneSpells> SPELLS = null;

	private IArcaneSpells instance = SPELLS.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == SPELLS;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == SPELLS ? SPELLS.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {

		return SPELLS.getStorage().writeNBT(SPELLS, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		SPELLS.getStorage().readNBT(SPELLS, this.instance, null, nbt);
	}

}
