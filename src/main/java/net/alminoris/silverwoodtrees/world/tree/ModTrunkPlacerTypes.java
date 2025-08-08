package net.alminoris.silverwoodtrees.world.tree;

import net.alminoris.silverwoodtrees.mixin.FoliagePlacerTypeInvoker;
import net.alminoris.silverwoodtrees.mixin.TrunkPlacerTypeInvoker;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacTrunkPlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModTrunkPlacerTypes
{
    public static final TrunkPlacerType<?> STAGHORN_SUMAC_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("staghorn_sumac_trunk_placer", StaghornSumacTrunkPlacer.CODEC);

    public static void register()
    {
    }
}
