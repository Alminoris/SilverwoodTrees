package net.alminoris.silverwoodtrees.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import net.alminoris.silverwoodtrees.world.tree.ModTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraft.util.dynamic.Codecs;

public class StaghornSumacTrunkPlacer extends TrunkPlacer
{
    public static <T extends IntProvider> Codec<T> createValidatingCodec(int min, int max, Codec<T> providerCodec) {
        return Codecs.validate(providerCodec, (provider) -> {
            if (provider.getMin() < min) {
                return DataResult.error(() -> "Value provider too low: " + min + " [" + provider.getMin() + "-" + provider.getMax() + "]");
            } else if (provider.getMax() > max) {
                return DataResult.error(() -> "Value provider too high: " + max + " [" + provider.getMin() + "-" + provider.getMax() + "]");
            } else if (provider.getMax() - provider.getMin() < 1) {
                return DataResult.error(() -> "Need at least 2 blocks variation for the branch starts to fit both branches");
            } else {
                return DataResult.success(provider);
            }
        });
    }

    private static final Codec<UniformIntProvider> BRANCH_START_OFFSET_FROM_TOP_CODEC = createValidatingCodec(
            -16, 0, UniformIntProvider.CODEC
    );

    public static final Codec<StaghornSumacTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance)
                    .<IntProvider, IntProvider, UniformIntProvider, IntProvider>and(
                            instance.group(
                                    IntProvider.createValidatingCodec(1, 3).fieldOf("branch_count").forGetter(tp -> tp.branchCount),
                                    IntProvider.createValidatingCodec(2, 16).fieldOf("branch_horizontal_length").forGetter(tp -> tp.branchHorizontalLength),
                                    BRANCH_START_OFFSET_FROM_TOP_CODEC.fieldOf("branch_start_offset_from_top").forGetter(tp -> tp.branchStartOffsetFromTop),
                                    IntProvider.createValidatingCodec(-16, 16).fieldOf("branch_end_offset_from_top").forGetter(tp -> tp.branchEndOffsetFromTop)
                            )
                    )
                    .apply(instance, StaghornSumacTrunkPlacer::new)
    );

    private final IntProvider branchCount;
    private final IntProvider branchHorizontalLength;
    private final UniformIntProvider branchStartOffsetFromTop;
    private final UniformIntProvider secondBranchStartOffsetFromTop;
    private final IntProvider branchEndOffsetFromTop;

    public StaghornSumacTrunkPlacer(
            int baseHeight,
            int firstRandomHeight,
            int secondRandomHeight,
            IntProvider branchCount,
            IntProvider branchHorizontalLength,
            UniformIntProvider branchStartOffsetFromTop,
            IntProvider branchEndOffsetFromTop)
    {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
        this.branchCount = branchCount;
        this.branchHorizontalLength = branchHorizontalLength;
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.secondBranchStartOffsetFromTop = UniformIntProvider.create(branchStartOffsetFromTop.getMin(), branchStartOffsetFromTop.getMax() - 1);
        this.branchEndOffsetFromTop = branchEndOffsetFromTop;
    }

    @Override
    protected TrunkPlacerType<?> getType()
    {
        return ModTrunkPlacerTypes.STAGHORN_SUMAC_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config)
    {
        setToDirt(world, replacer, random, startPos.down(), config);
        int i = Math.max(0, height - 1 + this.branchStartOffsetFromTop.get(random));
        int j = Math.max(0, height - 1 + this.secondBranchStartOffsetFromTop.get(random));
        if (j >= i) {
            j++;
        }

        int k = this.branchCount.get(random);
        boolean threeBranches = k == 3;
        boolean atLeastTwo = k >= 2;
        int trunkTop;
        if (threeBranches) {
            trunkTop = height;
        } else if (atLeastTwo) {
            trunkTop = Math.max(i, j) + 1;
        } else {
            trunkTop = i + 1;
        }

        for (int m = 0; m < trunkTop; m++) {
            this.getAndSetState(world, replacer, random, startPos.up(m), config);
        }

        List<FoliagePlacer.TreeNode> nodes = new ArrayList<>();
        if (threeBranches) {
            nodes.add(new FoliagePlacer.TreeNode(startPos.up(trunkTop), 0, false));
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        Function<BlockState, BlockState> withAxis = state -> state.withIfExists(PillarBlock.AXIS, direction.getAxis());
        nodes.add(this.generateBranch(world, replacer, random, height, startPos, config, withAxis, direction, i, i < trunkTop - 1, mutable));
        if (atLeastTwo) {
            nodes.add(this.generateBranch(world, replacer, random, height, startPos, config, withAxis, direction.getOpposite(), j, j < trunkTop - 1, mutable));
        }

        return nodes;
    }

    private FoliagePlacer.TreeNode generateBranch(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            int height,
            BlockPos startPos,
            TreeFeatureConfig config,
            Function<BlockState, BlockState> withAxisFunction,
            Direction direction,
            int branchStartOffset,
            boolean branchBelowHeight,
            BlockPos.Mutable mutablePos)
    {
        mutablePos.set(startPos).move(Direction.UP, branchStartOffset);
        int i = height - 1 + this.branchEndOffsetFromTop.get(random);
        boolean goUp = branchBelowHeight || i < branchStartOffset;
        int horizontal = this.branchHorizontalLength.get(random) + (goUp ? 1 : 0);
        BlockPos target = startPos.offset(direction, horizontal).up(i);
        int steps = goUp ? 2 : 1;

        for (int l = 0; l < steps; l++) {
            this.getAndSetState(world, replacer, random, mutablePos.move(direction), config, withAxisFunction);
        }

        Direction verticalDir = target.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;

        while (true) {
            int dist = mutablePos.getManhattanDistance(target);
            if (dist == 0) {
                return new FoliagePlacer.TreeNode(target.up(), 0, false);
            }

            float f = (float) Math.abs(target.getY() - mutablePos.getY()) / dist;
            boolean moveVertically = random.nextFloat() < f;
            mutablePos.move(moveVertically ? verticalDir : direction);
            this.getAndSetState(world, replacer, random, mutablePos, config, moveVertically ? Function.identity() : withAxisFunction);
        }
    }
}