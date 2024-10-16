## All Planets
<p align="center">
<img src="https://github.com/user-attachments/assets/d7ab433b-f00a-433a-aa31-aa3481460d2b" alt="preview"/>
</p>

## Project Setup Instructions
This document outlines the steps required to set up and run the Xiteb project on your local machine.
Cloning the Project
- Open your terminal or command prompt.
- Navigate to the directory where you want to clone the project.
- Execute the following command to clone the repository:
  `git clone https://github.com/x86xFX/Xiteb.git`
- Or simply you can add this url in Android studio -> Get from version controll.

## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Ktor Client](https://ktor.io): Asynchronous, multiplatform support HTTP client and server.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format reflectionless serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Coil](https://coil-kt.github.io/coil/compose): Image loading library that fetches and displays network images with Coil.
- [Material 3](https://m3.material.io/): Latest version of Google’s open-source design system.

## Architecture Overview
This project employs a `clean architecture` approach using `MVVM`, which is recommended by Google. The architecture is divided into distinct layers: the presentation layer, the domain layer, and the data layer. The presentation layer interacts with the domain layer, which in turn accesses the data layer, including the `SWAPI API` and its associated `DTOs`. Domain models are mapped from DTOs using a `mapper package`. The use of wrapper classes like `UiState` and `Response` enhances code clarity and maintainability.

## Key Design Decisions
This project leverages `StateFlow`, `suspend functions`, and `Lifecycle events` to efficiently manage data flow. `Kotlin Flow` is used to `fetch planet details`, but `suspend functions` could also be employed. For simplicity, `StateFlow` is used to collect planet data, which is then accessed by composables using `collectAsStateWithLifecycle`. The use of `extension functions` contributes to cleaner and more maintainable code. Ui is written in Jetpack Compose because Jetpack Compose is a modern UI toolkit for Android that simplifies UI development using a declarative syntax. It offers features like composable functions, state management, and theming, making it easier to create beautiful and performant user interfaces and ViewModels for save the current state across the reconfigurations.

## Testing
`Dagger Hilt` was employed for dependency injection, enabling flexible implementation selection for use cases. For instance, the `AllPlanetsRepository` could use `fake or mock implementations`, facilitating testing in various scenarios. The `AllPlanetsRepositoryImpl` is injected into view models, streamlining unit testing.

## Optional Features
Optional feature is to implement random icons for the planet cards, Before implementing another network service, I used `Coil` for image loading. Leveraging Coil's built-in network image fetching capabilities with OkHttp, a visually appealing solution was implemented within the project's time constraints.

## Releasing Project
The released APK, optimized by R8 and with shrunken resources to minimize its size, can be downloaded from the project's [releases section](https://github.com/x86xFX/Xiteb/releases/tag/Latest) without cloning the project.
