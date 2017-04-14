package abused_master.retromods.proxy;

import abused_master.retromods.registry.ModBlocks;
import abused_master.retromods.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModBlocks.initRenders();
        ModItems.initRenders();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    public World getServerWorld(int id) {
        return DimensionManager.getWorld(id);
    }
}
