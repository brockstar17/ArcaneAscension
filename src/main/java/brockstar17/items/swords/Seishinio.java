package brockstar17.items.swords;

import brockstar17.ArcaneAscension;
import brockstar17.items.ArcaneMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class Seishinio extends ItemSword
{

	public Seishinio(String name)
	{
		super(ArcaneMaterials.mSeishinio);
		this.setUnlocalizedName(name);
		this.setRegistryName(ArcaneAscension.prependModID(name));
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

}
