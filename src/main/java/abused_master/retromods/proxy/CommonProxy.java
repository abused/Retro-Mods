package abused_master.retromods.proxy;

import abused_master.retromods.registry.ModBlocks;
import abused_master.retromods.registry.ModItems;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModBlocks.initBlocks();
        ModItems.initItems();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public World getClientWorld() {
        return null;
    }

    public World getServerWorld(int id) {
        return DimensionManager.getWorld(id);
    }
}
