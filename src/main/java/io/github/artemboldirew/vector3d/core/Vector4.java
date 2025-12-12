package io.github.artemboldirew.vector3d.core;

public class Vector4 {
    private static final int n = 4;
    private static final int m = 1;
    private float[][] vector;

    public Vector4(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.vector = initialMatrix;
    }

    public Vector4 multiply(float num) {
        MathUtil.multiplyByNum(this.vector, num);
        return this;
    }

    public Vector4 divide(float num) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector4 add(Vector4 vec) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.addArrays(this.vector, vec.getVector());
        return this;
    }

    public Vector4 subtract(Vector4 vec) {
        MathUtil.checkArray(vec.getVector(), n, m);
        MathUtil.substractArrays(this.vector, vec.getVector());
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
        MathUtil.checkArray(vec.getVector(), n, m);
        return MathUtil.scalarArrayProduct(this.vector, vec.getVector());
    }

    public float[][] getVector() {
        return vector;
    }
}
