package brockstar17.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPedastal extends TileEntity
{

	private int pedItem = -1;
	private ItemStack stack;

	public boolean setItemOnPed(Item item) {
		if (pedItem == -1 && item != null) {
			this.pedItem = Item.getIdFromItem(item);
			markDirty();
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;

	}

	public Item getItemOnPed() {
		if (this.pedItem != -1) {
			return Item.getItemById(pedItem);
		}
		return null;
	}

	public void removeItemFromPed() {
		if (pedItem != -1) {
			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Item.getItemById(pedItem))));
		}
		this.pedItem = -1;
		markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.writeUpdateTag(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.readFromNBT(compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.getNbtCompound();
		readUpdateTag(nbt);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeUpdateTag(nbt);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), nbt);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		writeUpdateTag(nbt);
		return super.getUpdateTag();
	}

	public void writeUpdateTag(NBTTagCompound tag) {
		tag.setInteger("pedItem", this.pedItem);
	}

	public void readUpdateTag(NBTTagCompound tag) {
		this.pedItem = tag.getInteger("pedItem");
	}

}
