package brockstar17.tileentity.render;

import brockstar17.tileentity.TileEntityPedastal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class PedastalRenderer extends TileEntitySpecialRenderer<TileEntityPedastal>
{

	@Override
	public void renderTileEntityAt(TileEntityPedastal te, double x, double y, double z, float partialTicks, int destroyStage) {

		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		renderItem(new ItemStack(te.getItemOnPed()), x, y, z, partialTicks);
	}

	private void renderItem(ItemStack stack, double x, double y, double z, float partialTicks) {

		if (stack != null) {

			EntityItem entityitem = new EntityItem(Minecraft.getMinecraft().world, 0.0D, 0.0D, 0.0D, stack);
			// entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.translate(0.5, 0.75, 0.5);
				GlStateManager.disableLighting();

				float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

				GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
				// GlStateManager.scale(0.5F, 0.5F, 0.5F);
				GlStateManager.pushAttrib();
				RenderHelper.enableStandardItemLighting();
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityitem, 0, 0, 0, 0F, 0F, false);
				RenderHelper.disableStandardItemLighting();
				GlStateManager.popAttrib();

				GlStateManager.enableLighting();
			}
			GlStateManager.popMatrix();
		}
	}

}
