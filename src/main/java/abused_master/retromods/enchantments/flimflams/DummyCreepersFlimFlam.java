package abused_master.retromods.enchantments.flimflams;

import abused_master.retromods.api.IFlimFlamAction;
import abused_master.retromods.reflection.FieldAccess;
import com.google.common.base.Throwables;
import java.util.Random;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

public class DummyCreepersFlimFlam implements IFlimFlamAction {

	private static final Random random = new Random();

	private static final FieldAccess<Integer> EXPLOSION_RADIUS = FieldAccess.create(EntityCreeper.class, "explosionRadius", "field_82226_g");
	private static final FieldAccess<DataParameter<Boolean>> POWERED_DATA_PARAMETER = FieldAccess.create(EntityCreeper.class, "POWERED", "field_184714_b");

	@Override
	public boolean execute(EntityPlayerMP target) {

		for (int i = 0; i < 15; i++) {
			EntityCreeper creeper = new EntityCreeper(target.worldObj);
			try {
				EXPLOSION_RADIUS.set(creeper, 0);
				EntityDataManager watcher = creeper.getDataManager();
				watcher.set(POWERED_DATA_PARAMETER.get(null), true);
			} catch (Throwable t) {
				throw Throwables.propagate(t);
			}

			creeper.setPosition(target.posX + 20 * (random.nextFloat() - 0.5),
					target.posY + 5 * (1 + random.nextFloat()),
					target.posZ + 20 * (random.nextFloat() - 0.5));
			target.worldObj.spawnEntityInWorld(creeper);
		}
		return true;
	}

}
