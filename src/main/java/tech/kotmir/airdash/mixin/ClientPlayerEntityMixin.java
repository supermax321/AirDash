package tech.kotmir.airdash.mixin;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_2400;
import net.minecraft.class_243;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.kotmir.airdash.AirDashClient;
import tech.kotmir.airdash.config.AirDashConfig;

@Mixin(value={class_746.class})
public abstract class ClientPlayerEntityMixin {
    @Unique
    private boolean airDash$canDoubleJump = true;
    @Unique
    private boolean airDash$wasSpacePressed = false;
    @Unique
    private boolean airDash$isDashing = false;

    @Inject(method={"method_5773"}, at={@At(value="HEAD")})
    private void onTick(CallbackInfo ci) {
        boolean isSpacePressed;
        boolean customKeyMode;
        class_746 player = (class_746)this;
        if (!AirDashConfig.enabled) {
            return;
        }
        class_310 client = class_310.method_1551();
        if (client.field_1690 == null) {
            return;
        }
        int dashCode = KeyBindingHelper.getBoundKeyOf((class_304)AirDashClient.dashKey).method_1444();
        int jumpCode = KeyBindingHelper.getBoundKeyOf((class_304)client.field_1690.field_1903).method_1444();
        if (dashCode != -1 && dashCode != jumpCode) {
            customKeyMode = true;
            isSpacePressed = AirDashClient.dashKey.method_1434();
        } else {
            customKeyMode = false;
            isSpacePressed = client.field_1690.field_1903.method_1434();
        }
        if (!customKeyMode && (player.method_24828() || player.method_5799() || player.method_6101())) {
            this.airDash$canDoubleJump = true;
            this.airDash$isDashing = false;
        } else {
            if (isSpacePressed && !this.airDash$wasSpacePressed && this.airDash$canDoubleJump) {
                if (!AirDashConfig.infiniteAirJumps) {
                    this.airDash$canDoubleJump = false;
                }
                this.airDash$isDashing = true;
                class_243 look = player.method_5720();
                double forward = AirDashConfig.forwardPower;
                double jump = AirDashConfig.jumpPower;
                player.method_18800(look.field_1352 * forward, AirDashConfig.lookDirectionDash ? look.field_1351 * forward + jump : jump, look.field_1350 * forward);
            }
            if (this.airDash$isDashing && client.field_1687 != null) {
                if (AirDashConfig.disableFallDamage) {
                    player.field_6017 = 0.0;
                }
                class_2400 particle = this.getSelectedParticle(AirDashConfig.particleType);
                for (int i = 0; i < AirDashConfig.particleCount && i < 2000; ++i) {
                    double offsetX = (player.method_59922().method_43058() - 0.5) * 0.4;
                    double offsetY = player.method_59922().method_43058() * 0.5;
                    double offsetZ = (player.method_59922().method_43058() - 0.5) * 0.4;
                    client.field_1687.method_8406((class_2394)particle, player.method_23317() + offsetX, player.method_23318() + offsetY, player.method_23321() + offsetZ, -player.method_18798().field_1352 * 0.2, 0.01, -player.method_18798().field_1350 * 0.2);
                }
            }
        }
        this.airDash$wasSpacePressed = isSpacePressed;
    }

    @Unique
    private class_2400 getSelectedParticle(String type) {
        return switch (type) {
            case "SOUL_FIRE_FLAME" -> class_2398.field_22246;
            case "END_ROD" -> class_2398.field_11207;
            case "HEART" -> class_2398.field_11201;
            case "SMOKE" -> class_2398.field_11251;
            default -> class_2398.field_11240;
        };
    }
}
