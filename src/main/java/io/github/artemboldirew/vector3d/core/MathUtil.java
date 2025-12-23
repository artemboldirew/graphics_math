package io.github.artemboldirew.vector3d.core;

public class MathUtil {
    //создается новая
    public static float[][] multiplyMatrices(float[][] a, float[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bRows = b.length;
        int bCols = b[0].length;

        float[][] result = new float[aRows][bCols];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                float sum = 0.0f;
                for (int k = 0; k < aCols; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    //создается новая
    public static float[][] transposeMatrix(float[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        float[][] transposed = new float[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static float[][] addArrays(float[][] arr1, float[][] arr2) {
        int n = arr1.length;;
        int m = arr1[0].length;
        float[][] arr = new float[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return arr;
    }

    public static void addArraysInPlace(float[][] arr1, float[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                arr1[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
    }

    public static void subArraysInPlace(float[][] arr1, float[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                arr1[i][j] = arr1[i][j] - arr2[i][j];
            }
        }
    }

    public static float[][] subArrays(float[][] arr1, float[][] arr2) {
        int n = arr1.length;;
        int m = arr1[0].length;
        float[][] arr = new float[n][m];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                arr[i][j] = arr1[i][j] - arr2[i][j];
            }
        }
        return arr;
    }

    public static void multiplyByNumInPlace(float[][] arr, float num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = arr[i][j] * num;
            }
        }
    }

    public static float[][] multiplyByNum(float[][] arr, float num) {
        int n = arr.length;
        int m = arr[0].length;
        float[][] res = new float[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[i][j] = arr[i][j] * num;
            }
        }
        return res;
    }

    public static void divideByNumInPlace(float[][] arr, float num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = arr[i][j] / num;
            }
        }
    }

    public static float[][] divideByNum(float[][] arr, float num) {
        int n = arr.length;
        int m = arr[0].length;
        float[][] res = new float[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[i][j] = arr[i][j] / num;
            }
        }
        return res;
    }

    public static float scalarArrayProduct(float[][] arr1, float[][] arr2) {
        float res = 0;
        for (int i = 0; i < arr1.length; i++) {
            res += (arr1[i][0] * arr2[i][0]);
        }
        return res;
    }

    public static void checkArray(float[][] matrix, int rows, int cols) {
        if (matrix.length != rows || matrix[0].length != cols) {
            throw new IllegalArgumentException("Неправильные размеры матрицы");
        }
    }
}
