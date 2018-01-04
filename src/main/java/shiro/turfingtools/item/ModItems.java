package shiro.turfingtools.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import shiro.turfingtools.TurfingTools;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TurfingTools.MODID)
public final class ModItems {

    public static final Item TILLER_WOOD = new ItemTiller(Item.ToolMaterial.WOOD, "tiller_wood");
    public static final Item TILLER_STONE = new ItemTiller(Item.ToolMaterial.STONE, "tiller_stone");
    public static final Item TILLER_IRON = new ItemTiller(Item.ToolMaterial.IRON, "tiller_iron");
    public static final Item TILLER_GOLD = new ItemTiller(Item.ToolMaterial.GOLD, "tiller_gold");
    public static final Item TILLER_DIAMOND = new ItemTiller(Item.ToolMaterial.DIAMOND, "tiller_diamond");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.registerAll(
                TILLER_WOOD,
                TILLER_STONE,
                TILLER_IRON,
                TILLER_GOLD,
                TILLER_DIAMOND
        );
    }
}
