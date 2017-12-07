package brockstar17.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/*
 * This is a simple block class and will be the only block extensively commented
 * However more advanced features in other blocks will be documented by comments
 */
public class AshBlock extends BlockBase
{

	public AshBlock(String name)
	{
		super(Material.SAND, name); // Call to super to set the block material.
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setSoundType(SoundType.SAND);
	}

}
