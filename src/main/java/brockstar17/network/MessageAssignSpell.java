package brockstar17.network;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;

public class MessageAssignSpell extends MessageBase<MessageAssignSpell>
{
	private int icon1, icon2, icon3;
	private Capability<IArcaneSpells> cslot = ArcaneSpellsProvider.SPELLS;

	public MessageAssignSpell()
	{
	}

	public MessageAssignSpell(int i1, int i2, int i3)
	{
		this.icon1 = i1;
		this.icon2 = i2;
		this.icon3 = i3;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.icon1 = buf.readInt();
		this.icon2 = buf.readInt();
		this.icon3 = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(icon1);
		buf.writeInt(icon2);
		buf.writeInt(icon3);
	}

	@Override
	public void handleClientSide(MessageAssignSpell message, EntityPlayer player) {
		// Log.info("Assign Packet has been sent to client");
		IArcaneSpells slot = Minecraft.getMinecraft().player.getCapability(cslot, null);
		int[] arr = new int[] { slot.getActiveSlot(), message.icon1, message.icon2, message.icon3, slot.getSpellTargetId() };
		slot.initSpells(arr);

	}

	@Override
	public void handleServerSide(MessageAssignSpell message, EntityPlayer player) {
		// Log.info("Assign Packet has been sent to server");
		IArcaneSpells slot = player.getCapability(cslot, null);
		int[] arr = new int[] { slot.getActiveSlot(), message.icon1, message.icon2, message.icon3, slot.getSpellTargetId() };
		slot.initSpells(arr);
		NetworkHandler.sendTo(new MessageAssignSpell(message.icon1, message.icon2, message.icon3), (EntityPlayerMP) player);
	}

}
