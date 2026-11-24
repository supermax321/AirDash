package tech.kotmir.airdash.mixin;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.kotmir.airdash.AirDashClient;
import tech.kotmir.airdash.config.AirDashConfig;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {
    @Unique
    private boolean airDash$canDoubleJump = true;
    @Unique
    private boolean airDash$wasSpacePressed = false;
    @Unique
    private boolean airDash$isDashing = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (!AirDashConfig.enabled) {
            return;
        }
        Minecraft client = Minecraft.getInstance();
        if (client.options == null) {
            return;
        }

        int dashCode = KeyMappingHelper.getBoundKeyOf(AirDashClient.dashKey).getValue();
        int jumpCode = KeyMappingHelper.getBoundKeyOf(client.options.keyJump).getValue();

        boolean customKeyMode;
        boolean isSpacePressed;
        if (dashCode != -1 && dashCode != jumpCode) {
            customKeyMode = true;
            isSpacePressed = AirDashClient.dashKey.isDown();
        } else {
            customKeyMode = false;
            isSpacePressed = client.options.keyJump.isDown();
        }

        if (!customKeyMode && (player.onGround() || player.isInWater() || player.onClimbable())) {
            this.airDash$canDoubleJump = true;
            this.airDash$isDashing = false;
        } else {
            if (isSpacePressed && !this.airDash$wasSpacePressed && this.airDash$canDoubleJump) {
                if (!AirDashConfig.infiniteAirJumps) {
                    this.airDash$canDoubleJump = false;
                }
                this.airDash$isDashing = true;
                Vec3 look = player.getLookAngle();
                double forward = AirDashConfig.forwardPower;
                double jump = AirDashConfig.jumpPower;
                player.setDeltaMovement(
                        look.x * forward,
                        AirDashConfig.lookDirectionDash ? look.y * forward + jump : jump,
                        look.z * forward
                );
            }

            if (this.airDash$isDashing && client.level != null) {
                if (AirDashConfig.disableFallDamage) {
                    player.fallDistance = 0.0;
                }
                SimpleParticleType particle = this.getSelectedParticle(AirDashConfig.particleType);
                for (int i = 0; i < AirDashConfig.particleCount && i < 2000; ++i) {
                    double offsetX = (player.getRandom().nextDouble() - 0.5) * 0.4;
                    double offsetY = player.getRandom().nextDouble() * 0.5;
                    double offsetZ = (player.getRandom().nextDouble() - 0.5) * 0.4;
                    client.level.addParticle(
                            particle,
                            player.getX() + offsetX,
                            player.getY() + offsetY,
                            player.getZ() + offsetZ,
                            -player.getDeltaMovement().x * 0.2,
                            0.01,
                            -player.getDeltaMovement().z * 0.2
                    );
                }
            }
        }
        this.airDash$wasSpacePressed = isSpacePressed;
    }

    @Unique
    private SimpleParticleType getSelectedParticle(String type) {
        return switch (type) {
            case "SOUL_FIRE_FLAME" -> ParticleTypes.SOUL_FIRE_FLAME;
            case "END_ROD" -> ParticleTypes.END_ROD;
            case "HEART" -> ParticleTypes.HEART;
            case "SMOKE" -> ParticleTypes.SMOKE;
            default -> ParticleTypes.FLAME;
        };
    }
}
