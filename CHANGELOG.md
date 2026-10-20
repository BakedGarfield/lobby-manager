# Changelog

## 0.3.0-beta

- Rebranded all source files, Java packages, resource names, mod ID, and on-disk paths to **Lobby Manager**.
- Replaced the nested command structure with `/lobby` and `/buildmode`.
- Kept `/returntosurvival` for players and NPC actions.
- Made all player-facing messages configurable in `config/lobbymanager.json`, using English defaults.
- Updated the default NPC tag to `lobbymanager_npc`.
- Expanded the README with setup, NPC actions, config, upgrade, and build instructions.
- **Breaking change:** Existing installations must manually migrate player JSON files and settings to the new paths. Make a backup first.
