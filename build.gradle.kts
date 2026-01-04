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
    testImplementation("org.testng:testng:7.11.0")
}

tasks.test {
    // Use TestNG framework for running tests
    useTestNG()
}