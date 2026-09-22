package dev.dov.metalj.resources.indirect;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectCommandBufferDescriptor extends NSObject {
    private static final long MTL_INDIRECT_COMMAND_BUFFER_DESCRIPTOR = ObjC.cls("MTLIndirectCommandBufferDescriptor");

    private static final long NEW = ObjC.sel("new");
    private static final long SET_COMMAND_TYPES = ObjC.sel("setCommandTypes:");
    private static final long SET_INHERIT_BUFFERS = ObjC.sel("setInheritBuffers:");
    private static final long SET_INHERIT_CULL_MODE = ObjC.sel("setInheritCullMode:");
    private static final long SET_INHERIT_DEPTH_BIAS = ObjC.sel("setInheritDepthBias:");
    private static final long SET_INHERIT_DEPTH_CLIP_MODE = ObjC.sel("setInheritDepthClipMode:");
    private static final long SET_INHERIT_DEPTH_STENCIL_STATE = ObjC.sel("setInheritDepthStencilState:");
    private static final long SET_INHERIT_FRONT_FACING_WINDING = ObjC.sel("setInheritFrontFacingWinding:");
    private static final long SET_INHERIT_PIPELINE_STATE = ObjC.sel("setInheritPipelineState:");
    private static final long SET_INHERIT_TRIANGLE_FILL_MODE = ObjC.sel("setInheritTriangleFillMode:");
    private static final long SET_MAX_FRAGMENT_BUFFER_BIND_COUNT = ObjC.sel("setMaxFragmentBufferBindCount:");
    private static final long SET_MAX_KERNEL_BUFFER_BIND_COUNT = ObjC.sel("setMaxKernelBufferBindCount:");
    private static final long SET_MAX_KERNEL_THREADGROUP_MEMORY_BIND_COUNT = ObjC.sel("setMaxKernelThreadgroupMemoryBindCount:");
    private static final long SET_MAX_MESH_BUFFER_BIND_COUNT = ObjC.sel("setMaxMeshBufferBindCount:");
    private static final long SET_MAX_OBJECT_BUFFER_BIND_COUNT = ObjC.sel("setMaxObjectBufferBindCount:");
    private static final long SET_MAX_OBJECT_THREADGROUP_MEMORY_BIND_COUNT = ObjC.sel("setMaxObjectThreadgroupMemoryBindCount:");
    private static final long SET_MAX_VERTEX_BUFFER_BIND_COUNT = ObjC.sel("setMaxVertexBufferBindCount:");
    private static final long SET_SUPPORT_COLOR_ATTACHMENT_MAPPING = ObjC.sel("setSupportColorAttachmentMapping:");
    private static final long SET_SUPPORT_DYNAMIC_ATTRIBUTE_STRIDE = ObjC.sel("setSupportDynamicAttributeStride:");
    private static final long SET_SUPPORT_RAY_TRACING = ObjC.sel("setSupportRayTracing:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);

    private MTLIndirectCommandBufferDescriptor(long id) {
        super(id);
    }

    public static MTLIndirectCommandBufferDescriptor of(long id) {
        return new MTLIndirectCommandBufferDescriptor(id);
    }

    public static MTLIndirectCommandBufferDescriptor new_() {
        return new MTLIndirectCommandBufferDescriptor(sendPtr(MTL_INDIRECT_COMMAND_BUFFER_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public void setCommandTypes(long types) {
        L.invokeExact(id, SET_COMMAND_TYPES, types);
    }

    @SneakyThrows
    public void setInheritPipelineState(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_PIPELINE_STATE, inherit);
    }

    @SneakyThrows
    public void setInheritBuffers(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_BUFFERS, inherit);
    }

    @SneakyThrows
    public void setInheritDepthStencilState(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_DEPTH_STENCIL_STATE, inherit);
    }

    @SneakyThrows
    public void setInheritDepthBias(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_DEPTH_BIAS, inherit);
    }

    @SneakyThrows
    public void setInheritDepthClipMode(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_DEPTH_CLIP_MODE, inherit);
    }

    @SneakyThrows
    public void setInheritCullMode(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_CULL_MODE, inherit);
    }

    @SneakyThrows
    public void setInheritFrontFacingWinding(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_FRONT_FACING_WINDING, inherit);
    }

    @SneakyThrows
    public void setInheritTriangleFillMode(boolean inherit) {
        B.invokeExact(id, SET_INHERIT_TRIANGLE_FILL_MODE, inherit);
    }

    @SneakyThrows
    public void setMaxVertexBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_VERTEX_BUFFER_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxFragmentBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_FRAGMENT_BUFFER_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxKernelBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_KERNEL_BUFFER_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxKernelThreadgroupMemoryBindCount(long count) {
        L.invokeExact(id, SET_MAX_KERNEL_THREADGROUP_MEMORY_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxObjectBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_OBJECT_BUFFER_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxMeshBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_MESH_BUFFER_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setMaxObjectThreadgroupMemoryBindCount(long count) {
        L.invokeExact(id, SET_MAX_OBJECT_THREADGROUP_MEMORY_BIND_COUNT, count);
    }

    @SneakyThrows
    public void setSupportRayTracing(boolean support) {
        B.invokeExact(id, SET_SUPPORT_RAY_TRACING, support);
    }

    @SneakyThrows
    public void setSupportDynamicAttributeStride(boolean support) {
        B.invokeExact(id, SET_SUPPORT_DYNAMIC_ATTRIBUTE_STRIDE, support);
    }

    @SneakyThrows
    public void setSupportColorAttachmentMapping(boolean support) {
        B.invokeExact(id, SET_SUPPORT_COLOR_ATTACHMENT_MAPPING, support);
    }
}
