package net.alminoris.silverwoodtrees.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;

import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> recipeExporter)
    {
        for(String name : WOOD_NAMES)
        {
            offerShapelessRecipe(recipeExporter, WOODEN_PLANKS.get(name), LOGS.get(name), String.valueOf(RecipeCategory.MISC), 4);
            offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, WOODEN_SLABS.get(name), WOODEN_PLANKS.get(name));
            offerBoatRecipe(recipeExporter, WOODEN_BOATS.get(name), WOODEN_PLANKS.get(name));
            offerChestBoatRecipe(recipeExporter, WOODEN_CHEST_BOATS.get(name), WOODEN_PLANKS.get(name));
            offerHangingSignRecipe(recipeExporter, WOODEN_HANGING_SIGN_ITEMS.get(name), WOODEN_PLANKS.get(name));
            offerPressurePlateRecipe(recipeExporter, WOODEN_PRESSURE_PLATES.get(name), WOODEN_PLANKS.get(name));
            offerSingleOutputShapelessRecipe(recipeExporter, WOODEN_BUTTONS.get(name), WOODEN_PLANKS.get(name), String.valueOf(RecipeCategory.MISC));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .input('#', LOGS.get(name))
                    .criterion(hasItem(LOGS.get(name)), conditionsFromItem(LOGS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STRIPPED_WOODS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .input('#', STRIPPED_LOGS.get(name))
                    .criterion(hasItem(STRIPPED_LOGS.get(name)), conditionsFromItem(STRIPPED_LOGS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODEN_STAIRS.get(name), 4)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODEN_FENCES.get(name), 3)
                    .pattern("#/#")
                    .pattern("#/#")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, WOODEN_FENCE_GATES.get(name))
                    .pattern("/#/")
                    .pattern("/#/")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, WOODEN_SIGN_ITEMS.get(name), 3)
                    .pattern("###")
                    .pattern("###")
                    .pattern(" / ")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WOODEN_DOORS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WOODEN_TRAPDOORS.get(name), 2)
                    .pattern("###")
                    .pattern("###")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);
        }
    }
}