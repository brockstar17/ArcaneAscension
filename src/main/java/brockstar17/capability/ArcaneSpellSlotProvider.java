package brockstar17.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ArcaneSpellSlotProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IArcaneSpellSlot.class)
	public static final Capability<IArcaneSpellSlot> ACTIVESPELL = null;

	private IArcaneSpellSlot instance = ACTIVESPELL.getDefaultInstance();

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
