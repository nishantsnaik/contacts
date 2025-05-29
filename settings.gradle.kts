rootProject.name = "contacts"

// needed this to resolve plugins in the plugins {} block in build.gradle.kts.
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
