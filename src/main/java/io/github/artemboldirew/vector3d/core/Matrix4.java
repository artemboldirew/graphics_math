package io.github.artemboldirew.vector3d.core;

public class Matrix4 {
    private static final int n = 4;
    private static final int m = 4;
    private float[][] matrix;

    public Matrix4(float[][] initialMatrix) {
        this.matrix = initialMatrix;
    }

    public Matrix4 multiply(Matrix4 m) {
        this.matrix = MathUtil.multiplyMatrices(this.matrix, m.matrix);
        return this;
    }

    public Matrix4 transpose() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Matrix4 multiply(Vector4 m) {
        this.matrix = MathUtil.multiplyMatrices(this.matrix, m.getVector());
        return this;
    }

    public Matrix4 add(Matrix4 matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = this.matrix[i][j] + matrix.matrix[i][j];
            }
        }
        return this;
    }

    public Matrix4 subtract(Matrix4 matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = this.matrix[i][j] - matrix.matrix[i][j];
            }
        }
        return this;
    }

    public static Matrix4 getE() {
        return new Matrix4(new float[][]{{1.0F, 1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F, 1.0F},{1.0F, 1.0F, 1.0F, 1.0F}});
    }

    public static Matrix4 getZ() {
        return new Matrix4(new float[][]{{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}});
    }

    public float[][] getMatrix() {
        return matrix;
    }
}
