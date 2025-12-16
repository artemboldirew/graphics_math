package io.github.artemboldirew.vector3d.core;

public class Matrix4f {
    private static final int n = 4;
    private static final int m = 4;
    private float[][] matrix;

    public Matrix4f(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.matrix = initialMatrix;
    }

    public Matrix4f multiply(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, mat.matrix);
        return this;
    }

    public Matrix4f transposeInPlace() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Matrix4f transpose() {
        return new Matrix4f(MathUtil.transposeMatrix(getMatrix()));
    }

    public Matrix4f mulInPlace(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, mat.matrix);
        return this;
    }

    public Matrix4f mul(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix4f(MathUtil.multiplyMatrices(getMatrix(), mat.getMatrix()));
    }

    public Matrix4f mul(Vector4f vec) {
        MathUtil.checkArray(vec.getVector(), 3, 1);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, vec.getVector());
        return this;
    }

    public Matrix4f addInPlace(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.addArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix4f add(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix4f(MathUtil.addArrays(getMatrix(), mat.getMatrix()));
    }

    public Matrix4f subInPlace(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.substractArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix4f sub(Matrix4f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix4f(MathUtil.substractArrays(getMatrix(), mat.getMatrix()));
    }

    public static Matrix4f getE() {
        return new Matrix4f(new float[][]{{1.0F, 0.0F, 0.0F, 0.0F}, {0.0F, 1.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 1.0F, 0.0F},{0.0F, 0.0F, 0.0F, 1.0F}});
    }

    public static Matrix4f getZ() {
        return new Matrix4f(new float[][]{
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        });
    }

    public float[][] getMatrix() {
        float[][] copy = new float[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, 4);
        }
        return copy;
    }
}
