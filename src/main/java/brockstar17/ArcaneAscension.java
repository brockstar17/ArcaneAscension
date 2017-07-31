package brockstar17;

import brockstar17.capability.ArcaneMana;
import brockstar17.capability.ArcaneManaStorage;
import brockstar17.capability.CapabilityHandler;
import brockstar17.capability.IArcaneMana;
import brockstar17.events.ArcaneManaEventsHandler;
import brockstar17.items.ArcaneItems;
import brockstar17.proxy.CommonProxy;
import brockstar17.utility.Log;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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

		// Put this first because many items and spells will use mana
		CapabilityManager.INSTANCE.register(IArcaneMana.class, new ArcaneManaStorage(), ArcaneMana.class);

		ArcaneItems.preInit();
		proxy.preInit();

		Log.info("Pre-initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		// Put this first because many items and spells will use mana
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

		ArcaneItems.init();
		proxy.init();

		MinecraftForge.EVENT_BUS.register(new ArcaneManaEventsHandler());

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
