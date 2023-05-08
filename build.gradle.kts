plugins {
    kotlin("multiplatform") version "1.8.21"
}

repositories {
    mavenCentral()
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation("com.squareup.okio:okio:3.3.0")
            }
        }
    }

    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {
                entryPoint = "main"

                // From: https://stackoverflow.com/questions/64552597/pass-arguments-to-kotlin-native-run-tasks
                runTask?.run {
                    val args = providers.gradleProperty("runArgs")
                    argumentProviders.add(CommandLineArgumentProvider {
                        args.orNull?.split(' ') ?: emptyList()
                    })
                }
            }
        }
    }
}
