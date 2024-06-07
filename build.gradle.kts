plugins {
    id("java")
}

group = "org.abmmain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.apache.poi:poi-ooxml:5.1.0")
    implementation ("org.apache.logging.log4j:log4j-core:2.14.1")

}


tasks.test {
    useJUnitPlatform()
}