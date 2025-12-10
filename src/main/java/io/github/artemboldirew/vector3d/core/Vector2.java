package io.github.artemboldirew.vector3d.core;

public class Vector2 {
    private static final int n = 2;
    private static final int m = 1;
    private float[][] vector;

    public Vector2(float[][] initialMatrix) {
        this.vector = initialMatrix;
    }

    public Vector2 multiply(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] * num;
            }
        }
        return this;
    }

    public Vector2 divide(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] / num;
            }
        }
        return this;
    }


    public Vector2 add(Vector2 vec) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] += vec.vector[i][j];
            }
        }
        return this;
    }

    public Vector2 subtract(Vector2 vec) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] -= vec.vector[i][j];
            }
        }
        return this;
    }

    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) ,0.5);
    }

    public Vector2 normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        return this;
    }


    public float scalarProduct(Vector2 vec) {
        float res = 0;
        for (int i = 0; i < vec.vector.length; i++) {
            res += (this.vector[i][0] * vec.vector[i][0]);
        }
        return res;
    }

}
