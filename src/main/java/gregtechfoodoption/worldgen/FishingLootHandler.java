package gregtechfoodoption.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.item.GTFOMetaItem;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class FishingLootHandler {

    private static final ResourceLocation FISHING_FISH_LOOT = new ResourceLocation("minecraft", "gameplay/fishing/fish");

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {

        if (!FISHING_FISH_LOOT.equals(event.getName())) {
            return;
        }

        LootEntryItem troutEntry = new LootEntryItem(
                GTFOMetaItem.TROUT.getMetaItem(),
                15,
                1,
                new LootFunction[0],
                new LootCondition[0],
                "gregtechfoodoption:trout"
        );

        LootEntryItem sturgeonEntry = new LootEntryItem(
                GTFOMetaItem.STURGEON.getMetaItem(),
                5,
                1,
                new LootFunction[0],
                new LootCondition[0],
                "gregtechfoodoption:sturgeon"
        );

        LootPool pool = new LootPool(
                new LootEntryItem[]{troutEntry, sturgeonEntry},
                new LootCondition[0],
                new RandomValueRange(1.0F),
                new RandomValueRange(0.0F),
                "gtfo_fish_pool"
        );

        event.getTable().addPool(pool);
    }
}