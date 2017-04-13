package abused_master.retromods;

import abused_master.retromods.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RetroMods.MODID, name = RetroMods.MODNAME, version = RetroMods.VERSION)
public class RetroMods {

    public static final String MODID = "retromods";
    public static final String MODNAME = "Retro Mods";
    public static final String VERSION = "1.0_1.10.2";

    @Mod.Instance
    public static RetroMods instance;

    @SidedProxy(clientSide = "abused_master.retromods.ClientProxy", serverSide = "abused_master.retromods.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
    }

    public static CreativeTabs RetroTab = new CreativeTabs("RetroTab") {
        @Override
        public Item getTabIconItem() {
            return Items.COMMAND_BLOCK_MINECART;
        }
    };
}
