package net.alminoris.silverwoodtrees.world;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures
{
    public static RegistryKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        /*register(context, OLIVE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("olive")),
                new BendingTrunkPlacer(
                        4,
                        2,
                        1,
                        5,
                        ConstantIntProvider.create(2)
                ),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("olive")),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());*/
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries)
    {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(SilverwoodTrees.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}