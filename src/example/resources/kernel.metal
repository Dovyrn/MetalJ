#include <metal_stdlib>
using namespace metal;

[[visible]] float twice(float x) {
    return x * 2;
}

kernel void scale(device float *data [[buffer(0)]], constant float &factor [[buffer(1)]],
                  uint i [[thread_position_in_grid]]) {
    data[i] = twice(data[i]) * factor;
}
