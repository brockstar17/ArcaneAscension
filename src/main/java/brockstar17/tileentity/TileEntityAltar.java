package brockstar17.tileentity;

import java.util.UUID;

import javax.annotation.Nullable;

import brockstar17.network.NetworkHandler;
import brockstar17.network.terender.PacketRequestUpdateAltar;
import brockstar17.network.terender.PacketUpdatedAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityAltar extends TileEntity
{

	public long lastChangeTime;
	private boolean shouldRenderHologram = false;
	private EntityPlayer owner;

	public ItemStackHandler inventory = new ItemStackHandler(1) {
		public int getSlotLimit(int slot) {
			return 1;
		}

		protected void onContentsChanged(int slot) {
			if (!world.isRemote) {
				lastChangeTime = world.getTotalWorldTime();
				NetworkHandler.sendToAllAround(new PacketUpdatedAltar(TileEntityAltar.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
		}
	};

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("inventory", inventory.serializeNBT());
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setBoolean("hologram", shouldRenderHologram);
		tag.setString("owner", owner.getUniqueID().toString());
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
		lastChangeTime = tag.getLong("lastChangeTime");
		shouldRenderHologram = tag.getBoolean("hologram");
		owner = world.getPlayerEntityByUUID(UUID.fromString(tag.getString("owner")));
		super.readFromNBT(tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		shouldRenderHologram = tag.getBoolean("shouldRenderHolo");
		owner = world.getPlayerEntityByUUID(UUID.fromString(tag.getString("owner")));
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("shouldRenderHolo", shouldRenderHologram);
		tag.setString("owner", owner.getUniqueID().toString());
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setBoolean("shouldRenderHolo", shouldRenderHologram);
		tag.setString("owner", owner.getUniqueID().toString());
		return tag;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
	}

	@Override
	public void onLoad() {
		if (world.isRemote) {
			NetworkHandler.sendToServer(new PacketRequestUpdateAltar(this));
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
	}

	public EntityPlayer getOwner() {
		return owner;
	}

	public void setOwner(String uuid) {
		this.owner = world.getPlayerEntityByUUID(UUID.fromString(uuid));
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
		markDirty();
	}

	public boolean getShouldRendHolo() {
		return shouldRenderHologram;
	}

	public void setShouldRendHolo(boolean val) {
		this.shouldRenderHologram = val;
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
		markDirty();
	}

}
