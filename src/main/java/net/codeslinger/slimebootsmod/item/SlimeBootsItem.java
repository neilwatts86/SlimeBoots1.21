package net.codeslinger.slimebootsmod.item;

import net.codeslinger.slimebootsmod.SlimeBootsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.UUID;

public class SlimeBootsItem extends ArmorItem {
    public SlimeBootsItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties.attributes(createAttributeModifiers()));
    }

    private static final Holder<SoundEvent> JUMP_SOUND = BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.SLIME_SQUISH_SMALL);

    private static ItemAttributeModifiers createAttributeModifiers() {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(SlimeBootsMod.MOD_ID, "triplejump");

        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.JUMP_STRENGTH,
                        new AttributeModifier(
                                location,
                                2.0,
                                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                        ),
                        EquipmentSlotGroup.FEET // Applies when worn in feet slot
                )
                .build();
    }

    // Play sound when jumping (called from event handler)
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isWearingSlimeBoots(player)) {
                player.playSound(JUMP_SOUND.value(), 0.5F, 1.0F); // Volume and pitch
            }
        }

    }

    private static boolean isWearingSlimeBoots(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        return boots.getItem() instanceof SlimeBootsItem;
    }

}
