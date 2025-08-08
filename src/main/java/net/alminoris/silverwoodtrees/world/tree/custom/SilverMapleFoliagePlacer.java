package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.silverwoodtrees.world.tree.ModFoliagePlacerTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;

public class SilverMapleFoliagePlacer extends LargeOakFoliagePlacer {

    public static final MapCodec<SilverMapleFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> createCodec(instance).apply(instance, SilverMapleFoliagePlacer::new)
    );

    public SilverMapleFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.SILVER_MAPLE_FOLIAGE_PLACER; // Зарегистрируйте свой тип FoliagePlacer
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config,
                            int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        // Генерация листвы с небольшими вариациями
        for (int y = -foliageHeight; y <= offset; y++) {
            int layerRadius = radius + random.nextInt(2); // Небольшая случайность для радиуса каждого слоя

            for (int x = -layerRadius; x <= layerRadius; x++) {
                for (int z = -layerRadius; z <= layerRadius; z++) {
                    if (Math.abs(x) + Math.abs(z) <= layerRadius + random.nextInt(2)) {
                        BlockPos foliagePos = mutablePos.set(treeNode.getCenter().getX() + x,
                                treeNode.getCenter().getY() + y,
                                treeNode.getCenter().getZ() + z);
                        placer.placeBlock(foliagePos, config.foliageProvider.get(random, foliagePos));
                    }
                }
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int height, TreeFeatureConfig config) {
        return 3 + random.nextInt(2); // Немного варьируется высота листвы
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // Позволяет листву расти даже в "нерегулярных" местах
        return false;
    }
}