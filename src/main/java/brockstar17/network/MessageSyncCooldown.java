package brockstar17.network;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;

public class MessageSyncCooldown extends MessageBase<MessageSyncCooldown>
{

	private Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;

	private int id;
	private int duration;

	public MessageSyncCooldown()
	{
	}

	public MessageSyncCooldown(int id, int duration)
	{
		this.id = id;
		this.duration = duration;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.duration = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.id);
		buf.writeInt(this.duration);
	}

	@Override
	public void handleClientSide(MessageSyncCooldown message, EntityPlayer player) {
		IArcaneSpells spells = player.getCapability(cspells, null);
		spells.setCooldown(message.id, message.duration);
	}

	@Override
	public void handleServerSide(MessageSyncCooldown message, EntityPlayer player) {
		// player.addPotionEffect(new PotionEffect(ArcaneUtils.pcd[message.id], message.duration));
	}

}
