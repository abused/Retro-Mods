package abused_master.retromods;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.lang.reflect.TypeVariable;
import java.util.*;

public class RetroUtil {

    public static final TypeVariable<?> FUNCTION_A_PARAM;
    public static final TypeVariable<?> FUNCTION_B_PARAM;

    static {
        TypeVariable<?>[] vars = Function.class.getTypeParameters();
        FUNCTION_A_PARAM = vars[0];
        FUNCTION_B_PARAM = vars[1];
    }

    public static final Random rnd = new Random();

    public static class DummyException extends RuntimeException {
        private static final long serialVersionUID = 2594806051360685738L;
    }

    public static class Thrower<T extends Throwable> {
        @SuppressWarnings("unchecked")
        public T sneakyThrow(Throwable exception) throws T {
            throw (T)exception;
        }
    }

    private static final Thrower<DummyException> HELPER = new Thrower<DummyException>();

    public static DummyException sneakyThrow(Throwable exception) {
        HELPER.sneakyThrow(exception);
        return null;
    }

    public static final Predicate<Entity> NON_PLAYER = new Predicate<Entity>() {
        @Override
        public boolean apply(Entity entity) {
            return !(entity instanceof EntityPlayer);
        }
    };

    public static NBTTagCompound getItemTag(ItemStack stack) {
        NBTTagCompound result = stack.getTagCompound();
        if (result == null) {
            result = new NBTTagCompound();
            stack.setTagCompound(result);
        }
        return result;
    }

    public static <T> T getFirst(Collection<T> collection) {
        Preconditions.checkArgument(!collection.isEmpty(), "Collection cannot be empty");
        return collection.iterator().next();
    }

    public static <T> T getRandom(Collection<T> collection) {
        return getRandom(collection, rnd);
    }

    public static <T> T getRandom(Collection<T> collection, Random rand) {
        final int size = collection.size();
        Preconditions.checkArgument(size > 0, "Can't select from empty collection");
        if (size == 1) return getFirst(collection);
        int randomIndex = rnd.nextInt(size);
        int i = 0;
        for (T obj : collection) {
            if (i == randomIndex) return obj;
            i = i + 1;
        }
        return null;
    }

    public static <T> T getRandom(List<T> list) {
        return getRandom(list, rnd);
    }

    public static <T> T getRandom(List<T> list, Random rand) {
        final int size = list.size();
        Preconditions.checkArgument(size > 0, "Can't select from empty list");
        if (size == 0) return null;
        if (size == 1) return list.get(0);
        int randomIndex = rnd.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static EntityItem dropItemStackInWorld(World worldObj, Vec3i pos, ItemStack stack) {
        return dropItemStackInWorld(worldObj, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static EntityItem dropItemStackInWorld(World worldObj, double x, double y, double z, ItemStack stack) {
        float f = 0.7F;
        float d0 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        float d1 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        float d2 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        EntityItem entityitem = new EntityItem(worldObj, x + d0, y + d1, z + d2, stack);
        entityitem.setDefaultPickupDelay();
        if (stack.hasTagCompound()) {
            entityitem.getEntityItem().setTagCompound(stack.getTagCompound().copy());
        }
        worldObj.spawnEntityInWorld(entityitem);
        return entityitem;
    }

    public static void addAllBooks(Enchantment enchantment, List<ItemStack> items) {
        for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); i++)
            items.add(Items.ENCHANTED_BOOK.getEnchantedItemStack(new EnchantmentData(enchantment, i)));
    }

    public static void respond(ICommandSender sender, String format, Object... args) {
        sender.addChatMessage(new TextComponentTranslation(format, args));
    }

    public static CommandException error(String format, Object... args) throws CommandException {
        throw new CommandException(format, args);
    }

    public static List<String> filterPrefixes(String prefix, Iterable<String> proposals) {
        prefix = prefix.toLowerCase(Locale.ENGLISH);

        List<String> result = Lists.newArrayList();
        for (String s : proposals)
            if (s.toLowerCase(Locale.ENGLISH).startsWith(prefix)) result.add(s);

        return result;
    }

    public static List<String> filterPrefixes(String prefix, String... proposals) {
        return filterPrefixes(prefix, Arrays.asList(proposals));
    }

    public static List<String> getPlayerNames() {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server != null) return ImmutableList.copyOf(server.getAllUsernames());
        return ImmutableList.of();
    }

    public static List<String> fiterPlayerNames(String prefix) {
        return filterPrefixes(prefix, getPlayerNames());
    }
}
