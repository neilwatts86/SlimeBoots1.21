package net.codeslinger.slimebootsmod.item;

// In ModItems.java
import net.codeslinger.slimebootsmod.SlimeBootsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SlimeBootsMod.MOD_ID);

    public static final DeferredItem<Item> SLIME = ITEMS.register("slime",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<ArmorItem> SLIME_BOOTS = ITEMS.register("slimeboots",
            () -> new SlimeBootsItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));


    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}