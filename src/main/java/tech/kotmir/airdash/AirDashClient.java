package tech.kotmir.airdash;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import tech.kotmir.airdash.config.AirDashConfig;
import tech.kotmir.airdash.config.AirDashConfigScreen;

public class AirDashClient implements ClientModInitializer {
    public static KeyMapping openConfigKey;
    public static KeyMapping toggleModKey;
    public static KeyMapping dashKey;

    private static void onEndTick(Minecraft client) {
        while (openConfigKey.consumeClick()) {
            if (client.player == null) continue;
            client.setScreenAndShow(AirDashConfigScreen.createScreen(null));
        }
        while (toggleModKey.consumeClick()) {
            if (client.player == null) continue;
            if (AirDashConfig.enabled) {
                AirDashConfig.enabled = false;
                client.player.sendOverlayMessage(Component.literal("AirDash: ВЫКЛ").withStyle(ChatFormatting.RED));
            } else {
                AirDashConfig.enabled = true;
                client.player.sendOverlayMessage(Component.literal("AirDash: ВКЛ").withStyle(ChatFormatting.GREEN));
            }
            AirDashConfig.save();
        }
    }

    @Override
    public void onInitializeClient() {
        AirDashConfig.load();

        KeyMapping.Category category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("airdash", "general"));

        openConfigKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.airdash.open_config",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                category
        ));
        toggleModKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.airdash.toggle_mod",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                category
        ));
        dashKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.airdash.dash",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_SPACE,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(AirDashClient::onEndTick);
    }
}
