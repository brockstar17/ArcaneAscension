package brockstar17.network;

import brockstar17.ArcaneAscension;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class MessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{
	@Override
	public REQ onMessage(final REQ message, final MessageContext ctx) {

		if (ctx.side == Side.SERVER) {
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					handleServerSide(message, ctx.getServerHandler().playerEntity);
				}
			});

		}
		else {
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					handleClientSide(message, ArcaneAscension.proxy.getClientPlayer());
				}
			});

		}
		return null;
	}

	/**
	 * Handle a packet on the client side. Note this occurs after decoding has completed.
	 * 
	 * @param message
	 * @param player
	 *            the player reference
	 */
	public abstract void handleClientSide(REQ message, EntityPlayer player);

	/**
	 * Handle a packet on the server side. Note this occurs after decoding has completed.
	 * 
	 * @param message
	 * @param player
	 *            the player reference
	 */
	public abstract void handleServerSide(REQ message, EntityPlayer player);
}
