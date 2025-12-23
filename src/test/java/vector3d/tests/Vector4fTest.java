package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector4f;
import io.github.artemboldirew.vector3d.core.Vector3f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector4fTest {

    private final float DELTA = 0.0001f;

    private void assertVectorEquals(float[][] expected, float[][] actual, float delta) {
        assertNotNull(expected, "Ожидаемый вектор не должен быть null");
        assertNotNull(actual, "Актуальный вектор не должен быть null");
        assertEquals(4, expected.length, "Вектор должен иметь 4 строки");
        assertEquals(4, actual.length, "Вектор должен иметь 4 строки");

        for (int i = 0; i < 4; i++) {
            assertEquals(1, expected[i].length, "Каждая строка должна иметь 1 столбец");
            assertEquals(1, actual[i].length, "Каждая строка должна иметь 1 столбец");
            assertEquals(expected[i][0], actual[i][0], delta,
                    String.format("Элемент в строке %d не совпадает", i));
        }
    }

    private void assertVectorEquals(float[][] expected, float[][] actual) {
        assertVectorEquals(expected, actual, DELTA);
    }

    // ==================== Конструктор и setVector ====================

    @Test
    void testConstructorWithValidVector() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(input);
        float[][] result = vector.getVector();

        assertVectorEquals(input, result);
        // Проверяем, что это копия, а не ссылка
        input[0][0] = 999.0f;
        assertNotEquals(999.0f, result[0][0]);
    }

    @Test
    void testCopyConstructor() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f original = new Vector4f(input);
        Vector4f copy = new Vector4f(original);

        assertVectorEquals(input, copy.getVector());
        // Проверяем, что это глубокая копия
        original.getVector()[0][0] = 999.0f;
        assertNotEquals(999.0f, copy.getX(), DELTA);
    }

    @Test
    void testConstructorWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Vector4f((float[][]) null));
    }

    @Test
    void testConstructorWithInvalidRowsThrowsException() {
        float[][] invalidRows = {
                {1.0f},
                {2.0f},
                {3.0f}
                // Не хватает 4-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector4f(invalidRows));
    }

    @Test
    void testConstructorWithInvalidColumnsThrowsException() {
        float[][] invalidCols = {
                {1.0f, 2.0f},
                {3.0f, 4.0f},
                {5.0f, 6.0f},
                {7.0f, 8.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector4f(invalidCols));
    }

    // ==================== Геттеры компонентов ====================

    @Test
    void testGetComponents() {
        float[][] input = {
                {1.5f},
                {2.5f},
                {3.5f},
                {4.5f}
        };

        Vector4f vector = new Vector4f(input);

        assertEquals(1.5f, vector.getX(), DELTA);
        assertEquals(2.5f, vector.getY(), DELTA);
        assertEquals(3.5f, vector.getZ(), DELTA);
        assertEquals(4.5f, vector.getW(), DELTA);
    }

    // ==================== Умножение на скаляр ====================

    @Test
    void testMultiplicationByScalar() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f},
                {6.0f},
                {8.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.mul(scalar);

        assertVectorEquals(expected, result.getVector());
        // Проверяем, что исходный вектор не изменился
        assertVectorEquals(input, vector.getVector());
    }

    @Test
    void testMultiplicationByScalarInPlace() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f},
                {6.0f},
                {8.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.mulInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result); // Проверяем, что возвращается this
    }

    @Test
    void testMultiplicationByZero() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.mul(0.0f);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationByNegative() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] expected = {
                {-1.0f},
                {-2.0f},
                {-3.0f},
                {-4.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.mul(-1.0f);

        assertVectorEquals(expected, result.getVector());
    }

    // ==================== Деление на скаляр ====================

    @Test
    void testDivisionByScalar() {
        float[][] input = {
                {2.0f},
                {4.0f},
                {6.0f},
                {8.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.div(scalar);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testDivisionByScalarInPlace() {
        float[][] input = {
                {2.0f},
                {4.0f},
                {6.0f},
                {8.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.divInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result);
    }

    @Test
    void testDivisionByOne() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(input);
        Vector4f result = vector.div(1.0f);

        assertVectorEquals(input, result.getVector());
    }

    // ==================== Сложение векторов ====================

    @Test
    void testVectorAddition() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f},
                {5.0f},
                {5.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);
        Vector4f result = vectorA.add(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorAdditionInPlace() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f},
                {5.0f},
                {5.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);
        Vector4f result = vectorA.addInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testAdditionCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {5.0f},
                {6.0f},
                {7.0f},
                {8.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        Vector4f resultAB = vectorA.add(vectorB);
        Vector4f resultBA = vectorB.add(vectorA);

        assertVectorEquals(resultAB.getVector(), resultBA.getVector());
    }

    @Test
    void testAdditionWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f zeroVector = new Vector4f(zero);
        Vector4f result = vectorA.add(zeroVector);

        assertVectorEquals(a, result.getVector());
    }

    // ==================== Вычитание векторов ====================

    @Test
    void testVectorSubtraction() {
        float[][] a = {
                {5.0f},
                {5.0f},
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);
        Vector4f result = vectorA.sub(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorSubtractionInPlace() {
        float[][] a = {
                {5.0f},
                {5.0f},
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);
        Vector4f result = vectorA.subInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testSubtractionSameVectorGivesZero() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(data);
        Vector4f result = vector.sub(vector);

        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    // ==================== Длина вектора ====================

    @Test
    void testGetLength() {
        float[][] data = {
                {3.0f},
                {4.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);
        float length = vector.getLength();

        // √(3² + 4² + 0² + 0²) = √(9 + 16) = √25 = 5
        assertEquals(5.0f, length, DELTA);
    }

    @Test
    void testGetLengthWithAllComponents() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(data);
        float length = vector.getLength();

        // √(1² + 2² + 3² + 4²) = √(1 + 4 + 9 + 16) = √30 ≈ 5.477225575
        assertEquals(5.477225575f, length, DELTA);
    }

    @Test
    void testGetLengthOfZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);
        float length = vector.getLength();

        assertEquals(0.0f, length, DELTA);
    }

    @Test
    void testGetLengthOfUnitVector() {
        float[][] data = {
                {1.0f / (float) Math.sqrt(4)},
                {1.0f / (float) Math.sqrt(4)},
                {1.0f / (float) Math.sqrt(4)},
                {1.0f / (float) Math.sqrt(4)}
        };

        Vector4f vector = new Vector4f(data);
        float length = vector.getLength();

        assertEquals(1.0f, length, DELTA);
    }

    // ==================== Нормализация ====================

    @Test
    void testNormalize() {
        float[][] data = {
                {3.0f},
                {4.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);
        Vector4f normalized = new Vector4f(data);
        normalized.normalize();

        // Ожидаем вектор (3/5, 4/5, 0, 0) = (0.6, 0.8, 0, 0)
        float[][] expected = {
                {0.6f},
                {0.8f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, normalized.getVector());
        // Проверяем длину нормализованного вектора
        assertEquals(1.0f, normalized.getLength(), DELTA);
    }

    @Test
    void testNormalizeUnitVector() {
        float magnitude = (float) Math.sqrt(0.5 * 0.5 + 0.5 * 0.5 + 0.5 * 0.5 + 0.5 * 0.5);
        float[][] data = {
                {0.5f / magnitude},
                {0.5f / magnitude},
                {0.5f / magnitude},
                {0.5f / magnitude}
        };

        Vector4f vector = new Vector4f(data);
        vector.normalize();

        // Единичный вектор после нормализации остается единичным
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testNormalizeZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);

        // Нормализация нулевого вектора приведет к делению на ноль
        // В текущей реализации это вызовет ArithmeticException
        vector.normalize();

        // После нормализации все компоненты будут NaN или Inf
        assertTrue(Float.isNaN(vector.getX()) || Float.isInfinite(vector.getX()));
    }

    // ==================== Скалярное произведение ====================

    @Test
    void testDotProduct() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);
        float dotProduct = vectorA.dot(vectorB);

        // 1*4 + 2*3 + 3*2 + 4*1 = 4 + 6 + 6 + 4 = 20
        assertEquals(20.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {5.0f},
                {6.0f},
                {7.0f},
                {8.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        float dotAB = vectorA.dot(vectorB);
        float dotBA = vectorB.dot(vectorA);

        assertEquals(dotAB, dotBA, DELTA);
    }

    @Test
    void testDotProductWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f zeroVector = new Vector4f(zero);
        float dotProduct = vectorA.dot(zeroVector);

        assertEquals(0.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductWithSameVector() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(data);
        float dotProduct = vector.dot(vector);

        // Должно быть равно квадрату длины вектора
        float expected = 1*1 + 2*2 + 3*3 + 4*4; // 1 + 4 + 9 + 16 = 30
        assertEquals(expected, dotProduct, DELTA);
        assertEquals(vector.getLength() * vector.getLength(), dotProduct, DELTA);
    }

    // ==================== NDC преобразование ====================

    @Test
    void testNdcConversion() {
        float[][] data = {
                {2.0f},
                {4.0f},
                {6.0f},
                {2.0f}
        };

        Vector4f vector = new Vector4f(data);
        Vector3f ndc = vector.ndc();

        // Ожидаем (2/2, 4/2, 6/2) = (1, 2, 3)
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        assertVectorEquals(expected, ndc.getVector());
        // Проверяем, что исходный вектор изменился
        assertEquals(1.0f, vector.getX(), DELTA);
        assertEquals(2.0f, vector.getY(), DELTA);
        assertEquals(3.0f, vector.getZ(), DELTA);
        assertEquals(2.0f, vector.getW(), DELTA); // W не меняется
    }

    @Test
    void testNdcConversionWithWEqualsOne() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f},
                {1.0f}
        };

        Vector4f vector = new Vector4f(data);
        Vector3f ndc = vector.ndc();

        // При W=1 вектор не должен измениться
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        assertVectorEquals(expected, ndc.getVector());
    }

    @Test
    void testNdcConversionThrowsWhenWIsZero() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);

        assertThrows(RuntimeException.class, () -> vector.ndc());
    }

    @Test
    void testNdcConversionWithNegativeW() {
        float[][] data = {
                {2.0f},
                {4.0f},
                {6.0f},
                {-2.0f}
        };

        Vector4f vector = new Vector4f(data);
        Vector3f ndc = vector.ndc();

        // Ожидаем (2/-2, 4/-2, 6/-2) = (-1, -2, -3)
        float[][] expected = {
                {-1.0f},
                {-2.0f},
                {-3.0f}
        };

        assertVectorEquals(expected, ndc.getVector());
    }

    // ==================== GetVector и SetVector ====================

    @Test
    void testGetVectorReturnsCopy() {
        float[][] original = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        Vector4f vector = new Vector4f(original);
        float[][] copy = vector.getVector();

        // Изменяем копию
        copy[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, vector.getX(), DELTA);
    }

    @Test
    void testSetVectorWithValidData() {
        Vector4f vector = new Vector4f(new float[][]{{0}, {0}, {0}, {0}});

        float[][] newData = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        vector.setVector(newData);

        // Проверяем, что вектор установился
        assertVectorEquals(newData, vector.getVector());

        // Проверяем, что изменения в исходном массиве не влияют на вектор
        newData[0][0] = 999.0f;
        assertNotEquals(999.0f, vector.getX(), DELTA);
    }

    @Test
    void testSetVectorWithNullThrowsException() {
        Vector4f vector = new Vector4f(new float[][]{{0}, {0}, {0}, {0}});
        assertThrows(NullPointerException.class, () -> vector.setVector(null));
    }

    @Test
    void testSetVectorWithInvalidDimensionsThrowsException() {
        Vector4f vector = new Vector4f(new float[][]{{0}, {0}, {0}, {0}});

        float[][] wrongRows = {
                {1.0f},
                {2.0f},
                {3.0f}
                // Не хватает 4-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> vector.setVector(wrongRows));
    }

    // ==================== Комбинированные операции ====================

    @Test
    void testChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        // (A + B) * 2 - A
        Vector4f result = vectorA.add(vectorB).mul(2.0f).sub(vectorA);

        // Вычисляем ожидаемый результат:
        // A + B = (5, 5, 5, 5)
        // * 2 = (10, 10, 10, 10)
        // - A = (9, 8, 7, 6)
        float[][] expected = {
                {9.0f},
                {8.0f},
                {7.0f},
                {6.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testInPlaceChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        // A += B; A *= 2; A -= B
        vectorA.addInPlace(vectorB).mulInPlace(2.0f).subInPlace(vectorB);

        // Вычисляем ожидаемый результат:
        // A + B = (5, 5, 5, 5)
        // * 2 = (10, 10, 10, 10)
        // - B = (6, 7, 8, 9)
        float[][] expected = {
                {6.0f},
                {7.0f},
                {8.0f},
                {9.0f}
        };

        assertVectorEquals(expected, vectorA.getVector());
    }

    @Test
    void testNormalizeAfterOperations() {
        float[][] data = {
                {3.0f},
                {4.0f},
                {0.0f},
                {0.0f}
        };

        Vector4f vector = new Vector4f(data);

        // Умножаем на 2, нормализуем
        vector.mulInPlace(2.0f).normalize();

        // После умножения на 2: (6, 8, 0, 0)
        // После нормализации: (6/10, 8/10, 0, 0) = (0.6, 0.8, 0, 0)
        float[][] expected = {
                {0.6f},
                {0.8f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, vector.getVector());
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testDotProductAfterOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f},
                {1.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        // (A * 2) · (B / 2)
        Vector4f scaledA = vectorA.mul(2.0f);
        Vector4f scaledB = vectorB.div(2.0f);
        float dotProduct = scaledA.dot(scaledB);

        // Оригинальное скалярное произведение: 20
        // После операций: (2A) · (B/2) = A · B = 20
        assertEquals(20.0f, dotProduct, DELTA);
    }

    // ==================== Граничные случаи ====================

    @Test
    void testOperationsWithVerySmallValues() {
        float[][] data = {
                {0.0001f},
                {0.0002f},
                {0.0003f},
                {0.0004f}
        };

        Vector4f vector = new Vector4f(data);

        // Умножение на большое число
        Vector4f scaled = vector.mul(10000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testOperationsWithVeryLargeValues() {
        float[][] data = {
                {1000000.0f},
                {2000000.0f},
                {3000000.0f},
                {4000000.0f}
        };

        Vector4f vector = new Vector4f(data);

        // Деление на большое число
        Vector4f scaled = vector.div(1000000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testVectorEqualityThroughOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {2.0f},
                {4.0f},
                {6.0f},
                {8.0f}
        };

        Vector4f vectorA = new Vector4f(a);
        Vector4f vectorB = new Vector4f(b);

        // A * 2 должно быть равно B
        Vector4f scaledA = vectorA.mul(2.0f);
        assertVectorEquals(vectorB.getVector(), scaledA.getVector());
    }
}