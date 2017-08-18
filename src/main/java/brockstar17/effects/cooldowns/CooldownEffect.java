package brockstar17.effects.cooldowns;

import brockstar17.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class CooldownEffect extends Potion
{

	// The resource location of the effect icon
	public static final ResourceLocation icon = new ResourceLocation(Reference.MODID, "textures/gui/inventory.png");
	// Is the potion bad (who really cares, I guess minecraft does)
	private boolean isBad;

	public CooldownEffect(boolean isBadEffectIn, int amp)
	{
		super(isBadEffectIn, amp);

	}

	// This return if the potion is bad or not. This would be useful if you were making spoiled milk
	// and an effect for drinking it!
	public boolean isBadEffect() {
		return this.isBad;
	}

}
