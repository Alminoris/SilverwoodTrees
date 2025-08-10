package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.alminoris.silverwoodtrees.block.ModBlocks;
import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.alminoris.silverwoodtrees.world.tree.ModFoliagePlacerTypes;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;

public class SilverMapleFoliagePlacer extends LargeOakFoliagePlacer
{

    public static final MapCodec<SilverMapleFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> createCodec(instance).apply(instance, SilverMapleFoliagePlacer::new));

    public SilverMapleFoliagePlacer(IntProvider radius, IntProvider offset, int height)
    {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> getType()
    {
        return ModFoliagePlacerTypes.SILVER_MAPLE_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            int trunkHeight,
            FoliagePlacer.TreeNode treeNode,
            int foliageHeight,
            int radius,
            int offset)
    {
        int c = 0;
        for (int i = offset; i >= offset - foliageHeight; i--)
        {
            int rad = radius;

            int i1 = i != offset && i != offset - foliageHeight ? 1 : 0;
            if (c == 3)
            {
                int j = rad + i1;
                this.generateSquareWithoutCorners(world, placer, random, config, treeNode.getCenter(), j, i, treeNode.isGiantTrunk());
            }
            else if (c < 3)
            {
                rad--;
                int j = rad + i1;
                this.generateSquare(world, placer, random, config, treeNode.getCenter(), j, i, treeNode.isGiantTrunk());
            }
            else
            {
                int j = rad + i1;
                this.generateSquare(world, placer, random, config, treeNode.getCenter(), j, i, treeNode.isGiantTrunk());
            }
            c++;
        }
    }

    private void generateSquareWithoutCorners(
            TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos centerPos, int radius, int y, boolean giantTrunk)
    {
        int i = giantTrunk ? 1 : 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int j = -radius; j <= radius + i; j++)
        {
            for (int k = -radius; k <= radius + i; k++)
            {
                if ((Math.abs(j) == radius && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius - 1) ||
                        (Math.abs(j) == radius - 1 && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 2 && Math.abs(k) == radius - 2) ||
                        (Math.abs(j) == radius - 3 && Math.abs(k) == radius - 3) ||
                        (Math.abs(j) == radius && Math.abs(k) == radius - 3) ||
                        (Math.abs(j) == radius - 3 && Math.abs(k) == radius))
                {
                    SilverwoodTrees.LOGGER.info("TEST: Continue");
                    continue;
                }

                if (!this.isPositionInvalid(random, j, y, k, radius, giantTrunk))
                {
                    mutable.set(centerPos, j, y, k);
                    SilverwoodTrees.LOGGER.info("TEST: Place leaves block");
                    placeFoliageBlock(world, placer, random, config, mutable);
                }
            }
        }
    }
}