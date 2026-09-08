plugins {
    id("java-library")
    id("maven-publish")
}

val lombokVersion: String by project
val junitVersion: String by project
val githubUser: String by project

sourceSets.create("example") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    "exampleCompileOnly"("org.projectlombok:lombok:$lombokVersion")
    "exampleAnnotationProcessor"("org.projectlombok:lombok:$lombokVersion")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
    withSourcesJar()
    withJavadocJar()
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
        attributes(
            "Enable-Native-Access" to "ALL-UNNAMED",
            "Automatic-Module-Name" to "dev.dov.metalj",
        )
    }
    from("LICENSE.txt") {
        rename { "${it}_MetalJ" }
    }
}

tasks.javadoc {
    isFailOnError = false
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name = "MetalJ"
                description = "Java bindings for the Metal graphics API"
                url = "https://github.com/$githubUser/MetalJ"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = githubUser
                        name = "Dovyrn"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/$githubUser/MetalJ.git"
                    developerConnection = "scm:git:ssh://github.com/$githubUser/MetalJ.git"
                    url = "https://github.com/$githubUser/MetalJ"
                }
            }
        }
    }
}

