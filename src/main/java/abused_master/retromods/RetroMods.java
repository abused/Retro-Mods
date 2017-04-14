package abused_master.retromods;

import abused_master.retromods.commands.CommandFlimFlam;
import abused_master.retromods.config.Config;
import abused_master.retromods.enchantments.flimflams.*;
import abused_master.retromods.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Mod(modid = RetroMods.MODID, name = RetroMods.MODNAME, version = RetroMods.VERSION)
public class RetroMods {

    public static final String MODID = "retromods";
    public static final String MODNAME = "Retro Mods";
    public static final String VERSION = "1.0_1.10.2";

    @Mod.Instance
    public static RetroMods instance;

    @SidedProxy(clientSide = "abused_master.retromods.proxy.ClientProxy", serverSide = "abused_master.retromods.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
        Config.init(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
        if (Enchantments.flimFlam != null) {
            FlimFlamRegistry.instance.registerFlimFlam("inventory-shuffle", -50, 100, new InventoryShuffleFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("useless-tool", -125, 50, new UselessToolFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("bane", -125, 100, new BaneFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("epic-lore", -10, 100, new LoreFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("living-rename", -10, 100, new RenameFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("squid", -75, 50, new SquidFilmFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("sheep-dye", -5, 50, new SheepDyeFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("invisible-mobs", -25, 10, new InvisibleMobsFlimFlam()).markSafe();
            FlimFlamRegistry.instance.registerFlimFlam("sound", -5, 150, new SoundFlimFlam()).markSilent().markSafe();

            FlimFlamRegistry.instance.registerFlimFlam("snowballs", -50, 50, new SnowballsFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("teleport", -100, 30, new TeleportFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("mount", -150, 25, new MountFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("encase", -50, 50, new EncaseFlimFlam()).setRange(Integer.MIN_VALUE, -300);
            FlimFlamRegistry.instance.registerFlimFlam("creepers", -60, 50, new DummyCreepersFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("disarm", -50, 50, new ItemDropFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("effect", -75, 75, new EffectFlimFlam());
            FlimFlamRegistry.instance.registerFlimFlam("skyblock", -100, 150, new SkyblockFlimFlam()).setRange(Integer.MIN_VALUE, -400);

            FlimFlamRegistry.BLACKLIST.init();
        }
    }

    @Mod.EventHandler
    public void severStart(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new CommandFlimFlam());
    }

    public static CreativeTabs RetroTab = new CreativeTabs("RetroTab") {
        @Override
        public Item getTabIconItem() {
            return Items.COMMAND_BLOCK_MINECART;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void displayAllRelevantItems(List<ItemStack> result) {
            super.displayAllRelevantItems(result);
            if (Enchantments.flimFlam != null) RetroUtil.addAllBooks(Enchantments.flimFlam, result);
        }
    };

    public static ResourceLocation location(String path) {
        return new ResourceLocation("retromods", path);
    }

    @GameRegistry.ObjectHolder(MODID)
    public static class Enchantments {
        @GameRegistry.ObjectHolder("flim_flam")
        public static final Enchantment flimFlam = null;
    }
}
