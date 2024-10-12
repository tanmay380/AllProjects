pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Add other repositories here, including BintrayJCenter if needed
        maven { url = uri("https://jcenter.bintray.com/") } // BintrayJCente
    }
}

rootProject.name = "Music Playlist Copy"
include(":app")
//include(":auth-sample")
//include(":auth-lib")
//include(":app-remote-lib")
//include(":app-remote-sample")
