package brockstar17.network;

import brockstar17.capability.ArcaneManaProvider;
import brockstar17.capability.IArcaneMana;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;

public class MessageManaChange extends MessageBase<MessageManaChange>
{

	private int mana;

	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	public MessageManaChange()
	{
	}

	public MessageManaChange(int mana)
	{
		this.mana = mana;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.mana = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.mana);
	}

	@Override
	public void handleClientSide(MessageManaChange message, EntityPlayer player) {
		IArcaneMana mana = Minecraft.getMinecraft().player.getCapability(cmana, null);
		mana.setMana(message.mana);
	}

	@Override
	public void handleServerSide(MessageManaChange message, EntityPlayer player) {
		IArcaneMana mana = player.getCapability(cmana, null);
		mana.setMana(message.mana);
	}

}
