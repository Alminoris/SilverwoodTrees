package net.alminoris.silverwoodtrees.datagen;

import net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

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

        /*addDrop(ModBlockSetsHelper.LEAVES.get("olive"), leavesDrops(ModBlockSetsHelper.LEAVES.get("olive"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("olive"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("tamarisk"), leavesDrops(ModBlockSetsHelper.LEAVES.get("tamarisk"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("tamarisk"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("western_serviceberry"), leavesDrops(ModBlockSetsHelper.LEAVES.get("western_serviceberry"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("western_serviceberry"), 0.0025f));*/
    }

    public LootTable.Builder tinyPlantDrops(Block withShears)
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithShears(
                withShears,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        withShears,
                        ItemEntry.builder(Items.WHEAT_SEEDS)
                                .conditionally(RandomChanceLootCondition.builder(0.025F))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 2))
                )
        );
    }

    private LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops)
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    private LootTable.Builder leavesItemDrops(Block leaves, Block sapling, Item item, float... saplingChance)
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance)
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                        .with(
                                ((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(item)))
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.00625F, 0.008333334F, 0.025F, 0.05F, 0.06F))
                        )
                );
    }
}
