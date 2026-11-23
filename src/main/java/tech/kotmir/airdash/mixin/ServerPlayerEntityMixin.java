package tech.kotmir.airdash.mixin;

import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.kotmir.airdash.config.AirDashConfig;

@Mixin(value={class_3222.class})
public abstract class ServerPlayerEntityMixin {
    @Inject(method={"method_5773"}, at={@At(value="HEAD")})
    private void onServerTick(CallbackInfo ci) {
        if (!AirDashConfig.enabled) {
            return;
        }
        class_3222 player = (class_3222)this;
        if (AirDashConfig.disableFallDamage && !player.method_24828() && !player.method_5799() && !player.method_6101()) {
            player.field_6017 = 0.0;
        }
    }
}
