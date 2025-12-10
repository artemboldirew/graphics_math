package io.github.artemboldirew.vector3d.core;

public class Matrix3 {
    private static final int n = 4;
    private static final int m = 4;
    private float[][] matrix;

    public Matrix3(float[][] initialMatrix) {
        this.matrix = initialMatrix;
    }

    public Matrix3 multiply(Matrix3 m) {
        this.matrix = MathUtil.multiplyMatrices(this.matrix, m.matrix);
        return this;
    }

    public Matrix3 transpose() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Matrix3 multiply(Vector3 m) {
        this.matrix = MathUtil.multiplyMatrices(this.matrix, m.getVector());
        return this;
    }

    public Matrix3 add(Matrix3 matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = this.matrix[i][j] + matrix.matrix[i][j];
            }
        }
        return this;
    }

    public Matrix3 subtract(Matrix3 matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = this.matrix[i][j] - matrix.matrix[i][j];
            }
        }
        return this;
    }

    public static Matrix3 getE() {
        return new Matrix3(new float[][]{{1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F},{1.0F, 1.0F, 1.0F}});
    }

    public static Matrix3 getZ() {
        return new Matrix3(new float[][]{{0, 0, 0},{0, 0, 0},{0, 0, 0},{0, 0, 0}});
    }
}
