package brockstar17.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * A class for the server proxy. <br>
 * Server-side only code should go in the following methods.
 * 
 * @author Brockstar17
 */
public class ServerProxy extends CommonProxy
{

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
	}

	@Override
	public void postInit() {
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return null;
	}

}
