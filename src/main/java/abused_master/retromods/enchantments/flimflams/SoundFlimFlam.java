package abused_master.retromods.enchantments.flimflams;

import abused_master.retromods.RetroUtil;
import abused_master.retromods.api.IFlimFlamAction;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundFlimFlam implements IFlimFlamAction {

	private static final List<SoundEvent> sounds = ImmutableList.of(
			SoundEvents.ENTITY_TNT_PRIMED,
			SoundEvents.ENTITY_GENERIC_EXPLODE,
			SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE,
			SoundEvents.ENTITY_BLAZE_SHOOT,
			SoundEvents.ENTITY_ENDERMEN_STARE,
			SoundEvents.ENTITY_GHAST_SCREAM,
			SoundEvents.ENTITY_GHAST_SHOOT,
			SoundEvents.ENTITY_ZOMBIE_PIG_ANGRY,
			SoundEvents.ENTITY_CREEPER_PRIMED);

	@Override
	public boolean execute(EntityPlayerMP target) {
		SoundEvent sound = RetroUtil.getRandom(sounds);
		target.connection.sendPacket(new SPacketSoundEffect(sound, SoundCategory.MASTER, target.posX, target.posY, target.posZ, 1, 1));
		return true;
	}

}
