package brockstar17.blocks;

import brockstar17.ArcaneAscension;
import brockstar17.tileentity.BlockTileEntity;
import brockstar17.tileentity.TileEntityAltar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ArcaneAltar extends BlockTileEntity<TileEntityAltar>
{

	public ArcaneAltar(String name)
	{
		super(Material.ROCK, name);

	}

	// 0.0625 = 1 / 16
	private static final double pix = 0.0625;

	private static final AxisAlignedBB BOUNDING = new AxisAlignedBB(0, 0, 0, 16 * pix, 13 * pix, 16 * pix);

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
	public EnumBlockRenderType getRenderType(IBlockState state) {

		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityAltar tile = getTileEntity(world, pos);
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
			TileEntityAltar tile = getTileEntity(world, pos);
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
					String localized = ArcaneAscension.proxy.localize(stack.getUnlocalizedName() + ".name");
					player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
				}
				else {
					player.sendMessage(new TextComponentString("Empty"));
				}
			}
		}
		return true;
	}

	@Override
	public Class<TileEntityAltar> getTileEntityClass() {

		return TileEntityAltar.class;
	}

	@Override
	public TileEntityAltar createTileEntity(World world, IBlockState state) {

		return new TileEntityAltar();
	}

}
