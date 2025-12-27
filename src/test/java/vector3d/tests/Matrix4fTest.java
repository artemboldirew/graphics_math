package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Matrix4f;
import io.github.artemboldirew.vector3d.core.Vector4f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix4fTest {

    private final float DELTA = 0.0001f;
    private void assertMatrixEquals(float[][] expected, float[][] actual, float delta) {
        assertNotNull(expected, "Ожидаемая матрица не должна быть null");
        assertNotNull(actual, "Актуальная матрица не должна быть null");
        assertEquals(expected.length, actual.length, "Количество строк не совпадает");

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], delta,
                    String.format("Строка %d не совпадает", i));
        }
    }

    private void assertFloatMatrixEquals(float[][] expected, float[][] actual) {
        assertMatrixEquals(expected, actual, DELTA);
    }

    @Test
    void testConstructorWithValidMatrix() {
        //Проверка, что копируется а не ссылается
        float[][] input = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(input);
        float[][] result = matrix.getMatrix();

        assertArrayEquals(input, result);

        input[0][0] = 999.0f;
        assertNotEquals(input[0][0], result[0][0]);
    }

    @Test
    void testConstructorWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Matrix4f(null));
    }

    @Test
    void testConstructorWithInvalidRowsThrowsException() {
        float[][] invalidRows = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f}
                // Не хватает 4-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalidRows));
    }

    @Test
    void testConstructorWithInvalidColumnsThrowsException() {
        float[][] invalidCols = {
                {1.0f, 2.0f, 3.0f}, // Только 3 колонки
                {5.0f, 6.0f, 7.0f},
                {9.0f, 10.0f, 11.0f},
                {13.0f, 14.0f, 15.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix4f(invalidCols));
    }

    // ==================== Транспонирование ====================

    @Test
    void testTranspose() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] expected = {
                {1.0f, 5.0f, 9.0f, 13.0f},
                {2.0f, 6.0f, 10.0f, 14.0f},
                {3.0f, 7.0f, 11.0f, 15.0f},
                {4.0f, 8.0f, 12.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(input);
        Matrix4f transposed = matrix.transpose();

        assertArrayEquals(expected, transposed.getMatrix());
        // Проверяем, что исходная матрица не изменилась
        assertArrayEquals(input, matrix.getMatrix());
    }

    @Test
    void testTransposeInPlace() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] expected = {
                {1.0f, 5.0f, 9.0f, 13.0f},
                {2.0f, 6.0f, 10.0f, 14.0f},
                {3.0f, 7.0f, 11.0f, 15.0f},
                {4.0f, 8.0f, 12.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(input);
        Matrix4f result = matrix.transposeInPlace();

        assertArrayEquals(expected, matrix.getMatrix());
        assertSame(matrix, result); // Проверяем, что возвращается this
    }

    @Test
    void testTransposeTwiceReturnsOriginal() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(input);
        Matrix4f transposedTwice = matrix.transpose().transpose();

        assertArrayEquals(input, transposedTwice.getMatrix());
    }

    // ==================== Умножение матриц ====================

    @Test
    void testMultiplication() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 2.0f}
        };

        float[][] expected = {
                {2.0f, 4.0f, 6.0f, 8.0f},
                {10.0f, 12.0f, 14.0f, 16.0f},
                {18.0f, 20.0f, 22.0f, 24.0f},
                {26.0f, 28.0f, 30.0f, 32.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.mul(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplicationWithIdentity() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(input);
        Matrix4f identity = Matrix4f.getE();
        Matrix4f result = matrix.mul(identity);

        assertFloatMatrixEquals(input, result.getMatrix());
    }

    @Test
    void testMultiplicationInPlace() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 2.0f}
        };

        float[][] expected = {
                {2.0f, 4.0f, 6.0f, 8.0f},
                {10.0f, 12.0f, 14.0f, 16.0f},
                {18.0f, 20.0f, 22.0f, 24.0f},
                {26.0f, 28.0f, 30.0f, 32.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.mulInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result); // Проверяем, что возвращается this
    }

    @Test
    void testMulMethodHasBug() {
        // В текущей реализации есть ошибка: mul использует mat.matrix дважды
        float[][] a = {
                {1.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 1.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 3.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 4.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 5.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);

        // Ожидаем умножение a на b, но в текущем коде будет b на b
        Matrix4f result = matrixA.mul(matrixB);

        // Этот тест упадет, показывая ошибку в реализации
        float[][] expected = b; // a * E = a, но E * b = b
        // На самом деле должно быть a * b = b
        assertFloatMatrixEquals(b, result.getMatrix());
    }

    // ==================== Умножение на вектор ====================

    @Test
    void testMultiplicationWithVector() {
        float[][] matrixData = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[] vectorData = {2.0f, 0.0f, 1.0f, 3.0f};

        Matrix4f matrix = new Matrix4f(matrixData);
        Vector4f vector = new Vector4f(vectorData);
        Vector4f result = matrix.mul(vector);

        // Вычисляем ожидаемый результат:
        // [1*2 + 2*0 + 3*1 + 4*3] = 2 + 0 + 3 + 12 = 17
        // [5*2 + 6*0 + 7*1 + 8*3] = 10 + 0 + 7 + 24 = 41
        // [9*2 + 10*0 + 11*1 + 12*3] = 18 + 0 + 11 + 36 = 65
        // [13*2 + 14*0 + 15*1 + 16*3] = 26 + 0 + 15 + 48 = 89

        float[] expected = {17.0f, 41.0f, 65.0f, 89.0f};

        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationWithZeroVector() {
        float[][] matrixData = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[] zeroVector = {0.0f, 0.0f, 0.0f, 0.0f};

        Matrix4f matrix = new Matrix4f(matrixData);
        Vector4f vector = new Vector4f(zeroVector);
        Vector4f result = matrix.mul(vector);

        float[] expected = {0.0f, 0.0f, 0.0f, 0.0f};
        assertArrayEquals(expected, result.getVector());
    }

    // ==================== Сложение матриц ====================

    @Test
    void testAddition() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 3.0f, 4.0f, 5.0f},
                {6.0f, 7.0f, 8.0f, 9.0f},
                {10.0f, 11.0f, 12.0f, 13.0f},
                {14.0f, 15.0f, 16.0f, 17.0f}
        };

        float[][] expected = {
                {3.0f, 5.0f, 7.0f, 9.0f},
                {11.0f, 13.0f, 15.0f, 17.0f},
                {19.0f, 21.0f, 23.0f, 25.0f},
                {27.0f, 29.0f, 31.0f, 33.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.add(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
        // Проверяем, что исходная матрица не изменилась
        assertArrayEquals(a, matrixA.getMatrix());
    }

    @Test
    void testAdditionInPlace() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 3.0f, 4.0f, 5.0f},
                {6.0f, 7.0f, 8.0f, 9.0f},
                {10.0f, 11.0f, 12.0f, 13.0f},
                {14.0f, 15.0f, 16.0f, 17.0f}
        };

        float[][] expected = {
                {3.0f, 5.0f, 7.0f, 9.0f},
                {11.0f, 13.0f, 15.0f, 17.0f},
                {19.0f, 21.0f, 23.0f, 25.0f},
                {27.0f, 29.0f, 31.0f, 33.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.addInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result);
    }

    @Test
    void testAdditionWithZeroMatrix() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f zeroMatrix = Matrix4f.getZ();
        Matrix4f result = matrixA.add(zeroMatrix);

        assertFloatMatrixEquals(a, result.getMatrix());
    }

    // ==================== Вычитание матриц ====================

    @Test
    void testSubtraction() {
        float[][] a = {
                {10.0f, 9.0f, 8.0f, 7.0f},
                {6.0f, 5.0f, 4.0f, 3.0f},
                {2.0f, 1.0f, 0.0f, -1.0f},
                {-2.0f, -3.0f, -4.0f, -5.0f}
        };

        float[][] b = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] expected = {
                {9.0f, 7.0f, 5.0f, 3.0f},
                {1.0f, -1.0f, -3.0f, -5.0f},
                {-7.0f, -9.0f, -11.0f, -13.0f},
                {-15.0f, -17.0f, -19.0f, -21.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.sub(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractionInPlace() {
        float[][] a = {
                {10.0f, 9.0f, 8.0f, 7.0f},
                {6.0f, 5.0f, 4.0f, 3.0f},
                {2.0f, 1.0f, 0.0f, -1.0f},
                {-2.0f, -3.0f, -4.0f, -5.0f}
        };

        float[][] b = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] expected = {
                {9.0f, 7.0f, 5.0f, 3.0f},
                {1.0f, -1.0f, -3.0f, -5.0f},
                {-7.0f, -9.0f, -11.0f, -13.0f},
                {-15.0f, -17.0f, -19.0f, -21.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f result = matrixA.subInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result);
    }

    @Test
    void testSubtractionSameMatrixGivesZero() {
        float[][] data = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(data);
        Matrix4f result = matrix.sub(matrix);
        Matrix4f zeroMatrix = Matrix4f.getZ();

        assertFloatMatrixEquals(zeroMatrix.getMatrix(), result.getMatrix());
    }

    // ==================== GetMatrix и SetMatrix ====================

    @Test
    void testGetMatrixReturnsCopy() {
        float[][] original = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        Matrix4f matrix = new Matrix4f(original);
        float[][] copy = matrix.getMatrix();

        // Изменяем копию
        copy[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, matrix.getMatrix()[0][0]);
    }

    @Test
    void testSetMatrixWithValidData() {
        Matrix4f matrix = Matrix4f.getZ();

        float[][] newData = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        matrix.setMatrix(newData);

        // Проверяем, что матрица установилась
        assertArrayEquals(newData, matrix.getMatrix());

        // Проверяем, что изменения в исходном массиве не влияют на матрицу
        newData[0][0] = 999.0f;
        assertNotEquals(999.0f, matrix.getMatrix()[0][0]);
    }

    @Test
    void testSetMatrixWithNullThrowsException() {
        Matrix4f matrix = Matrix4f.getZ();
        assertThrows(NullPointerException.class, () -> matrix.setMatrix(null));
    }

    @Test
    void testSetMatrixWithInvalidDimensionsThrowsException() {
        Matrix4f matrix = Matrix4f.getZ();

        float[][] wrongRows = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f}
                // Не хватает 4-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> matrix.setMatrix(wrongRows));
    }

    // ==================== Комбинированные операции ====================

    @Test
    void testChainedOperations() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 2.0f}
        };

        float[][] c = {
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);
        Matrix4f matrixC = new Matrix4f(c);

        // (A * B) + C
        Matrix4f result = matrixA.mul(matrixB).add(matrixC);

        // Ожидаем: (A * 2) + 1 для каждого элемента
        float[][] expected = {
                {3.0f, 5.0f, 7.0f, 9.0f},
                {11.0f, 13.0f, 15.0f, 17.0f},
                {19.0f, 21.0f, 23.0f, 25.0f},
                {27.0f, 29.0f, 31.0f, 33.0f}
        };

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testInPlaceChainedOperations() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f, 8.0f},
                {9.0f, 10.0f, 11.0f, 12.0f},
                {13.0f, 14.0f, 15.0f, 16.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 2.0f}
        };

        Matrix4f matrixA = new Matrix4f(a);
        Matrix4f matrixB = new Matrix4f(b);

        // A *= B; A += A
        matrixA.mulInPlace(matrixB).addInPlace(matrixA);

        // Ожидаем: A * 2 + A * 2 = A * 4
        float[][] expected = {
                {4.0f, 8.0f, 12.0f, 16.0f},
                {20.0f, 24.0f, 28.0f, 32.0f},
                {36.0f, 40.0f, 44.0f, 48.0f},
                {52.0f, 56.0f, 60.0f, 64.0f}
        };

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
    }
}