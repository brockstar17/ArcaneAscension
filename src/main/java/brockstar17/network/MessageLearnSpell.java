package brockstar17.network;

import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedProvider;
import brockstar17.utility.ArcaneUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;

public class MessageLearnSpell extends MessageBase<MessageLearnSpell>
{
	private boolean[] learned = new boolean[8];
	private Capability<ILearnedSpells> cls = LearnedProvider.LEARNED;

	public MessageLearnSpell()
	{
	}

	public MessageLearnSpell(boolean[] arr)
	{
		this.learned = arr;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		for (int i = 0; i < this.learned.length; i++) {
			this.learned[i] = buf.readBoolean();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// Log.info("Writing boolean array learned");
		for (int i = 0; i < this.learned.length; i++) {
			// Log.info("Debug: written " + i + " values");
			buf.writeBoolean(this.learned[i]);
		}
	}

	@Override
	public void handleClientSide(MessageLearnSpell message, EntityPlayer player) {
		ILearnedSpells ls = player.getCapability(cls, null);
		ls.setLearnedArray(ArcaneUtils.boolArrIntArr(message.learned));
	}

	@Override
	public void handleServerSide(MessageLearnSpell message, EntityPlayer player) {

		ILearnedSpells ls = player.getCapability(cls, null);
		ls.setLearnedArray(ArcaneUtils.boolArrIntArr(message.learned));
		NetworkHandler.sendTo(new MessageLearnSpell(ArcaneUtils.intArrBoolArr(ls.getLearnedArray())), (EntityPlayerMP) player);
	}

}
