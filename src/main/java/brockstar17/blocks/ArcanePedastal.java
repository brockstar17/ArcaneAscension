package brockstar17.blocks;

import brockstar17.ArcaneAscension;
import brockstar17.tileentity.TileEntityPedastal;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ArcanePedastal extends Block implements ITileEntityProvider
{

	// 0.0625 = 1 / 16
	private static final double pix = 0.0625;

	private static final AxisAlignedBB BOUNDING = new AxisAlignedBB(0, 0, 0, 16 * pix, 13 * pix, 16 * pix);

	public ArcanePedastal(Material material)
	{
		super(material);

	}

	public ArcanePedastal(String name)
	{
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setRegistryName(ArcaneAscension.prependModID(name));
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

		TileEntity te = world.getTileEntity(pos);
		if (te != null && te instanceof TileEntityPedastal) {
			TileEntityPedastal pedastal = (TileEntityPedastal) te;
			if (pedastal.getItemOnPed() != null) {
				pedastal.removeItemFromPed();
			}
			world.removeTileEntity(pos);
		}

		super.onBlockDestroyedByPlayer(world, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityPedastal) {
				TileEntityPedastal pedastal = (TileEntityPedastal) world.getTileEntity(pos);
				if (!pedastal.setItemOnPed(player.getHeldItemMainhand().getItem())) {
					pedastal.removeItemFromPed();
				}
				else {
					player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new TileEntityPedastal();
	}

}
