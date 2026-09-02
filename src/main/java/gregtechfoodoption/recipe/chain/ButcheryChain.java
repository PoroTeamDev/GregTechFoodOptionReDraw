package gregtechfoodoption.recipe.chain;

import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.machines.GTFOTileEntities.MEAT_PROCESSING_STATION;

import gregtech.api.items.toolitem.ItemGTTool;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;

public class ButcheryChain {

    public static void init() {
        if (!GTFOConfig.gtfoButcheryConfig.enableButcheryMode) {
            return;
        }

        int meatMult = GTFOConfig.gtfoButcheryConfig.carcassMeatMultiplier;

        MEAT_PROCESSING_STATION.getRecipeMap().recipeBuilder()
                .inputs(CARCASS_BEEF.getStackForm())
                .duration(200)
                .EUt(30)
                .outputs(
                        new ItemStack(Items.BEEF, 6 * meatMult),
                        new ItemStack(Items.BEEF, 4 * meatMult),
                        new ItemStack(Items.LEATHER, 2),
                        new ItemStack(Items.BONE, 8),
                        new ItemStack(Items.BONE, 4),
                        GTFOMaterialHandler.Fat.getItemStack(8),
                        new ItemStack(Items.BEEF, 2 * meatMult),
                        GTFOMaterialHandler.ToughMeat.getItemStack(4),
                        ItemStack.EMPTY)
                .fluidOutputs(GTFOMaterialHandler.Blood.getFluid(1000))
                .buildAndRegister();

        MEAT_PROCESSING_STATION.getRecipeMap().recipeBuilder()
                .inputs(CARCASS_PORK.getStackForm())
                .duration(200)
                .EUt(30)
                .outputs(
                        new ItemStack(Items.PORKCHOP, 6 * meatMult),
                        new ItemStack(Items.PORKCHOP, 4 * meatMult),
                        new ItemStack(Items.LEATHER, 1),
                        new ItemStack(Items.BONE, 6),
                        new ItemStack(Items.BONE, 3),
                        GTFOMaterialHandler.Fat.getItemStack(10),
                        new ItemStack(Items.PORKCHOP, 2 * meatMult),
                        GTFOMaterialHandler.ToughMeat.getItemStack(3),
                        ItemStack.EMPTY)
                .fluidOutputs(GTFOMaterialHandler.Blood.getFluid(800))
                .buildAndRegister();

        MEAT_PROCESSING_STATION.getRecipeMap().recipeBuilder()
                .inputs(CARCASS_CHICKEN.getStackForm())
                .duration(100)
                .EUt(16)
                .outputs(
                        new ItemStack(Items.CHICKEN, 4 * meatMult),
                        new ItemStack(Items.CHICKEN, 2 * meatMult),
                        new ItemStack(Items.FEATHER, 8),
                        new ItemStack(Items.BONE, 4),
                        GTFOMaterialHandler.Fat.getItemStack(4),
                        ItemStack.EMPTY,
                        ItemStack.EMPTY,
                        ItemStack.EMPTY,
                        ItemStack.EMPTY)
                .fluidOutputs(GTFOMaterialHandler.Blood.getFluid(300))
                .buildAndRegister();

        MEAT_PROCESSING_STATION.getRecipeMap().recipeBuilder()
                .inputs(CARCASS_MUTTON.getStackForm())
                .duration(200)
                .EUt(30)
                .outputs(
                        new ItemStack(Items.MUTTON, 6 * meatMult),
                        new ItemStack(Items.MUTTON, 4 * meatMult),
                        new ItemStack(Items.LEATHER, 2),
                        new ItemStack(Items.BONE, 6),
                        new ItemStack(Items.BONE, 3),
                        GTFOMaterialHandler.Fat.getItemStack(8),
                        new ItemStack(Items.MUTTON, 2 * meatMult),
                        GTFOMaterialHandler.ToughMeat.getItemStack(3),
                        ItemStack.EMPTY)
                .fluidOutputs(GTFOMaterialHandler.Blood.getFluid(700))
                .buildAndRegister();

        MEAT_PROCESSING_STATION.getRecipeMap().recipeBuilder()
                .inputs(CARCASS_RABBIT.getStackForm())
                .duration(100)
                .EUt(16)
                .outputs(
                        new ItemStack(Items.RABBIT, 4 * meatMult),
                        new ItemStack(Items.RABBIT, 2 * meatMult),
                        new ItemStack(Items.RABBIT_HIDE, 2),
                        new ItemStack(Items.BONE, 3),
                        GTFOMaterialHandler.Fat.getItemStack(3),
                        ItemStack.EMPTY,
                        ItemStack.EMPTY,
                        ItemStack.EMPTY,
                        ItemStack.EMPTY)
                .fluidOutputs(GTFOMaterialHandler.Blood.getFluid(200))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_butchery_beef_hand",
                new ItemStack(Items.BEEF, 6 * meatMult),
                CARCASS_BEEF,
                "craftingToolKnife"
        );
        ModHandler.addShapelessRecipe("gtfo_butchery_pork_hand",
                new ItemStack(Items.PORKCHOP, 5 * meatMult),
                CARCASS_PORK,
                "craftingToolKnife"
        );
        ModHandler.addShapelessRecipe("gtfo_butchery_mutton_hand",
                new ItemStack(Items.MUTTON, 5 * meatMult),
                CARCASS_MUTTON,
                "craftingToolKnife"
        );
        ModHandler.addShapelessRecipe("gtfo_butchery_chicken_hand",
                new ItemStack(Items.CHICKEN, 3 * meatMult),
                CARCASS_CHICKEN,
                "craftingToolKnife"
        );
        ModHandler.addShapelessRecipe("gtfo_butchery_rabbit_hand",
                new ItemStack(Items.RABBIT, 2 * meatMult),
                CARCASS_RABBIT,
                "craftingToolKnife"
        );

    }
}