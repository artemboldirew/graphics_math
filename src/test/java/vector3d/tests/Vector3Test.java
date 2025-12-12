package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {
    private Vector3 vector1;
    private Vector3 vector2;
    private Vector3 zeroVector;

    @BeforeEach
    void setUp() {
        vector1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        vector2 = new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}});
        zeroVector = new Vector3(new float[][]{{0.0f}, {0.0f}, {0.0f}});
    }

    @Test
    void testConstructorValidVector() {
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}};
        assertArrayEquals(expected, vector1.getVector());
    }

    @Test
    void testConstructorInvalidVectorThrowsException() {
        float[][] invalidVector = {{1.0f}, {2.0f}}; // 2x1 instead of 3x1
        assertThrows(IllegalArgumentException.class, () -> new Vector3(invalidVector));
    }

    @Test
    void testConstructorInvalidMatrixThrowsException() {
        float[][] invalidMatrix = {{1.0f, 2.0f}, {3.0f, 4.0f}, {5.0f, 6.0f}};
        assertThrows(IllegalArgumentException.class, () -> new Vector3(invalidMatrix));
    }

    @Test
    void testMultiplyByPositiveNumber() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}).multiply(2.0f);
        float[][] expected = {{4.0f}, {8.0f}, {12.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyByZero() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}).multiply(0.0f);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyByNegativeNumber() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}).multiply(-1.0f);
        float[][] expected = {{-2.0f}, {-4.0f}, {-6.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideByPositiveNumber() {
        Vector3 result = new Vector3(new float[][]{{4.0f}, {8.0f}, {12.0f}}).divide(2.0f);
        float[][] expected = {{2.0f}, {4.0f}, {6.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideByOne() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}).divide(1.0f);
        float[][] expected = {{2.0f}, {4.0f}, {6.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideBySmallNumber() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}).divide(0.5f);
        float[][] expected = {{4.0f}, {8.0f}, {12.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddTwoVectors() {
        Vector3 result = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}).add(new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}}));
        float[][] expected = {{3.0f}, {5.0f}, {7.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddZeroVector() {
        Vector3 result = vector1.add(zeroVector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddCommutative() {
        Vector3 result1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}).add(new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}}));
        Vector3 result2 = new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}}).add(new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}));
        assertArrayEquals(result1.getVector(), result2.getVector());
    }

    @Test
    void testSubtractTwoVectors() {
        Vector3 result = new Vector3(new float[][]{{5.0f}, {7.0f}, {9.0f}}).subtract(new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}}));
        float[][] expected = {{3.0f}, {4.0f}, {5.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testSubtractSelf() {
        Vector3 result = vector1.subtract(vector1);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testSubtractZeroVector() {
        Vector3 result = vector1.subtract(zeroVector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testGetLength() {
        // sqrt(1² + 2² + 3²) = sqrt(1 + 4 + 9) = sqrt(14) ≈ 3.742
        Vector3 vec = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        assertEquals(3.742f, vec.getLength(), 0.001f);
    }

    @Test
    void testGetLengthOfZeroVector() {
        assertEquals(0.0f, zeroVector.getLength(), 0.001f);
    }

    @Test
    void testGetLengthOfUnitVector() {
        Vector3 unitVec = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}});
        assertEquals(1.0f, unitVec.getLength(), 0.001f);
    }

    @Test
    void testNormalize() {
        Vector3 vec = new Vector3(new float[][]{{3.0f}, {0.0f}, {0.0f}});
        Vector3 normalized = new Vector3(new float[][]{{3.0f}, {0.0f}, {0.0f}}).normalize();
        float[][] expected = {{1.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, normalized.getVector());
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testNormalizeAlreadyUnitVector() {
        Vector3 unitVec = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}});
        Vector3 normalized = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}}).normalize();
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testNormalizeGeneralVector() {
        Vector3 vec = new Vector3(new float[][]{{2.0f}, {2.0f}, {2.0f}});
        Vector3 normalized = new Vector3(new float[][]{{2.0f}, {2.0f}, {2.0f}}).normalize();
        // Длина после нормализации должна быть 1
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testScalarProduct() {
        Vector3 vec1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Vector3 vec2 = new Vector3(new float[][]{{2.0f}, {3.0f}, {4.0f}});
        // 1*2 + 2*3 + 3*4 = 2 + 6 + 12 = 20
        assertEquals(20.0f, vec1.scalarProduct(vec2), 0.001f);
    }

    @Test
    void testScalarProductWithZeroVector() {
        assertEquals(0.0f, vector1.scalarProduct(zeroVector), 0.001f);
    }

    @Test
    void testScalarProductCommutative() {
        float result1 = vector1.scalarProduct(vector2);
        float result2 = vector2.scalarProduct(vector1);
        assertEquals(result1, result2, 0.001f);
    }

    @Test
    void testVectorProduct() {
        Vector3 vec1 = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}});
        Vector3 vec2 = new Vector3(new float[][]{{0.0f}, {1.0f}, {0.0f}});
        Vector3 result = new Vector3(new float[][]{{1.0f}, {0.0f}, {0.0f}}).vectorProduct(vec2);
        // i × j = k = (0, 0, 1)
        float[][] expected = {{0.0f}, {0.0f}, {1.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testVectorProductAnticommutative() {
        Vector3 vec1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Vector3 vec2 = new Vector3(new float[][]{{4.0f}, {5.0f}, {6.0f}});

        Vector3 result1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}).vectorProduct(vec2);
        Vector3 result2 = new Vector3(new float[][]{{4.0f}, {5.0f}, {6.0f}}).vectorProduct(vec1);

        // a × b = -(b × a)
        assertArrayEquals(new float[][]{{-result1.getVector()[0][0]}, {-result1.getVector()[1][0]}, {-result1.getVector()[2][0]}}, result2.getVector());
    }

    @Test
    void testVectorProductWithZeroVector() {
        Vector3 result = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}).vectorProduct(zeroVector);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testVectorProductWithParallelVectors() {
        Vector3 vec1 = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}});
        Vector3 vec2 = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}}); // vec2 = 2 * vec1
        Vector3 result = new Vector3(new float[][]{{1.0f}, {2.0f}, {3.0f}}).vectorProduct(vec2);
        // Параллельные векторы дают нулевой вектор
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMethodChaining() {
        Vector3 result = new Vector3(new float[][]{{2.0f}, {4.0f}, {6.0f}})
                .multiply(2.0f)
                .add(new Vector3(new float[][]{{1.0f}, {1.0f}, {1.0f}}))
                .subtract(new Vector3(new float[][]{{1.0f}, {1.0f}, {1.0f}}))
                .normalize();

        // Проверяем, что длина нормализованного вектора равна 1
        assertEquals(1.0f, result.getLength(), 0.001f);
    }

    @Test
    void testGetVectorReturnsCorrectStructure() {
        float[][] vectorArray = vector1.getVector();
        assertEquals(3, vectorArray.length);
        assertEquals(1, vectorArray[0].length);
    }
}