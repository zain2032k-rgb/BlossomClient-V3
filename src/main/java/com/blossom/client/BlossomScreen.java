package com.blossom.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.client.input.KeyInput;

public final class BlossomScreen extends Screen {
    private int hovered = -1;
    private int lastHovered = -1;
    private static final int PANEL_W = 330;
    private static final int PANEL_H = 236;

    public BlossomScreen() { super(Text.literal("Blossom Client")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0xB80D0912);
        int x = (width - PANEL_W) / 2;
        int y = (height - PANEL_H) / 2;
        context.fill(x - 2, y - 2, x + PANEL_W + 2, y + PANEL_H + 2, 0xFFE8AFC7);
        context.fill(x, y, x + PANEL_W, y + PANEL_H, 0xFFFCEBF2);
        context.fill(x, y, x + PANEL_W, y + 48, 0xFFF4C9DA);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("✿ BLOSSOM CLIENT ✿"), width / 2, y + 12, 0xFF8C4568);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("A little softer, a little sweeter."), width / 2, y + 31, 0xFF9D6680);

        context.drawTextWithShadow(textRenderer, Text.literal("CREAMY KEYS"), x + 22, y + 66, 0xFF9C5574);
        drawToggle(context, x, y + 84, "Keypress sounds", BlossomClient.keySounds, 0);
        drawToggle(context, x, y + 116, "GUI sounds", BlossomClient.guiSounds, 1);
        drawToggle(context, x, y + 148, "Menu theme sound", BlossomClient.menuTheme, 2);
        drawToggle(context, x, y + 180, "Blossom loading screen", BlossomClient.splashBranding, 3);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("RIGHT SHIFT  •  CLOSE"), width / 2, y + PANEL_H - 15, 0xFFB47B96);
        super.render(context, mouseX, mouseY, delta);
    }

    private void drawToggle(DrawContext context, int x, int y, String label, boolean enabled, int index) {
        int left = x + 18;
        int right = x + PANEL_W - 18;
        boolean hover = hovered == index;
        context.fill(left, y, right, y + 25, hover ? 0xFFF6D5E2 : 0xFFF8DFE9);
        context.drawTextWithShadow(textRenderer, Text.literal(label), left + 10, y + 8, 0xFF70435A);
        String state = enabled ? "ON" : "OFF";
        context.drawTextWithShadow(textRenderer, Text.literal(state), right - 39, y + 8, enabled ? 0xFFB34F7B : 0xFF9B8B94);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        int x = (width - PANEL_W) / 2;
        int y = (height - PANEL_H) / 2;
        int next = -1;
        for (int i = 0; i < 4; i++) {
            int top = y + 84 + i * 32;
            if (mouseX >= x + 18 && mouseX <= x + PANEL_W - 18 && mouseY >= top && mouseY <= top + 25) next = i;
        }
        hovered = next;
        if (hovered != lastHovered && hovered >= 0 && BlossomClient.guiSounds) {
            BlossomClient.playRandomUi(new String[]{"silk_hover", "glide_hover"});
            lastHovered = hovered;
        }
        if (hovered < 0) lastHovered = -1;
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        BlossomClient.onMouseClick();
        int x = (width - PANEL_W) / 2;
        int y = (height - PANEL_H) / 2;
        for (int i = 0; i < 4; i++) {
            int top = y + 84 + i * 32;
            if (mouseX >= x + 18 && mouseX <= x + PANEL_W - 18 && mouseY >= top && mouseY <= top + 25) {
                switch (i) {
                    case 0 -> BlossomClient.keySounds = !BlossomClient.keySounds;
                    case 1 -> BlossomClient.guiSounds = !BlossomClient.guiSounds;
                    case 2 -> BlossomClient.menuTheme = !BlossomClient.menuTheme;
                    case 3 -> BlossomClient.splashBranding = !BlossomClient.splashBranding;
                }
                BlossomClient.saveConfig();
                if (BlossomClient.guiSounds) {
                    boolean enabled = switch (i) {
                        case 0 -> BlossomClient.keySounds;
                        case 1 -> BlossomClient.guiSounds;
                        case 2 -> BlossomClient.menuTheme;
                        default -> BlossomClient.splashBranding;
                    };
                    BlossomClient.playRandomUi(enabled
                        ? new String[]{"glide_check_on", "glide_on", "pulse_check", "pulse_on", "silk_on"}
                        : new String[]{"glide_check_off", "glide_off", "pulse_off", "silk_off"});
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.key() == 256 || input.key() == 344) {
            close();
            return true;
        }
        return super.keyPressed(input);
    }

    @Override
    public void close() {
        BlossomClient.playRandomUi(new String[]{"silk_close", "glide_close", "pulse_close"});
        if (client != null) client.setScreen(null);
    }
}
