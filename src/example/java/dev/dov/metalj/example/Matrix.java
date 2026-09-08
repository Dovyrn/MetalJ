package dev.dov.metalj.example;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Matrix {
    public float[] perspective(float fov, float aspect, float near, float far) {
        float scale = 1 / (float) Math.tan(fov / 2);
        return new float[] {
            scale / aspect, 0, 0, 0,
            0, scale, 0, 0,
            0, 0, far / (near - far), -1,
            0, 0, near * far / (near - far), 0,
        };
    }

    public float[] rotation(float yaw, float pitch) {
        float cy = (float) Math.cos(yaw);
        float sy = (float) Math.sin(yaw);
        float cp = (float) Math.cos(pitch);
        float sp = (float) Math.sin(pitch);
        return new float[] {
            cy, sy * sp, -sy * cp, 0,
            0, cp, sp, 0,
            sy, -cy * sp, cy * cp, 0,
            0, 0, 0, 1,
        };
    }

    public float[] translation(float x, float y, float z) {
        return new float[] {
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            x, y, z, 1,
        };
    }

    public float[] multiply(float[] a, float[] b) {
        float[] out = new float[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += a[k * 4 + j] * b[i * 4 + k];
                }
                out[i * 4 + j] = sum;
            }
        }
        return out;
    }
}
