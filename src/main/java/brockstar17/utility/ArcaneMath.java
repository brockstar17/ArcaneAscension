package brockstar17.utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class ArcaneMath
{
	/**
	 * Returns the ordinal direction that the player is facing.
	 * 
	 * @param player
	 *            the player instance
	 * @return 0:south,<br>
	 * 		1:west,<br>
	 * 		2: north,<br>
	 * 		3:east
	 */
	public static int getOrdinalFacing(EntityPlayer player) {
		return MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	}
}
