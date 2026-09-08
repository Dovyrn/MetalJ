package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.resources.MTLTexture;

public class CAMetalDrawable extends NSObject {
    private CAMetalDrawable(long id) {
        super(id);
    }

    public static CAMetalDrawable of(long id) {
        return new CAMetalDrawable(id);
    }

    public MTLTexture texture() {
        return MTLTexture.of(sendPtr(id, "texture"));
    }

    public void present() {
        sendVoid(id, "present");
    }
}
