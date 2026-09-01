package gregtechfoodoption.machines.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.client.renderer.ICubeRenderer;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOMetalCasing;
import gregtechfoodoption.client.GTFOClientHandler;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityMeatProcessingStation extends RecipeMapMultiblockController {

    public static final RecipeMap<SimpleRecipeBuilder> MEAT_PROCESSING_RECIPES = new RecipeMap<>(
            "meat_processing",
            1,  // 1 входной слот
            9,  // 9 выходных слотов
            0,  // 0 флюидных входов
            0,  // 0 флюидных выходов
            new SimpleRecipeBuilder(),
            false
    )
            .setSlotOverlay(false, false, GuiTextures.IN_SLOT_OVERLAY)   // Вход
            .setSlotOverlay(true, false, GuiTextures.OUT_SLOT_OVERLAY)   // Выходы
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL);

    public MetaTileEntityMeatProcessingStation(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, MEAT_PROCESSING_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityMeatProcessingStation(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XYX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XXX", "XXX")
                .where('X', states(getCasingState()).or(autoAbilities()))
                .where('Y', selfPredicate())
                .where('#', air())
                .build();
    }

    protected IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_METAL_CASING.getState(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.BISMUTH_BRONZE_CASING;
    }

    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.SLICER_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, this.getFrontFacing(),
                this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 220, 210)
                .label(10, 5, getMetaFullName())
                // Вход: 1 слот для туши
                .widget(new SlotWidget(importItems, 0, 25, 50, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.IN_SLOT_OVERLAY))
                // Прогресс-бар (используем SLICE, он есть)
                .progressBar(recipeMapWorkable::getProgressPercent, 50, 65, 20, 20,
                        GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                // 9 выходных слотов (3x3) с OUT_SLOT_OVERLAY
                .widget(new SlotWidget(exportItems, 0, 80, 20, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 1, 100, 20, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 2, 120, 20, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 3, 80, 40, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 4, 100, 40, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 5, 120, 40, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 6, 80, 60, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 7, 100, 60, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .widget(new SlotWidget(exportItems, 8, 120, 60, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 130);

        return builder.build(getHolder(), entityPlayer);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gregtechfoodoption.machine.meat_processing_station.tooltip.1"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.meat_processing_station.tooltip.2"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.meat_processing_station.tooltip.3"));
    }
}