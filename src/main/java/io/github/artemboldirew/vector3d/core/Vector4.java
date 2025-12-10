package io.github.artemboldirew.vector3d.core;

public class Vector4 {
    private static final int n = 4;
    private static final int m = 1;
    private float[][] vector;

    public Vector4(float[][] initialMatrix) {
        this.vector = initialMatrix;
    }

    public Vector4 multiply(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] * num;
            }
        }
        return this;
    }

    public Vector4 divide(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] / num;
            }
        }
        return this;
    }


    public Vector4 add(Vector4 vec) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] += vec.vector[i][j];
            }
        }
        return this;
    }

    public Vector4 subtract(Vector4 vec) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] -= vec.vector[i][j];
            }
        }
        return this;
    }

    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) + Math.pow(vector[2][0], 2) ,0.5);
    }

    public Vector4 normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        return this;
    }


    public float scalarProduct(Vector4 vec) {
        float res = 0;
        for (int i = 0; i < vec.vector.length; i++) {
            res += (this.vector[i][0] * vec.vector[i][0]);
        }
        return res;
    }

    public float[][] getVector() {
        return vector;
    }
}
