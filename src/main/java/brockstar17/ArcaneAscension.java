package brockstar17;

import brockstar17.blocks.ArcaneBlocks;
import brockstar17.capability.CapabilityHandler;
import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedSpells;
import brockstar17.capability.learned.LearnedStorage;
import brockstar17.capability.mana.ArcaneMana;
import brockstar17.capability.mana.ArcaneManaStorage;
import brockstar17.capability.mana.IArcaneMana;
import brockstar17.capability.spells.ArcaneSpells;
import brockstar17.capability.spells.ArcaneSpellsStorage;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.client.gui.RenderArcaneGui;
import brockstar17.effects.FreezeEffect;
import brockstar17.effects.cooldowns.EntombCD;
import brockstar17.effects.cooldowns.FireballCD;
import brockstar17.effects.cooldowns.FreezeCD;
import brockstar17.effects.cooldowns.GatewayCD;
import brockstar17.effects.cooldowns.HealCD;
import brockstar17.effects.cooldowns.LightningCD;
import brockstar17.effects.cooldowns.RHealCD;
import brockstar17.effects.cooldowns.WhirlwindCD;
import brockstar17.events.ArcaneEffectHandler;
import brockstar17.events.ArcaneEvents;
import brockstar17.events.ArcaneGuiHandler;
import brockstar17.items.ArcaneItems;
import brockstar17.network.NetworkHandler;
import brockstar17.proxy.CommonProxy;
import brockstar17.utility.Log;
import net.minecraft.potion.Potion;
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
 * This is what forge will look for to inject the mod into minecraft.<br>
 * I also hope to describe a lot of what I do with comments in order to help others in their modding
 * pursuits. It is also in an effort to demonstrate to myself that I know partially what I am doing
 * and to share that knowledge with others that are interested in learning.
 * 
 * @author Brockstar17
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ArcaneAscension
{
	// The mod instance, used for some advanced features and is generally good practice to add
	@Mod.Instance(Reference.MODID)
	public static ArcaneAscension instance;

	// Declare the sided proxy so that I only have to make one call that will call both proxies from
	// the common one
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	// Declare potion effects here
	public static Potion freezeEffect = new FreezeEffect(true, 0).setIconIndex(0, 0).setRegistryName(Reference.MODID, "potion.freezeEffect").setPotionName("Frozen");
	public static Potion entombCD = new EntombCD(false, 0).setRegistryName(Reference.MODID, "potion.ecd");
	public static Potion fireballCD = new FireballCD(false, 0).setRegistryName(Reference.MODID, "potion.fbcd");
	public static Potion freezeCD = new FreezeCD(false, 0).setRegistryName(Reference.MODID, "potion.fecd");
	public static Potion gatewayCD = new GatewayCD(false, 0).setRegistryName(Reference.MODID, "potion.gcd");
	public static Potion healCD = new HealCD(false, 0).setRegistryName(Reference.MODID, "potion.hcd");
	public static Potion lightningCD = new LightningCD(false, 0).setRegistryName(Reference.MODID, "potion.lcd");
	public static Potion rHealCD = new RHealCD(false, 0).setRegistryName(Reference.MODID, "potion.rhcd");
	public static Potion whirlwindCD = new WhirlwindCD(false, 0).setRegistryName(Reference.MODID, "potion.wcd");
	// End of potion effects

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		// Put this first because many items and spells will use mana
		CapabilityManager.INSTANCE.register(IArcaneMana.class, new ArcaneManaStorage(), ArcaneMana.class);
		CapabilityManager.INSTANCE.register(IArcaneSpells.class, new ArcaneSpellsStorage(), ArcaneSpells.class);
		CapabilityManager.INSTANCE.register(ILearnedSpells.class, new LearnedStorage(), LearnedSpells.class);

		// Register Items and Blocks
		ArcaneItems.preInit();
		ArcaneBlocks.init();

		// Run proxy pre initialization code
		proxy.preInit();
		// Initialize the network handler
		NetworkHandler.init();

		Log.info("Pre-initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		// Put this first because many items and spells will use mana
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

		// Run proxy initialization code
		proxy.init();

		// Register my event handlers to the appropriate bus
		MinecraftForge.EVENT_BUS.register(new ArcaneEvents());
		MinecraftForge.EVENT_BUS.register(new ArcaneEffectHandler());
		// Register my gui to the network registry
		NetworkRegistry.INSTANCE.registerGuiHandler(ArcaneAscension.instance, new ArcaneGuiHandler());

		Log.info("Initialization of Arcane Ascension is complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

		// Run proxy post initialization code
		proxy.postInit();

		// Register my gui event handler to the appropriate bus
		MinecraftForge.EVENT_BUS.register(new RenderArcaneGui());

		Log.info("Post-initialization of Arcane Ascension is complete");
	}

	// A nifty method to prepend the mod id before a string
	public static String prependModID(String name) {
		return Reference.MODID + ":" + name;
	}

}
