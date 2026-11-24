package tech.kotmir.airdash.config;

import java.net.URI;
import java.util.List;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import tech.kotmir.airdash.AirDashClient;

public class AirDashConfigScreen {
    public static Screen createScreen(Screen parent) {
        AirDashConfig.load();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("AirDash — Настройки"))
                .setSavingRunnable(AirDashConfigScreen::saveAll);

        ConfigEntryBuilder entryBuilder = builder.getEntryBuilder();

        ConfigCategory movement = builder.getOrCreateCategory(Component.literal("Физика & Механика"));

        movement.addEntry(entryBuilder.startBooleanToggle(Component.literal("Включить AirDash"), AirDashConfig.enabled)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> AirDashConfig.enabled = newValue)
                .build());

        movement.addEntry(entryBuilder.startBooleanToggle(Component.literal("Рывок по взгляду (3D)"), AirDashConfig.lookDirectionDash)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> AirDashConfig.lookDirectionDash = newValue)
                .build());

        movement.addEntry(entryBuilder.startBooleanToggle(Component.literal("Бесконечные прыжки в воздухе"), AirDashConfig.infiniteAirJumps)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> AirDashConfig.infiniteAirJumps = newValue)
                .build());

        movement.addEntry(entryBuilder.startDoubleField(Component.literal("Сила прыжка (Высота)"), AirDashConfig.jumpPower)
                .setDefaultValue(0.7)
                .setTooltip(Component.literal("Отвечает за вертикальный импульс при рывке."))
                .setSaveConsumer(newValue -> AirDashConfig.jumpPower = newValue)
                .build());

        movement.addEntry(entryBuilder.startDoubleField(Component.literal("Сила рывка (Вперед)"), AirDashConfig.forwardPower)
                .setDefaultValue(0.8)
                .setTooltip(Component.literal("Ускорение по направлению взгляда игрока."))
                .setSaveConsumer(newValue -> AirDashConfig.forwardPower = newValue)
                .build());

        movement.addEntry(entryBuilder.startBooleanToggle(Component.literal("Защита от урона при падении"), AirDashConfig.disableFallDamage)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Обнуляет дистанцию падения после применения двойного прыжка."))
                .setSaveConsumer(newValue -> AirDashConfig.disableFallDamage = newValue)
                .build());

        ConfigCategory controls = builder.getOrCreateCategory(Component.literal("Управление"));

        controls.addEntry(entryBuilder.startKeyCodeField(Component.literal("Открыть меню AirDash"), KeyMappingHelper.getBoundKeyOf(AirDashClient.openConfigKey))
                .setKeySaveConsumer(key -> AirDashClient.openConfigKey.setKey(key))
                .build());

        controls.addEntry(entryBuilder.startKeyCodeField(Component.literal("Вкл / Выкл AirDash"), KeyMappingHelper.getBoundKeyOf(AirDashClient.toggleModKey))
                .setKeySaveConsumer(key -> AirDashClient.toggleModKey.setKey(key))
                .build());

        controls.addEntry(entryBuilder.startKeyCodeField(Component.literal("Совершить рывок"), KeyMappingHelper.getBoundKeyOf(AirDashClient.dashKey))
                .setKeySaveConsumer(key -> AirDashClient.dashKey.setKey(key))
                .build());

        ConfigCategory visual = builder.getOrCreateCategory(Component.literal("Эффекты & Партиклы"));

        visual.addEntry(entryBuilder.startIntField(Component.literal("Количество партиклов"), AirDashConfig.particleCount)
                .setDefaultValue(25)
                .setTooltip(Component.literal("Плотность шлейфа партиклов во время полёта."))
                .setSaveConsumer(newValue -> AirDashConfig.particleCount = newValue)
                .build());

        visual.addEntry(entryBuilder.startStringDropdownMenu(Component.literal("Тип партиклов"), AirDashConfig.particleType)
                .setDefaultValue("FLAME")
                .setSelections(List.of("FLAME", "SOUL_FIRE_FLAME", "END_ROD", "HEART", "SMOKE"))
                .setTooltip(Component.literal("Выбери визуал шлейфа за спиной."))
                .setSaveConsumer(newValue -> AirDashConfig.particleType = newValue)
                .build());

        ConfigCategory info = builder.getOrCreateCategory(Component.literal("Об авторе"));

        info.addEntry(entryBuilder.startTextDescription(
                Component.literal("Автор мода: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal("Kotmir").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD))
        ).build());

        info.addEntry(entryBuilder.startTextDescription(
                Component.literal("Официальный сайт: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal("https://kotmir.tech")
                                .withStyle(ChatFormatting.AQUA, ChatFormatting.UNDERLINE)
                                .withStyle(s -> s.withClickEvent(new ClickEvent.OpenUrl(URI.create("https://kotmir.tech")))))
        ).build());

        info.addEntry(entryBuilder.startTextDescription(
                Component.literal("Discord сообщество: ").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal("https://discord.gg")
                                .withStyle(ChatFormatting.BLUE, ChatFormatting.UNDERLINE)
                                .withStyle(s -> s.withClickEvent(new ClickEvent.OpenUrl(URI.create("https://discord.gg")))))
        ).build());

        return builder.build();
    }

    private static void saveAll() {
        AirDashConfig.save();
        KeyMapping.resetMapping();
        Minecraft.getInstance().options.save();
    }
}
