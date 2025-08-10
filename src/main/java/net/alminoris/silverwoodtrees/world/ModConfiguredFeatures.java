package net.alminoris.silverwoodtrees.world;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.tree.custom.SilverMapleFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacTrunkPlacer;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.*;

public class ModConfiguredFeatures
{
    public static RegistryKey<ConfiguredFeature<?, ?>> WALNUT_KEY = registerKey("walnut");

    public static RegistryKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_KEY = registerKey("silver_maple");

    public static RegistryKey<ConfiguredFeature<?, ?>> STAGHORN_SUMAC_KEY = registerKey("staghorn_sumac");

    public static RegistryKey<ConfiguredFeature<?, ?>> SILVERBERRY_KEY = registerKey("silverberry");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        register(context, WALNUT_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("walnut")),
                new BendingTrunkPlacer(4, 2, 3, 4, ConstantIntProvider.create(2)),

                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("walnut")),
                new LargeOakFoliagePlacer(
                        ConstantIntProvider.create(3),
                        ConstantIntProvider.create(2),
                        3),

                new TwoLayersFeatureSize(2, 0, 2)
        ).ignoreVines().build());

        register(context, SILVER_MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("silver_maple")),
                new StraightTrunkPlacer(5, 1, 1),

                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("silver_maple")),
                new SilverMapleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(3), 7),

                new TwoLayersFeatureSize(2, 0, 2)
        ).build());

        register(context, STAGHORN_SUMAC_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("staghorn_sumac")),
                new StaghornSumacTrunkPlacer(
                        2, 1, 1,
                        ConstantIntProvider.create(3),
                        UniformIntProvider.create(2, 3),
                        UniformIntProvider.create(-1, 0),
                        UniformIntProvider.create(1, 2)
                ),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("staghorn_sumac")),
                new StaghornSumacFoliagePlacer(
                        UniformIntProvider.create(3, 4),
                        ConstantIntProvider.create(2),
                        UniformIntProvider.create(4, 6),
                        0.4f,
                        0.3f,
                        0.2f,
                        0.15f
                ),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().build());

        register(context, SILVERBERRY_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("silverberry")),
                new ForkingTrunkPlacer(3, 2, 2),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("silverberry")),
                new BushFoliagePlacer(
                        ConstantIntProvider.create(1),
                        ConstantIntProvider.create(0),
                        2
                ),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());
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