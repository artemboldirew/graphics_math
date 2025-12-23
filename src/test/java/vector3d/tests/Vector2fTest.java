package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector2f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2fTest {

    private final float DELTA = 0.0001f;

    private void assertVectorEquals(float[][] expected, float[][] actual, float delta) {
        assertNotNull(expected, "Ожидаемый вектор не должен быть null");
        assertNotNull(actual, "Актуальный вектор не должен быть null");
        assertEquals(2, expected.length, "Вектор должен иметь 2 строки");
        assertEquals(2, actual.length, "Вектор должен иметь 2 строки");

        for (int i = 0; i < 2; i++) {
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
                {2.0f}
        };

        Vector2f vector = new Vector2f(input);
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
                {2.0f}
        };

        Vector2f original = new Vector2f(input);
        Vector2f copy = new Vector2f(original);

        assertVectorEquals(input, copy.getVector());
        // Проверяем, что это глубокая копия
        original.getVector()[0][0] = 999.0f;
        assertNotEquals(999.0f, copy.getX(), DELTA);
    }

    @Test
    void testConstructorWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> new Vector2f((float[][]) null));
    }

    @Test
    void testConstructorWithInvalidRowsThrowsException() {
        float[][] invalidRows = {
                {1.0f}
                // Не хватает 2-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector2f(invalidRows));
    }

    @Test
    void testConstructorWithInvalidColumnsThrowsException() {
        float[][] invalidCols = {
                {1.0f, 2.0f},
                {3.0f, 4.0f}
        };

        assertThrows(IllegalArgumentException.class, () -> new Vector2f(invalidCols));
    }

    // ==================== Геттеры компонентов ====================

    @Test
    void testGetComponents() {
        float[][] input = {
                {1.5f},
                {2.5f}
        };

        Vector2f vector = new Vector2f(input);

        assertEquals(1.5f, vector.getX(), DELTA);
        assertEquals(2.5f, vector.getY(), DELTA);
    }

    @Test
    void testGetXWithNullVector() {
        // В текущей реализации getX() проверяет vector != null
        // Но в конструкторе vector всегда инициализируется, так что эта проверка избыточна
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(input);
        // Удалить vector невозможно из-за private доступа
        // Этот тест в основном для документации
        assertEquals(1.0f, vector.getX(), DELTA);
    }

    // ==================== Умножение на скаляр ====================

    @Test
    void testMultiplicationByScalar() {
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.mul(scalar);

        assertVectorEquals(expected, result.getVector());
        // Проверяем, что исходный вектор не изменился
        assertVectorEquals(input, vector.getVector());
    }

    @Test
    void testMultiplicationByScalarInPlace() {
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {2.0f},
                {4.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.mulInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result); // Проверяем, что возвращается this
    }

    @Test
    void testMultiplicationByZero() {
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        float[][] expected = {
                {0.0f},
                {0.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.mul(0.0f);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testMultiplicationByNegative() {
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        float[][] expected = {
                {-1.0f},
                {-2.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.mul(-1.0f);

        assertVectorEquals(expected, result.getVector());
    }

    // ==================== Деление на скаляр ====================

    @Test
    void testDivisionByScalar() {
        float[][] input = {
                {2.0f},
                {4.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.div(scalar);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testDivisionByScalarInPlace() {
        float[][] input = {
                {2.0f},
                {4.0f}
        };

        float scalar = 2.0f;
        float[][] expected = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.divInPlace(scalar);

        assertVectorEquals(expected, vector.getVector());
        assertSame(vector, result);
    }

    @Test
    void testDivisionByOne() {
        float[][] input = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(input);
        Vector2f result = vector.div(1.0f);

        assertVectorEquals(input, result.getVector());
    }

    // ==================== Сложение векторов ====================

    @Test
    void testVectorAddition() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        Vector2f result = vectorA.add(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorAdditionInPlace() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {4.0f},
                {3.0f}
        };

        float[][] expected = {
                {5.0f},
                {5.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        Vector2f result = vectorA.addInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testAdditionCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        Vector2f resultAB = vectorA.add(vectorB);
        Vector2f resultBA = vectorB.add(vectorA);

        assertVectorEquals(resultAB.getVector(), resultBA.getVector());
    }

    @Test
    void testAdditionWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f zeroVector = new Vector2f(zero);
        Vector2f result = vectorA.add(zeroVector);

        assertVectorEquals(a, result.getVector());
    }

    // ==================== Вычитание векторов ====================

    @Test
    void testVectorSubtraction() {
        float[][] a = {
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        Vector2f result = vectorA.sub(vectorB);

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testVectorSubtractionInPlace() {
        float[][] a = {
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f}
        };

        float[][] expected = {
                {4.0f},
                {3.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        Vector2f result = vectorA.subInPlace(vectorB);

        assertVectorEquals(expected, vectorA.getVector());
        assertSame(vectorA, result);
    }

    @Test
    void testSubtractionSameVectorGivesZero() {
        float[][] data = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(data);
        Vector2f result = vector.sub(vector);

        float[][] expected = {
                {0.0f},
                {0.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testSubtractionIsNotCommutative() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        Vector2f resultAB = vectorA.sub(vectorB);
        Vector2f resultBA = vectorB.sub(vectorA);

        // Вычитание не коммутативно, результаты должны быть противоположны
        for (int i = 0; i < 2; i++) {
            assertEquals(-resultAB.getVector()[i][0], resultBA.getVector()[i][0], DELTA);
        }
    }

    // ==================== Длина вектора ====================

    @Test
    void testGetLength() {
        float[][] data = {
                {3.0f},
                {4.0f}
        };

        Vector2f vector = new Vector2f(data);
        float length = vector.getLength();

        // √(3² + 4²) = √(9 + 16) = √25 = 5
        assertEquals(5.0f, length, DELTA);
    }

    @Test
    void testGetLengthWithAllComponents() {
        float[][] data = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(data);
        float length = vector.getLength();

        // √(1² + 2²) = √(1 + 4) = √5 ≈ 2.236067977
        assertEquals(2.236067977f, length, DELTA);
    }

    @Test
    void testGetLengthOfZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f}
        };

        Vector2f vector = new Vector2f(data);
        float length = vector.getLength();

        assertEquals(0.0f, length, DELTA);
    }

    @Test
    void testGetLengthOfUnitVector() {
        float magnitude = (float) Math.sqrt(0.5 * 0.5 + 0.5 * 0.5);
        float[][] data = {
                {0.5f / magnitude},
                {0.5f / magnitude}
        };

        Vector2f vector = new Vector2f(data);
        float length = vector.getLength();

        assertEquals(1.0f, length, DELTA);
    }

    // ==================== Нормализация ====================

    @Test
    void testNormalize() {
        float[][] data = {
                {3.0f},
                {4.0f}
        };

        Vector2f vector = new Vector2f(data);
        Vector2f normalized = new Vector2f(data);
        normalized.normalize();

        // Ожидаем вектор (3/5, 4/5) = (0.6, 0.8)
        float[][] expected = {
                {0.6f},
                {0.8f}
        };

        assertVectorEquals(expected, normalized.getVector());
        // Проверяем длину нормализованного вектора
        assertEquals(1.0f, normalized.getLength(), DELTA);
    }

    @Test
    void testNormalizeUnitVector() {
        float magnitude = (float) Math.sqrt(0.5 * 0.5 + 0.5 * 0.5);
        float[][] data = {
                {0.5f / magnitude},
                {0.5f / magnitude}
        };

        Vector2f vector = new Vector2f(data);
        vector.normalize();

        // Единичный вектор после нормализации остается единичным
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testNormalizeZeroVector() {
        float[][] data = {
                {0.0f},
                {0.0f}
        };

        Vector2f vector = new Vector2f(data);

        // Нормализация нулевого вектора приведет к делению на ноль
        vector.normalize();

        // После нормализации все компоненты будут NaN или Inf
        assertTrue(Float.isNaN(vector.getX()) || Float.isInfinite(vector.getX()));
        assertTrue(Float.isNaN(vector.getY()) || Float.isInfinite(vector.getY()));
    }

    // ==================== Скалярное произведение ====================

    @Test
    void testDotProduct() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        float dotProduct = vectorA.dot(vectorB);

        // 1*3 + 2*4 = 3 + 8 = 11
        assertEquals(11.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductCommutativity() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        float dotAB = vectorA.dot(vectorB);
        float dotBA = vectorB.dot(vectorA);

        assertEquals(dotAB, dotBA, DELTA);
    }

    @Test
    void testDotProductWithZeroVector() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] zero = {
                {0.0f},
                {0.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f zeroVector = new Vector2f(zero);
        float dotProduct = vectorA.dot(zeroVector);

        assertEquals(0.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductWithSameVector() {
        float[][] data = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(data);
        float dotProduct = vector.dot(vector);

        // Должно быть равно квадрату длины вектора
        float expected = 1*1 + 2*2; // 1 + 4 = 5
        assertEquals(expected, dotProduct, DELTA);
        assertEquals(vector.getLength() * vector.getLength(), dotProduct, DELTA);
    }

    @Test
    void testDotProductPerpendicularVectors() {
        float[][] a = {
                {1.0f},
                {0.0f}
        };

        float[][] b = {
                {0.0f},
                {1.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        float dotProduct = vectorA.dot(vectorB);

        // Перпендикулярные векторы имеют нулевое скалярное произведение
        assertEquals(0.0f, dotProduct, DELTA);
    }

    @Test
    void testDotProductAngleRelation() {
        // Скалярное произведение связано с косинусом угла: a·b = |a||b|cosθ
        float[][] a = {
                {1.0f},
                {0.0f}
        };

        float[][] b = {
                {(float) Math.cos(Math.PI/3)},  // cos(60°) = 0.5
                {(float) Math.sin(Math.PI/3)}   // sin(60°) ≈ 0.866
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        float dotProduct = vectorA.dot(vectorB);

        // |a| = 1, |b| = 1, cos(60°) = 0.5
        float expected = 1.0f * 1.0f * 0.5f;
        assertEquals(expected, dotProduct, DELTA);
    }

    // ==================== GetVector и SetVector ====================

    @Test
    void testGetVectorReturnsCopy() {
        float[][] original = {
                {1.0f},
                {2.0f}
        };

        Vector2f vector = new Vector2f(original);
        float[][] copy = vector.getVector();

        // Изменяем копию
        copy[0][0] = 999.0f;

        // Проверяем, что оригинал не изменился
        assertNotEquals(999.0f, vector.getX(), DELTA);
    }

    @Test
    void testSetVectorWithValidData() {
        Vector2f vector = new Vector2f(new float[][]{{0}, {0}});

        float[][] newData = {
                {1.0f},
                {2.0f}
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
        Vector2f vector = new Vector2f(new float[][]{{0}, {0}});
        assertThrows(NullPointerException.class, () -> vector.setVector(null));
    }

    @Test
    void testSetVectorWithInvalidDimensionsThrowsException() {
        Vector2f vector = new Vector2f(new float[][]{{0}, {0}});

        float[][] wrongRows = {
                {1.0f}
                // Не хватает 2-й строки
        };

        assertThrows(IllegalArgumentException.class, () -> vector.setVector(wrongRows));
    }

    // ==================== Комбинированные операции ====================

    @Test
    void testChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        // (A + B) * 2 - A
        Vector2f result = vectorA.add(vectorB).mul(2.0f).sub(vectorA);

        // Вычисляем ожидаемый результат:
        // A + B = (4, 6)
        // * 2 = (8, 12)
        // - A = (7, 10)
        float[][] expected = {
                {7.0f},
                {10.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testInPlaceChainedOperations() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        // A += B; A *= 2; A -= B
        vectorA.addInPlace(vectorB).mulInPlace(2.0f).subInPlace(vectorB);

        // Вычисляем ожидаемый результат:
        // A + B = (4, 6)
        // * 2 = (8, 12)
        // - B = (5, 8)
        float[][] expected = {
                {5.0f},
                {8.0f}
        };

        assertVectorEquals(expected, vectorA.getVector());
    }

    @Test
    void testNormalizeAfterOperations() {
        float[][] data = {
                {3.0f},
                {4.0f}
        };

        Vector2f vector = new Vector2f(data);

        // Умножаем на 2, нормализуем
        vector.mulInPlace(2.0f).normalize();

        // После умножения на 2: (6, 8)
        // После нормализации: (6/10, 8/10) = (0.6, 0.8)
        float[][] expected = {
                {0.6f},
                {0.8f}
        };

        assertVectorEquals(expected, vector.getVector());
        assertEquals(1.0f, vector.getLength(), DELTA);
    }

    @Test
    void testDotProductAfterOperations() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {3.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        // (A * 2) · (B / 2)
        Vector2f scaledA = vectorA.mul(2.0f);
        Vector2f scaledB = vectorB.div(2.0f);
        float dotProduct = scaledA.dot(scaledB);

        // Оригинальное скалярное произведение: 11
        // После операций: (2A) · (B/2) = A · B = 11
        assertEquals(11.0f, dotProduct, DELTA);
    }

    // ==================== Граничные случаи ====================

    @Test
    void testOperationsWithVerySmallValues() {
        float[][] data = {
                {0.0001f},
                {0.0002f}
        };

        Vector2f vector = new Vector2f(data);

        // Умножение на большое число
        Vector2f scaled = vector.mul(10000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testOperationsWithVeryLargeValues() {
        float[][] data = {
                {1000000.0f},
                {2000000.0f}
        };

        Vector2f vector = new Vector2f(data);

        // Деление на большое число
        Vector2f scaled = vector.div(1000000.0f);

        float[][] expected = {
                {1.0f},
                {2.0f}
        };

        assertVectorEquals(expected, scaled.getVector(), 0.01f);
    }

    @Test
    void testVectorEqualityThroughOperations() {
        float[][] a = {
                {1.0f},
                {2.0f}
        };

        float[][] b = {
                {2.0f},
                {4.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        // A * 2 должно быть равно B
        Vector2f scaledA = vectorA.mul(2.0f);
        assertVectorEquals(vectorB.getVector(), scaledA.getVector());
    }

    @Test
    void testBugInSubMethod() {
        // В методе sub() есть потенциальная ошибка: используется getVector() вместо this.vector
        float[][] a = {
                {5.0f},
                {5.0f}
        };

        float[][] b = {
                {1.0f},
                {2.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);
        Vector2f result = vectorA.sub(vectorB);

        // Ожидаем (4, 3)
        float[][] expected = {
                {4.0f},
                {3.0f}
        };

        assertVectorEquals(expected, result.getVector());
    }

    @Test
    void testAngleBetweenVectorsUsingDotProduct() {
        // Угол между векторами можно найти через скалярное произведение
        float[][] a = {
                {1.0f},
                {0.0f}
        };

        float[][] b = {
                {0.0f},
                {1.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        float dot = vectorA.dot(vectorB);
        float lengthA = vectorA.getLength();
        float lengthB = vectorB.getLength();

        // cosθ = (a·b) / (|a||b|)
        float cosTheta = dot / (lengthA * lengthB);

        // Для перпендикулярных векторов cosθ = 0
        assertEquals(0.0f, cosTheta, DELTA);
    }

    @Test
    void testVectorProjection() {
        // Проекция вектора a на вектор b: proj_b(a) = (a·b / |b|²) * b
        float[][] a = {
                {3.0f},
                {4.0f}
        };

        float[][] b = {
                {1.0f},
                {0.0f}
        };

        Vector2f vectorA = new Vector2f(a);
        Vector2f vectorB = new Vector2f(b);

        float dot = vectorA.dot(vectorB);
        float lengthBSquared = vectorB.getLength() * vectorB.getLength();
        float scalar = dot / lengthBSquared;

        Vector2f projection = vectorB.mul(scalar);

        // Ожидаем проекцию (3, 0) - только x-компонента
        float[][] expected = {
                {3.0f},
                {0.0f}
        };

        assertVectorEquals(expected, projection.getVector());
    }

    // ==================== Специальные векторы ====================

    @Test
    void testUnitVectors() {
        // Единичные векторы по осям
        float[][] i = {{1.0f}, {0.0f}};
        float[][] j = {{0.0f}, {1.0f}};

        Vector2f vectorI = new Vector2f(i);
        Vector2f vectorJ = new Vector2f(j);

        assertEquals(1.0f, vectorI.getLength(), DELTA);
        assertEquals(1.0f, vectorJ.getLength(), DELTA);

        // Они ортогональны
        assertEquals(0.0f, vectorI.dot(vectorJ), DELTA);
    }

    @Test
    void testReflection() {
        // Отражение вектора относительно оси
        float[][] v = {{3.0f}, {4.0f}};
        float[][] normal = {{0.0f}, {1.0f}}; // Вертикальная ось

        Vector2f vector = new Vector2f(v);
        Vector2f normalVector = new Vector2f(normal);

        // Формула отражения: r = v - 2*(v·n)*n
        float dot = vector.dot(normalVector);
        Vector2f reflection = vector.sub(normalVector.mul(2.0f * dot));

        // Отражаем относительно вертикальной оси: (3,4) -> (-3,4)
        float[][] expected = {
                {-3.0f},
                {4.0f}
        };

        assertVectorEquals(expected, reflection.getVector());
    }
}