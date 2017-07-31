package brockstar17.items;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ArcaneMaterials
{
	private static int esd = 1562 * 2;

	/*
	 * addToolMaterial: parameters: String name, int harvestLevel, int durability, float efficiency,
	 * float damage, int enchantability.
	 */
	public static ToolMaterial mKKM = EnumHelper.addToolMaterial("mKKM", 3, esd, 8.0F, (float) (3 * 1.3), 14);
	public static ToolMaterial mSeishinio = EnumHelper.addToolMaterial("mSeishinio", 3, esd, 8.0F, (float) (3 * 1.5), 14);

}
