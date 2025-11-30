# 🏎️ RoadRunner Benchmark App

## 📸 Screenshots

| Splash Screen | Result (1,000,000 Iterations) | Result (1,000,000,000 Iterations) | Ranking Screen |
|---------------|-------------------------------|-----------------------------------|----------------|
| ![Splash Screen](screen/splash.png) | ![Result 1M](screen/result_1m.png) | ![Result 1B](screen/result_1b.png) | ![Ranking](screen/ranking.png) |

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-orange?style=flat-square)](https://kotlinlang.org/)  
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-blue?style=flat-square)](https://developer.android.com/jetpack/compose)  
[![Coroutines](https://img.shields.io/badge/Coroutines-green?style=flat-square)](https://kotlinlang.org/docs/coroutines-overview.html)  
[![RxJava](https://img.shields.io/badge/RxJava-red?style=flat-square)](https://github.com/ReactiveX/RxJava)  
[![RxKotlin](https://img.shields.io/badge/RxKotlin-purple?style=flat-square)](https://github.com/ReactiveX/RxKotlin)  
[![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)](LICENSE)

**RoadRunner Benchmark App** is a modern Android application designed to compare CPU‑intensive performance across multiple concurrency technologies.  
It calculates π using **Coroutines, RxJava, RxKotlin, and ThreadPool**, visualizes execution graphs in real‑time, and ranks results with speed metrics.

---

## ✨ Features

- 🚀 **Splash Screen** – Clean entry animation on app start  
- 📊 **Benchmark Execution** – Run π calculation with configurable iterations (1M, 10M, 100M, 1B)  
- 📈 **Real‑Time Graphs** – Execution time samples plotted live until completion  
- 🟢 **Colored π Result** – Digits highlighted green until mismatch, then red for all subsequent digits  
- 🏁 **Ranking System** – Displays race speed (iterations/ms) and execution time for each technology in order of completion  
- 🔧 **Iteration Selector** – Dropdown menu to choose iteration count before running benchmarks  
- 🛠️ **Clean Architecture + MVVM + UDF** – Scalable, testable, and maintainable design  

---

## 🏗️ Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** Clean Architecture + MVVM + UDF (Unidirectional Data Flow)  
- **Dependency Injection:** Koin  
- **Concurrency:** Coroutines, RxJava, RxKotlin, ThreadPool  
- **State Management:** ViewModel + StateFlow  
- **Graphs:** Custom Compose Canvas components  
- **Extras:** Coroutine Flow, Modular design, Explicit configuration  

---

## 🧩 Architecture Overview

RoadRunner follows **Clean Architecture** principles combined with **MVVM** and **UDF**:

- **Domain Layer**  
  - Pure business logic (π calculation, benchmark use cases)  
  - Independent of frameworks  

- **Data Layer**  
  - Provides implementations (BenchmarkRunner, PiCalculator)  
  - Centralized configuration (`BenchmarkConfig`)  

- **Presentation Layer**  
  - **MVVM**: `BenchmarkViewModel` exposes immutable `BenchmarkState` via `StateFlow`  
  - **UDF**: User actions (intents) trigger state updates, ensuring predictable flow  
  - **UI**: Composables (`MainScreen`, `BenchmarkSection`, `CombinedResultsGraph`) render state reactively  

- **DI Layer**  
  - Koin modules (`appModule`) wire dependencies cleanly  

This separation ensures **testability, scalability, and regression‑safe iteration**.

---

## 📂 Project Structure

```plaintext
com
 └── chtmed
     └── roadrunner
         ├── data
         ├── di
         ├── domain
         └── presentation
             ├── BenchmarkConfig.kt
             ├── BenchmarkRunner.kt
             ├── appModule.kt
             ├── RoadRunnerApp.kt
             ├── PiCalculator.kt
             ├── ui
             │   ├── theme
             │   ├── BenchmarkSection.kt
             │   ├── CombinedResultsGraph.kt
             │   ├── ExecutionGraph.kt
             │   ├── MainScreen.kt
             │   ├── MultiApproachGraph.kt
             │   └── BenchmarkColors.kt
             ├── viewmodel
             │   ├── BenchmarkState.kt
             │   └── BenchmarkViewModel.kt
             └── MainActivity.kt
