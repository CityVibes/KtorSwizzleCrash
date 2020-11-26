# KtorSwizzleCrash

Reproducable project for https://youtrack.jetbrains.com/issue/KT-42482 issue

1. iOS application crashes only with `FirebaseApp.configure()`, commenting it out fixes the issue.
2. Attempting to disable Firebase swizzling did not help
3. `FirebaseApp.configure()` works well, in fact using modified Ktor version that does not rely on delegate that requires `NSObject` fixes the issue. Ktor fork here - https://github.com/CityVibes/ktor
4. Note `TestCrash` is KMM project with relevant Ktor logic, `ReproSwizzle` is iOS project, shared KMM code is built and included into iOS project separately (running iOS project will not rebuild KMM code)
