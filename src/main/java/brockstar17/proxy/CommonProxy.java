package brockstar17.proxy;

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

}
