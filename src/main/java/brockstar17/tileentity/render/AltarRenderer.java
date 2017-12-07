package brockstar17.tileentity.render;

import org.lwjgl.opengl.GL11;

import brockstar17.tileentity.TileEntityAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class AltarRenderer extends TileEntitySpecialRenderer<TileEntityAltar>
{

	@Override
	public void renderTileEntityAt(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage) {
		// Log.info("Should Render Holo: " + te.shouldRenderHologram);
		// Log.info("Owner " + te.getOwner().getName());
		if (te.getShouldRendHolo()) {
			renderHologram(te, partialTicks, x, y, z, te.getOwner());
		}
		else {
			renderItem(te, partialTicks, x, y, z);
		}
	}

	private void renderItem(TileEntityAltar te, float partialTicks, double x, double y, double z) {
		ItemStack stack = te.inventory.getStackInSlot(0);
		if (!stack.isEmpty()) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			double offset = Math.sin((te.getWorld().getTotalWorldTime() - te.lastChangeTime + partialTicks) / 8) / 8.0;
			GlStateManager.translate(x + 0.5, y + 1 + offset, z + 0.5);
			GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);

			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}

	private void renderHologram(TileEntityAltar te, float partialTicks, double x, double y, double z, EntityPlayer player) {
		if (player != null) {
			// Log.info("Render Holo");
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();

			GlStateManager.translate(x, y, z);

			// Do stuff here

			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}

}
