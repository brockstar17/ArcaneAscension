package brockstar17.blocks;

import brockstar17.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ArcaneBlocks
{

	// Start block declarations
	public static Block ash_block;
	// End block declarations

	// Initialize blocks
	// Gets called during init, call it after items
	public static void preInit() {

		ash_block = new AshBlock("ash_block");

		registerBlocks();
	}

	// Register blocks with game registry
	public static void registerBlocks() {
		registerBlock(ash_block, new ItemBlock(ash_block));
	}

	// Register the block. Forge requires that you also create an item from the block and register
	// that item. This is how the itemblock can be used in inventories.
	private static void registerBlock(Block block, Item item) {
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}

	// Register the block textures
	public static void reigsterRenders() {
		registerRender(ash_block);
	}

	// Register itemblock texture
	private static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block); // The item from the block
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));

	}
}
