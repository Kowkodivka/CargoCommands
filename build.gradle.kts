import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
    application
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.lucin"
            artifactId = "library"
            version = "1.0.2"

            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

repositories {
    mavenCentral()
    maven(url = "https://www.jitpack.io")
}

dependencies {
    val mindustryVersion = "v136"

    compileOnly("com.github.Anuken.Arc:arc-core:$mindustryVersion")
    compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) {
        exclude("**/META-INF/*.SF")
        exclude("**/META-INF/*.DSA")
        exclude("**/META-INF/*.RSA")
    }

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}