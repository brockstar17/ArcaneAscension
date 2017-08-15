package brockstar17.blocks;

import brockstar17.ArcaneAscension;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/*
 * This is a simple block class and will be the only block extensively commented
 * However more advanced features in other blocks will be documented by comments
 */
public class AshBlock extends Block
{

	public AshBlock(String name)
	{
		super(Material.SAND); // Call to super to set the block material.
		this.setUnlocalizedName(name); // Set the name of the block
		this.setRegistryName(ArcaneAscension.prependModID(name)); // Set the registry name, this is
																	 // what forge uses to find the
																	 // models for the block
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS); // Add this to a the building block
															 // creative tab
		this.setSoundType(SoundType.SAND); // Set this block's sound type, the super call sometimes
											 // does this but it did not appear to do so for this
											 // particular block and material type.
	}

}
