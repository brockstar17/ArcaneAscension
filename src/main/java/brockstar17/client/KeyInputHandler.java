package brockstar17.client;

import java.util.List;

import brockstar17.ArcaneAscension;
import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.events.ArcaneGuiHandler;
import brockstar17.network.MessageActiveSlotChange;
import brockstar17.network.MessageEntityLookingAt;
import brockstar17.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyInputHandler
{
	private Keybindings getPressedKey() {
		for (Keybindings key : Keybindings.values()) {
			if (key.isPressed())
				return key;
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		Keybindings key = getPressedKey();

		Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;

		if (key != null) {
			switch (key) {
			case SAS1:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(0));
				break;
			case SAS2:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(1));
				break;
			case SAS3:
				NetworkHandler.sendToServer(new MessageActiveSlotChange(2));
				break;
			case SPELLGUI:
				World world = player.world;
				BlockPos pos = player.getPosition();
				player.openGui(ArcaneAscension.instance, ArcaneGuiHandler.SPELL_GUI, world, pos.getX(), pos.getY(), pos.getZ());
				break;
			case USESPELL:

				int id = getTargetID(30);
				// Log.info("KIH is sending MELA packet to server");
				NetworkHandler.sendToServer(new MessageEntityLookingAt(id));

				break;
			default:
				break;
			}
		}
	}

	private int getTargetID(double distance) {
		Minecraft mc = Minecraft.getMinecraft(); // The minecraft instance

		Entity theRenderViewEntity = mc.getRenderViewEntity(); // The render view entity
		AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(theRenderViewEntity.posX - 0.5D, theRenderViewEntity.posY - 0.0D, theRenderViewEntity.posZ - 0.5D, theRenderViewEntity.posX + 0.5D, theRenderViewEntity.posY + 1.5D, theRenderViewEntity.posZ + 0.5D); // The
																																																																	 // bounding
																																																																	 // box
																																																																	 // of
																																																																	 // the
																																																																	 // view
		RayTraceResult returnMOP = null; // A ray trace result

		if (mc.world != null) { // Check that the world is not null
			double var2 = distance; // How far to trace for
			returnMOP = theRenderViewEntity.rayTrace(var2, 0); // Perform a ray trace
			double calcdist = var2;
			Vec3d pos = theRenderViewEntity.getPositionEyes(0); // The look vector from the entity's
																 // eyes
			var2 = calcdist;
			if (returnMOP != null) // Check that the ray trace is not null
				calcdist = returnMOP.hitVec.distanceTo(pos); // Get the distance to the hit vector

			Vec3d lookvec = theRenderViewEntity.getLook(0); // The interpolated look vector
			Vec3d var8 = pos.addVector(lookvec.xCoord * var2, lookvec.yCoord * var2, lookvec.zCoord * var2); // Adding
																											 // the
																											 // interpolated
																											 // vector
																											 // to
																											 // the
																											 // position
																											 // of
																											 // the
																											 // entity's
																											 // eyes
			Entity pointedEntity = null; // The entity traced
			float var9 = 1.0F; // Not sure, need to play around with this
			@SuppressWarnings("unchecked")
			List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(theRenderViewEntity, theViewBoundingBox.expand(lookvec.xCoord * var2, lookvec.yCoord * var2, lookvec.zCoord * var2).expand(var9, var9, var9)); // Get
																																																							 // a
																																																							 // list
																																																							 // of
																																																							 // entities
																																																							 // in
																																																							 // the
																																																							 // bounding
																																																							 // box
			double d = calcdist; // Reassign the distance to another variable

			for (Entity entity : list) // Loop through the list
				if (entity.canBeCollidedWith()) { // Check if the entity would be collided with
					float bordersize = entity.getCollisionBorderSize(); // Collision border
					AxisAlignedBB aabb = new AxisAlignedBB(entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2, entity.posX + entity.width / 2, entity.posY + entity.height, entity.posZ + entity.width / 2); // The
																																																										 // bounding
																																																										 // box
																																																										 // of
																																																										 // the
																																																										 // entity
					aabb.expand(bordersize, bordersize, bordersize); // Increase the bounding box to
																	 // the size of the collision
																	 // box
					RayTraceResult mop0 = aabb.calculateIntercept(pos, var8); // Calculate if the
																				 // ray intercepts
																				 // this bounding
																				 // box

					if (aabb.isVecInside(pos)) { // Not sure what this does yet. But
												 // somehow this determines
						if (0.0D < d || d == 0.0D) { // which entity was actually hit as
													 // pointedEntity is assigned
							pointedEntity = entity;
							d = 0.0D;
						}
					}
					else if (mop0 != null) { // This handles the other case for assigning the
											 // pointedEntity
						double d1 = pos.distanceTo(mop0.hitVec); // I'm hoping to one day figure out
																 // what these are and do

						if (d1 < d || d == 0.0D) {
							pointedEntity = entity;
							d = d1;
						}
					}
				}

			// Check that ray trace has found an entity, and d is still less than
			// calcdist or the ray trace result has not been assigned yet
			if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
				return new RayTraceResult(pointedEntity).entityHit.getEntityId(); // Get a ray trace
																					 // result and
																					 // the hit
																					 // entity's id
			}

		}

		return -1; // Default to this and handle elsewhere.
	}

}
