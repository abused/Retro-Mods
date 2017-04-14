package abused_master.retromods.enchantments.flimflams;

import java.util.Arrays;
import java.util.Collections;

import abused_master.retromods.api.IFlimFlamAction;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;

public class InventoryShuffleFlimFlam implements IFlimFlamAction {

	@Override
	public boolean execute(EntityPlayerMP target) {
		if (target.openContainer != null && !(target.openContainer instanceof ContainerPlayer)) return false;
		final ItemStack[] mainInventory = target.inventory.mainInventory;
		Collections.shuffle(Arrays.asList(mainInventory));

		return true;
	}

}
