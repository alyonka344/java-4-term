plugins {
    id("java")
    id("io.freefair.lombok").version("8.6")
}

group = "ru.kholmogorova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project("path" to ":labs:Kotiki:Services"))
}

tasks.test {
    useJUnitPlatform()
}