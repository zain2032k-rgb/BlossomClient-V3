package com.blossom.client.mixin;

import com.blossom.client.BlossomClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public abstract class SplashOverlayMixin {
    @Shadow private float progress;
    @Inject(method = "render", at = @At("TAIL"))
    private void blossom$replaceMojangBranding(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!BlossomClient.splashBranding) return;
        int w = context.getScaledWindowWidth();
        int h = context.getScaledWindowHeight();
        context.fill(0, 0, w, h, 0xFFFBEAF1);
        context.fill(0, h - 5, w, h, 0xFFE8B2C9);
        context.fill(0, h - 5, (int) ((w - 100) * Math.max(0.0f, Math.min(1.0f, progress))) + 50, h, 0xFFC66D96);
        context.drawCenteredTextWithShadow(net.minecraft.client.MinecraftClient.getInstance().textRenderer,
            Text.literal("✿ BLOSSOM CLIENT ✿"), w / 2, h / 2 - 14, 0xFF9B4D70);
        context.drawCenteredTextWithShadow(net.minecraft.client.MinecraftClient.getInstance().textRenderer,
            Text.literal("blooming into your world..."), w / 2, h / 2 + 5, 0xFFB77B95);
    }
}
