package brockstar17.items.swords;

import brockstar17.ArcaneAscension;
import brockstar17.items.ArcaneMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class Kazio extends ItemSword
{

	public Kazio(String name)
	{
		super(ArcaneMaterials.mKKM);
		this.setUnlocalizedName(name);
		this.setRegistryName(ArcaneAscension.prependModID(name));
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

}
