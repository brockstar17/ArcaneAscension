package brockstar17.effects;

import brockstar17.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

/**
 * This Class defines the freeze effect
 * 
 * @author Brockstar17
 */
public class FreezeEffect extends Potion // Extend potion because the potion class has all the nifty
										 // effect methods already
{

	// The resource location of the effect icon
	public static final ResourceLocation icon = new ResourceLocation(Reference.MODID, "textures/gui/inventory.png");
	// Is the potion bad (who really cares, I guess minecraft does)
	private boolean isBad;

	// A constructor, is it bad? how much should we amplify it?
	// These values are used by the potion class. But you can use the amplifier to amplify your
	// effects, a minecraft example is speed 1 and 2 or poison 1 and 2
	public FreezeEffect(boolean isBad, int amp)
	{
		super(isBad, amp);
		this.isBad = isBad;
	}

	// Set the icon index with x and y as the offset coords from the default start. When you make
	// your icon texture, if you use the methods below you have to
	// map out your texture exactly as Minecraft does. The reference minecraft texture is the
	// inventory texture.
	public Potion setIconIndex(int x, int y) {
		super.setIconIndex(x, y);
		return (Potion) this;
	}

	// This displays your status icon in the player inventory when the player is affected by this
	// effect.
	public int getStatusIconIndex() {
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);

		return super.getStatusIconIndex();
	}

	// This return if the potion is bad or not. This would be useful if you were making spoiled milk
	// and an effect for drinking it!
	public boolean isBadEffect() {
		return this.isBad;
	}

}
