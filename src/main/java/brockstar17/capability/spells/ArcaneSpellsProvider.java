package brockstar17.capability.spells;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ArcaneSpellsProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IArcaneSpells.class)
	public static final Capability<IArcaneSpells> ACTIVESPELL = null;

	private IArcaneSpells instance = ACTIVESPELL.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == ACTIVESPELL;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == ACTIVESPELL ? ACTIVESPELL.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {

		return ACTIVESPELL.getStorage().writeNBT(ACTIVESPELL, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ACTIVESPELL.getStorage().readNBT(ACTIVESPELL, this.instance, null, nbt);
	}

}
