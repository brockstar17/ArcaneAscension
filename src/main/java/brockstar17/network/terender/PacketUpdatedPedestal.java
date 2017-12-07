package brockstar17.network.terender;

import brockstar17.tileentity.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatedPedestal implements IMessage
{

	private BlockPos pos;
	private ItemStack stack;
	private long lastChangeTime;

	public PacketUpdatedPedestal(BlockPos pos, ItemStack stack, long lastChangeTime)
	{
		this.pos = pos;
		this.stack = stack;
		this.lastChangeTime = lastChangeTime;
	}

	public PacketUpdatedPedestal(TileEntityPedestal te)
	{
		this(te.getPos(), te.inventory.getStackInSlot(0), te.lastChangeTime);
	}

	public PacketUpdatedPedestal()
	{
	}

	public static class Handler implements IMessageHandler<PacketUpdatedPedestal, IMessage>
	{

		@Override
		public IMessage onMessage(PacketUpdatedPedestal message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				TileEntityPedestal te = (TileEntityPedestal) Minecraft.getMinecraft().world.getTileEntity(message.pos);
				te.inventory.setStackInSlot(0, message.stack);
				te.lastChangeTime = message.lastChangeTime;
			});
			return null;
		}

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeItemStack(buf, stack);
		buf.writeLong(lastChangeTime);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		stack = ByteBufUtils.readItemStack(buf);
		lastChangeTime = buf.readLong();
	}
}
