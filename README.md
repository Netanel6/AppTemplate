# AppTemplate

Reusable Android starter template built with Kotlin, Jetpack Compose, Material 3, Hilt, Retrofit, Room, DataStore, Coroutines, and a lightweight MVI presentation pattern.

## Architecture

- `app`: application entrypoint and centralized navigation.
- `core`: cross-cutting building blocks only, including MVI, networking, persistence, design system, navigation, and testing helpers.
- `feature`: feature-first packages, each with `presentation`, `domain`, `data`, and `di`.

## Feature conventions

- Contracts stay inside each feature.
- Repository interfaces live in feature domain; implementations live in feature data.
- DTOs, entities, domain models, and UI models stay separated.
- Navigation is declared centrally, while feature routes expose clean screen entry points.

## Starter flow

- Home loads and caches placeholder API content into Room.
- Detail reads an `itemId` route arg and resolves cached data.
- DataStore exposes simple app preferences for future settings/onboarding work.
