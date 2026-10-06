# Licznik Pojazdów

A simple Android application designed to support **manual traffic surveys** by making it quick and easy to count selected vehicle types and review recorded events.

> \\\*\\\*Portfolio project:\\\*\\\* built as a practical Android application while learning Kotlin, Android development, Git and GitHub. AI tools, including ChatGPT, were used as development support for learning, debugging and code assistance.

## Features

* Count passenger cars, trucks and buses
* Live counters for each vehicle category
* Timestamped click history
* Local persistence of counters and history
* Reset counters
* Light / dark theme
* Custom background color
* Custom button color
* Simple settings section
* Offline operation — no account or backend required

## Screenshots

\## Screenshots



\### Main screen



!\[Main screen](docs/screenshots/main\_screen.png)



\### Settings



!\[Settings](docs/screenshots/settings.png)



\### Click history



!\[Click history](docs/screenshots/history.png)



\### Dark mode



!\[Dark mode](docs/screenshots/dark\_mode.png)



\## Tech Stack

|Technology|Usage|
|-|-|
|**Kotlin**|Application logic|
|**Android SDK**|Mobile application platform|
|**XML**|User interface layouts|
|**View Binding**|Type-safe view access|
|**AndroidX AppCompat**|Activity and theme support|
|**Material Components**|Android UI components|
|**Gson**|Local JSON serialization|
|**Gradle Kotlin DSL**|Build configuration|
|**Git / GitHub**|Version control and portfolio hosting|

### Project configuration

* Package: `com.example.vehiclecounter`
* Minimum Android version: API 24 (Android 7.0)
* Target Android version: API 35
* Java / JVM target: 17
* Kotlin: 2.1.21
* Android Gradle Plugin: 8.10.0

## How It Works

When a vehicle button is pressed:

1. The corresponding counter is incremented.
2. The event is added to the click history with the current time.
3. Counters and history are serialized to JSON.
4. The data is stored locally in the application's private storage.
5. The UI is refreshed immediately.

The application therefore keeps collected data between launches without requiring an internet connection or an external database.

## Project Structure

```text
LicznikPojazdow/
├── app/
│   └── src/main/
│       ├── java/com/example/vehiclecounter/
│       │   └── MainActivity.kt
│       ├── res/
│       │   ├── layout/
│       │   └── values/
│       └── AndroidManifest.xml
├── gradle/
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── README.md
```

## Running the Project

### Requirements

* Android Studio
* Android SDK
* JDK 17

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Artur11231/LicznikPojazdow.git
```

2. Open the project in Android Studio.
3. Allow Gradle to synchronize the project.
4. Connect an Android device or start an Android Emulator.
5. Run the `app` configuration.

No API keys or external services are required.

## Building an APK

In Android Studio:

**Build → Build APK(s)**

The debug APK will normally be generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Development Approach

This project was created as a learning and portfolio project with an emphasis on practical implementation:

* converting a real-world manual task into a small mobile tool,
* designing a simple and fast user interface,
* implementing local data persistence,
* handling Android UI state,
* working with Gradle and Android project configuration,
* using Git and GitHub for version control.

AI tools were used as a **development aid**, particularly for explaining Android/Kotlin concepts, troubleshooting build errors, reviewing approaches and accelerating implementation. The project was tested and run locally in Android Studio.

## Possible Future Improvements

* Additional traffic categories
* Separate counts by traffic direction
* Export collected data to CSV / Excel
* Statistics and charts
* Date-based survey sessions
* Undo the last entry
* Search and filter history
* Backup / restore collected data
* Improved accessibility and responsive layouts
* Automated unit and UI tests

## Project Status

**Working prototype / portfolio project**

The current version focuses on the core counting workflow and local persistence. Further features can be added as the project develops.

## License

This project is released under the MIT License. See [`LICENSE`](LICENSE).



