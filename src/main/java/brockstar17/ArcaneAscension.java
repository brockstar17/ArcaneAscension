package brockstar17;

import brockstar17.items.ArcaneItems;
import brockstar17.proxy.CommonProxy;
import brockstar17.utility.Log;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The main class of the mod. <br>
 * This is what forge will look for to inject the mod into minecraft.
 * 
 * @author Brockstar17
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ArcaneAscension
{

	@Mod.Instance(Reference.MODID)
	public static ArcaneAscension instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		ArcaneItems.preInit();
		proxy.preInit();

		Log.info("Pre-initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		ArcaneItems.init();
		proxy.init();

		Log.info("Initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

		proxy.postInit();

		Log.info("Post-initialization of Arcane Ascension is complete");
	}

	public static String prependModID(String name) {
		return Reference.MODID + ":" + name;
	}

}
