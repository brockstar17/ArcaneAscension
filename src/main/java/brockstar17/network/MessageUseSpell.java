package brockstar17.network;

import java.util.List;
import java.util.Random;

import brockstar17.capability.spells.ArcaneSpellsProvider;
import brockstar17.capability.spells.IArcaneSpells;
import brockstar17.utility.Log;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class MessageUseSpell extends MessageBase<MessageUseSpell>
{

	private int spellId;
	private int entId = -1;

	private Capability<IArcaneSpells> cspells = ArcaneSpellsProvider.SPELLS;

	public MessageUseSpell()
	{
	}

	public MessageUseSpell(int spellId, int entId)
	{
		this.spellId = spellId;
		this.entId = entId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.spellId = buf.readInt();
		this.entId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.spellId);
		buf.writeInt(this.entId);
	}

	@Override
	public void handleClientSide(MessageUseSpell message, EntityPlayer player) {
		int id = message.spellId;
		World world = player.world;
		EntityLiving target = null;
		if (message.entId != -1) {
			target = (EntityLiving) world.getEntityByID(message.entId);
		}
		spellParticles(id, world, player, target);
	}

	@Override
	public void handleServerSide(MessageUseSpell message, EntityPlayer player) {
		IArcaneSpells spells = player.getCapability(cspells, null);
		World world = player.world;
		BlockPos pos = player.getPosition();
		int spellId = message.spellId;

		EntityLiving target = null;
		if (message.entId != -1) {
			target = (EntityLiving) world.getEntityByID(message.entId);
		}

		useSpell(spellId, world, pos, player, target);
		NetworkHandler.sendTo(new MessageUseSpell(spellId, message.entId), (EntityPlayerMP) player);

	}

	private void useSpell(int id, World world, BlockPos pos, EntityPlayer player, EntityLivingBase target) {
		Random r = new Random();
		switch (id) {
		case 0: // Whirlwind
			AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - 8, pos.getY() - 1, pos.getZ() - 8, pos.getX() + 8, pos.getY() + 3, pos.getZ() + 8);
			List<EntityLiving> l = world.getEntitiesWithinAABB(EntityLiving.class, bb);
			for (EntityLiving i : l) {
				Vec3d vp = player.getPositionVector();
				Vec3d vi = i.getPositionVector();
				int sx = (int) (vp.xCoord - vi.xCoord);
				int sz = (int) (vp.zCoord - vi.zCoord);
				i.knockBack(player, 2F, sx, sz);
			}
			break;
		case 1: // Lightning
			if (target != null) {
				target.setHealth(target.getHealth() - (r.nextFloat() * 4));
				if (target.getHealth() <= 0 && !target.isDead) {
					target.onDeath(DamageSource.causePlayerDamage(player));
				}
				int chance = r.nextInt(20);
				if (chance == 1) {
					target.setFire(20);
				}
			}
			break;
		case 2: // Entomb
			if (target != null) {

				world.setBlockState(target.getPosition(), Blocks.DIRT.getDefaultState(), 2);
				target.setHealth(target.getHealth() - 0.5F);
				world.destroyBlock(target.getPosition(), false);
				if (target.getHealth() <= 0 && !target.isDead) {
					target.onDeath(DamageSource.causePlayerDamage(player));
				}
			}

			break;
		case 3: // Fireball
			Log.info("Use Fireball");
			Vec3d look = player.getLookVec();
			EntitySmallFireball fireball2 = new EntitySmallFireball(world, player, 1, 1, 1);
			fireball2.setPosition(player.posX + look.xCoord * 1.1, player.posY + look.yCoord + 1, player.posZ + look.zCoord * 1.1);
			fireball2.accelerationX = look.xCoord;
			fireball2.accelerationY = look.yCoord;
			fireball2.accelerationZ = look.zCoord;

			world.spawnEntity(fireball2);
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

	private void spellParticles(int id, World world, EntityPlayer player, EntityLivingBase target) {
		Random r = new Random();
		switch (id) {
		case 0: // Whirlwind
			for (int i = 0; i < 50; i++) {
				int x = r.nextInt(8), y = (int) player.posY, z = r.nextInt(8);
				x *= r.nextInt(4) > 2 ? -1 : 1;
				z *= r.nextInt(4) > 2 ? -1 : 1;

				x += player.posX;
				z += player.posZ;
				world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, x, y, z, 0, 0, 0, new int[0]);
			}
			break;
		case 1: // Lightning
			if (target != null) {
				world.playSound(player, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER, 2.0F, 1.0F);
				world.spawnEntity(new EntityLightningBolt(world, target.posX, target.posY, target.posZ, true));
				target.performHurtAnimation();
			}
			break;
		case 2: // Entomb
			if (target != null) {
				target.performHurtAnimation();
			}
			break;

		case 3: // Fireball

			break;
		case 4: // Heal
			for (int i = 0; i < 10; i++) {
				world.spawnParticle(EnumParticleTypes.HEART, player.posX + r.nextDouble() - .5, player.posY + r.nextDouble() + .5, player.posZ + r.nextDouble() - .5, 0, 0, 0, new int[0]);
			}
			break;
		case 5: // Ranged heal

			if (target != null) {
				for (int i = 0; i < 10; i++) {
					world.spawnParticle(EnumParticleTypes.HEART, target.posX + r.nextDouble(), target.posY + r.nextDouble(), target.posZ + r.nextDouble(), 0, 0, 0, new int[0]);
				}
			}
			break;
		case 6: // Gateway

			break;
		case 7: // Freeze

			break;
		}
	}

	private void particleBeam(World world, EntityPlayer player, EntityLivingBase target, EnumParticleTypes particle, Random rand) {

		Vec3d vp = player.getPositionVector();
		Vec3d vi = target.getPositionVector();
		int sx = (int) (vp.xCoord - vi.xCoord);
		int sz = (int) (vp.zCoord - vi.zCoord);

	}

}
