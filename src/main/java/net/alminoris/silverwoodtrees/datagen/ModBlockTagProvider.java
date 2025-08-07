package net.alminoris.silverwoodtrees.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        for (String name : WOOD_NAMES)
        {
            getOrCreateTagBuilder(BlockTags.SAPLINGS)
                    .add(WOODEN_SAPLINGS.get(name));

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .add(LOGS.get(name))
                    .add(STRIPPED_LOGS.get(name))
                    .add(WOODS.get(name))
                    .add(STRIPPED_WOODS.get(name));

            getOrCreateTagBuilder(BlockTags.PLANKS)
                    .add(WOODEN_PLANKS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                    .add(WOODEN_FENCES.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                    .add(WOODEN_SLABS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                    .add(WOODEN_STAIRS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                    .add(WOODEN_BUTTONS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(WOODEN_PRESSURE_PLATES.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(WOODEN_TRAPDOORS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                    .add(WOODEN_DOORS.get(name));

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                    .add(WOODEN_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                    .add(WOODEN_WALL_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                    .add(WOODEN_HANGING_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                    .add(WOODEN_WALL_HANGING_SIGNS.get(name));
        }
    }
}