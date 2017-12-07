package brockstar17.network.terender;

import brockstar17.tileentity.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdatePedestal implements IMessage
{
	private BlockPos pos;
	private int dimension;

	public PacketRequestUpdatePedestal(BlockPos pos, int dimension)
	{
		this.pos = pos;
		this.dimension = dimension;
	}

	public PacketRequestUpdatePedestal(TileEntityPedestal te)
	{
		this(te.getPos(), te.getWorld().provider.getDimension());
	}

	public PacketRequestUpdatePedestal()
	{
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
	}

	public static class Handler implements IMessageHandler<PacketRequestUpdatePedestal, PacketUpdatedPedestal>
	{

		@Override
		public PacketUpdatedPedestal onMessage(PacketRequestUpdatePedestal message, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension);
			TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(message.pos);
			if (te != null) {
				return new PacketUpdatedPedestal(te);
			}
			else {
				return null;
			}
		}

	}
}
