📱 Android App – Jetpack Compose + Clean Architecture + Modularisation

This project demonstrates a scalable, maintainable, and testable Android application using Jetpack Compose with Clean Architecture principles and a fully modularised project structure.

🧱 Architecture Overview
The app is structured following Clean Architecture, with each layer in its own independent module:

app/
├── feature-weather/
├── feature-address/
├── feature-festival/
├── core-ui/
├── core-utils/
├── core-data/
├── core-domain/
├── core-di/


LAYERS
	- Presentation Layer
		Built with Jetpack Compose
		Handles UI logic, state management, and user events
		Uses ViewModels to communicate with the domain layer

	- Domain Layer
		Pure Kotlin module
		Contains business rules, use cases, and interfaces

	- Data Layer
		Implements repositories defined in the domain layer
		Manages data sources like REST APIs (Retrofit) and local DB (Room)
		Includes data mappers to convert between DTOs and domain models

	- Dependency Injection Layer
		Configured using Hilt
		Provides all dependencies for ViewModels, use cases, and repositories

🧩 MODULARISATION STRATEGY

	Each feature is developed as a self-contained module, allowing:
		- Independent development and testing
		- Clear separation of concerns
		- Faster build times through parallelisation
		- Easier onboarding for new developers

In addition to feature modules, the project includes core modules (e.g., UI components, utilities, design system) to encourage reusability across features.

⚙️ Tech Stack

Category			Technology

UI				Jetpack Compose
Architecture			Clean Architecture (MVVM)
Dependency Injection		Hilt
Networking			Retrofit, OkHttp
Local Storage			Room
Async Programming		Kotlin Coroutines, Flow
Modularisation			Multi-module Gradle setup
