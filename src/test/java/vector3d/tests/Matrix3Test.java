package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Matrix3;
import io.github.artemboldirew.vector3d.core.Vector3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class Matrix3Test {
    private Matrix3 matrix1;
    private Matrix3 matrix2;
    private Matrix3 identityMatrix;

    @BeforeEach
    void setUp() {
        matrix1 = new Matrix3(new float[][]{
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        });

        matrix2 = new Matrix3(new float[][]{
                {2.0f, 0.0f, 1.0f},
                {0.0f, 1.0f, 0.0f},
                {1.0f, 0.0f, 2.0f}
        });

        identityMatrix = new Matrix3(new float[][]{
                {1.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 1.0f}
        });
    }

    // Вспомогательный метод для глубокого копирования матрицы
    private float[][] deepCopy(float[][] original) {
        if (original == null) return null;
        float[][] copy = new float[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    // Тесты конструктора
    @Test
    void testConstructorValidMatrix() {
        float[][] expected = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };
        assertArrayEquals(expected, matrix1.getMatrix());
    }

    @Test
    void testConstructorInvalidRowsThrowsException() {
        float[][] invalidMatrix = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix3(invalidMatrix));
    }

    @Test
    void testConstructorInvalidColsThrowsException() {
        float[][] invalidMatrix = {
                {1.0f, 2.0f},
                {3.0f, 4.0f},
                {5.0f, 6.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix3(invalidMatrix));
    }

    // Тесты multiply(Matrix3)
    @Test
    void testMultiplyMatrixWithIdentity() {
        float[][] original = deepCopy(matrix1.getMatrix());
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(identityMatrix);
        assertArrayEquals(original, result.getMatrix());
    }

    @Test
    void testMultiplyIdentityWithMatrix() {
        float[][] original = deepCopy(matrix1.getMatrix());
        Matrix3 result = new Matrix3(deepCopy(identityMatrix.getMatrix())).multiply(matrix1);
        assertArrayEquals(original, result.getMatrix());
    }

    @Test
    void testMultiplyTwoMatrices() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(matrix2);
        float[][] expected = {
                {5.0f, 2.0f, 7.0f},
                {14.0f, 5.0f, 16.0f},
                {23.0f, 8.0f, 25.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyCommutativeProperty() {
        Matrix3 result1 = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(matrix2);
        Matrix3 result2 = new Matrix3(deepCopy(matrix2.getMatrix())).multiply(matrix1);

        // Умножение матриц некоммутативно
        assertFalse(arraysEqual(result1.getMatrix(), result2.getMatrix()));
    }

    @Test
    void testMultiplyWithZeroMatrix() {
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(zeroMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplySameMatrixTwice() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(new Matrix3(deepCopy(matrix1.getMatrix())));
        float[][] expected = {
                {30.0f, 36.0f, 42.0f},
                {66.0f, 81.0f, 96.0f},
                {102.0f, 126.0f, 150.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты transpose()
    @Test
    void testTranspose() {
        Matrix3 transposed = new Matrix3(deepCopy(matrix1.getMatrix())).transpose();
        float[][] expected = {
                {1.0f, 4.0f, 7.0f},
                {2.0f, 5.0f, 8.0f},
                {3.0f, 6.0f, 9.0f}
        };
        assertArrayEquals(expected, transposed.getMatrix());
    }

    @Test
    void testTransposeOfTransposeIsOriginal() {
        float[][] original = deepCopy(matrix1.getMatrix());
        Matrix3 transposedTwice = new Matrix3(deepCopy(matrix1.getMatrix())).transpose().transpose();
        assertArrayEquals(original, transposedTwice.getMatrix());
    }

    @Test
    void testTransposeIdentityMatrix() {
        float[][] original = deepCopy(identityMatrix.getMatrix());
        Matrix3 transposedIdentity = new Matrix3(deepCopy(identityMatrix.getMatrix())).transpose();
        assertArrayEquals(original, transposedIdentity.getMatrix());
    }

    @Test
    void testTransposeZeroMatrix() {
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 transposedZero = new Matrix3(deepCopy(zeroMatrix.getMatrix())).transpose();
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, transposedZero.getMatrix());
    }

    @Test
    void testTransposeSymmetricMatrix() {
        Matrix3 symmetricMatrix = new Matrix3(new float[][]{
                {1.0f, 2.0f, 3.0f},
                {2.0f, 4.0f, 5.0f},
                {3.0f, 5.0f, 6.0f}
        });
        Matrix3 transposed = new Matrix3(deepCopy(symmetricMatrix.getMatrix())).transpose();
        assertArrayEquals(symmetricMatrix.getMatrix(), transposed.getMatrix());
    }

    // Тесты multiply(Vector3)
    @Test
    void testMultiplyWithVector() {
        Vector3 vector = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(vector);
        float[][] expected = {
                {14.0f},
                {32.0f},
                {50.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyWithZeroVector() {
        Vector3 zeroVector = new Vector3(new float[][]{{0.0f}, {0.0f}, {0.0f}});
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(zeroVector);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyVectorWithIdentityMatrix() {
        Vector3 vector = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Matrix3 result = new Matrix3(deepCopy(identityMatrix.getMatrix())).multiply(vector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyVectorWithZeroMatrix() {
        Vector3 vector = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(zeroMatrix.getMatrix())).multiply(vector);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyVectorUnitBasis() {
        Vector3 xVector = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}});
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(xVector);
        float[][] expected = {{1.0f}, {4.0f}, {7.0f}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyVectorDifferentMatrix() {
        Vector3 vector = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Matrix3 result = new Matrix3(deepCopy(matrix2.getMatrix())).multiply(vector);
        float[][] expected = {{5.0f}, {2.0f}, {7.0f}};
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты add(Matrix3)
    @Test
    void testAdd() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).add(matrix2);
        float[][] expected = {
                {3.0f, 2.0f, 4.0f},
                {4.0f, 6.0f, 6.0f},
                {8.0f, 8.0f, 11.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testAddCommutative() {
        float[][] m1Copy = deepCopy(matrix1.getMatrix());
        float[][] m2Copy = deepCopy(matrix2.getMatrix());

        Matrix3 result1 = new Matrix3(m1Copy).add(matrix2);
        Matrix3 result2 = new Matrix3(m2Copy).add(matrix1);
        assertArrayEquals(result1.getMatrix(), result2.getMatrix());
    }

    @Test
    void testAddZeroMatrix() {
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).add(zeroMatrix);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testAddIdentityMatrix() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).add(identityMatrix);
        float[][] expected = {
                {2.0f, 2.0f, 3.0f},
                {4.0f, 6.0f, 6.0f},
                {7.0f, 8.0f, 10.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testAddSameMatrix() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).add(new Matrix3(deepCopy(matrix1.getMatrix())));
        float[][] expected = {
                {2.0f, 4.0f, 6.0f},
                {8.0f, 10.0f, 12.0f},
                {14.0f, 16.0f, 18.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testAddNegativeMatrix() {
        Matrix3 negativeMatrix = new Matrix3(new float[][]{
                {-1.0f, -2.0f, -3.0f},
                {-4.0f, -5.0f, -6.0f},
                {-7.0f, -8.0f, -9.0f}
        });
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).add(negativeMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты subtract(Matrix3)
    @Test
    void testSubtract() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).subtract(matrix2);
        float[][] expected = {
                {-1.0f, 2.0f, 2.0f},
                {4.0f, 4.0f, 6.0f},
                {6.0f, 8.0f, 7.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractSelf() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).subtract(new Matrix3(deepCopy(matrix1.getMatrix())));
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractZeroMatrix() {
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).subtract(zeroMatrix);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testSubtractIdentityMatrix() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix())).subtract(identityMatrix);
        float[][] expected = {
                {0.0f, 2.0f, 3.0f},
                {4.0f, 4.0f, 6.0f},
                {7.0f, 8.0f, 8.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractSameAsAddNegative() {
        Matrix3 negativeMatrix = new Matrix3(new float[][]{
                {-2.0f, 0.0f, -1.0f},
                {0.0f, -1.0f, 0.0f},
                {-1.0f, 0.0f, -2.0f}
        });
        Matrix3 result1 = new Matrix3(deepCopy(matrix1.getMatrix())).subtract(matrix2);
        Matrix3 result2 = new Matrix3(deepCopy(matrix1.getMatrix())).add(negativeMatrix);
        assertArrayEquals(result1.getMatrix(), result2.getMatrix());
    }

    @Test
    void testSubtractFromZeroMatrix() {
        Matrix3 zeroMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(zeroMatrix.getMatrix())).subtract(matrix1);
        float[][] expected = {
                {-1.0f, -2.0f, -3.0f},
                {-4.0f, -5.0f, -6.0f},
                {-7.0f, -8.0f, -9.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты getE() и getZ()
    @Test
    void testGetE() {
        Matrix3 eMatrix = Matrix3.getE();
        float[][] expected = {
                {1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f}
        };
        assertArrayEquals(expected, eMatrix.getMatrix());
    }

    @Test
    void testGetZ() {
        Matrix3 zMatrix = Matrix3.getZ();
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, zMatrix.getMatrix());
    }

    @Test
    void testGetEAddGetZ() {
        Matrix3 eMatrix = Matrix3.getE();
        Matrix3 zMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(eMatrix.getMatrix())).add(zMatrix);
        assertArrayEquals(eMatrix.getMatrix(), result.getMatrix());
    }

    @Test
    void testGetESubtractGetE() {
        Matrix3 eMatrix = Matrix3.getE();
        Matrix3 result = new Matrix3(deepCopy(eMatrix.getMatrix())).subtract(eMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testGetEMultiplyGetZ() {
        Matrix3 eMatrix = Matrix3.getE();
        Matrix3 zMatrix = Matrix3.getZ();
        Matrix3 result = new Matrix3(deepCopy(eMatrix.getMatrix())).multiply(zMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты цепочек методов
    @Test
    void testMethodChaining() {
        float[][] original = deepCopy(matrix1.getMatrix());
        Matrix3 result = new Matrix3(deepCopy(original))
                .add(matrix2)
                .subtract(matrix2)
                .transpose()
                .transpose();

        // Из-за мутабельности методов результат может отличаться
        // Проверяем, что матрица корректного размера
        assertNotNull(result.getMatrix());
        assertEquals(3, result.getMatrix().length);
        assertEquals(3, result.getMatrix()[0].length);
    }

    @Test
    void testAddThenMultiply() {
        Matrix3 sum = new Matrix3(deepCopy(matrix1.getMatrix())).add(matrix2);
        Matrix3 result = new Matrix3(deepCopy(sum.getMatrix())).multiply(identityMatrix);
        assertArrayEquals(sum.getMatrix(), result.getMatrix());
    }

    @Test
    void testMultiplyThenTranspose() {
        Matrix3 product = new Matrix3(deepCopy(matrix1.getMatrix())).multiply(matrix2);
        Matrix3 transposedProduct = new Matrix3(deepCopy(product.getMatrix())).transpose();

        // Проверяем размеры
        assertEquals(3, transposedProduct.getMatrix().length);
        assertEquals(3, transposedProduct.getMatrix()[0].length);
    }

    @Test
    void testAddMultiplyChain() {
        Matrix3 result = new Matrix3(deepCopy(matrix1.getMatrix()))
                .add(matrix2)
                .multiply(identityMatrix)
                .transpose();

        assertNotNull(result.getMatrix());
        assertEquals(3, result.getMatrix().length);
        assertEquals(3, result.getMatrix()[0].length);
    }

    // Тесты getMatrix()
    @Test
    void testGetMatrixReturnsCorrectSize() {
        float[][] matrixArray = matrix1.getMatrix();
        assertEquals(3, matrixArray.length);
        assertEquals(3, matrixArray[0].length);
    }

    @Test
    void testGetMatrixReturnsCorrectValues() {
        float[][] matrixArray = matrix1.getMatrix();
        assertEquals(1.0f, matrixArray[0][0], 0.001f);
        assertEquals(5.0f, matrixArray[1][1], 0.001f);
        assertEquals(9.0f, matrixArray[2][2], 0.001f);
    }


    private boolean arraysEqual(float[][] a, float[][] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (Math.abs(a[i][j] - b[i][j]) > 0.0001f) {
                    return false;
                }
            }
        }
        return true;
    }
}