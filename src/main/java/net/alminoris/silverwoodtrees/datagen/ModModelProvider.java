package net.alminoris.silverwoodtrees.datagen;

import net.alminoris.silverwoodtrees.SilverwoodTrees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Function;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;
import static net.minecraft.data.client.BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations;
import static net.minecraft.data.client.BlockStateModelGenerator.createSingletonBlockState;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        Dictionary<String, BlockStateModelGenerator.BlockTexturePool> woodenPlanksPool = new Hashtable<>()
        {{
            for(String name : WOOD_NAMES)
                put(name, blockStateModelGenerator.registerCubeAllModelTexturePool(WOODEN_PLANKS.get(name)));
        }};

        for(String name : WOOD_NAMES)
        {
            woodenPlanksPool.get(name).slab(WOODEN_SLABS.get(name));
            woodenPlanksPool.get(name).stairs(WOODEN_STAIRS.get(name));

            registerLogBlock(blockStateModelGenerator, LOGS.get(name),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/"+name+"_log_top"),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, STRIPPED_LOGS.get(name),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/stripped_"+name+"_log_top"),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/stripped_"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, WOODS.get(name),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/"+name+"_log"),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, STRIPPED_WOODS.get(name),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/stripped_"+name+"_log"),
                    Identifier.of(SilverwoodTrees.MOD_ID, "block/stripped_"+name+"_log"));

            blockStateModelGenerator.registerSingleton(LEAVES.get(name), TexturedModel.LEAVES);
            blockStateModelGenerator.registerTintableCross(WOODEN_SAPLINGS.get(name), BlockStateModelGenerator.TintType.NOT_TINTED);

            blockStateModelGenerator.registerDoor(WOODEN_DOORS.get(name));

            blockStateModelGenerator.registerOrientableTrapdoor(WOODEN_TRAPDOORS.get(name));

            woodenPlanksPool.get(name).fence(WOODEN_FENCES.get(name));
            woodenPlanksPool.get(name).fenceGate(WOODEN_FENCE_GATES.get(name));
            woodenPlanksPool.get(name).button(WOODEN_BUTTONS.get(name));
            woodenPlanksPool.get(name).pressurePlate(WOODEN_PRESSURE_PLATES.get(name));

            woodenPlanksPool.get(name).family(WOODEN_BLOCK_FAMILIES.get(name));
            registerHangingSign(blockStateModelGenerator, STRIPPED_LOGS.get(name), WOODEN_HANGING_SIGNS.get(name), WOODEN_WALL_HANGING_SIGNS.get(name));
        }
    }

    private void registerLilyPad(BlockStateModelGenerator blockStateModelGenerator, Block block)
    {
        blockStateModelGenerator.registerItemModel(block);
        blockStateModelGenerator.blockStateCollector.accept(createBlockStateWithRandomHorizontalRotations(block, ModelIds.getBlockModelId(block)));
    }

    private void registerGrassBlock(BlockStateModelGenerator generator, Block grassBlock)
    {
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.BOTTOM, TextureMap.getId(Blocks.DIRT))
                .put(TextureKey.TOP, TextureMap.getSubId(grassBlock, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(grassBlock, "_side"));
        generator.blockStateCollector.accept(createSingletonBlockState(grassBlock, Models.CUBE_BOTTOM_TOP.upload(grassBlock, textureMap, generator.modelCollector)));
    }

    public final void registerCarpet(BlockStateModelGenerator blockStateModelGenerator, Block wool, Block carpet)
    {
        Identifier identifier = TexturedModel.CARPET.get(wool).upload(carpet, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(carpet, identifier));
    }

    private void registerHangingSign(BlockStateModelGenerator blockStateModelGenerator, Block strippedLog, Block hangingSign, Block wallHangingSign)
    {
        TextureMap textureMap = TextureMap.particle(strippedLog);
        Identifier identifier = Models.PARTICLE.upload(hangingSign, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(hangingSign, identifier));
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(wallHangingSign, identifier));
    }

    private void registerLogBlock(BlockStateModelGenerator generator, Block logBlock, Identifier topTexture, Identifier sideTexture)
    {
        Function<Block, TextureMap> texturesGetter = block -> new TextureMap()
                .put(TextureKey.TOP, topTexture)
                .put(TextureKey.SIDE, sideTexture)
                .put(TextureKey.END, topTexture);

        generator.registerAxisRotated(
                logBlock,
                TexturedModel.makeFactory(texturesGetter, Models.CUBE_COLUMN),
                TexturedModel.makeFactory(texturesGetter, Models.CUBE_COLUMN_HORIZONTAL)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        for(String name : WOOD_NAMES)
        {
            itemModelGenerator.register(WOODEN_BOATS.get(name), Models.GENERATED);
            itemModelGenerator.register(WOODEN_CHEST_BOATS.get(name), Models.GENERATED);
            itemModelGenerator.register(WOODEN_HANGING_SIGN_ITEMS.get(name), Models.GENERATED);
        }
    }
}