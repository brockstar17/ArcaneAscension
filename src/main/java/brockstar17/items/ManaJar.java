package brockstar17.items;

import brockstar17.ArcaneAscension;
import brockstar17.capability.ArcaneManaProvider;
import brockstar17.capability.IArcaneMana;
import brockstar17.utility.Log;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

/**
 * This class defines the item mana jar. <br>
 * When on ground the jar will display how much mana the player has.
 * 
 * @author Brockstar17
 */
public class ManaJar extends Item
{

	private boolean shouldDisplayMana;

	public ManaJar(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(ArcaneAscension.prependModID(name));
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		Capability<IArcaneMana> cmana = ArcaneManaProvider.MANA;

		if (player.getCapability(cmana, null) != null) {
			IArcaneMana mana = player.getCapability(cmana, null);

			int amount = mana.getMana(); // The amount of mana that the player has

			if (!world.isRemote) // Only output from server-side
				Log.info("The player has " + amount + " mana"); // Log how much mana the player has.
		}
		else {
			if (!world.isRemote) // Check if server-side
				Log.error("IArcaneMana has not been registered correctly, things are about to break.");
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {

		return super.onDroppedByPlayer(item, player);
	}

	public void setShouldDisplayMana(boolean value) {
		this.shouldDisplayMana = value;
	}

	public boolean getShouldDisplayMana() {
		return this.shouldDisplayMana;
	}

}
