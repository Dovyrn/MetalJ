plugins {
    id("java-library")
    id("maven-publish")
}

val lombokVersion: String by project
val junitVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:deprecation")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    onlyIf { System.getProperty("os.name").lowercase().contains("mac") }
}

tasks.jar {
    manifest {
        attributes("Enable-Native-Access" to "ALL-UNNAMED")
    }
    from("LICENSE.txt") {
        rename { "${it}_MetalJ" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
