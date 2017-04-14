package abused_master.retromods.enchantments.flimflams;

import abused_master.retromods.api.IFlimFlamAction;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class TeleportFlimFlam implements IFlimFlamAction {

	@Override
	public boolean execute(EntityPlayerMP target) {
		final World world = target.worldObj;

		EntityEnderPearl e = new EntityEnderPearl(world, target);
		e.setPosition(target.posX, target.posY + 1, target.posZ);
		e.motionX = world.rand.nextGaussian();
		e.motionY = 0.5;
		e.motionZ = world.rand.nextGaussian();
		world.spawnEntityInWorld(e);
		return true;
	}

}
