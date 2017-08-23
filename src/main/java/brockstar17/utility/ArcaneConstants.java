package brockstar17.utility;

import brockstar17.ArcaneAscension;
import net.minecraft.potion.Potion;

public class ArcaneConstants
{
	private static ArcaneAscension aa;
	public static Potion[] pcd = { aa.whirlwindCD, aa.lightningCD, aa.entombCD, aa.fireballCD, aa.healCD, aa.rHealCD, aa.gatewayCD, aa.freezeCD };

	// Cooldown order: whirlwind, lighting, entomb, fireball, heal, ranged heal, gateway, freeze
	public static int[] coolDowns = { 200, 600, 300, 300, 700, 400, 1200, 400 };

	// Mana Cost order: whirlwind, lighting, entomb, fireball, heal, ranged heal, gateway, freeze
	public static int[] manaCosts = { 10, 30, 15, 15, 35, 20, 60, 20 };

}
