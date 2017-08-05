package brockstar17.network;

import java.util.Random;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.utility.Log;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class MessageUseSpell extends MessageBase<MessageUseSpell>
{

	private int spellId;

	private Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;

	public MessageUseSpell()
	{
	}

	public MessageUseSpell(int spellId)
	{
		this.spellId = spellId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.spellId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.spellId);
	}

	@Override
	public void handleClientSide(MessageUseSpell message, EntityPlayer player) {
	}

	@Override
	public void handleServerSide(MessageUseSpell message, EntityPlayer player) {
		IArcaneSpells spells = player.getCapability(cspells, null);
		World world = player.world;
		BlockPos pos = player.getPosition();
		int spellId = message.spellId;

		NetworkHandler.sendTo(new MessageEntityLookingAt(), (EntityPlayerMP) player);
		EntityLiving target = null;
		if (spells.getSpellTargetId() != -1) {
			target = (EntityLiving) world.getEntityByID(spells.getSpellTargetId());
		}
		else {
			target = null;
		}

		useSpell(spellId, world, pos, player, target);

	}

	private void useSpell(int id, World world, BlockPos pos, EntityPlayer player, EntityLivingBase target) {
		Random r = new Random();
		switch (id) {
		case 0: // Whirlwind

			break;
		case 1: // Lightning

			break;
		case 2: // Entomb

			break;
		case 3: // Fireball
			Log.info("Use Fireball");

			break;
		case 4: // Heal
			Log.info("Use Heal");
			float cHealth = player.getHealth();
			float mHealth = player.getMaxHealth() - cHealth;
			float mult = (r.nextFloat() / 4F) + .25F;
			player.setHealth(cHealth + (mHealth * mult));
			break;
		case 5: // Ranged heal
			Log.info("Use Ranged Heal");
			if (target != null) {
				/*
				 * for (int i = 0; i < 10; i++) { world.spawnParticle(EnumParticleTypes.HEART,
				 * target.posX + r.nextDouble(), target.posY + r.nextDouble(), target.posZ +
				 * r.nextDouble(), 0, 0, 0, new int[0]); }
				 */
				cHealth = target.getHealth();
				mHealth = target.getMaxHealth() - cHealth;
				mult = (r.nextFloat() / 4F) + .25F;
				target.setHealth(cHealth + (mHealth * mult));
			}

			break;
		case 6: // Gateway

			break;
		case 7: // Freeze

			break;
		}
	}

	private void particleBeam(World world, EntityPlayer player, EntityLivingBase target, EnumParticleTypes particle, Random rand) {

		if (target != null) {

			double d0 = target.posX - player.posX;
			double d1 = target.posY + (double) (target.height * 0.5F) - (player.posY + (double) player.getEyeHeight());
			double d2 = target.posZ - player.posZ;
			double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
			d0 = d0 / d3;
			d1 = d1 / d3;
			d2 = d2 / d3;
			double d4 = rand.nextDouble();

			while (d4 < d3) {
				d4 += 1.8D + rand.nextDouble() * (1.7D);
				world.spawnParticle(particle, player.posX + d0 * d4, player.posY + d1 * d4 + (double) player.getEyeHeight(), player.posZ + d2 * d4, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

	}

}
