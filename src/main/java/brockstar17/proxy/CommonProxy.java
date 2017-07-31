package brockstar17.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * The client and server proxies will extend this class and use the abstract methods below. <br>
 * This allows for the separation of client-side and server-side code.
 * 
 * @author Brockstar17
 */
public abstract class CommonProxy
{

	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

	public abstract EntityPlayer getClientPlayer();

}
