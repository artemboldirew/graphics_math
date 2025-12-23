package io.github.artemboldirew.vector3d.core;

import java.util.Objects;

public class Vector4f {
    private static final int n = 4;
    private static final int m = 1;
    private float[][] vector = new float[4][1];

    public Vector4f(float[][] initialMatrix) {
        setVector(initialMatrix);
    }

    public Vector4f(Vector4f vec) {
        setVector(vec.vector);
    }

    public Vector4f mulInPlace(float num) {
        MathUtil.multiplyByNumInPlace(this.vector, num);
        return this;
    }

    public Vector4f mul(float num) {
        float[][] arr = MathUtil.multiplyByNum(this.vector, num);
        return new Vector4f(arr);
    }

    public Vector4f div(float num) {
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector4f divInPlace(float num) {
        MathUtil.divideByNumInPlace(this.vector, num);
        return this;
    }

    public Vector4f addInPlace(Vector4f vec) {
        MathUtil.addArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector4f add(Vector4f vec) {
        float[][] arr = MathUtil.addArrays(this.vector, vec.vector);
        return new Vector4f(arr);
    }

    public Vector4f subInPlace(Vector4f vec) {
        MathUtil.subArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector4f sub(Vector4f vec) {
        float[][] arr = MathUtil.subArrays(this.vector, vec.vector);
        return new Vector4f(arr);
    }


    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) + Math.pow(vector[2][0], 2) + Math.pow(vector[3][0], 2), 0.5);
    }

    public Vector4f normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        this.vector[3][0] /= len;
        return this;
    }


    public float dot(Vector4f vec) {
        return MathUtil.scalarArrayProduct(this.vector, vec.getVector());
    }

    public float[][] getVector() {
        float[][] copy = new float[4][1];
        copy[0][0] = vector[0][0];
        copy[1][0] = vector[1][0];
        copy[2][0] = vector[2][0];
        copy[3][0] = vector[3][0];
        return copy;
    }

    public float getX() {
        return vector[0][0];
    }

    public float getY() {
        return vector[1][0];
    }

    public float getZ() {
        return vector[2][0];
    }

    public float getW() {
        return vector[3][0];
    }

    public Vector3f ndc() {
        float len = vector[3][0];
        if (len == 0.0f) {
            throw new RuntimeException("W = 0, приведение к ndc невозможно");
        }
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        return new Vector3f(new float[][]{{vector[0][0]} ,{vector[1][0]}, {vector[2][0]}});
    }

    public void setVector(float[][] mat) {
        Objects.requireNonNull(mat, "Матрица не может быть null");
        MathUtil.checkArray(mat, n, m);
        vector[0][0] = mat[0][0];
        vector[1][0] = mat[1][0];
        vector[2][0] = mat[2][0];
        vector[3][0] = mat[3][0];
    }

}
