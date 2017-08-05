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

	private Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.SPELLS;

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
		int[] arr = new int[] { message.activeSlot, slot.getIcon(0), slot.getIcon(1), slot.getIcon(2) };
		slot.initSpells(arr);
	}

	@Override
	public void handleServerSide(MessageActiveSlotChange message, EntityPlayer player) {
		IArcaneSpells slot = player.getCapability(cslot, null);
		int[] arr = new int[] { message.activeSlot, slot.getIcon(0), slot.getIcon(1), slot.getIcon(2) };
		slot.initSpells(arr);
		NetworkHandler.sendTo(new MessageActiveSlotChange(message.activeSlot), (EntityPlayerMP) player);
	}

}
