package brockstar17.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * This class provides the capability for injection.
 * 
 * @author Brockstar17
 */
public class ArcaneManaProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IArcaneMana.class)
	public static final Capability<IArcaneMana> MANA = null;

	private IArcaneMana instance = MANA.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == MANA;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == MANA ? MANA.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {

		return MANA.getStorage().writeNBT(MANA, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		MANA.getStorage().readNBT(MANA, this.instance, null, nbt);
	}

}
