package net.alminoris.silverwoodtrees.world.tree;

import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.ModConfiguredFeatures;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;

public class ModSaplingGenerators
{
    public static final Dictionary<String, RegistryKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("walnut", ModConfiguredFeatures.WALNUT_KEY);
        put("silver_maple", ModConfiguredFeatures.SILVER_MAPLE_KEY);
        put("staghorn_sumac", ModConfiguredFeatures.STAGHORN_SUMAC_KEY);
        put("silverberry", ModConfiguredFeatures.SILVERBERRY_KEY);
    }};

    public static final Dictionary<String, net.minecraft.block.sapling.SaplingGenerator> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            put(name, new CustomSaplingGenerator(keys.get(name)));
        }
    }};
}