package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Matrix3f;
import io.github.artemboldirew.vector3d.core.Vector3f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix3fTest {

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

    // ==================== Конструктор и setMatrix ====================

    @Test
    void testConstructorWithValidMatrix() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(input);
        float[][] result = matrix.getMatrix();

        assertArrayEquals(input, result);
        // Проверяем, что это копия, а не ссылка
        input[0][0] = 999.0f;
        assertNotEquals(input[0][0], result[0][0]);
    }

    @Test
    void testConstructorWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Matrix3f(null));
    }

    @Test
    void testConstructorWithInvalidRowsThrowsException() {
        float[][] invalidRows = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f}
                // Не хватает 3-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix3f(invalidRows));
    }

    @Test
    void testConstructorWithInvalidColumnsThrowsException() {
        float[][] invalidCols = {
                {1.0f, 2.0f},
                {4.0f, 5.0f},
                {7.0f, 8.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix3f(invalidCols));
    }

    // ==================== Транспонирование ====================

    @Test
    void testTranspose() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] expected = {
                {1.0f, 4.0f, 7.0f},
                {2.0f, 5.0f, 8.0f},
                {3.0f, 6.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(input);
        Matrix3f transposed = matrix.transpose();

        assertArrayEquals(expected, transposed.getMatrix());
        // Проверяем, что исходная матрица не изменилась
        assertArrayEquals(input, matrix.getMatrix());
    }

    @Test
    void testTransposeInPlace() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] expected = {
                {1.0f, 4.0f, 7.0f},
                {2.0f, 5.0f, 8.0f},
                {3.0f, 6.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(input);
        Matrix3f result = matrix.transposeInPlace();

        assertArrayEquals(expected, matrix.getMatrix());
        assertSame(matrix, result); // Проверяем, что возвращается this
    }

    @Test
    void testTransposeTwiceReturnsOriginal() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(input);
        Matrix3f transposedTwice = matrix.transpose().transpose();

        assertArrayEquals(input, transposedTwice.getMatrix());
    }

    // ==================== Умножение матриц ====================

    @Test
    void testMultiplication() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 2.0f}
        };

        float[][] expected = {
                {2.0f, 4.0f, 6.0f},
                {8.0f, 10.0f, 12.0f},
                {14.0f, 16.0f, 18.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.mul(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testMultiplicationWithIdentity() {
        float[][] input = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(input);
        Matrix3f identity = Matrix3f.getE();
        Matrix3f result = matrix.mul(identity);

        assertFloatMatrixEquals(input, result.getMatrix());
    }

    @Test
    void testMultiplicationInPlace() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 2.0f}
        };

        float[][] expected = {
                {2.0f, 4.0f, 6.0f},
                {8.0f, 10.0f, 12.0f},
                {14.0f, 16.0f, 18.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.mulInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result); // Проверяем, что возвращается this
    }

    // ==================== Умножение на вектор ====================

    @Test
    void testMultiplicationWithVector() {
        float[][] matrixData = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[] vectorData = {2.0f, 0.0f, 1.0f};

        Matrix3f matrix = new Matrix3f(matrixData);
        Vector3f vector = new Vector3f(vectorData);
        Vector3f result = matrix.mul(vector);

        // Вычисляем ожидаемый результат:
        // [1*2 + 2*0 + 3*1] = 2 + 0 + 3 = 5
        // [4*2 + 5*0 + 6*1] = 8 + 0 + 6 = 14
        // [7*2 + 8*0 + 9*1] = 14 + 0 + 9 = 23

        float[] expected = {5.0f, 14.0f, 23.0f};

        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationWithZeroVector() {
        float[][] matrixData = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[] zeroVector = {0.0f, 0.0f, 0.0f};

        Matrix3f matrix = new Matrix3f(matrixData);
        Vector3f vector = new Vector3f(zeroVector);
        Vector3f result = matrix.mul(vector);

        float[] expected = {0.0f, 0.0f, 0.0f};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationWithIdentityVector() {
        float[][] matrixData = {
                {1.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 1.0f}
        };

        float[] vectorData = {2.0f, 3.0f, 4.0f};

        Matrix3f matrix = new Matrix3f(matrixData); // Единичная матрица
        Vector3f vector = new Vector3f(vectorData);
        Vector3f result = matrix.mul(vector);

        // Единичная матрица * вектор = тот же вектор
        float[] expected = vectorData;
        assertArrayEquals(expected, result.getVector());
    }

    // ==================== Сложение матриц ====================

    @Test
    void testAddition() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f},
                {8.0f, 9.0f, 10.0f}
        };

        float[][] expected = {
                {3.0f, 5.0f, 7.0f},
                {9.0f, 11.0f, 13.0f},
                {15.0f, 17.0f, 19.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.add(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
        // Проверяем, что исходная матрица не изменилась
        assertArrayEquals(a, matrixA.getMatrix());
    }

    @Test
    void testAdditionInPlace() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f},
                {8.0f, 9.0f, 10.0f}
        };

        float[][] expected = {
                {3.0f, 5.0f, 7.0f},
                {9.0f, 11.0f, 13.0f},
                {15.0f, 17.0f, 19.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.addInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result);
    }

    @Test
    void testAdditionWithZeroMatrix() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f zeroMatrix = Matrix3f.getZ();
        Matrix3f result = matrixA.add(zeroMatrix);

        assertFloatMatrixEquals(a, result.getMatrix());
    }

    @Test
    void testAdditionCommutativity() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 3.0f, 4.0f},
                {5.0f, 6.0f, 7.0f},
                {8.0f, 9.0f, 10.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);

        Matrix3f resultAB = matrixA.add(matrixB);
        Matrix3f resultBA = matrixB.add(matrixA);

        assertFloatMatrixEquals(resultAB.getMatrix(), resultBA.getMatrix());
    }

    // ==================== Вычитание матриц ====================

    @Test
    void testSubtraction() {
        float[][] a = {
                {10.0f, 9.0f, 8.0f},
                {7.0f, 6.0f, 5.0f},
                {4.0f, 3.0f, 2.0f}
        };

        float[][] b = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] expected = {
                {9.0f, 7.0f, 5.0f},
                {3.0f, 1.0f, -1.0f},
                {-3.0f, -5.0f, -7.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.sub(matrixB);

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testSubtractionInPlace() {
        float[][] a = {
                {10.0f, 9.0f, 8.0f},
                {7.0f, 6.0f, 5.0f},
                {4.0f, 3.0f, 2.0f}
        };

        float[][] b = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] expected = {
                {9.0f, 7.0f, 5.0f},
                {3.0f, 1.0f, -1.0f},
                {-3.0f, -5.0f, -7.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f result = matrixA.subInPlace(matrixB);

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
        assertSame(matrixA, result);
    }

    @Test
    void testSubtractionSameMatrixGivesZero() {
        float[][] data = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(data);
        Matrix3f result = matrix.sub(matrix);
        Matrix3f zeroMatrix = Matrix3f.getZ();

        assertFloatMatrixEquals(zeroMatrix.getMatrix(), result.getMatrix());
    }

    // ==================== GetMatrix и SetMatrix ====================

    @Test
    void testGetMatrixReturnsCopy() {
        float[][] original = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(original);
        float[][] copy = matrix.getMatrix();

        // Изменяем копию
        copy[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, matrix.getMatrix()[0][0]);
    }

    @Test
    void testSetMatrixWithValidData() {
        Matrix3f matrix = Matrix3f.getZ();

        float[][] newData = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
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
        Matrix3f matrix = Matrix3f.getZ();
        assertThrows(NullPointerException.class, () -> matrix.setMatrix(null));
    }

    @Test
    void testSetMatrixWithInvalidDimensionsThrowsException() {
        Matrix3f matrix = Matrix3f.getZ();

        float[][] wrongRows = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f}
                // Не хватает 3-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> matrix.setMatrix(wrongRows));
    }

    // ==================== Специальные матрицы ====================

    @Test
    void testGetE() {
        Matrix3f identity = Matrix3f.getE();
        float[][] expected = {
                {1.0f, 0.0f, 0.0f},
                {0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 1.0f}
        };

        assertArrayEquals(expected, identity.getMatrix());
    }

    @Test
    void testGetZ() {
        Matrix3f zero = Matrix3f.getZ();
        float[][] expected = {
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, 0.0f}
        };

        assertArrayEquals(expected, zero.getMatrix());
    }

    // ==================== Комбинированные операции ====================

    @Test
    void testChainedOperations() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 2.0f}
        };

        float[][] c = {
                {1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);
        Matrix3f matrixC = new Matrix3f(c);

        // (A * B) + C
        Matrix3f result = matrixA.mul(matrixB).add(matrixC);

        // Ожидаем: (A * 2) + 1 для каждого элемента
        float[][] expected = {
                {3.0f, 5.0f, 7.0f},
                {9.0f, 11.0f, 13.0f},
                {15.0f, 17.0f, 19.0f}
        };

        assertFloatMatrixEquals(expected, result.getMatrix());
    }

    @Test
    void testInPlaceChainedOperations() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 2.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);

        // A *= B; A += A
        matrixA.mulInPlace(matrixB).addInPlace(matrixA);

        // Ожидаем: A * 2 + A * 2 = A * 4
        float[][] expected = {
                {4.0f, 8.0f, 12.0f},
                {16.0f, 20.0f, 24.0f},
                {28.0f, 32.0f, 36.0f}
        };

        assertFloatMatrixEquals(expected, matrixA.getMatrix());
    }

    // ==================== Граничные случаи ====================

    @Test
    void testOperationsWithNegativeValues() {
        float[][] a = {
                {-1.0f, -2.0f, -3.0f},
                {-4.0f, -5.0f, -6.0f},
                {-7.0f, -8.0f, -9.0f}
        };

        float[][] b = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);

        // A + B должно дать нулевую матрицу
        Matrix3f sum = matrixA.add(matrixB);
        Matrix3f zero = Matrix3f.getZ();
        assertFloatMatrixEquals(zero.getMatrix(), sum.getMatrix());
    }

    @Test
    void testChainOfOperations() {
        float[][] a = {
                {1.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 3.0f}
        };

        float[][] b = {
                {4.0f, 0.0f, 0.0f},
                {0.0f, 5.0f, 0.0f},
                {0.0f, 0.0f, 6.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);

        // Создаем копию A для in-place операций
        Matrix3f matrixACopy = new Matrix3f(a);

        // Функциональный стиль
        Matrix3f functionalResult = matrixA
                .mul(matrixB)
                .transpose()
                .add(matrixA);

        // In-place стиль
        Matrix3f inPlaceResult = matrixACopy
                .mulInPlace(matrixB)
                .transposeInPlace()
                .addInPlace(new Matrix3f(a));

        // Результаты должны быть одинаковыми
        assertFloatMatrixEquals(functionalResult.getMatrix(), inPlaceResult.getMatrix());
    }

    @Test
    void testMatrixVectorChain() {
        float[][] matrixData = {
                {1.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 3.0f}
        };

        float[] vectorData = {1.0f, 2.0f, 3.0f};

        Matrix3f matrix = new Matrix3f(matrixData);
        Vector3f vector = new Vector3f(vectorData);

        // Умножаем матрицу на вектор, потом транспонируем матрицу и снова умножаем
        Vector3f result1 = matrix.mul(vector);

        matrix.transposeInPlace();
        Vector3f result2 = matrix.mul(vector);

        // Для диагональной матрицы транспонирование не меняет матрицу
        // Поэтому результаты должны быть одинаковыми
        assertArrayEquals(result1.getVector(), result2.getVector());
    }

    // ==================== Тесты на неизменяемость ====================

    @Test
    void testImmutabilityOfFunctionalMethods() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        float[][] b = {
                {2.0f, 0.0f, 0.0f},
                {0.0f, 2.0f, 0.0f},
                {0.0f, 0.0f, 2.0f}
        };

        Matrix3f matrixA = new Matrix3f(a);
        Matrix3f matrixB = new Matrix3f(b);

        // Выполняем операцию
        Matrix3f result = matrixA.add(matrixB);

        // Проверяем, что matrixA не изменился
        assertFloatMatrixEquals(a, matrixA.getMatrix());

        // Проверяем, что matrixB не изменился
        assertFloatMatrixEquals(b, matrixB.getMatrix());
    }

    @Test
    void testReturnNewObjectForFunctionalMethods() {
        float[][] a = {
                {1.0f, 2.0f, 3.0f},
                {4.0f, 5.0f, 6.0f},
                {7.0f, 8.0f, 9.0f}
        };

        Matrix3f matrix = new Matrix3f(a);
        Matrix3f result = matrix.transpose();

        // Проверяем, что это разные объекты
        assertNotSame(matrix, result);

        // Проверяем, что результат - новая матрица
        assertNotNull(result);

        // Проверяем, что у них разные данные в памяти
        float[][] originalData = matrix.getMatrix();
        float[][] resultData = result.getMatrix();

        // Меняем данные в результате
        resultData[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, originalData[0][0]);
    }
}