# SoberClient

Copyright (C) 2026 Violet

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
version. See the `LICENSE` file for the full license text.

## Included third party work

### SkyFX 1.0.0

The animated skies, the block aura, the item aura, the sky and aura shaders and the custom enchantment glints are based
on **SkyFX** by **2660master**, licensed under GPL-3.0-or-later. The SkyFX code and assets are integrated into the
SoberClient (package `dev.sober.sky`, the sky mixins in `dev.sober.mixin`, `assets/sober/shaders`,
`assets/sober/textures/aura` and `assets/sober/textures/glint`) and were adapted to Violet's namespace and `Sky` module.
The original SkyFX source is included in `third_party/skyfx` of the source archive.

A later SkyFX release by **2660master** (GPL-3.0-or-later) added the procedural skies Synthwave, Black Hole, Sakura,
Underwater and Cyberpunk and the animated shader glints Fire, Lightning, Sakura, Gold, Void, Matrix, Synthwave, Chrome,
Toxic and Hearts. Their shaders were taken over into `assets/sober/shaders` (`include/skyfx_common.glsl`,
`include/glint_common.glsl`, `include/sky_*.glsl`, `include/glint_*.glsl`, `core/sky_*.fsh`, `core/glintfx*.vsh/.fsh`),
moved to the `sober` namespace, and drawn through Violet's own sky renderer and glint layers.

### Anubis 0.9.9

The staff rank matcher (`dev.sober.util.StaffMatcher`), the extra DonutSMP staff names and the join and
leave tracking in `dev.sober.util.StaffList` come from the Anubis Staff List, without its vanish and spectator
detection. The region map (`dev.sober.gui.RegionMap`) follows the Anubis region map layout, region data, player
indicator, info panel and legend. The Anubis license text is included as `LICENSE_anubis`.

### Anubis 0.9.9.3

The **Amethyst Bypass** module (`dev.sober.modules.AmethystBypass`) and the **Anti Vanish** module
(`dev.sober.modules.AntiVanish`) are ported unchanged from **Anubis 0.9.9.3** by **4ldenz** (GPL-3.0-only,
https://github.com/4ldenz/Anubis-client), together with their helpers `dev.sober.util.CompletionChannel`,
`dev.sober.util.ShardTracker`, `dev.sober.util.antivanish.*` and the DonutSMP staff names in `dev.sober.util.DonutStaff`.
Only the alarm of Anti Vanish was changed: it shows the SoberClient logo and "Good Luck!" (`dev.sober.gui.VanishAlert`)
instead of the Anubis head and note.

The **SusChunk** module (`dev.sober.modules.SusChunk`, formerly FixedSusChunk) is ported from the Better Sus Chunk module of
**Anubis 0.9.9.3** by **4ldenz**, licensed under GPL-3.0-only. The growth, soil, snow, ice and shaft checks, the
coverage and support scoring and the display selection were taken over and adapted to Violet's module, setting and
render classes. The look follows Violet's Sus Chunk: flat chunk overlay, optional tracers, render distance and radar.

The **SusChunks** module (`dev.sober.modules.SusChunk`) is the **Better Sus Chunk** module of **Anubis 0.9.9.3** by
**4ldenz** (GPL-3.0-only) with all its detection and settings; only the look was changed to plain filled chunks.

The **CsPickaxe** module (`dev.sober.modules.CsPickaxe`) and `dev.sober.render.knife.*` port the karambit model, its
materials, the inspect and deploy animations, the slash trail and the blade sparkle of the **CS Knife**
(`WeaponInspectModule`, `KnifeSkin`, `KnifeModels`, `KnifeMesh`, `KnifeMaterial`, `InspectAnimation`, `SlashTrail`) from
**Anubis 0.9.9.3** by **4ldenz** (GPL-3.0-only). Violet shows it on pickaxes instead of swords and leaves out the first
person arm.

The **Chunk Scanner** (`dev.sober.modules.ChunkScannerModule`) module is ported unchanged from **Anubis 0.9.9.3** by
**4ldenz** (GPL-3.0-only), with all their settings. The supporting classes `dev.sober.util.seedscan.*`,
`dev.sober.util.AmethystLight`, `dev.sober.util.ChunkTileOverlay` (Anubis `ChunkOverlay`) and
`dev.sober.util.WorldQuads` come from the same release. Only package names, Minecraft mapping names and the links to
Violet's module base class, performance settings and accent color were adapted.

### Anubis 0.9.9.1

The following modules are ported from **Anubis 0.9.9.1** by **4ldenz** (GPL-3.0-only) and adapted to Violet's
module, setting, HUD and render classes:

- Movement: Fly, Speed, Sprint, Jesus, Spider, Elytra Fly, Elytra Swap, Boat Noclip, Entity Control, Entity Speed,
  Auto Walk, Auto Jump, Click TP, Sneak, No Jump Delay, Trident Boost (anti kick, NCP bypass and packet spoof options
  left out).
- Base finding: SusChunkV2, Light Debug, Home Meta, Render Distance Toggle and Prime Chunk
  Finder (replacing Violet's own).
- Render: Tracers and Block ESP (replacing Violet's own).
- Donut: Netherite Finder and Panic Pay (as "Panic Order/Pay", replacing Panic Order).
- Client and misc: FPS Booster (the Anubis Performance module), Region Map (as its own module, drawn like the Anubis
  region map card), Hotkeys (shown in the Violet HUD), Discord Presence and Skin Change.
- Spotify Player: the Anubis Spotify HUD (media session, synced lyrics from lrclib.net, album art, media keys) with the
  Anubis card drawing in `dev.sober.gui.SpotifyPanel`, plus a Spotify logo in the top right corner. It replaces
  Violet's own Spotify Player.

Together with them came the Anubis event bus (`dev.sober.event`), its lightweight settings
(`dev.sober.setting.lite`), the ESP chunk scanner (`dev.sober.esp`), helpers in `dev.sober.util`, the
module mixins in `dev.sober.mixin.feature`, and the Geist font (SIL Open Font License, see `FONT-LICENSES.txt`).
Because the Anubis code is GPL-3.0-only, the combined SoberClient is distributed under GPL-3.0 (version 3 only).

### Odin

The timing of the Dropdown Click GUI animations in `dev.sober.gui.DropdownScreen` (panel and settings reveal,
search collapse, hover and toggle blends, switch fill and the hover scale of switches and buttons, with the cubic
ease in out curve) follows the Click GUI of **Odin** (https://github.com/odtheking/odin), used under the BSD 3-Clause
License:

    Copyright (c) 2025, odtheking
    All rights reserved.

    Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
    following conditions are met:

    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the
       following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
       following disclaimer in the documentation and/or other materials provided with the distribution.
    3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote
       products derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
    INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
    WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
    USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

### Fonts

The typefaces in `assets/sober/font` are bundled unmodified: Inter, Montserrat, Source Sans Pro, Nunito, Xuong, Isometra, Roboto Mono, Edu QLD Hand, Black Ops One and Caveat
(SIL Open Font License 1.1), Roboto (Apache License 2.0) and Ubuntu (Ubuntu Font Licence 1.0). Copyright notices and the full license texts are in
`FONT-LICENSES.txt`.

### Sky textures

`assets/sober/textures/sky/nebula_bloom.png` and `assets/sober/textures/sky/ashen_squall.png` are the unmodified skybox textures from the
resource packs "Cosmic Dunes Sky" and "Stormy Sunset Sky Overlay" made by Ouranos, included for personal use as the Sky modes "Nebula Bloom" and "Ashen Squall".
