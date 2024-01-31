pluginManagement {
    repositories {
        jcenter()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven(url="https://jitpack.io")
//        maven {
//            url = url()
//        }
        jcenter()
        mavenCentral()
    }
}

rootProject.name = "NewsApp"
include(":app")
 