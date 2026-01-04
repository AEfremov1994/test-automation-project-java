plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Add the TestNG dependency
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testng:testng:7.11.0")
    testImplementation("io.rest-assured:rest-assured:5.5.6")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
}

tasks.test {
    // Use TestNG framework for running tests
    useTestNG()
}