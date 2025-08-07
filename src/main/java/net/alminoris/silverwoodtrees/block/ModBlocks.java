package net.alminoris.silverwoodtrees.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks 
{
    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SilverwoodTrees.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(SilverwoodTrees.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks()
    {

    }

    public static Block registerLeavesBlock(String name)
    {
        return registerBlock(name+"_leaves",
                new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    }

    public static Block registerLogsBlock(String name)
    {
        return registerBlock(name+"_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    }

    public static Block registerWoodsBlock(String name)
    {
        return registerBlock(name+"_wood",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    }

    public static Block registerStrippedLogsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    }

    public static Block registerStrippedWoodsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_wood",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    }

    public static Block registerPlanksBlock(String name)
    {
        return registerBlock(name+"_planks",
                new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    }

    public static Block registerSlabBlock(String name)
    {
        return registerBlock(name+"_slab",
                new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    }

    public static Block registerStairsBlock(String name, Block planks)
    {
        return registerBlock(name+"_stairs",
                new StairsBlock(planks.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    }

    public static Block registerFenceBlock(String name)
    {
        return registerBlock(name+"_fence",
                new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    }

    public static Block registerFenceGateBlock(String name)
    {
        return registerBlock(name+"_fence_gate",
                new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    }

    public static Block registerDoorBlock(String name)
    {
        return registerBlock(name+"_door",
                new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    }

    public static Block registerTrapdoorBlock(String name)
    {
        return registerBlock(name+"_trapdoor",
                new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    }

    public static Block registerButtonBlock(String name)
    {
        return registerBlock(name+"_button",
                new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    }

    public static Block registerPressurePlateBlock(String name)
    {
        return registerBlock(name+"_pressure_plate",
                new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    }

    public static Block registerSaplingBlock(String name, SaplingGenerator saplingGenerator)
    {
        return registerBlock(name+"_sapling",
                new SaplingBlock(saplingGenerator, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    }

    public static Block registerSignBlock(String name)
    {
        return registerBlock(name+"_sign",
                new TerraformSignBlock(Identifier.of(SilverwoodTrees.MOD_ID, "entity/signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    }

    public static Block registerWallSignBlock(String name)
    {
        return registerBlock(name+"_wall_sign",
                new TerraformWallSignBlock(Identifier.of(SilverwoodTrees.MOD_ID, "entity/signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)));
    }

    public static Block registerHangingSignBlock(String name)
    {
        return registerBlock(name+"_hanging_sign",
                new TerraformHangingSignBlock(Identifier.of(SilverwoodTrees.MOD_ID, "entity/signs/hanging/"+name),
                        Identifier.of(SilverwoodTrees.MOD_ID, "textures/gui/hanging_signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    }

    public static Block registerWallHangingSignBlock(String name)
    {
        return registerBlock(name+"_wall_hanging_sign",
                new TerraformWallHangingSignBlock(Identifier.of(SilverwoodTrees.MOD_ID, "entity/signs/hanging/"+name),
                        Identifier.of(SilverwoodTrees.MOD_ID, "textures/gui/hanging_signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)));
    }

    public static BlockFamily registerBlockFamily(Block planks, Block sign, Block wallSign)
    {
        return new BlockFamily.Builder(planks)
                .sign(sign, wallSign)
                .group("wooden")
                .unlockCriterionName("has_planks").build();
    }
}