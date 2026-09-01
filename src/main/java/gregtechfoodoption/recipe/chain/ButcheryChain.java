package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.recipe.GTFORecipeMaps;

public class ButcheryChain {

    public static void init() {
        if (!GTFOConfig.gtfoButcheryConfig.enableButcheryMode) {
            return;
        }

        int time = GTFOConfig.gtfoButcheryConfig.carcassProcessingTime;
        int meatMult = GTFOConfig.gtfoButcheryConfig.carcassMeatMultiplier;

        // ===== РАЗДЕЛКА В CUISINE ASSEMBLER =====

        // Говяжья туша
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CARCASS_BEEF.getStackForm())
                .fluidInputs(Materials.Water.getFluid(1000))
                .duration(time)
                .EUt(16)
                .outputs(new ItemStack(Items.BEEF, 3 * meatMult))
                .output(new ItemStack(Items.LEATHER, 1).getItem())
                .buildAndRegister();

        // Свиная туша
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CARCASS_PORK.getStackForm())
                .fluidInputs(Materials.Water.getFluid(1000))
                .duration(time)
                .EUt(16)
                .outputs(new ItemStack(Items.PORKCHOP, 3 * meatMult))
                .output(new ItemStack(Items.LEATHER, 1).getItem())
                .buildAndRegister();

        // Куриная туша
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CARCASS_CHICKEN.getStackForm())
                .fluidInputs(Materials.Water.getFluid(1000))
                .duration(time)
                .EUt(16)
                .outputs(new ItemStack(Items.CHICKEN, 3 * meatMult))
                .output(new ItemStack(Items.FEATHER, 4).getItem())
                .buildAndRegister();

        // Баранина
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CARCASS_MUTTON.getStackForm())
                .fluidInputs(Materials.Water.getFluid(1000))
                .duration(time)
                .EUt(16)
                .outputs(new ItemStack(Items.MUTTON, 3 * meatMult))
                .output(new ItemStack(Items.LEATHER, 1).getItem())
                .buildAndRegister();

        // Кролик
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CARCASS_RABBIT.getStackForm())
                .fluidInputs(Materials.Water.getFluid(1000))
                .duration(time)
                .EUt(16)
                .outputs(new ItemStack(Items.RABBIT, 2 * meatMult))
                .output(new ItemStack(Items.RABBIT_HIDE, 2).getItem())
                .buildAndRegister();

        // ===== РУЧНАЯ РАЗДЕЛКА (КРАФТ) =====
        // Получаем нож как ItemStack (приводим IGTTool к Item)
        ItemStack knife = new ItemStack((Item) BUTCHERY_KNIFE_HV);

        ModHandler.addShapelessRecipe("gtfo_butchery_beef_hand",
                new ItemStack(Items.BEEF, 2 * meatMult),
                CARCASS_BEEF,
                knife);

        ModHandler.addShapelessRecipe("gtfo_butchery_pork_hand",
                new ItemStack(Items.PORKCHOP, 2 * meatMult),
                CARCASS_PORK,
                knife);

        ModHandler.addShapelessRecipe("gtfo_butchery_chicken_hand",
                new ItemStack(Items.CHICKEN, 2 * meatMult),
                CARCASS_CHICKEN,
                knife);

        ModHandler.addShapelessRecipe("gtfo_butchery_mutton_hand",
                new ItemStack(Items.MUTTON, 2 * meatMult),
                CARCASS_MUTTON,
                knife);

        ModHandler.addShapelessRecipe("gtfo_butchery_rabbit_hand",
                new ItemStack(Items.RABBIT, 2 * meatMult),
                CARCASS_RABBIT,
                knife);
    }
}