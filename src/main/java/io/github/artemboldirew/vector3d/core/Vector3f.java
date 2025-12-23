package io.github.artemboldirew.vector3d.core;

import java.util.Objects;

public class Vector3f {
    private static final int n = 3;
    private static final int m = 1;
    private float[][] vector = new float[3][1];

    public Vector3f(float[][] initialMatrix) {
        setVector(initialMatrix);
    }

    public Vector3f(Vector3f vec) {
        setVector(vec.vector);
    }

    public Vector3f mulInPlace(float num) {
        MathUtil.multiplyByNumInPlace(this.vector, num);
        return this;
    }

    public Vector3f mul(float num) {
        float[][] arr = MathUtil.multiplyByNum(this.vector, num);
        return new Vector3f(arr);
    }

    public Vector3f div(float num) {
        MathUtil.divideByNumInPlace(this.vector, num);
        return this;
    }

    public Vector3f divInPlace(float num) {
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector3f addInPlace(Vector3f vec) {
        MathUtil.addArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector3f add(Vector3f vec) {
        float[][] arr = MathUtil.addArrays(this.vector, vec.vector);
        return new Vector3f(arr);
    }

    public Vector3f subInPlace(Vector3f vec) {
        MathUtil.subArraysInPlace(this.vector, vec.vector);
        return this;
    }

    public Vector3f sub(Vector3f vec) {
        float[][] arr = MathUtil.subArrays(this.vector, vec.vector);
        return new Vector3f(arr);
    }

    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) + Math.pow(vector[2][0], 2) ,0.5);
    }

    public Vector3f normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        return this;
    }

    public float dot(Vector3f vec) {
        return MathUtil.scalarArrayProduct(this.vector, vec.vector);
    }

    public Vector3f cross(Vector3f vec) {
        float[] first = new float[]{this.vector[0][0], this.vector[1][0], this.vector[2][0]};
        float[] second = new float[]{vec.vector[0][0], vec.vector[1][0], vec.vector[2][0]};
        float[][] arr = new float[3][1];
        arr[0][0] = first[1]*second[2] - first[2]*second[1];
        arr[1][0] = first[2]*second[0] - first[0]*second[2];
        arr[2][0] = first[0]*second[1] - first[1]*second[0];

        return new Vector3f(arr);
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

    public float[][] getVector() {
        float[][] copy = new float[3][1];
        copy[0][0] = vector[0][0];
        copy[1][0] = vector[1][0];
        copy[2][0] = vector[2][0];
        return copy;
    }

    public void setVector(float[][] mat) {
        Objects.requireNonNull(mat, "Матрица не может быть null");
        MathUtil.checkArray(mat, n, m);
        vector[0][0] = mat[0][0];
        vector[1][0] = mat[1][0];
        vector[2][0] = mat[2][0];
    }
}
