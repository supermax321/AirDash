package tech.kotmir.airdash.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.kotmir.airdash.config.AirDashConfig;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onServerTick(CallbackInfo ci) {
        if (!AirDashConfig.enabled) {
            return;
        }
        ServerPlayer player = (ServerPlayer) (Object) this;
        if (AirDashConfig.disableFallDamage && !player.onGround() && !player.isInWater() && !player.onClimbable()) {
            player.fallDistance = 0.0;
        }
    }
}
