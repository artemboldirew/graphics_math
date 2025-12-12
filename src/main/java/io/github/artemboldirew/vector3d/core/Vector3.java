package io.github.artemboldirew.vector3d.core;

public class Vector3 {
    private static final int n = 3;
    private static final int m = 1;
    private float[][] vector;

    public Vector3(float[][] initialMatrix) {
        MathUtil.checkArray(initialMatrix, n, m);
        this.vector = initialMatrix;
    }

    public Vector3 multiply(float num) {
        MathUtil.multiplyByNum(this.vector, num);
        return this;
    }

    public Vector3 divide(float num) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.divideByNum(this.vector, num);
        return this;
    }

    public Vector3 add(Vector3 vec) {
        MathUtil.checkArray(this.vector, n, m);
        MathUtil.addArrays(this.vector, vec.getVector());
        return this;
    }

    public Vector3 subtract(Vector3 vec) {
        MathUtil.checkArray(vec.getVector(), n, m);
        MathUtil.substractArrays(this.vector, vec.getVector());
        return this;
    }

    public float getLength() {
        return (float) Math.pow(Math.pow(vector[0][0], 2) + Math.pow(vector[1][0], 2) + Math.pow(vector[2][0], 2) ,0.5);
    }

    public Vector3 normalize() {
        float len = getLength();
        this.vector[0][0] /= len;
        this.vector[1][0] /= len;
        this.vector[2][0] /= len;
        return this;
    }


    public float scalarProduct(Vector3 vec) {
        MathUtil.checkArray(vec.getVector(), n, m);
        return MathUtil.scalarArrayProduct(this.vector, vec.getVector());
    }

    public Vector3 vectorProduct(Vector3 vec) {
        float[] first = new float[]{this.vector[0][0], this.vector[1][0], this.vector[2][0]};
        float[] second = new float[]{vec.vector[0][0], vec.vector[1][0], vec.vector[2][0]};
        this.vector[0][0] = first[1]*second[2] - first[2]*second[1];
        this.vector[1][0] = first[2]*second[0] - first[0]*second[2];
        this.vector[2][0] = first[0]*second[1] - first[1]*second[0];

        return this;
    }

    public float[][] getVector() {
        return vector;
    }
}
