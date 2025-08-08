package net.alminoris.silverwoodtrees.world.gen;

import net.alminoris.silverwoodtrees.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModBiomeModifications
{
    public static void addTrees()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.WALNUT_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SILVER_MAPLE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA_PLATEAU),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.STAGHORN_SUMAC_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.TAIGA),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SILVERBERRY_PLACED_KEY);
    }
}