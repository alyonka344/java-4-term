plugins {
    id("java")
    id("io.freefair.lombok").version("8.6")
    id("org.springframework.boot").version("3.2.5") apply false
}

group = "ru.kholmogorova"
version = "0.0.1-SNAPSHOT"

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

    implementation(project(":labs:Kotiki2:Entities"))

    implementation("org.springframework.security:spring-security-config:6.2.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0-M1")
    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-web
    implementation("org.springframework.security:spring-security-web:6.3.0")


    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt:0.12.3")
}

tasks.test {
    useJUnitPlatform()
}