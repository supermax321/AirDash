package tech.kotmir.airdash.config;

import java.net.URI;
import java.util.List;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_124;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_437;
import tech.kotmir.airdash.AirDashClient;
import tech.kotmir.airdash.config.AirDashConfig;

public class AirDashConfigScreen {
    public static class_437 createScreen(class_437 parent) {
        AirDashConfig.load();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle((class_2561)class_2561.method_43470((String)"AirDash \u2014 \u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438")).setSavingRunnable(AirDashConfigScreen::saveAll);
        ConfigEntryBuilderImpl entryBuilder = builder.getEntryBuilder();
        ConfigCategory movement = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"\u0424\u0438\u0437\u0438\u043a\u0430 & \u041c\u0435\u0445\u0430\u043d\u0438\u043a\u0430"));
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c AirDash"), AirDashConfig.enabled).setDefaultValue(true).setSaveConsumer(newValue -> {
            AirDashConfig.enabled = newValue;
        }).build());
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"\u0420\u044b\u0432\u043e\u043a \u043f\u043e \u0432\u0437\u0433\u043b\u044f\u0434\u0443 (3D)"), AirDashConfig.lookDirectionDash).setDefaultValue(false).setSaveConsumer(newValue -> {
            AirDashConfig.lookDirectionDash = newValue;
        }).build());
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"\u0411\u0435\u0441\u043a\u043e\u043d\u0435\u0447\u043d\u044b\u0435 \u043f\u0440\u044b\u0436\u043a\u0438 \u0432 \u0432\u043e\u0437\u0434\u0443\u0445\u0435"), AirDashConfig.infiniteAirJumps).setDefaultValue(false).setSaveConsumer(newValue -> {
            AirDashConfig.infiniteAirJumps = newValue;
        }).build());
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((class_2561)class_2561.method_43470((String)"\u0421\u0438\u043b\u0430 \u043f\u0440\u044b\u0436\u043a\u0430 (\u0412\u044b\u0441\u043e\u0442\u0430)"), AirDashConfig.jumpPower).setDefaultValue(0.7).setTooltip(new class_2561[]{class_2561.method_43470((String)"\u041e\u0442\u0432\u0435\u0447\u0430\u0435\u0442 \u0437\u0430 \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0438\u043c\u043f\u0443\u043b\u044c\u0441 \u043f\u0440\u0438 \u0440\u044b\u0432\u043a\u0435.")}).setSaveConsumer(newValue -> {
            AirDashConfig.jumpPower = newValue;
        }).build());
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startDoubleField((class_2561)class_2561.method_43470((String)"\u0421\u0438\u043b\u0430 \u0440\u044b\u0432\u043a\u0430 (\u0412\u043f\u0435\u0440\u0435\u0434)"), AirDashConfig.forwardPower).setDefaultValue(0.8).setTooltip(new class_2561[]{class_2561.method_43470((String)"\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435 \u043f\u043e \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u044e \u0432\u0437\u0433\u043b\u044f\u0434\u0430 \u0438\u0433\u0440\u043e\u043a\u0430.")}).setSaveConsumer(newValue -> {
            AirDashConfig.forwardPower = newValue;
        }).build());
        movement.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"\u0417\u0430\u0449\u0438\u0442\u0430 \u043e\u0442 \u0443\u0440\u043e\u043d\u0430 \u043f\u0440\u0438 \u043f\u0430\u0434\u0435\u043d\u0438\u0438"), AirDashConfig.disableFallDamage).setDefaultValue(true).setTooltip(new class_2561[]{class_2561.method_43470((String)"\u041e\u0431\u043d\u0443\u043b\u044f\u0435\u0442 \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e \u043f\u0430\u0434\u0435\u043d\u0438\u044f \u043f\u043e\u0441\u043b\u0435 \u043f\u0440\u0438\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0434\u0432\u043e\u0439\u043d\u043e\u0433\u043e \u043f\u0440\u044b\u0436\u043a\u0430.")}).setSaveConsumer(newValue -> {
            AirDashConfig.disableFallDamage = newValue;
        }).build());
        ConfigCategory controls = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435"));
        controls.addEntry((AbstractConfigListEntry)entryBuilder.startKeyCodeField((class_2561)class_2561.method_43470((String)"\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043c\u0435\u043d\u044e AirDash"), KeyBindingHelper.getBoundKeyOf((class_304)AirDashClient.openConfigKey)).setKeySaveConsumer(key -> AirDashClient.openConfigKey.method_1422(key)).build());
        controls.addEntry((AbstractConfigListEntry)entryBuilder.startKeyCodeField((class_2561)class_2561.method_43470((String)"\u0412\u043a\u043b / \u0412\u044b\u043a\u043b AirDash"), KeyBindingHelper.getBoundKeyOf((class_304)AirDashClient.toggleModKey)).setKeySaveConsumer(key -> AirDashClient.toggleModKey.method_1422(key)).build());
        controls.addEntry((AbstractConfigListEntry)entryBuilder.startKeyCodeField((class_2561)class_2561.method_43470((String)"\u0421\u043e\u0432\u0435\u0440\u0448\u0438\u0442\u044c \u0440\u044b\u0432\u043e\u043a"), KeyBindingHelper.getBoundKeyOf((class_304)AirDashClient.dashKey)).setKeySaveConsumer(key -> AirDashClient.dashKey.method_1422(key)).build());
        ConfigCategory visual = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"\u042d\u0444\u0444\u0435\u043a\u0442\u044b & \u041f\u0430\u0440\u0442\u0438\u043a\u043b\u044b"));
        visual.addEntry((AbstractConfigListEntry)entryBuilder.startIntField((class_2561)class_2561.method_43470((String)"\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432"), AirDashConfig.particleCount).setDefaultValue(25).setTooltip(new class_2561[]{class_2561.method_43470((String)"\u041f\u043b\u043e\u0442\u043d\u043e\u0441\u0442\u044c \u0448\u043b\u0435\u0439\u0444\u0430 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u043f\u043e\u043b\u0451\u0442\u0430.")}).setSaveConsumer(newValue -> {
            AirDashConfig.particleCount = newValue;
        }).build());
        visual.addEntry((AbstractConfigListEntry)entryBuilder.startStringDropdownMenu((class_2561)class_2561.method_43470((String)"\u0422\u0438\u043f \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432"), AirDashConfig.particleType).setDefaultValue((Object)"FLAME").setSelections(List.of("FLAME", "SOUL_FIRE_FLAME", "END_ROD", "HEART", "SMOKE")).setTooltip(new class_2561[]{class_2561.method_43470((String)"\u0412\u044b\u0431\u0435\u0440\u0438 \u0432\u0438\u0437\u0443\u0430\u043b \u0448\u043b\u0435\u0439\u0444\u0430 \u0437\u0430 \u0441\u043f\u0438\u043d\u043e\u0439.")}).setSaveConsumer(newValue -> {
            AirDashConfig.particleType = newValue;
        }).build());
        ConfigCategory info = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"\u041e\u0431 \u0430\u0432\u0442\u043e\u0440\u0435"));
        info.addEntry((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)class_2561.method_43470((String)"\u0410\u0432\u0442\u043e\u0440 \u043c\u043e\u0434\u0430: ").method_27692(class_124.field_1080).method_10852((class_2561)class_2561.method_43470((String)"Kotmir").method_27695(new class_124[]{class_124.field_1065, class_124.field_1067}))).build());
        info.addEntry((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)class_2561.method_43470((String)"\u041e\u0444\u0438\u0446\u0438\u0430\u043b\u044c\u043d\u044b\u0439 \u0441\u0430\u0439\u0442: ").method_27692(class_124.field_1080).method_10852((class_2561)class_2561.method_43470((String)"https://kotmir.tech").method_27695(new class_124[]{class_124.field_1075, class_124.field_1073}).method_27694(s -> s.method_10958((class_2558)new class_2558.class_10608(URI.create("https://kotmir.tech")))))).build());
        info.addEntry((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)class_2561.method_43470((String)"Discord \u0441\u043e\u043e\u0431\u0449\u0435\u0441\u0442\u0432\u043e: ").method_27692(class_124.field_1080).method_10852((class_2561)class_2561.method_43470((String)"discord.gg").method_27695(new class_124[]{class_124.field_1076, class_124.field_1073}).method_27694(s -> s.method_10958((class_2558)new class_2558.class_10608(URI.create("discord.gg")))))).build());
        return builder.build();
    }

    private static void saveAll() {
        AirDashConfig.save();
        class_304.method_1426();
        class_310.method_1551().field_1690.method_1640();
    }
}
