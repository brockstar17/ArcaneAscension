package brockstar17.proxy;

import brockstar17.ArcaneAscension;
import brockstar17.Reference;
import brockstar17.client.KeyInputHandler;
import brockstar17.client.Keybindings;
import brockstar17.items.ArcaneItems;
import brockstar17.tileentity.TileEntityAltar;
import brockstar17.tileentity.TileEntityPedestal;
import brockstar17.tileentity.render.AltarRenderer;
import brockstar17.tileentity.render.PedastalRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * A class for the client proxy. <br>
 * Client-side code should be placed in the following methods.
 * 
 * @author Brockstar17
 */
public class ClientProxy extends CommonProxy
{

	// Register keybinds and setup the 3D model loader
	@Override
	public void preInit() {
		OBJLoader.INSTANCE.addDomain(Reference.MODID);
		ArcaneItems.register3dModels();
		registerKeybinds();
	}

	// Register keybindings, I mentioned that they were client side only, so do that in this proxy
	private void registerKeybinds() {
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		for (Keybindings key : Keybindings.values()) { // Loop through all keybinds
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

	// Register the textures for all the junk that has a texture
	@Override
	public void init() {
		ArcaneItems.registerRenders();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new AltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new PedastalRenderer());
	}

	@Override
	public void postInit() {
	}

	// Used to get an instance of the player, not particularly useful but I'll keep it to make my
	// mod take up more memory! JK this will likely be removed
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {

		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ArcaneAscension.prependModID(id), "inventory"));
	}

	@Override
	public String localize(String unlocalized, Object... args) {
		return I18n.format(unlocalized, args);
	}

}
