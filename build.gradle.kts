plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.excoder"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-neo4j")
    implementation("org.springframework.kafka:spring-kafka:3.1.4")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.modelmapper:modelmapper:3.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // testImplementation("org.neo4j.test:neo4j-harness")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveFileName.set("customer-service-1.0-SNAPSHOT.jar")
}
tasks.named<Jar>("jar") {
    enabled = false
}

spotless {
    java {
        target("**/*.java")
        targetExclude("${layout.buildDirectory}/**/*.java")
        replaceRegex("Remove wildcard import statements", "import\\s+(?:static\\s+)?[^\\*\\s]+\\*;(\\r\\n|\\r|\\n)", "$1")
        toggleOffOn()
        palantirJavaFormat()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint("1.2.1") // Apply ktlint to Gradle Kotlin scripts
    }
}
