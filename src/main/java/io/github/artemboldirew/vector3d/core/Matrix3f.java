package io.github.artemboldirew.vector3d.core;

public class Matrix3f {
    private static final int n = 3;
    private static final int m = 3;
    private float[][] matrix;

    public Matrix3f(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.matrix = initialMatrix;
    }

    public Matrix3f transposeInPlace() {
        this.matrix = MathUtil.transposeMatrix(this.matrix);
        return this;
    }

    public Matrix3f transpose() {
        return new Matrix3f(MathUtil.transposeMatrix(getMatrix()));
    }

    public Matrix3f mulInPlace(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, mat.matrix);
        return this;
    }

    public Matrix3f mul(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix3f(MathUtil.multiplyMatrices(getMatrix(), mat.getMatrix()));
    }



    public Matrix3f mul(Vector3f vec) {
        MathUtil.checkArray(vec.getVector(), 3, 1);
        this.matrix = MathUtil.multiplyMatrices(this.matrix, vec.getVector());
        return this;
    }

    public Matrix3f addInPlace(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.addArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix3f add(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix3f(MathUtil.addArrays(getMatrix(), mat.getMatrix()));
    }

    public Matrix3f subInPlace(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        MathUtil.substractArrays(this.matrix, mat.getMatrix());
        return this;
    }

    public Matrix3f sub(Matrix3f mat) {
        MathUtil.checkArray(mat.getMatrix(), n, m);
        return new Matrix3f(MathUtil.substractArrays(getMatrix(), mat.getMatrix()));
    }

    public static Matrix3f getE() {
        return new Matrix3f(new float[][]{{1.0F, 0.0F, 0.0F}, {0.0F, 1.0F, 0.0F}, {0.0F, 0.0F, 1.0F}});
    }

    public static Matrix3f getZ() {
        return new Matrix3f(new float[][]{
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        });
    }

    public float[][] getMatrix() {
        float[][] copy = new float[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}
