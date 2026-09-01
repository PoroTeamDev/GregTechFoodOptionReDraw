package gregtechfoodoption;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.items.toolitem.ToolHelper;
import gregtechfoodoption.item.GTFOMetaItem;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOButcheryHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        // Проверяем, включён ли режим
        if (!GTFOConfig.gtfoButcheryConfig.enableButcheryMode) {
            return;
        }

        Entity entity = event.getEntity();
        EntityPlayer player = event.getSource().getTrueSource() instanceof EntityPlayer ?
                (EntityPlayer) event.getSource().getTrueSource() : null;

        if (player == null) {
            return;
        }

        // Проверяем, есть ли у игрока Butchery Knife в руке
        ItemStack mainHand = player.getHeldItemMainhand();
        if (mainHand.isEmpty() || !ToolHelper.isTool(mainHand, ToolClasses.BUTCHERY_KNIFE)) {
            return;
        }

        // Определяем тип животного и создаём тушу
        ItemStack carcass = getCarcassForEntity(entity);
        if (carcass == null || carcass.isEmpty()) {
            return;
        }

        // Удаляем все стандартные дропы
        event.getDrops().clear();

        // Добавляем одну тушу (1 штука, без рандома)
        carcass.setCount(1);
        event.getDrops().add(new net.minecraft.entity.item.EntityItem(
                entity.getEntityWorld(),
                entity.posX, entity.posY, entity.posZ,
                carcass
        ));
    }

    private static ItemStack getCarcassForEntity(Entity entity) {
        if (entity instanceof EntityCow || entity instanceof EntityMooshroom) {
            return GTFOMetaItem.CARCASS_BEEF.getStackForm();
        }
        if (entity instanceof EntityPig) {
            return GTFOMetaItem.CARCASS_PORK.getStackForm();
        }
        if (entity instanceof EntityChicken) {
            return GTFOMetaItem.CARCASS_CHICKEN.getStackForm();
        }
        if (entity instanceof EntitySheep) {
            return GTFOMetaItem.CARCASS_MUTTON.getStackForm();
        }
        if (entity instanceof EntityRabbit) {
            return GTFOMetaItem.CARCASS_RABBIT.getStackForm();
        }
        return null;
    }
}