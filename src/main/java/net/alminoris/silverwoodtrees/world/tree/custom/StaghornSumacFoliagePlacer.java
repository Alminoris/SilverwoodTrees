package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.silverwoodtrees.world.tree.ModFoliagePlacerTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class StaghornSumacFoliagePlacer extends CherryFoliagePlacer
{
    public StaghornSumacFoliagePlacer(
            IntProvider radius,
            IntProvider offset,
            IntProvider height,
            float wideBottomLayerHoleChance,
            float cornerHoleChance,
            float hangingLeavesChance,
            float hangingLeavesExtensionChance)
    {
        super(radius, offset, height, wideBottomLayerHoleChance, cornerHoleChance, hangingLeavesChance, hangingLeavesExtensionChance);
    }

    @Override
    protected FoliagePlacerType<?> getType()
    {
        return ModFoliagePlacerTypes.STAGHORN_SUMAC_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config,
                            int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset)
    {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for (int y = -foliageHeight; y <= offset; y++)
        {
            int layerRadius = radius + (random.nextInt(2) - 1);
            for (int x = -layerRadius; x <= layerRadius; x++)
            {
                for (int z = -layerRadius; z <= layerRadius; z++)
                {
                    double dist = Math.sqrt(x * x + z * z);

                    if (dist <= layerRadius + random.nextFloat() * 0.5f)
                    {
                        if (random.nextFloat() > 0.25f)
                        {
                            BlockPos foliagePos = mutablePos.set(
                                    treeNode.getCenter().getX() + x,
                                    treeNode.getCenter().getY() + y,
                                    treeNode.getCenter().getZ() + z
                            );
                            placer.placeBlock(foliagePos, config.foliageProvider.get(random, foliagePos));
                        }
                    }
                }
            }
        }
    }
}