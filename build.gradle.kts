val exposedVersion: String by project
val postgres_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //implementation("org.postgresql:postgresql:$postgres_version")
    //implementation("com.impossibl.pgjdbc-ng:pgjdbc-ng:LATEST")
    implementation("com.impossibl.pgjdbc-ng:pgjdbc-ng:0.8.9")
    implementation("ch.qos.logback:logback-classic:$logback_version")


    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    // or
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    // or
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")

    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
    //implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}