package brockstar17.network;

import brockstar17.capability.mana.ArcaneManaProvider;
import brockstar17.capability.mana.IArcaneMana;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Because of the client server relationship.<br>
 * The player capability for mana is kept server side although it exists client side.<br>
 * So to keep the mana bar rendering correctly the client side data needs to be updated.
 * 
 * @author Brockstar17
 */
public class MessageManaChange extends MessageBase<MessageManaChange>
{
	// A field to store the mana the player has
	private int mana;

	private Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

	// An empty constructor because sometimes the game will call back a request from this and if the
	// constructor require parameters the game will crash
	public MessageManaChange()
	{
	}

	// This is the constructor that I will be using and I will pass in the value of the player's
	// mana
	public MessageManaChange(int mana)
	{
		this.mana = mana;
	}

	// Read the message data from the byte buffer and set it to the field mana
	@Override
	public void fromBytes(ByteBuf buf) {
		this.mana = buf.readInt();
	}

	// Write the field mana to the byte buffer to send the message
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.mana);
	}

	// Handle the message on the client side
	@Override
	public void handleClientSide(MessageManaChange message, EntityPlayer player) {
		// Update the value of mana
		IArcaneMana mana = Minecraft.getMinecraft().player.getCapability(cmana, null);
		mana.setMana(message.mana);

	}

	// Handle the message on the server side
	@Override
	public void handleServerSide(MessageManaChange message, EntityPlayer player) {
		// Update the value of mana
		IArcaneMana mana = player.getCapability(cmana, null);
		mana.setMana(message.mana);
	}

}
