package abused_master.retromods.config;

import abused_master.retromods.RetroMods;
import abused_master.retromods.enchantments.EnchantmentFlimFlam;
import abused_master.retromods.enchantments.FlimFlamEnchantmentsHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Config {

    private static final String MOD_OPENBLOCKS = "OpenBlocks";
    public static String configpath;
    public static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
        configpath = event.getModConfigurationDirectory().getAbsolutePath() + File.separator;
        config = new Configuration(new File(configpath + "RetroMods.cfg"));
        try {
            config.load();
            Config.configure(config);
        } catch (Exception e1) {
            System.out.println("Error Loading Config File: RetroMods.cfg");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }


    @OnLineModifiable
    @ConfigProperty(category = "tomfoolery", name = "flimFlamBlacklist", comment = "Blacklist/Whitelist for effects used by flim-flam enchantment")
    public static String[] flimFlamList = new String[0];

    @OnLineModifiable
    @ConfigProperty(category = "tomfoolery", name = "reverseBlacklist", comment = "If true, flim-flam blacklist will become whitelist")
    public static boolean flimFlamWhitelist = false;

    @OnLineModifiable
    @ConfigProperty(category = "tomfoolery", name = "safeOnly", comment = "Allow only flimflams that don't cause death (or at least very rarely)")
    public static boolean safeFlimFlams = false;

    @ConfigProperty(category = "features", name = "flimFlamEnchantment", comment = "Is  'Flim-flam' enchantment enabled")
    public static boolean flimFlamEnchantmentEnabled = true;

    @OnLineModifiable
    @ConfigProperty(category = "tomfoolery", name = "sillyLoreDisplay", comment = "0 - lore hidden, 1 - visible only with pressed ALT, 2 - always visible")
    public static int loreDisplay = 1;

    public static void configure(Configuration config) {
        config.addCustomCategoryComment(MOD_OPENBLOCKS, "Configure Open Blocks");
        flimFlamEnchantmentEnabled = config.getBoolean("flimFlamEnchantmentEnabled", MOD_OPENBLOCKS, true, "Is  'Flim-flam' enchantment enabled");

        final List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

        if (flimFlamEnchantmentEnabled) {
            FlimFlamEnchantmentsHandler.registerCapability();
            MinecraftForge.EVENT_BUS.register(new FlimFlamEnchantmentsHandler());
            final Enchantment flimFlam = GameRegistry.register(new EnchantmentFlimFlam().setRegistryName(RetroMods.location("flim_flam")));

            for (int i = 0; i < 4; i++) {
                int emeraldCount = 1 << i;
                ItemStack result = Items.ENCHANTED_BOOK.getEnchantedItemStack(new EnchantmentData(flimFlam, i + 1));
                Object recipe[] = new Object[emeraldCount + 1];
                recipe[0] = Items.BOOK;
                Arrays.fill(recipe, 1, recipe.length, "gemEmerald");
                recipeList.add(new ShapelessOreRecipe(result, recipe));
            }

        }
    }
}
