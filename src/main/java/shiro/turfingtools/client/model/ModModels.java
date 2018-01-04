package shiro.turfingtools.client.model;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import shiro.turfingtools.TurfingTools;
import shiro.turfingtools.item.ModItems;

import java.util.Objects;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = TurfingTools.MODID)
public class ModModels {

    @SubscribeEvent
    public static void registerModels(final ModelRegistryEvent event) {
        registerItemModel(
                ModItems.TILLER_WOOD,
                ModItems.TILLER_STONE,
                ModItems.TILLER_IRON,
                ModItems.TILLER_GOLD,
                ModItems.TILLER_DIAMOND
        );
    }

    private static void registerItemModel(Item... items) {
        for (final Item item : items) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
        }
    }
}
