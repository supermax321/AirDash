package tech.kotmir.airdash;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import tech.kotmir.airdash.config.AirDashConfig;
import tech.kotmir.airdash.config.AirDashConfigScreen;

public class AirDashClient
implements ClientModInitializer {
    public static class_304 openConfigKey;
    public static class_304 toggleModKey;
    public static class_304 dashKey;

    public void onInitializeClient() {
        AirDashConfig.load();
        class_304.class_11900 category = class_304.class_11900.method_74698((class_2960)class_2960.method_60655((String)"airdash", (String)"general"));
        openConfigKey = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.airdash.open_config", class_3675.class_307.field_1668, 79, category));
        toggleModKey = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.airdash.toggle_mod", class_3675.class_307.field_1668, 82, category));
        dashKey = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.airdash.dash", class_3675.class_307.field_1668, 32, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.method_1436()) {
                if (client.field_1724 == null) continue;
                client.method_1507(AirDashConfigScreen.createScreen(client.field_1755));
            }
            while (toggleModKey.method_1436()) {
                if (client.field_1724 == null) continue;
                if (AirDashConfig.enabled) {
                    AirDashConfig.enabled = false;
                    client.field_1724.method_7353((class_2561)class_2561.method_43470((String)"AirDash: \u0412\u042b\u041a\u041b").method_27692(class_124.field_1061), true);
                } else {
                    AirDashConfig.enabled = true;
                    client.field_1724.method_7353((class_2561)class_2561.method_43470((String)"AirDash: \u0412\u041a\u041b").method_27692(class_124.field_1060), true);
                }
                AirDashConfig.save();
            }
        });
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {});
    }
}
