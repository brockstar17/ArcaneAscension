package brockstar17.blocks;

import brockstar17.tileentity.BlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ArcaneBlocks
{

	public static BlockBase arcane_altar;
	public static BlockBase arcane_pedestal;

	public static BlockBase ash_block;

	public static void init() {
		arcane_altar = register(new ArcaneAltar("arcane_altar"));
		arcane_pedestal = register(new ArcanePedestal("arcane_pedastal"));

		ash_block = register(new AshBlock("ash_block"));
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof BlockBase) {
			((BlockBase) block).registerItemModel(itemBlock);
		}

		if (block instanceof BlockTileEntity) {
			GameRegistry.registerTileEntity(((BlockTileEntity<?>) block).getTileEntityClass(), block.getRegistryName().toString());
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
