package net.codeslinger.slimebootsmod.item;

import net.codeslinger.slimebootsmod.SlimeBootsMod;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
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

import java.util.UUID;

public class SlimeBootsItem extends ArmorItem {
    public SlimeBootsItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties.attributes(createAttributeModifiers()));
    }

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

}
