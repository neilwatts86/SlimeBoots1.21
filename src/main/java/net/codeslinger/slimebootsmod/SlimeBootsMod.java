package net.codeslinger.slimebootsmod;

import net.codeslinger.slimebootsmod.item.SlimeBootsItem;
import com.mojang.logging.LogUtils;
import net.codeslinger.slimebootsmod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(SlimeBootsMod.MOD_ID)
public class SlimeBootsMod {
    public static final String MOD_ID = "slimebootsmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SlimeBootsMod(IEventBus modEventBus, ModContainer modContainer) {

        // Register common setup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register mod events (using NeoForge's event bus)
        NeoForge.EVENT_BUS.register(this);

        // Register items
        ModItems.register(modEventBus);

        // Register item to a creative tab
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.addListener(SlimeBootsItem::onLivingJump);

        // Register ModConfig so FML can load config file
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // This is where you can initialize items and other things that need to be set up
        LOGGER.info("Common setup for " + MOD_ID);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SLIME_BOOTS.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server startup logic
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup for " + MOD_ID);
        }
    }
}
