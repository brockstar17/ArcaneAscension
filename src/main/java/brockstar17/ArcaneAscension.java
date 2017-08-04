package brockstar17;

import brockstar17.capability.CapabilityHandler;
import brockstar17.capability.mana.ArcaneMana;
import brockstar17.capability.mana.ArcaneManaStorage;
import brockstar17.capability.mana.IArcaneMana;
import brockstar17.capability.spells.ArcaneSpells;
import brockstar17.capability.spells.ArcaneSpellsStorage;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.events.ArcaneGuiHandler;
import brockstar17.events.ArcaneManaEventsHandler;
import brockstar17.gui.RenderArcaneGui;
import brockstar17.items.ArcaneItems;
import brockstar17.network.NetworkHandler;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
		CapabilityManager.INSTANCE.register(IArcaneSpells.class, new ArcaneSpellsStorage(), ArcaneSpells.class);

		ArcaneItems.preInit();
		proxy.preInit();
		NetworkHandler.init();

		Log.info("Pre-initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		// Put this first because many items and spells will use mana
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

		ArcaneItems.init();
		proxy.init();

		MinecraftForge.EVENT_BUS.register(new ArcaneManaEventsHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(ArcaneAscension.instance, new ArcaneGuiHandler());

		Log.info("Initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

		proxy.postInit();

		MinecraftForge.EVENT_BUS.register(new RenderArcaneGui());

		Log.info("Post-initialization of Arcane Ascension is complete");
	}

	public static String prependModID(String name) {
		return Reference.MODID + ":" + name;
	}

}
