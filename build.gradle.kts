plugins {
    kotlin("jvm") version "1.5.0"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":date-saver-core"))
    implementation("com.h2database:h2:1.4.200")
}

application {
    mainClass.value("pro.azhidkov.solid.DateSaver")
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls")
}
