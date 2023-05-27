import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.lucin"
version = "1.0.2"
description = "Annotation-based library for registration commands"

plugins {
    kotlin("jvm") version "1.7.21"
    `maven-publish`
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

repositories {
    mavenCentral()
    maven(url = "https://www.jitpack.io")
}

dependencies {
    val mindustryVersion = "v140.4"

    compileOnly("com.github.Anuken.Arc:arc-core:$mindustryVersion")
    compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.21")
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
    
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])

            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/Kowkodivka/CargoCommands")

                licenses {
                    license {
                        name.set("GPL-3.0")
                        url.set("https://github.com/Kowkodivka/CargoCommands/LICENSE")
                    }
                }

                developers {
                    name.set("Kowkodivka")
                }

                scm {
                    url.set("https://github.com/Kowkodivka/CargoCommands")
                    connection.set("scm:git:git://github.com/Kowkodivka/CargoCommands.git")
                    developerConnection.set("scm:git:ssh://git@github.com:Kowkodivka/CargoCommands.git")
                }
            }
        }
    }
}
