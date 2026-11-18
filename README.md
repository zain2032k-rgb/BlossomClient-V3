# Blossom Client (Fabric 1.21.11)

A client-side pastel blossom UI and sound mod for Minecraft 1.21.11.

## Features
- Right Shift opens/closes the Blossom Client menu.
- Creamy Keys tab with switches for keypress sounds, GUI sounds, menu theme sound, and loading-screen branding.
- Keypress sounds play while the game window is focused and no screen is open.
- The splash overlay is covered with Blossom Client branding while the loading-screen option is enabled.
- Settings are saved to `config/blossom-client.properties`.
- Includes all 24 sound assets copied from the user-provided SoberClient JAR: 23 UI effects plus `menu_theme.ogg`.

## Build on GitHub
1. Upload the contents of this folder to the root of a GitHub repository (not the folder itself nested one level down).
2. Open **Actions** and run **Build Blossom Client** (or push to `main`).
3. Download the `Blossom-Client` artifact from the successful workflow run.
4. Put the resulting `.jar` in your Minecraft `mods` folder. Install Fabric Loader for Minecraft 1.21.11 and Fabric API 0.141.6+1.21.11.

The workflow installs Gradle directly, so a `gradlew` wrapper is not required.

## Controls
- **Right Shift**: open/close the menu.
- In the menu, click the four settings rows to toggle sounds and splash branding.

## Sound asset notice
The supplied SoberClient JAR identifies itself as GPL-3.0-only. The original `LICENSE` and `NOTICE.md` are included for attribution and license context. This project only reuses the audio files supplied by the user; it does not include SoberClient Java classes.
