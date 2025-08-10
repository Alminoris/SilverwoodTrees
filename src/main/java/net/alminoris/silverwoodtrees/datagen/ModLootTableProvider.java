package net.alminoris.silverwoodtrees.datagen;

import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            addDrop(ModBlockSetsHelper.LOGS.get(name));
            addDrop(ModBlockSetsHelper.STRIPPED_LOGS.get(name));
            addDrop(ModBlockSetsHelper.WOODS.get(name));
            addDrop(ModBlockSetsHelper.STRIPPED_WOODS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_PLANKS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_SLABS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_STAIRS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_FENCES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_BUTTONS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_SIGNS.get(name), drops(ModBlockSetsHelper.WOODEN_WALL_SIGNS.get(name)));
            addDrop(ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get(name), drops(ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get(name)));
            addDrop(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name));
        }

        addDrop(ModBlockSetsHelper.LEAVES.get("walnut"), leavesDrops(ModBlockSetsHelper.LEAVES.get("walnut"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("walnut"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("silver_maple"), leavesDrops(ModBlockSetsHelper.LEAVES.get("silver_maple"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("silver_maple"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("staghorn_sumac"), leavesDrops(ModBlockSetsHelper.LEAVES.get("staghorn_sumac"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("staghorn_sumac"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("silverberry"), leavesDrops(ModBlockSetsHelper.LEAVES.get("silverberry"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("silverberry"), 0.0025f));
    }
}