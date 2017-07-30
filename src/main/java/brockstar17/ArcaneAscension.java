package brockstar17;

import brockstar17.proxy.CommonProxy;
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

	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

}
