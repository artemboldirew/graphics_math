package io.github.artemboldirew.vector3d.core;

public class Vector2 {
    private static final int n = 2;
    private static final int m = 1;
    private float[][] vector;

    public Vector2(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.vector = initialMatrix;
    }

    public Vector2 multiply(float num) {
        MathUtil.multiplyByNum(this.vector, num);
        return this;
    }

    public Vector2 divide(float num) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector2 add(Vector2 vec) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.addArrays(this.vector, vec.getVector());
        return this;
    }

    public Vector2 subtract(Vector2 vec) {
        MathUtil.checkArray(vec.getVector(), n, m);
        MathUtil.substractArrays(this.vector, vec.getVector());
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
        MathUtil.checkArray(vec.getVector(), n, m);
        return MathUtil.scalarArrayProduct(this.vector, vec.getVector());
    }

    public float[][] getVector() {
        return vector;
    }
}
