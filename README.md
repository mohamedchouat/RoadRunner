# 🏎️ RoadRunner Benchmark App

## 📸 Screenshots

<table>
  <tr>
    <td>Splash Screen</td>
    <td>Top (1,000,000 Iterations)</td>
    <td>Bottom (1,000,000 Iterations)</td>
    <td>Top (10,000,000 Iterations)</td>
    <td>Bottom (10,000,000 Iterations)</td>
  </tr>
  <tr>
    <td><img src="https://github.com/mohamedchouat/RoadRunner/blob/master/screens/splash.jpg" width="160"/></td>
    <td><img src="https://github.com/mohamedchouat/RoadRunner/blob/master/screens/top1_000_000.png" width="160"/></td>
    <td><img src="https://github.com/mohamedchouat/RoadRunner/blob/master/screens/bottom1_000_000.png" width="160"/></td>
    <td><img src="https://github.com/mohamedchouat/RoadRunner/blob/master/screens/top10_000_000.png" width="160"/></td>
    <td><img src="https://github.com/mohamedchouat/RoadRunner/blob/master/screens/bottom10_000_000.png" width="160"/></td>
  </tr>
</table>


## 🎥 Demo Video

You can watch the benchmark demo here:

[![RoadRunner Demo](https://img.shields.io/badge/Video-RoadRunner%20Demo-red?style=for-the-badge)](https://github.com/mohamedchouat/RoadRunner/blob/master/screens/video.mp4)

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
