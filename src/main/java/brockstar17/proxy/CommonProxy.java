package brockstar17.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

/**
 * The client proxy will extend this class and override the methods below. <br>
 * This allows for the separation of client-side and server-side code.
 * 
 * @author Brockstar17
 */
public class CommonProxy
{

	public void preInit() {

	}

	public void init() {

	}

	public void postInit() {

	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public String localize(String unlocalized, Object... args) {
		return I18n.translateToLocalFormatted(unlocalized, args);
	}

}
