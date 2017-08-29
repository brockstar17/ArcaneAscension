package brockstar17.blocks;

import brockstar17.ArcaneAscension;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ArcaneAltar extends Block
{

	// 0.0625 = 1 / 16
	private static final double pix = 0.0625;

	private static final AxisAlignedBB BOUNDING = new AxisAlignedBB(0, 0, 0, 16 * pix, 13 * pix, 16 * pix);

	public ArcaneAltar(String name)
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
}
