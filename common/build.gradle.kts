plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "dev.holstad.henrik"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-testcontainers")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.testcontainers:junit-jupiter:1.20.1")
    implementation("org.testcontainers:postgresql")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
