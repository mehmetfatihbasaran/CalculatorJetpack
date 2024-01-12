buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        /*
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0")

         */
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
