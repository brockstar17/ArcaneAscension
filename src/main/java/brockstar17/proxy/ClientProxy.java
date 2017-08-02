package brockstar17.proxy;

import brockstar17.Reference;
import brockstar17.client.KeyInputHandler;
import brockstar17.client.Keybindings;
import brockstar17.items.ArcaneItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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

	@Override
	public void preInit() {
		OBJLoader.INSTANCE.addDomain(Reference.MODID);
		ArcaneItems.register3dModels();
		registerKeybinds();
	}

	private void registerKeybinds() {
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		for (Keybindings key : Keybindings.values()) {
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

	@Override
	public void init() {
		ArcaneItems.registerRenders();
	}

	@Override
	public void postInit() {
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}

}
