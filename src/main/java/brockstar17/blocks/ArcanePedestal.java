package brockstar17.blocks;

import brockstar17.tileentity.BlockTileEntity;
import brockstar17.tileentity.TileEntityPedestal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ArcanePedestal extends BlockTileEntity<TileEntityPedestal>
{

	// 0.0625 = 1 / 16
	private static final double pix = 0.0625;

	private static final AxisAlignedBB BOUNDING = new AxisAlignedBB(1 * pix, 0, 1 * pix, 15 * pix, 11 * pix, 15 * pix);

	public ArcanePedestal(String name)
	{
		super(Material.ROCK, name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public boolean isFullCube(IBlockState state) {

		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {

		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return BOUNDING;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityPedestal tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			TileEntityPedestal tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if (!player.isSneaking()) {
				if (player.getHeldItemMainhand().isEmpty()) {
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				}
				else {
					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItemMainhand(), false));
				}
				tile.markDirty();
			}
			else {
				ItemStack stack = itemHandler.getStackInSlot(0);
				if (!stack.isEmpty()) {

				}
				else {

				}
			}
		}
		return true;
	}

	@Override
	public Class<TileEntityPedestal> getTileEntityClass() {

		return TileEntityPedestal.class;
	}

	@Override
	public TileEntityPedestal createTileEntity(World world, IBlockState state) {

		return new TileEntityPedestal();
	}

}
