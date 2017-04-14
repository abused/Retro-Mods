package abused_master.retromods.enchantments.flimflams;

import abused_master.retromods.api.IFlimFlamAction;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayerMP;

public class SquidFilmFlam implements IFlimFlamAction {

	private static final Random random = new Random();

	private static final List<String> names = ImmutableList.of(
			"Fancy Hat",
			"Hello there!",
			"Look at my hat!",
			"My hat is amazing!",
			"Ceci n'est pas une pipe",
			"???");

	@Override
	public boolean execute(EntityPlayerMP target) {
		if (target.isBeingRidden()) return false;

		EntitySquid squid = new EntitySquid(target.worldObj);
		squid.moveEntity(target.posX, target.getEntityBoundingBox().minY, target.posZ);

		int selected = random.nextInt(names.size());
		squid.setCustomNameTag(names.get(selected));

		target.worldObj.spawnEntityInWorld(squid);
		squid.startRiding(target);
		return true;
	}

}
