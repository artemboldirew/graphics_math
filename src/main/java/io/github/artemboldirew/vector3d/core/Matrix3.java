package io.github.artemboldirew.vector3d.core;

public class Matrix3 {
    private static final int n = 3;
    private static final int m = 3;
    private float[][] matrix;

    public Matrix3(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.matrix = initialMatrix;
    }

    public Matrix3 multiply(Matrix3 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, mat.matrix);
        return this;
    }

    public Matrix3 transpose() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Matrix3 multiply(Vector3 vec) {
        MathUtil.checkArray(vec.getVector(), 3, 1);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, vec.getVector());
        return this;
    }

    public Matrix3 add(Matrix3 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.addArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix3 subtract(Matrix3 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.substractArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public static Matrix3 getE() {
        return new Matrix3(new float[][]{{1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F}});
    }

    public static Matrix3 getZ() {
        return new Matrix3(new float[][]{
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        });
    }

    public float[][] getMatrix() {
        return matrix;
    }
}
