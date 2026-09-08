plugins {
    id("java-library")
    id("maven-publish")
}

val lombokVersion: String by project
val junitVersion: String by project
val lwjglVersion: String by project

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
    "exampleImplementation"(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    "exampleImplementation"("org.lwjgl:lwjgl")
    "exampleImplementation"("org.lwjgl:lwjgl-glfw")
    "exampleRuntimeOnly"("org.lwjgl:lwjgl::natives-macos-arm64")
    "exampleRuntimeOnly"("org.lwjgl:lwjgl-glfw::natives-macos-arm64")
    "exampleRuntimeOnly"("org.lwjgl:lwjgl::natives-macos")
    "exampleRuntimeOnly"("org.lwjgl:lwjgl-glfw::natives-macos")
}

tasks.register<JavaExec>("runMesh") {
    classpath = sourceSets["example"].runtimeClasspath
    mainClass = "dev.dov.metalj.example.Mesh"
    jvmArgs("--enable-native-access=ALL-UNNAMED", "-XstartOnFirstThread")
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
