package net.alminoris.silverwoodtrees.world;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.include.com.google.common.collect.ImmutableList;

import java.util.List;

public class ModPlacedFeatures
{
    public static RegistryKey<PlacedFeature> WALNUT_PLACED_KEY = registerKey("walnut_placed");

    public static RegistryKey<PlacedFeature> SILVER_MAPLE_PLACED_KEY = registerKey("silver_maple_placed");

    public static RegistryKey<PlacedFeature> STAGHORN_SUMAC_PLACED_KEY = registerKey("staghorn_sumac_placed");

    public static RegistryKey<PlacedFeature> SILVERBERRY_PLACED_KEY = registerKey("silverberry_placed");

    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, WALNUT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WALNUT_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("walnut")));

        register(context, SILVER_MAPLE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SILVER_MAPLE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("silver_maple")));

        register(context, STAGHORN_SUMAC_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STAGHORN_SUMAC_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("staghorn_sumac")));

        register(context, SILVERBERRY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SILVERBERRY_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.05f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("silverberry")));
    }

    private static List<PlacementModifier> mushroomModifiers(int chance, @Nullable PlacementModifier modifier)
    {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (modifier != null)
            builder.add(modifier);

        if (chance != 0)
            builder.add(RarityFilterPlacementModifier.of(chance));

        builder.add(SquarePlacementModifier.of());
        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
        builder.add(BiomePlacementModifier.of());
        return builder.build();
    }

    public static RegistryKey<PlacedFeature> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(SilverwoodTrees.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}