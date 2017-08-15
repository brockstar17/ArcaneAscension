package brockstar17.events;

import brockstar17.client.gui.GuiSpellSelect;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * This class is needed because my spell select gui is an actual gui and not an in-game overlay. Not
 * much changes in drawing it though.
 * 
 * @author Brockstar17
 */
public class ArcaneGuiHandler implements IGuiHandler
{
	// The id of the gui, if this causes conflicts i guess just change it, I'm not sure if it does
	// or not.
	public static final int SPELL_GUI = 0;

	// Its an override method so you have to have it, but most straight non-container guis are going
	// to return null on server side
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == SPELL_GUI) {
			return null;
		}

		return null;
	}

	// Open the gui on client side
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == SPELL_GUI) {
			return new GuiSpellSelect(Minecraft.getMinecraft());
		}
		return null;
	}

}
