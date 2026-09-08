#include <metal_stdlib>
#include <metal_mesh>
using namespace metal;

struct Uniforms {
    float4x4 viewProjection;
    float time;
    uint side;
    float spacing;
};

struct Payload {
    float4x4 model;
    float3 tint;
};

struct Fragment {
    float4 position [[position]];
    float3 normal;
    float3 tint;
};

using Triangles = mesh<Fragment, void, 24, 12, topology::triangle>;

constant float3 corners[8] = {
    float3(-1, -1, -1), float3(1, -1, -1), float3(1, 1, -1), float3(-1, 1, -1),
    float3(-1, -1, 1), float3(1, -1, 1), float3(1, 1, 1), float3(-1, 1, 1),
};

constant uint4 faces[6] = {
    uint4(4, 5, 6, 7), uint4(1, 0, 3, 2), uint4(0, 4, 7, 3),
    uint4(5, 1, 2, 6), uint4(3, 7, 6, 2), uint4(0, 1, 5, 4),
};

constant float3 normals[6] = {
    float3(0, 0, 1), float3(0, 0, -1), float3(-1, 0, 0),
    float3(1, 0, 0), float3(0, 1, 0), float3(0, -1, 0),
};

static float4x4 spin(float yaw, float pitch) {
    float cy = cos(yaw);
    float sy = sin(yaw);
    float cp = cos(pitch);
    float sp = sin(pitch);
    return float4x4(float4(cy, sy * sp, -sy * cp, 0), float4(0, cp, sp, 0),
                    float4(sy, -cy * sp, cy * cp, 0), float4(0, 0, 0, 1));
}

[[object]] void cubes_object(object_data Payload &payload [[payload]],
                             constant Uniforms &uniforms [[buffer(0)]],
                             uint index [[threadgroup_position_in_grid]],
                             mesh_grid_properties grid) {
    uint side = uniforms.side;
    uint3 cell = uint3(index % side, (index / side) % side, index / (side * side));
    float3 origin = (float3(cell) - float3(side - 1) * 0.5) * uniforms.spacing;
    float phase = float(index) * 0.35;
    float4x4 model = spin(uniforms.time * 0.9 + phase, uniforms.time * 0.6 + phase);
    model[3] = float4(origin, 1);
    payload.model = model;
    payload.tint = 0.45 + 0.55 * cos(float3(cell) * 0.9 + float3(0, 2, 4));
    grid.set_threadgroups_per_grid(uint3(1, 1, 1));
}

[[mesh]] void cubes_mesh(Triangles output,
                         const object_data Payload &payload [[payload]],
                         constant Uniforms &uniforms [[buffer(0)]],
                         uint lane [[thread_index_in_threadgroup]]) {
    if (lane == 0) {
        output.set_primitive_count(12);
    }
    if (lane < 24) {
        uint face = lane / 4;
        uint corner = lane % 4;
        float4 world = payload.model * float4(corners[faces[face][corner]], 1);
        Fragment point;
        point.position = uniforms.viewProjection * world;
        point.normal = (payload.model * float4(normals[face], 0)).xyz;
        point.tint = payload.tint;
        output.set_vertex(lane, point);
    }
    if (lane < 12) {
        uint face = lane / 2;
        uint base = face * 4;
        uint3 triangle = lane % 2 == 0 ? uint3(base, base + 1, base + 2) : uint3(base, base + 2, base + 3);
        output.set_index(lane * 3 + 0, triangle.x);
        output.set_index(lane * 3 + 1, triangle.y);
        output.set_index(lane * 3 + 2, triangle.z);
    }
}

fragment float4 cubes_fragment(Fragment in [[stage_in]]) {
    float3 light = normalize(float3(0.4, 0.8, 0.5));
    float shade = saturate(dot(normalize(in.normal), light)) * 0.75 + 0.25;
    return float4(in.tint * shade, 1);
}
