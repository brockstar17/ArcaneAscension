package brockstar17.items;

import brockstar17.ArcaneAscension;
import brockstar17.capability.learned.ILearnedSpells;
import brockstar17.capability.learned.LearnedProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

/**
 * A basic item. I shouldn't have to explain this, so I'm not going to.
 * 
 * @author Brockstar17
 */
public class ArcaneAsh extends Item
{
	private Capability<ILearnedSpells> cls = LearnedProvider.LEARNED;

	public ArcaneAsh(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(ArcaneAscension.prependModID(name));
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
