# Serenity Journal

Serenity Journal is a modern, tranquil Android journaling application designed to help users document their thoughts and track their moods within a serene, Material 3-driven interface.

## Description

The app provides a peaceful space for daily reflection. It combines a robust Java-based data layer with a cutting-edge Kotlin Jetpack Compose UI. The design focuses on tranquility, incorporating calming nature imagery and a clean, responsive layout that adapts to various device form factors.

## Key Features

- **Chronological Entry Feed**: A clean home screen displaying a list of past journal entries sorted by date (newest first).
- **Intuitive Entry Composer**: A dedicated creation screen with fields for title, date selection, and long-form content.
- **Mood Integration**: A categorized dropdown menu within the editor to tag entries with specific moods (Happy, Calm, Peaceful, Reflective, Anxious, Sad, Grateful, Tired).
- **Date Picker**: Integrated Material 3 date picker for choosing the entry date.
- **Nature-Themed UI**: Immersive headers featuring tranquil nature scenes with gradient overlays for better readability.
- **Material 3 Design**: Follows the latest Material Design guidelines, including support for light/dark modes and edge-to-edge display.
- **Local Persistence**: All entries are stored locally on the device using the Room database.

## Technical Stack

- **Languages**: Java (Data Layer), Kotlin (UI Layer)
- **UI Framework**: Jetpack Compose
- **Database**: Room Persistence Library
- **Navigation**: Jetpack Navigation 3 (State-driven)
- **Architecture**: MVVM (Model-View-ViewModel) with Repository pattern
- **Image Loading**: Coil
- **Concurrency**: Java ExecutorService and Kotlin Coroutines/Flow

## Testing

The project includes a comprehensive test suite:
- **Unit Tests (Java)**: Verify data conversion logic for Dates and Moods.
- **Instrumented Data Tests (Kotlin)**: Verify Room DAO operations, including CRUD and chronological sorting.
- **UI Tests (Kotlin/Compose)**: Verify screen interactions, empty states, and form validation logic.

## GitHub Actions

Automated CI is configured to:
- Build the project using Gradle.
- Run Lint checks to ensure code quality.
- Verify the build on Java 21.

## Getting Started

To run the app:
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device (supports API 29+).

> [!TIP]
> For the best experience on an emulator, use **Software Rendering** and **Cold Boot** if you encounter any startup issues.
