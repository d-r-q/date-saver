plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // Никаких зависимостей!
    // Можно спокойно покрывать юнит тестами!
}
