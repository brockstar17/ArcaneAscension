package brockstar17.utility;

import brockstar17.blocks.ArcaneBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualUtils
{
	/**
	 * Check if the ritual is setup correctly
	 * 
	 * @param pos
	 *            the BlockPos of the altar
	 * @return true if setup correctly
	 */
	public static boolean hasCorrectShape(BlockPos pos, World world) {

		BlockPos px = pos.down().add(4, 0, 0), nx = pos.down().add(-4, 0, 0), pz = pos.down().add(0, 0, 4),
				nz = pos.down().add(0, 0, -4);

		BlockPos[] ashBlocks = {
				// Under altar
				pos.down(), pos.down().add(1, 0, 0), pos.down().add(1, 0, 1), pos.down().add(1, 0, -1), pos.down().add(0, 0, 1), pos.down().add(0, 0, -1), pos.down().add(-1, 0, 0), pos.down().add(-1, 0, 1), pos.down().add(-1, 0, -1),

				// Pos x
				px, px.add(1, 0, 0), px.add(1, 0, 1), px.add(1, 0, -1), px.add(0, 0, 1), px.add(0, 0, -1), px.add(-1, 0, 0), px.add(-1, 0, 1), px.add(-1, 0, -1),

				// Neg x
				nx, nx.add(1, 0, 0), nx.add(1, 0, 1), nx.add(1, 0, -1), nx.add(0, 0, 1), nx.add(0, 0, -1), nx.add(-1, 0, 0), nx.add(-1, 0, 1), nx.add(-1, 0, -1),

				// Pos z
				pz, pz.add(1, 0, 0), pz.add(1, 0, 1), pz.add(1, 0, -1), pz.add(0, 0, 1), pz.add(0, 0, -1), pz.add(-1, 0, 0), pz.add(-1, 0, 1), pz.add(-1, 0, -1),

				// Neg z
				nz, nz.add(1, 0, 0), nz.add(1, 0, 1), nz.add(1, 0, -1), nz.add(0, 0, 1), nz.add(0, 0, -1), nz.add(-1, 0, 0), nz.add(-1, 0, 1), nz.add(-1, 0, -1), };

		for (BlockPos p : ashBlocks) {
			if (world.getBlockState(p).getBlock() != ArcaneBlocks.ash_block) {
				return false;
			}
		}

		if (world.getBlockState(px.up()).getBlock() != ArcaneBlocks.arcane_pedestal) {
			return false;
		}

		if (world.getBlockState(nx.up()).getBlock() != ArcaneBlocks.arcane_pedestal) {
			return false;
		}

		if (world.getBlockState(pz.up()).getBlock() != ArcaneBlocks.arcane_pedestal) {
			return false;
		}

		if (world.getBlockState(nz.up()).getBlock() != ArcaneBlocks.arcane_pedestal) {
			return false;
		}

		return true;

	}
}
