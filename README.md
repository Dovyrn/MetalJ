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
    implementation("com.github.Dovyrn:MetalJ:0.4.2")
}
```

Or take the jar straight off a [release](https://github.com/Dovyrn/MetalJ/releases).

## Ownership

Every method that creates an object returns it retained, and you release it when you are
done. This covers `new*` factories and the command encoder factories on `MTLCommandBuffer`
and `MTL4CommandBuffer`, in both Metal generations.

Property accessors return borrowed references, so never release what `colorAttachments()`,
`depthAttachment()`, `device()` or `label()` hand back.
