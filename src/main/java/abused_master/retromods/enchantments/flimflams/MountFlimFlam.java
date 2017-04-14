package abused_master.retromods.enchantments.flimflams;

import abused_master.retromods.RetroUtil;
import abused_master.retromods.api.IFlimFlamAction;
import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class MountFlimFlam implements IFlimFlamAction {

	private static final Predicate<EntityLiving> SAFE_SELECTOR = new Predicate<EntityLiving>() {
		@Override
		public boolean apply(EntityLiving entity) {
			return !(entity instanceof EntityCreeper) && !(entity instanceof EntitySquid);
		}
	};

	@Override
	public boolean execute(EntityPlayerMP target) {
		final World world = target.worldObj;

		AxisAlignedBB around = target.getEntityBoundingBox().expand(40, 40, 40);
		List<EntityLiving> mobs = world.getEntitiesWithinAABB(EntityLiving.class, around, SAFE_SELECTOR);
		if (mobs.isEmpty()) return false;
		EntityLiving selected = RetroUtil.getRandom(mobs);
		return target.startRiding(selected);
	}

}
