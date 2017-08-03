package brockstar17.network;

import brockstar17.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
	private static SimpleNetworkWrapper INSTANCE;

	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		INSTANCE.registerMessage(MessageManaChange.class, MessageManaChange.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageManaChange.class, MessageManaChange.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageActiveSlotChange.class, MessageActiveSlotChange.class, 2, Side.CLIENT);
		INSTANCE.registerMessage(MessageActiveSlotChange.class, MessageActiveSlotChange.class, 3, Side.SERVER);
		INSTANCE.registerMessage(MessageAssignSpell.class, MessageAssignSpell.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageAssignSpell.class, MessageAssignSpell.class, 5, Side.CLIENT);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static void sendToAllAround(IMessage message, TargetPoint point) {
		INSTANCE.sendToAllAround(message, point);
	}

}
