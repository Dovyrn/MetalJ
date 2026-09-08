# MetalJ

Java bindings for the Metal graphics API.

## Requirements

macOS and Java 25. Run with `--enable-native-access=ALL-UNNAMED`, or add
`Enable-Native-Access: ALL-UNNAMED` to your jar manifest.

## Install

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Dovyrn:MetalJ:0.1.0")
}
```

Or take the jar straight off a [release](https://github.com/Dovyrn/MetalJ/releases).
