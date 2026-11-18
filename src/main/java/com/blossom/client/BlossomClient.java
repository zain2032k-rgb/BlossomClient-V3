
package com.blossom.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import net.minecraft.client.sound.PositionedSoundInstance;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public final class BlossomClient implements ClientModInitializer {
    public static final String MOD_ID = "blossom";

    private static KeyBinding openMenuKey;

    private static final String[] UI_SOUND_NAMES = {
        "glide_check_off", "glide_check_on", "glide_click", "glide_close", "glide_hover",
        "glide_off", "glide_on", "glide_open", "glide_type", "pulse_check", "pulse_close",
        "pulse_off", "pulse_on", "pulse_open", "pulse_swish", "silk_close", "silk_hover",
        "silk_nav", "silk_off", "silk_on", "silk_open", "silk_select", "silk_type"
    };

    public static SoundEvent MENU_THEME;
    public static boolean keySounds = true;
    public static boolean guiSounds = true;
    public static boolean menuTheme = true;
    public static boolean splashBranding = true;

    private static Path configPath;
    private static long lastKeySoundNanos;
    private static long lastClickSoundNanos;

    @Override
    public void onInitializeClient() {
        for (String name : UI_SOUND_NAMES) {
            register(name);
        }

        Identifier menuId = id("menu_theme");
        MENU_THEME = Registry.register(
            Registries.SOUND_EVENT,
            menuId,
            SoundEvent.of(menuId)
        );

        loadConfig();

        openMenuKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.blossom.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.MISC
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                if (client.currentScreen instanceof BlossomScreen) {
                    client.setScreen(null);
                    playRandomUi(new String[]{
                        "glide_close", "silk_close", "pulse_close"
                    });
                } else {
                    client.setScreen(new BlossomScreen());
                    playRandomUi(new String[]{
                        "glide_open", "silk_open", "pulse_open"
                    });

                    if (menuTheme) {
                        play(MENU_THEME, 1.0f);
                    }
                }
            }
        });
    }

    private static void register(String name) {
        Identifier soundId = id("ui/" + name);
        Registry.register(
            Registries.SOUND_EVENT,
            soundId,
            SoundEvent.of(soundId)
        );
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static void onKeyPressed(int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && keySounds && client.currentScreen == null
                && System.nanoTime() - lastKeySoundNanos > 18_000_000L) {
            lastKeySoundNanos = System.nanoTime();

            String[] choices = {
                "glide_type", "silk_type", "silk_nav"
            };

            playUi(
                choices[ThreadLocalRandom.current().nextInt(choices.length)],
                0.94f + ThreadLocalRandom.current().nextFloat() * 0.12f
            );
        }
    }

    public static void onMouseClick() {
        if (!keySounds || System.nanoTime() - lastClickSoundNanos < 25_000_000L) {
            return;
        }

        lastClickSoundNanos = System.nanoTime();

        String[] choices = {"glide_click", "silk_select"};
        playUi(choices[ThreadLocalRandom.current().nextInt(choices.length)], 1.0f);
    }

    public static void playRandomUi(String[] choices) {
        if (choices.length == 0) return;

        playUi(
            choices[ThreadLocalRandom.current().nextInt(choices.length)],
            1.0f
        );
    }

    public static void playUi(String name, float pitch) {
        if (!guiSounds && !name.endsWith("_type")) return;

        SoundEvent event = Registries.SOUND_EVENT.get(id("ui/" + name));
        play(event, pitch);
    }

    public static void play(SoundEvent event, float pitch) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && client.getSoundManager() != null && event != null) {
            client.getSoundManager().play(
                PositionedSoundInstance.master(event, pitch)
            );
        }
    }

    public static void saveConfig() {
        if (configPath == null) return;

        Properties p = new Properties();
        p.setProperty("keySounds", Boolean.toString(keySounds));
        p.setProperty("guiSounds", Boolean.toString(guiSounds));
        p.setProperty("menuTheme", Boolean.toString(menuTheme));
        p.setProperty("splashBranding", Boolean.toString(splashBranding));

        try (var out = Files.newOutputStream(configPath)) {
            p.store(out, "Blossom Client settings");
        } catch (IOException e) {
            System.err.println("[Blossom] Could not save config: " + e.getMessage());
        }
    }

    private static void loadConfig() {
        configPath = MinecraftClient.getInstance()
            .runDirectory.toPath()
            .resolve("config/blossom-client.properties");

        try {
            Files.createDirectories(configPath.getParent());

            if (!Files.exists(configPath)) return;

            Properties p = new Properties();

            try (var in = Files.newInputStream(configPath)) {
                p.load(in);
            }

            keySounds = Boolean.parseBoolean(p.getProperty("keySounds", "true"));
            guiSounds = Boolean.parseBoolean(p.getProperty("guiSounds", "true"));
            menuTheme = Boolean.parseBoolean(p.getProperty("menuTheme", "true"));
            splashBranding = Boolean.parseBoolean(p.getProperty("splashBranding", "true"));

        } catch (IOException e) {
            System.err.println("[Blossom] Could not load config: " + e.getMessage());
        }
    }
}
