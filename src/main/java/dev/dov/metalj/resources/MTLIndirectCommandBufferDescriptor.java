package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectCommandBufferDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);

    private MTLIndirectCommandBufferDescriptor(long id) {
        super(id);
    }

    public static MTLIndirectCommandBufferDescriptor of(long id) {
        return new MTLIndirectCommandBufferDescriptor(id);
    }

    public static MTLIndirectCommandBufferDescriptor new_() {
        return new MTLIndirectCommandBufferDescriptor(sendPtr(ObjC.cls("MTLIndirectCommandBufferDescriptor"), "new"));
    }

    @SneakyThrows
    public void setCommandTypes(long types) {
        L.invokeExact(id, ObjC.sel("setCommandTypes:"), types);
    }

    @SneakyThrows
    public void setInheritPipelineState(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritPipelineState:"), inherit);
    }

    @SneakyThrows
    public void setInheritBuffers(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritBuffers:"), inherit);
    }

    @SneakyThrows
    public void setInheritDepthStencilState(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritDepthStencilState:"), inherit);
    }

    @SneakyThrows
    public void setInheritDepthBias(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritDepthBias:"), inherit);
    }

    @SneakyThrows
    public void setInheritDepthClipMode(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritDepthClipMode:"), inherit);
    }

    @SneakyThrows
    public void setInheritCullMode(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritCullMode:"), inherit);
    }

    @SneakyThrows
    public void setInheritFrontFacingWinding(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritFrontFacingWinding:"), inherit);
    }

    @SneakyThrows
    public void setInheritTriangleFillMode(boolean inherit) {
        B.invokeExact(id, ObjC.sel("setInheritTriangleFillMode:"), inherit);
    }

    @SneakyThrows
    public void setMaxVertexBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxVertexBufferBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxFragmentBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxFragmentBufferBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxKernelBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxKernelBufferBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxKernelThreadgroupMemoryBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxKernelThreadgroupMemoryBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxObjectBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxObjectBufferBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxMeshBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxMeshBufferBindCount:"), count);
    }

    @SneakyThrows
    public void setMaxObjectThreadgroupMemoryBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxObjectThreadgroupMemoryBindCount:"), count);
    }

    @SneakyThrows
    public void setSupportRayTracing(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportRayTracing:"), support);
    }

    @SneakyThrows
    public void setSupportDynamicAttributeStride(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportDynamicAttributeStride:"), support);
    }

    @SneakyThrows
    public void setSupportColorAttachmentMapping(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportColorAttachmentMapping:"), support);
    }
}
