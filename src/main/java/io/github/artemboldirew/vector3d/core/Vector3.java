package io.github.artemboldirew.vector3d.core;

public class Vector3 {
    private static final int n = 3;
    private static final int m = 1;
    private float[][] vector;

    public Vector3(float[][] initialMatrix) {
        this.vector = initialMatrix;
    }

    public Vector3 multiply(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] * num;
            }
        }
        return this;
    }

    public Vector3 divide(float num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] = this.vector[i][j] / num;
            }
        }
        return this;
    }


    public Vector3 add(Vector3 vec) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.vector[i][j] += vec.vector[i][j];
            }
        }
        return this;
    }

    public Vector3 subtract(Vector3 vec) {
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

    public Vector3 normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        return this;
    }


    public float scalarProduct(Vector3 vec) {
        float res = 0;
        for (int i = 0; i < vec.vector.length; i++) {
            res += (this.vector[i][0] * vec.vector[i][0]);
        }
        return res;
    }

    public Vector3 vectorProduct(Vector3 vec) {
        float[] first = new float[]{this.vector[0][0], this.vector[1][0], this.vector[2][0]};
        float[] second = new float[]{vec.vector[0][0], vec.vector[1][0], vec.vector[2][0]};
        this.vector[0][0] = first[1]*second[2] - first[2]*second[1];
        this.vector[1][0] = first[2]*second[0] - first[0]*second[2];
        this.vector[2][0] = first[0]*second[1] - first[1]*second[0];

        return this;
    }

    public float[][] getVector() {
        return vector;
    }
}
