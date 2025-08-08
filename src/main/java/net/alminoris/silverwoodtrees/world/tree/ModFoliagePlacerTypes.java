package net.alminoris.silverwoodtrees.world.tree;

import net.alminoris.silverwoodtrees.mixin.FoliagePlacerTypeInvoker;
import net.alminoris.silverwoodtrees.world.tree.custom.SilverMapleFoliagePlacer;
import net.alminoris.silverwoodtrees.world.tree.custom.StaghornSumacFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerTypes
{
    public static final FoliagePlacerType<?> SILVER_MAPLE_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("silver_maple_foliage_placer", SilverMapleFoliagePlacer.CODEC);

    public static final FoliagePlacerType<?> STAGHORN_SUMAC_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("staghorn_sumac_foliage_placer", StaghornSumacFoliagePlacer.CODEC);

    public static void register()
    {
    }
}