package brockstar17.items;

import brockstar17.Reference;
import brockstar17.items.swords.Kasio;
import brockstar17.items.swords.Kazio;
import brockstar17.items.swords.Mizurio;
import brockstar17.items.swords.Seishinio;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ArcaneItems
{
	// Declare the item fields here
	public static Item kasio;
	public static Item kazio;
	public static Item mizurio;
	public static Item seishinio;
	public static Item mana_jar;
	// End of item fields

	/**
	 * Initialize items, must be called on both sides during preInit <br>
	 * If called client-side only, the item exists but server will not know of it <br>
	 * Game will crash trying to render item if only called on server-side
	 */
	public static void preInit() {

		kasio = new Kasio("kasio");
		kazio = new Kazio("kazio");
		mizurio = new Mizurio("mizurio");
		seishinio = new Seishinio("seishinio");
		mana_jar = new ManaJar("mana_jar");

		registerItems();
	}

	/**
	 * Do anything with items that needs to be done during initialization.<br>
	 * This method is likely not going to be used and may be deprecated in a future release.
	 */
	public static void init() {

	}

	/**
	 * Register items with the GameRegistry
	 */
	public static void registerItems() {
		GameRegistry.register(kasio);
		GameRegistry.register(kazio);
		GameRegistry.register(mizurio);
		GameRegistry.register(seishinio);
		GameRegistry.register(mana_jar);
	}

	/**
	 * Register the item textures, must be called during client-side init
	 */
	public static void registerRenders() {

	}

	/**
	 * Bind textures to the item
	 * 
	 * @param item
	 *            the item to bind the texture to
	 */
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	/**
	 * Register item textures from 3d object
	 */
	public static void register3dModels() {
		register3dModel(kasio);
		register3dModel(kazio);
		register3dModel(mizurio);
		register3dModel(seishinio);
		register3dModel(mana_jar);
	}

	/**
	 * Register item texture (model) from obj
	 * 
	 * @param item
	 *            the item to bind the texure to.
	 */
	private static void register3dModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
