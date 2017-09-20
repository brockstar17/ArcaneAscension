package brockstar17.network;

import brockstar17.Reference;
import brockstar17.network.terender.PacketRequestUpdateAltar;
import brockstar17.network.terender.PacketUpdatedAltar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Handle all packets here
 * 
 * @author Brockstar17
 */
public class NetworkHandler
{
	// I use the Forge SimpleNetworkWrapper, I have not needed anything else yet.
	private static SimpleNetworkWrapper INSTANCE;

	// Register all packets, each packet needs to have...
	// 1.) A message handler (can be itself),
	// 2.) A Request Message Type (also can be itself),
	// 3.) A discriminator, each one must be different,
	// 4.) A side context, which side the packet is going to.
	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		INSTANCE.registerMessage(MessageManaChange.class, MessageManaChange.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageManaChange.class, MessageManaChange.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageActiveSlotChange.class, MessageActiveSlotChange.class, 2, Side.CLIENT);
		INSTANCE.registerMessage(MessageActiveSlotChange.class, MessageActiveSlotChange.class, 3, Side.SERVER);
		INSTANCE.registerMessage(MessageAssignSpell.class, MessageAssignSpell.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageAssignSpell.class, MessageAssignSpell.class, 5, Side.CLIENT);
		INSTANCE.registerMessage(MessageUseSpell.class, MessageUseSpell.class, 6, Side.SERVER);
		INSTANCE.registerMessage(MessageUseSpell.class, MessageUseSpell.class, 7, Side.CLIENT);
		INSTANCE.registerMessage(MessageEntityLookingAt.class, MessageEntityLookingAt.class, 8, Side.SERVER);
		INSTANCE.registerMessage(MessageLearnSpell.class, MessageLearnSpell.class, 9, Side.CLIENT);
		INSTANCE.registerMessage(MessageLearnSpell.class, MessageLearnSpell.class, 10, Side.SERVER);
		INSTANCE.registerMessage(MessageSyncCooldown.class, MessageSyncCooldown.class, 11, Side.CLIENT);
		INSTANCE.registerMessage(new PacketUpdatedAltar.Handler(), PacketUpdatedAltar.class, 12, Side.CLIENT);
		INSTANCE.registerMessage(new PacketRequestUpdateAltar.Handler(), PacketRequestUpdateAltar.class, 13, Side.SERVER);
	}

	// Send to the server
	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	// Send to a specific player
	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	// Send to all players around a specific point
	public static void sendToAllAround(IMessage message, TargetPoint point) {
		INSTANCE.sendToAllAround(message, point);
	}

}
