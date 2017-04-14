package abused_master.retromods.enchantments.flimflams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import abused_master.retromods.RetroUtil;
import abused_master.retromods.api.IFlimFlamAction;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class SheepDyeFlimFlam implements IFlimFlamAction {

	private static final Random random = new Random();

	@Override
	public boolean execute(EntityPlayerMP target) {
		World world = target.worldObj;
		AxisAlignedBB around = target.getEntityBoundingBox().expand(20, 20, 20);
		List<EntitySheep> sheeps = world.getEntitiesWithinAABB(EntitySheep.class, around);
		if (sheeps.isEmpty()) return false;

		EntitySheep chosenOne = sheeps.get(random.nextInt(sheeps.size()));
		chosenOne.setFleeceColor(RetroUtil.getRandom(Arrays.asList(EnumDyeColor.values())));
		return true;
	}

}
