plugins {
    id("java")
    id("io.freefair.lombok").version("8.6")
    id("org.springframework.boot").version("3.2.5") apply false
}

group = "ru.kholmogorova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(platform("org.hibernate.orm:hibernate-platform:6.4.4.Final"))

    implementation("org.hibernate.orm:hibernate-core")
    implementation("jakarta.transaction:jakarta.transaction-api")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.springframework.security:spring-security-core:6.2.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    implementation(project(":labs:Kotiki2:Services"))
    implementation(project(":labs:Kotiki2:Entities"))
}

tasks.test {
    useJUnitPlatform()
}