package tech.kotmir.airdash.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import tech.kotmir.airdash.config.AirDashConfigScreen;

public class AirDashModMenu
implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return AirDashConfigScreen::createScreen;
    }
}
