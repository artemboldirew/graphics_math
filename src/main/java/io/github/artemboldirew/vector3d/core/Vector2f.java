package io.github.artemboldirew.vector3d.core;

import java.util.Objects;

public class Vector2f {
    private static final int n = 2;
    private static final int m = 1;
    private float[][] vector = new float[2][1];

    public Vector2f(float[][] initialMatrix) {
        setVector(initialMatrix);
    }

    public Vector2f(Vector2f vec) {
        setVector(vec.vector);
    }

    public Vector2f mulInPlace(float num) {
        MathUtil.multiplyByNumInPlace(this.vector, num);
        return this;
    }

    public Vector2f mul(float num) {
        float[][] arr = MathUtil.multiplyByNum(this.vector, num);
        return new Vector2f(arr);
    }

    public Vector2f div(float num) {
        MathUtil.divideByNumInPlace(this.vector, num);
        return this;
    }

    public Vector2f divInPlace(float num) {
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector2f addInPlace(Vector2f vec) {
        MathUtil.addArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector2f add(Vector2f vec) {
        float[][] arr = MathUtil.addArrays(this.vector, vec.vector);
        return new Vector2f(arr);
    }

    public Vector2f subInPlace(Vector2f vec) {
        MathUtil.subArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector2f sub(Vector2f vec) {
        float[][] arr = MathUtil.subArrays(getVector(), vec.getVector());
        return new Vector2f(arr);
    }

    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) ,0.5);
    }

    public Vector2f normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        return this;
    }


    public float dot(Vector2f vec) {
        return MathUtil.scalarArrayProduct(this.vector, vec.getVector());
    }

    public float[][] getVector() {
        float[][] copy = new float[2][1];
        copy[0][0] = vector[0][0];
        copy[1][0] = vector[1][0];
        return copy;
    }

    public void setVector(float[][] mat) {
        Objects.requireNonNull(mat, "Матрица не может быть null");
        MathUtil.checkArray(mat, n, m);
        vector[0][0] = mat[0][0];
        vector[1][0] = mat[1][0];
    }

    public float getX() {
        if (vector != null) {
            return vector[0][0];
        }
        throw new RuntimeException("Вектор пуст");
    }

    public float getY() {
        return vector[1][0];
    }
}
