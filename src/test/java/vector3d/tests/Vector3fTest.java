package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector3f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector3fTest {

    private final float DELTA = 0.0001f;

    private void assertVectorEquals(float[][] expected, float[][] actual, float delta) {
        assertNotNull(expected, "Ожидаемый вектор не должен быть null");
        assertNotNull(actual, "Актуальный вектор не должен быть null");
        assertEquals(3, expected.length, "Вектор должен иметь 3 строки");
        assertEquals(3, actual.length, "Вектор должен иметь 3 строки");

        for (int i = 0; i < 3; i++) {
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
                {3.0f}
        };

        Vector3f vector = new Vector3f(input);
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
                {3.0f}
        };

        Vector3f original = new Vector3f(input);
        Vector3f copy = new Vector3f(original);

        assertVectorEquals(input, copy.getVector());
        // Проверяем, что это глубокая копия
        original.getVector()[0][0] = 999.0f;
        assertNotEquals(999.0f, copy.getX(), DELTA);
    }

    @Test
    void testConstructorWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Vector3f((float[][]) null));
    }

    @Test
    void testConstructorWithInvalidRowsThrowsException() {
        float[][] invalidRows = {
                {1.0f},
                {2.0f}
                // Не хватает 3-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector3f(invalidRows));
    }

    @Test
    void testConstructorWithInvalidColumnsThrowsException() {
        float[][] invalidCols = {
                {1.0f, 2.0f},
                {3.0f, 4.0f},
                {5.0f, 6.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector3f(invalidCols));
    }

    // ==================== Геттеры компонентов ====================

    @Test
    void testGetComponents() {
        float[][] input = {
                {1.5f},
                {2.5f},
                {3.5f}
        };

        Vector3f vector = new Vector3f(input);

        assertEquals(1.5f, vector.getX(), DELTA);
        assertEquals(2.5f, vector.getY(), DELTA);
        assertEquals(3.5f, vector.getZ(), DELTA);
    }

    // ==================== Умножение на скаляр ====================

    @Test
    void testMultiplicationByScalar() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.mul(scalar);

        assertVectorEquals(expected, result.getVector());
        // Проверяем, что исходный вектор не изменился
        assertVectorEquals(input, vector.getVector());
    }

    @Test
    void testMultiplicationByScalarInPlace() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.mulInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result); // Проверяем, что возвращается this
    }

    @Test
    void testMultiplicationByZero() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.mul(0.0f);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationByNegative() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] expected = {
                {-1.0f},
                {-2.0f},
                {-3.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.mul(-1.0f);

        assertVectorEquals(expected, result.getVector());
    }

    // ==================== Деление на скаляр ====================

    @Test
    void testDivisionByScalar() {
        float[][] input = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.div(scalar);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testDivisionByScalarInPlace() {
        float[][] input = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.divInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result);
    }

    @Test
    void testDivisionByOne() {
        float[][] input = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(input);
        Vector3f result = vector.div(1.0f);

        assertVectorEquals(input, result.getVector());
    }

    // ==================== Сложение векторов ====================

    @Test
    void testVectorAddition() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f},
                {5.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f result = vectorA.add(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorAdditionInPlace() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f},
                {5.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f result = vectorA.addInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testAdditionCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        Vector3f resultAB = vectorA.add(vectorB);
        Vector3f resultBA = vectorB.add(vectorA);

        assertVectorEquals(resultAB.getVector(), resultBA.getVector());
    }

    @Test
    void testAdditionWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f zeroVector = new Vector3f(zero);
        Vector3f result = vectorA.add(zeroVector);

        assertVectorEquals(a, result.getVector());
    }

    // ==================== Вычитание векторов ====================

    @Test
    void testVectorSubtraction() {
        float[][] a = {
                {5.0f},
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f result = vectorA.sub(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorSubtractionInPlace() {
        float[][] a = {
                {5.0f},
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f result = vectorA.subInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testSubtractionSameVectorGivesZero() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(data);
        Vector3f result = vector.sub(vector);

        float[][] expected = {
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
                {0.0f}
        };

        Vector3f vector = new Vector3f(data);
        float length = vector.getLength();

        // √(3² + 4² + 0²) = √(9 + 16) = √25 = 5
        assertEquals(5.0f, length, DELTA);
    }

    @Test
    void testGetLengthWithAllComponents() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(data);
        float length = vector.getLength();

        // √(1² + 2² + 3²) = √(1 + 4 + 9) = √14 ≈ 3.741657387
        assertEquals(3.741657387f, length, DELTA);
    }

    @Test
    void testGetLengthOfZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vector = new Vector3f(data);
        float length = vector.getLength();

        assertEquals(0.0f, length, DELTA);
    }

    @Test
    void testGetLengthOfUnitVector() {
        float[][] data = {
                {1.0f / (float) Math.sqrt(3)},
                {1.0f / (float) Math.sqrt(3)},
                {1.0f / (float) Math.sqrt(3)}
        };

        Vector3f vector = new Vector3f(data);
        float length = vector.getLength();

        assertEquals(1.0f, length, DELTA);
    }

    // ==================== Нормализация ====================

    @Test
    void testNormalize() {
        float[][] data = {
                {3.0f},
                {4.0f},
                {0.0f}
        };

        Vector3f vector = new Vector3f(data);
        Vector3f normalized = new Vector3f(data);
        normalized.normalize();

        // Ожидаем вектор (3/5, 4/5, 0) = (0.6, 0.8, 0)
        float[][] expected = {
                {0.6f},
                {0.8f},
                {0.0f}
        };

        assertVectorEquals(expected, normalized.getVector());
        // Проверяем длину нормализованного вектора
        assertEquals(1.0f, normalized.getLength(), DELTA);
    }

    @Test
    void testNormalizeUnitVector() {
        float magnitude = (float) Math.sqrt(1.0/3);
        float[][] data = {
                {magnitude},
                {magnitude},
                {magnitude}
        };

        Vector3f vector = new Vector3f(data);
        vector.normalize();

        // Единичный вектор после нормализации остается единичным
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testNormalizeZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vector = new Vector3f(data);

        // Нормализация нулевого вектора приведет к делению на ноль
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
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        float dotProduct = vectorA.dot(vectorB);

        // 1*4 + 2*5 + 3*6 = 4 + 10 + 18 = 32
        assertEquals(32.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        float dotAB = vectorA.dot(vectorB);
        float dotBA = vectorB.dot(vectorA);

        assertEquals(dotAB, dotBA, DELTA);
    }

    @Test
    void testDotProductWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f zeroVector = new Vector3f(zero);
        float dotProduct = vectorA.dot(zeroVector);

        assertEquals(0.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductWithSameVector() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(data);
        float dotProduct = vector.dot(vector);

        // Должно быть равно квадрату длины вектора
        float expected = 1*1 + 2*2 + 3*3; // 1 + 4 + 9 = 14
        assertEquals(expected, dotProduct, DELTA);
        assertEquals(vector.getLength() * vector.getLength(), dotProduct, DELTA);
    }

    @Test
    void testDotProductPerpendicularVectors() {
        float[][] a = {
                {1.0f},
                {0.0f},
                {0.0f}
        };

        float[][] b = {
                {0.0f},
                {1.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        float dotProduct = vectorA.dot(vectorB);

        // Перпендикулярные векторы имеют нулевое скалярное произведение
        assertEquals(0.0f, dotProduct, DELTA);
    }

    // ==================== Векторное произведение ====================

    @Test
    void testCrossProductBasic() {
        float[][] a = {
                {1.0f},
                {0.0f},
                {0.0f}
        };

        float[][] b = {
                {0.0f},
                {1.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f cross = vectorA.cross(vectorB);

        // i × j = k
        float[][] expected = {
                {0.0f},
                {0.0f},
                {1.0f}
        };

        assertVectorEquals(expected, cross.getVector());
    }

    @Test
    void testCrossProductAnticommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        Vector3f crossAB = vectorA.cross(vectorB);
        Vector3f crossBA = vectorB.cross(vectorA);

        // Векторное произведение антикоммутативно: A × B = -(B × A)
        for (int i = 0; i < 3; i++) {
            assertEquals(-crossAB.getVector()[i][0], crossBA.getVector()[i][0], DELTA);
        }
    }

    @Test
    void testCrossProductWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f zeroVector = new Vector3f(zero);
        Vector3f cross = vectorA.cross(zeroVector);

        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, cross.getVector());
    }

    @Test
    void testCrossProductWithSameVector() {
        float[][] data = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(data);
        Vector3f cross = vector.cross(vector);

        // Векторное произведение вектора на себя равно нулевому вектору
        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, cross.getVector());
    }

    @Test
    void testCrossProductOrthogonality() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f cross = vectorA.cross(vectorB);

        // Векторное произведение ортогонально обоим исходным векторам
        float dotWithA = cross.dot(vectorA);
        float dotWithB = cross.dot(vectorB);

        assertEquals(0.0f, dotWithA, DELTA);
        assertEquals(0.0f, dotWithB, DELTA);
    }

    @Test
    void testCrossProductRightHandRule() {
        // Проверяем правило правой руки
        float[][] i = {{1.0f}, {0.0f}, {0.0f}};
        float[][] j = {{0.0f}, {1.0f}, {0.0f}};
        float[][] k = {{0.0f}, {0.0f}, {1.0f}};

        Vector3f vectorI = new Vector3f(i);
        Vector3f vectorJ = new Vector3f(j);
        Vector3f vectorK = new Vector3f(k);

        // i × j = k
        Vector3f crossIJ = vectorI.cross(vectorJ);
        assertVectorEquals(k, crossIJ.getVector());

        // j × k = i
        Vector3f crossJK = vectorJ.cross(vectorK);
        assertVectorEquals(i, crossJK.getVector());

        // k × i = j
        Vector3f crossKI = vectorK.cross(vectorI);
        assertVectorEquals(j, crossKI.getVector());
    }

    @Test
    void testCrossProductLengthRelation() {
        float[][] a = {
                {3.0f},
                {0.0f},
                {0.0f}
        };

        float[][] b = {
                {0.0f},
                {4.0f},
                {0.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f cross = vectorA.cross(vectorB);

        // Длина векторного произведения равна произведению длин векторов на синус угла
        // Для перпендикулярных векторов sin(90°) = 1
        float expectedLength = vectorA.getLength() * vectorB.getLength() * 1.0f;
        assertEquals(expectedLength, cross.getLength(), DELTA);
    }

    // ==================== GetVector и SetVector ====================

    @Test
    void testGetVectorReturnsCopy() {
        float[][] original = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        Vector3f vector = new Vector3f(original);
        float[][] copy = vector.getVector();

        // Изменяем копию
        copy[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, vector.getX(), DELTA);
    }

    @Test
    void testSetVectorWithValidData() {
        Vector3f vector = new Vector3f(new float[][]{{0}, {0}, {0}});

        float[][] newData = {
                {1.0f},
                {2.0f},
                {3.0f}
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
        Vector3f vector = new Vector3f(new float[][]{{0}, {0}, {0}});
        assertThrows(NullPointerException.class, () -> vector.setVector(null));
    }

    @Test
    void testSetVectorWithInvalidDimensionsThrowsException() {
        Vector3f vector = new Vector3f(new float[][]{{0}, {0}, {0}});

        float[][] wrongRows = {
                {1.0f},
                {2.0f}
                // Не хватает 3-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> vector.setVector(wrongRows));
    }

    // ==================== Комбинированные операции ====================

    @Test
    void testChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        // (A + B) * 2 - A
        Vector3f result = vectorA.add(vectorB).mul(2.0f).sub(vectorA);

        // Вычисляем ожидаемый результат:
        // A + B = (5, 5, 5)
        // * 2 = (10, 10, 10)
        // - A = (9, 8, 7)
        float[][] expected = {
                {9.0f},
                {8.0f},
                {7.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testInPlaceChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f},
                {2.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        // A += B; A *= 2; A -= B
        vectorA.addInPlace(vectorB).mulInPlace(2.0f).subInPlace(vectorB);

        // Вычисляем ожидаемый результат:
        // A + B = (5, 5, 5)
        // * 2 = (10, 10, 10)
        // - B = (6, 7, 8)
        float[][] expected = {
                {6.0f},
                {7.0f},
                {8.0f}
        };

        assertVectorEquals(expected, vectorA.getVector());
    }

    @Test
    void testNormalizeAfterOperations() {
        float[][] data = {
                {3.0f},
                {4.0f},
                {0.0f}
        };

        Vector3f vector = new Vector3f(data);

        // Умножаем на 2, нормализуем
        vector.mulInPlace(2.0f).normalize();

        // После умножения на 2: (6, 8, 0)
        // После нормализации: (6/10, 8/10, 0) = (0.6, 0.8, 0)
        float[][] expected = {
                {0.6f},
                {0.8f},
                {0.0f}
        };

        assertVectorEquals(expected, vector.getVector());
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testCrossAndDotCombination() {
        // Проверяем тождество Лагранжа: |a × b|² = |a|²|b|² - (a·b)²
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {4.0f},
                {5.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        Vector3f cross = vectorA.cross(vectorB);
        float dot = vectorA.dot(vectorB);
        float lengthA = vectorA.getLength();
        float lengthB = vectorB.getLength();
        float lengthCross = cross.getLength();

        // |a × b|²
        float leftSide = lengthCross * lengthCross;

        // |a|²|b|² - (a·b)²
        float rightSide = (lengthA * lengthA) * (lengthB * lengthB) - (dot * dot);

        assertEquals(leftSide, rightSide, DELTA);
    }

    @Test
    void testCrossProductDistributivity() {
        // Проверяем свойство дистрибутивности: a × (b + c) = a × b + a × c
        float[][] a = {{1.0f}, {2.0f}, {3.0f}};
        float[][] b = {{4.0f}, {5.0f}, {6.0f}};
        float[][] c = {{7.0f}, {8.0f}, {9.0f}};

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f vectorC = new Vector3f(c);

        // Левая часть: a × (b + c)
        Vector3f leftSide = vectorA.cross(vectorB.add(vectorC));

        // Правая часть: a × b + a × c
        Vector3f rightSide = vectorA.cross(vectorB).add(vectorA.cross(vectorC));

        assertVectorEquals(leftSide.getVector(), rightSide.getVector(), DELTA);
    }

    // ==================== Граничные случаи ====================

    @Test
    void testOperationsWithVerySmallValues() {
        float[][] data = {
                {0.0001f},
                {0.0002f},
                {0.0003f}
        };

        Vector3f vector = new Vector3f(data);

        // Умножение на большое число
        Vector3f scaled = vector.mul(10000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testOperationsWithVeryLargeValues() {
        float[][] data = {
                {1000000.0f},
                {2000000.0f},
                {3000000.0f}
        };

        Vector3f vector = new Vector3f(data);

        // Деление на большое число
        Vector3f scaled = vector.div(1000000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testVectorEqualityThroughOperations() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        float[][] b = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);

        // A * 2 должно быть равно B
        Vector3f scaledA = vectorA.mul(2.0f);
        assertVectorEquals(vectorB.getVector(), scaledA.getVector());
    }

    @Test
    void testCrossProductWithParallelVectors() {
        float[][] a = {
                {1.0f},
                {2.0f},
                {3.0f}
        };

        // Вектор b параллелен a (b = 2 * a)
        float[][] b = {
                {2.0f},
                {4.0f},
                {6.0f}
        };

        Vector3f vectorA = new Vector3f(a);
        Vector3f vectorB = new Vector3f(b);
        Vector3f cross = vectorA.cross(vectorB);

        // Векторное произведение параллельных векторов равно нулю
        float[][] expected = {
                {0.0f},
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, cross.getVector());
    }
}