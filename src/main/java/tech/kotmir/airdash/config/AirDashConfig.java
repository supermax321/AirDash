package tech.kotmir.airdash.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import net.fabricmc.loader.api.FabricLoader;

public class AirDashConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("airdash.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static double jumpPower = 0.7;
    public static double forwardPower = 0.8;
    public static int particleCount = 25;
    public static boolean disableFallDamage = true;
    public static String particleType = "FLAME";
    public static boolean enabled = true;
    public static boolean lookDirectionDash = false;
    public static boolean infiniteAirJumps = false;

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE);){
                ConfigData data = GSON.fromJson((Reader)reader, ConfigData.class);
                if (data != null) {
                    jumpPower = data.jumpPower;
                    forwardPower = data.forwardPower;
                    particleCount = data.particleCount;
                    disableFallDamage = data.disableFallDamage;
                    particleType = data.particleType;
                    enabled = data.enabled;
                    lookDirectionDash = data.lookDirectionDash;
                    infiniteAirJumps = data.infiniteAirJumps;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AirDashConfig.save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE);){
            ConfigData data = new ConfigData();
            data.jumpPower = jumpPower;
            data.forwardPower = forwardPower;
            data.particleCount = particleCount;
            data.disableFallDamage = disableFallDamage;
            data.particleType = particleType;
            data.enabled = enabled;
            data.lookDirectionDash = lookDirectionDash;
            data.infiniteAirJumps = infiniteAirJumps;
            GSON.toJson((Object)data, (Appendable)writer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ConfigData {
        double jumpPower;
        double forwardPower;
        int particleCount;
        boolean disableFallDamage;
        String particleType;
        boolean enabled = true;
        boolean lookDirectionDash = false;
        boolean infiniteAirJumps = false;

        private ConfigData() {
        }
    }
}
