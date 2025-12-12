package io.github.artemboldirew.vector3d.core;

public class Matrix4 {
    private static final int n = 4;
    private static final int m = 4;
    private float[][] matrix;

    public Matrix4(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.matrix = initialMatrix;
    }

    public Matrix4 multiply(Matrix4 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, mat.matrix);
        return this;
    }

    public Matrix4 transpose() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Vector4 multiply(Vector4 m) {
        MathUtil.checkArray(m.getVector(), 4, 1);
        return new Vector4(MathUtil.multiplyMatrices(this.matrix, m.getVector()));
    }

    public Matrix4 add(Matrix4 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.addArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix4 subtract(Matrix4 mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.substractArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public static Matrix4 getE() {
        return new Matrix4(new float[][]{{1.0F, 1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F, 1.0F},{1.0F, 1.0F, 1.0F, 1.0F}});
    }

    public static Matrix4 getZ() {
        return new Matrix4(new float[][]{
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        });
    }

    public float[][] getMatrix() {
        return matrix;
    }
}
