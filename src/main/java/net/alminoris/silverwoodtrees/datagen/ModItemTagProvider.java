package net.alminoris.silverwoodtrees.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.silverwoodtrees.util.helper.ModBlockSetsHelper.*;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        for (String name : WOOD_NAMES)
        {
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                    .add(WOODEN_HANGING_SIGN_ITEMS.get(name));

            getOrCreateTagBuilder(ItemTags.SIGNS)
                    .add(WOODEN_SIGN_ITEMS.get(name));
        }
    }
}