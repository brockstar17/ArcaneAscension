package brockstar17.events;

import brockstar17.gui.SpellSelectGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ArcaneGuiHandler implements IGuiHandler
{

	public static final int SPELL_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == SPELL_GUI) {
			return null;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == SPELL_GUI) {
			return new SpellSelectGui(Minecraft.getMinecraft());
		}
		return null;
	}

}
