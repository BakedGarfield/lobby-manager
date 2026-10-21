# Changelog

## 0.4.1-beta

- Fixed late authentication / Register Mod teleports overriding the location set with `/setlobby`.
- While the player is in the lobby login flow, recover from unexpected world changes and large teleports inside the lobby; prevent temporary world changes from overwriting survival inventory snapshots.
- Stop enforcing the login lobby after `/returntosurvival` (also for NPCs) or death. Manual `/lobby` re-enables lobby protection for that session.
- Updated setup and troubleshooting instructions. No configuration migration is required.

## 0.4.0-beta

- Added operator-only `/setlobby` command, saving exact lobby coordinates and orientation in `config/lobbymanager.json`.
- Lobby join and `/lobby` now use the saved spawn, with backward-compatible fallback to the lobby world's own spawn.
- Added `respawnAtOverworldSpawn` (default `true`). After death, players respawn at the vanilla Overworld spawn set by `/setworldspawn`, rather than the lobby or a bed/respawn anchor.
- Keep death handling separate from server login; update the saved survival return position and the post-death inventory snapshot.
- Added configurable messages for `/setlobby` and missing Overworld; updated the README and sample config.

# Changelog

## 0.3.0-beta

- Rebranded all source files, Java packages, resource names, mod ID, and on-disk paths to **Lobby Manager**.
- Replaced the nested command structure with `/lobby` and `/buildmode`.
- Kept `/returntosurvival` for players and NPC actions.
- Made all player-facing messages configurable in `config/lobbymanager.json`, using English defaults.
- Updated the default NPC tag to `lobbymanager_npc`.
- Expanded the README with setup, NPC actions, config, upgrade, and build instructions.
- **Breaking change:** Existing installations must manually migrate player JSON files and settings to the new paths. Make a backup first.
