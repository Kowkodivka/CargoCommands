import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.lucin"
version = "1.0.2"

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
}

val sourcesJar = task<Jar>("sourcesJar") {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }

    archiveFileName.set("cargo-commands-${project.version}.jar")

    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"])
            artifact("build/libs/cargo-commands-${project.version}.jar")

            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
        }
    }
}