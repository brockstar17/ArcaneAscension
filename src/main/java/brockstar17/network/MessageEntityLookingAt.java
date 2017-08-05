package brockstar17.network;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;

public class MessageEntityLookingAt extends MessageBase<MessageEntityLookingAt>
{

	private int entityId;

	private Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;

	public MessageEntityLookingAt()
	{
	}

	public MessageEntityLookingAt(int entityId)
	{
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
	}

	@Override
	public void handleClientSide(MessageEntityLookingAt message, EntityPlayer player) {

	}

	@Override
	public void handleServerSide(MessageEntityLookingAt message, EntityPlayer player) {
		// Log.info("MELA packet has been recieved on server");
		IArcaneSpells spells = player.getCapability(cspells, null);
		int[] arr = new int[] { spells.getActiveSlot(), spells.getIcon(0), spells.getIcon(1), spells.getIcon(2), message.entityId };
		spells.initSpells(arr);
		NetworkHandler.sendToServer(new MessageUseSpell(spells.getIcon(spells.getActiveSlot())));
	}

}
