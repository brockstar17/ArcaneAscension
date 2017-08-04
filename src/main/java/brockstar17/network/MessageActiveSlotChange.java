package brockstar17.network;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;

public class MessageActiveSlotChange extends MessageBase<MessageActiveSlotChange>
{

	private int activeSlot;

	private Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.ACTIVESPELL;

	public MessageActiveSlotChange()
	{
	}

	public MessageActiveSlotChange(int slot)
	{
		this.activeSlot = slot;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.activeSlot = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.activeSlot);
	}

	@Override
	public void handleClientSide(MessageActiveSlotChange message, EntityPlayer player) {
		IArcaneSpells slot = Minecraft.getMinecraft().player.getCapability(cslot, null);
		slot.setActiveSpellSlot(message.activeSlot);
	}

	@Override
	public void handleServerSide(MessageActiveSlotChange message, EntityPlayer player) {
		IArcaneSpells slot = player.getCapability(cslot, null);
		slot.setActiveSpellSlot(message.activeSlot);
		NetworkHandler.sendTo(new MessageActiveSlotChange(slot.getActiveSpellSlot()), (EntityPlayerMP) player);
	}

}
