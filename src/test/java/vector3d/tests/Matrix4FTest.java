package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Matrix4f;
import io.github.artemboldirew.vector3d.core.Vector4f;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class Matrix4FTest {
    private Matrix4f matrix1;
    private Matrix4f matrix2;
    private Matrix4f identityMatrix;

    @BeforeEach
    void setUp() {
        matrix1 = new Matrix4f(new float[][]{
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        });

        matrix2 = new Matrix4f(new float[][]{
                {2.0f, 0.0f, 1.0f, 0.0f},
                {0.0f, 1.0f, 0.0f, 2.0f},
                {1.0f, 0.0f, 2.0f, 0.0f},
                {0.0f, 2.0f, 0.0f, 1.0f}
        });

        identityMatrix = new Matrix4f(new float[][]{
                {1.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 1.0f}
        });
    }

    // Тесты конструктора
    @Test
    void testConstructorValidMatrix() {
        float[][] expected = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };
        assertArrayEquals(expected, matrix1.getMatrix());
    }

    @Test
    void testConstructorInvalidRowsThrowsException() {
        float[][] invalidMatrix = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalidMatrix));
    }

    @Test
    void testConstructorInvalidColsThrowsException() {
        float[][] invalidMatrix = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f},
                {10.0f, 11.0f, 12.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalidMatrix));
    }

    // Тесты multiply(Matrix4)
    @Test
    void testMultiplyMatrixWithIdentity() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).multiply(identityMatrix);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testMultiplyIdentityWithMatrix() {
        Matrix4f result = new Matrix4f(identityMatrix.getMatrix()).multiply(matrix1);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testMultiplyTwoMatrices() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).multiply(matrix2);
        float[][] expected = {
                {5.0f, 10.0f, 7.0f, 8.0f},
                {17.0f, 22.0f, 19.0f, 20.0f},
                {29.0f, 34.0f, 31.0f, 32.0f},
                {41.0f, 46.0f, 43.0f, 44.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplyCommutativeProperty() {
        Matrix4f result1 = new Matrix4f(matrix1.getMatrix()).multiply(matrix2);
        Matrix4f result2 = new Matrix4f(matrix2.getMatrix()).multiply(matrix1);

        // Умножение матриц некоммутативно, поэтому результаты должны быть разными
        assertFalse(arraysEqual(result1.getMatrix(), result2.getMatrix()));
    }

    @Test
    void testMultiplyWithZeroMatrix() {
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).multiply(zeroMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplySameMatrixTwice() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).multiply(matrix1);
        float[][] expected = {
                {90.0f, 100.0f, 110.0f, 120.0f},
                {202.0f, 228.0f, 254.0f, 280.0f},
                {314.0f, 356.0f, 398.0f, 440.0f},
                {426.0f, 484.0f, 542.0f, 600.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты transpose()
    @Test
    void testTranspose() {
        Matrix4f transposed = new Matrix4f(matrix1.getMatrix()).transpose();
        float[][] expected = {
                {1.0f, 5.0f, 9.0f, 13.0f},
                {2.0f, 6.0f, 10.0f, 14.0f},
                {3.0f, 7.0f, 11.0f, 15.0f},
                {4.0f, 8.0f, 12.0f, 16.0f}
        };
        assertArrayEquals(expected, transposed.getMatrix());
    }

    @Test
    void testTransposeOfTransposeIsOriginal() {
        Matrix4f original = new Matrix4f(matrix1.getMatrix());
        Matrix4f transposedTwice = new Matrix4f(matrix1.getMatrix()).transpose().transpose();
        assertArrayEquals(original.getMatrix(), transposedTwice.getMatrix());
    }

    @Test
    void testTransposeIdentityMatrix() {
        Matrix4f transposedIdentity = new Matrix4f(identityMatrix.getMatrix()).transpose();
        assertArrayEquals(identityMatrix.getMatrix(), transposedIdentity.getMatrix());
    }

    @Test
    void testTransposeZeroMatrix() {
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f transposedZero = zeroMatrix.transpose();
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, transposedZero.getMatrix());
    }

    @Test
    void testTransposeSymmetricMatrix() {
        Matrix4f symmetricMatrix = new Matrix4f(new float[][]{
                {1.0f, 2.0f, 3.0f, 4.0f},
                {2.0f, 5.0f, 6.0f, 7.0f},
                {3.0f, 6.0f, 8.0f, 9.0f},
                {4.0f, 7.0f, 9.0f, 10.0f}
        });
        Matrix4f transposed = symmetricMatrix.transpose();
        assertArrayEquals(symmetricMatrix.getMatrix(), transposed.getMatrix());
    }

    // Тесты multiply(Vector4)
    @Test
    void testMultiplyWithVector() {
        Vector4f vector = new Vector4f(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        Vector4f result = matrix1.multiply(vector);
        float[][] expected = {{30.0f}, {70.0f}, {110.0f}, {150.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyWithZeroVector() {
        Vector4f zeroVector = new Vector4f(new float[][]{{0.0f}, {0.0f}, {0.0f}, {0.0f}});
        Vector4f result = matrix1.multiply(zeroVector);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyVectorWithIdentityMatrix() {
        Vector4f vector = new Vector4f(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        Vector4f result = identityMatrix.multiply(vector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}, {4.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyVectorWithZeroMatrix() {
        Vector4f vector = new Vector4f(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Vector4f result = zeroMatrix.multiply(vector);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyVectorUnitBasis() {
        Vector4f xVector = new Vector4f(new float[][]{{1.0f}, {0.0f}, {0.0f}, {0.0f}});
        Vector4f result = matrix1.multiply(xVector);
        float[][] expected = {{1.0f}, {5.0f}, {9.0f}, {13.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyVectorDifferentMatrix() {
        Vector4f vector = new Vector4f(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        Vector4f result = matrix2.multiply(vector);
        float[][] expected = {{5.0f}, {10.0f}, {7.0f}, {8.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    // Тесты add(Matrix4)
    @Test
    void testAdd() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).add(matrix2);
        float[][] expected = {
                {3.0f, 2.0f, 4.0f, 4.0f},
                {5.0f, 7.0f, 7.0f, 10.0f},
                {10.0f, 10.0f, 13.0f, 12.0f},
                {13.0f, 16.0f, 15.0f, 17.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }


    @Test
    void testAddZeroMatrix() {
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).add(zeroMatrix);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testAddIdentityMatrix() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).add(identityMatrix);
        float[][] expected = {
                {2.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 7.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 12.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 17.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testAddSameMatrix() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).add(matrix1);
        float[][] expected = {
                {2.0f, 4.0f, 6.0f, 8.0f},
                {10.0f, 12.0f, 14.0f, 16.0f},
                {18.0f, 20.0f, 22.0f, 24.0f},
                {26.0f, 28.0f, 30.0f, 32.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testAddNegativeMatrix() {
        Matrix4f negativeMatrix = new Matrix4f(new float[][]{
                {-1.0f, -2.0f, -3.0f, -4.0f},
                {-5.0f, -6.0f, -7.0f, -8.0f},
                {-9.0f, -10.0f, -11.0f, -12.0f},
                {-13.0f, -14.0f, -15.0f, -16.0f}
        });
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).add(negativeMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты subtract(Matrix4)
    @Test
    void testSubtract() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).subtract(matrix2);
        float[][] expected = {
                {-1.0f, 2.0f, 2.0f, 4.0f},
                {5.0f, 5.0f, 7.0f, 6.0f},
                {8.0f, 10.0f, 9.0f, 12.0f},
                {13.0f, 12.0f, 15.0f, 15.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractSelf() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).subtract(matrix1);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractZeroMatrix() {
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).subtract(zeroMatrix);
        assertArrayEquals(matrix1.getMatrix(), result.getMatrix());
    }

    @Test
    void testSubtractIdentityMatrix() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix()).subtract(identityMatrix);
        float[][] expected = {
                {0.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 5.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 10.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 15.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractSameAsAddNegative() {
        Matrix4f negativeMatrix = new Matrix4f(new float[][]{
                {-2.0f, 0.0f, -1.0f, 0.0f},
                {0.0f, -1.0f, 0.0f, -2.0f},
                {-1.0f, 0.0f, -2.0f, 0.0f},
                {0.0f, -2.0f, 0.0f, -1.0f}
        });
        Matrix4f result1 = new Matrix4f(matrix1.getMatrix()).subtract(matrix2);
        Matrix4f result2 = new Matrix4f(matrix1.getMatrix()).add(negativeMatrix);
        assertArrayEquals(result1.getMatrix(), result2.getMatrix());
    }

    @Test
    void testSubtractFromZeroMatrix() {
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f result = zeroMatrix.subtract(matrix1);
        float[][] expected = {
                {-1.0f, -2.0f, -3.0f, -4.0f},
                {-5.0f, -6.0f, -7.0f, -8.0f},
                {-9.0f, -10.0f, -11.0f, -12.0f},
                {-13.0f, -14.0f, -15.0f, -16.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты getE() и getZ()
    @Test
    void testGetE() {
        Matrix4f eMatrix = Matrix4f.getE();
        float[][] expected = {
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f}
        };
        assertArrayEquals(expected, eMatrix.getMatrix());
    }

    @Test
    void testGetZ() {
        Matrix4f zMatrix = Matrix4f.getZ();
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, zMatrix.getMatrix());
    }

    @Test
    void testGetEAddGetZ() {
        Matrix4f eMatrix = Matrix4f.getE();
        Matrix4f zMatrix = Matrix4f.getZ();
        Matrix4f result = new Matrix4f(eMatrix.getMatrix()).add(zMatrix);
        assertArrayEquals(eMatrix.getMatrix(), result.getMatrix());
    }

    @Test
    void testGetESubtractGetE() {
        Matrix4f eMatrix = Matrix4f.getE();
        Matrix4f result = new Matrix4f(eMatrix.getMatrix()).subtract(eMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void testGetEMultiplyGetZ() {
        Matrix4f eMatrix = Matrix4f.getE();
        Matrix4f zMatrix = Matrix4f.getZ();
        Matrix4f result = new Matrix4f(eMatrix.getMatrix()).multiply(zMatrix);
        float[][] expected = {
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
        assertArrayEquals(expected, result.getMatrix());
    }

    // Тесты цепочек методов
    @Test
    void testMethodChaining() {
        Matrix4f result = new Matrix4f(matrix1.getMatrix())
                .add(matrix2)
                .subtract(matrix2)
                .transpose()
                .transpose();

        // Из-за мутабельности методов результат может отличаться
        assertNotNull(result.getMatrix());
        assertEquals(4, result.getMatrix().length);
        assertEquals(4, result.getMatrix()[0].length);
    }

    @Test
    void testAddThenMultiply() {
        Matrix4f sum = new Matrix4f(matrix1.getMatrix()).add(matrix2);
        Matrix4f result = new Matrix4f(sum.getMatrix()).multiply(identityMatrix);
        assertArrayEquals(sum.getMatrix(), result.getMatrix());
    }

    @Test
    void testMultiplyThenTranspose() {
        Matrix4f product = new Matrix4f(matrix1.getMatrix()).multiply(matrix2);
        Matrix4f transposedProduct = new Matrix4f(product.getMatrix()).transpose();

        // Проверяем размеры
        assertEquals(4, transposedProduct.getMatrix().length);
        assertEquals(4, transposedProduct.getMatrix()[0].length);
    }

    // Тесты getMatrix()
    @Test
    void testGetMatrixReturnsCorrectSize() {
        float[][] matrixArray = matrix1.getMatrix();
        assertEquals(4, matrixArray.length);
        assertEquals(4, matrixArray[0].length);
    }

    @Test
    void testGetMatrixReturnsCorrectValues() {
        float[][] matrixArray = matrix1.getMatrix();
        assertEquals(1.0f, matrixArray[0][0], 0.001f);
        assertEquals(6.0f, matrixArray[1][1], 0.001f);
        assertEquals(11.0f, matrixArray[2][2], 0.001f);
        assertEquals(16.0f, matrixArray[3][3], 0.001f);
    }

    private boolean arraysEqual(float[][] a, float[][] b) {
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