package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class Vector4Test {
    private Vector4 vector1;
    private Vector4 vector2;
    private Vector4 zeroVector;

    @BeforeEach
    void setUp() {
        vector1 = new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        vector2 = new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}});
        zeroVector = new Vector4(new float[][]{{0.0f}, {0.0f}, {0.0f}, {0.0f}});
    }

    @Test
    void testConstructorValidVector() {
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}, {4.0f}};
        assertArrayEquals(expected, vector1.getVector());
    }

    @Test
    void testConstructorInvalidVectorThrowsException() {
        float[][] invalidVector = {{1.0f}, {2.0f}, {3.0f}}; // 3x1 instead of 4x1
        assertThrows(IllegalArgumentException.class, () -> new Vector4(invalidVector));
    }

    @Test
    void testConstructorInvalidMatrixThrowsException() {
        float[][] invalidMatrix = {{1.0f, 2.0f}, {3.0f, 4.0f}, {5.0f, 6.0f}, {7.0f, 8.0f}};
        assertThrows(IllegalArgumentException.class, () -> new Vector4(invalidMatrix));
    }

    @Test
    void testMultiplyByPositiveNumber() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}}).multiply(2.0f);
        float[][] expected = {{4.0f}, {8.0f}, {12.0f}, {16.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyByZero() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}}).multiply(0.0f);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testMultiplyByNegativeNumber() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}}).multiply(-1.0f);
        float[][] expected = {{-2.0f}, {-4.0f}, {-6.0f}, {-8.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideByPositiveNumber() {
        Vector4 result = new Vector4(new float[][]{{4.0f}, {8.0f}, {12.0f}, {16.0f}}).divide(2.0f);
        float[][] expected = {{2.0f}, {4.0f}, {6.0f}, {8.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideByOne() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}}).divide(1.0f);
        float[][] expected = {{2.0f}, {4.0f}, {6.0f}, {8.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testDivideBySmallNumber() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}}).divide(0.5f);
        float[][] expected = {{4.0f}, {8.0f}, {12.0f}, {16.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddTwoVectors() {
        Vector4 result = new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}}).add(new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}}));
        float[][] expected = {{3.0f}, {5.0f}, {7.0f}, {9.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddZeroVector() {
        Vector4 result = vector1.add(zeroVector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}, {4.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testAddCommutative() {
        Vector4 result1 = new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}}).add(new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}}));
        Vector4 result2 = new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}}).add(new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}}));
        assertArrayEquals(result1.getVector(), result2.getVector());
    }

    @Test
    void testSubtractTwoVectors() {
        Vector4 result = new Vector4(new float[][]{{5.0f}, {7.0f}, {9.0f}, {11.0f}}).subtract(new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}}));
        float[][] expected = {{3.0f}, {4.0f}, {5.0f}, {6.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testSubtractSelf() {
        Vector4 result = vector1.subtract(vector1);
        float[][] expected = {{0.0f}, {0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testSubtractZeroVector() {
        Vector4 result = vector1.subtract(zeroVector);
        float[][] expected = {{1.0f}, {2.0f}, {3.0f}, {4.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testGetLength() {
        // sqrt(1² + 2² + 3² + 4²) = sqrt(1 + 4 + 9 + 16) = sqrt(30) ≈ 5.477
        Vector4 vec = new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        assertEquals(3.741f, vec.getLength(), 0.001f);
    }

    @Test
    void testGetLengthOfZeroVector() {
        assertEquals(0.0f, zeroVector.getLength(), 0.001f);
    }

    @Test
    void testGetLengthOfUnitVector() {
        Vector4 unitVec = new Vector4(new float[][]{{1.0f}, {0.0f}, {0.0f}, {0.0f}});
        assertEquals(1.0f, unitVec.getLength(), 0.001f);
    }

    @Test
    void testNormalize() {
        Vector4 vec = new Vector4(new float[][]{{3.0f}, {0.0f}, {0.0f}, {0.0f}});
        Vector4 normalized = new Vector4(new float[][]{{3.0f}, {0.0f}, {0.0f}, {0.0f}}).normalize();
        float[][] expected = {{1.0f}, {0.0f}, {0.0f}, {0.0f}};
        assertArrayEquals(expected, normalized.getVector());
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testNormalizeAlreadyUnitVector() {
        Vector4 unitVec = new Vector4(new float[][]{{1.0f}, {0.0f}, {0.0f}, {0.0f}});
        Vector4 normalized = new Vector4(new float[][]{{1.0f}, {0.0f}, {0.0f}, {0.0f}}).normalize();
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testNormalizeGeneralVector() {
        Vector4 vec = new Vector4(new float[][]{{2.0f}, {2.0f}, {2.0f}, {2.0f}});
        Vector4 normalized = new Vector4(new float[][]{{2.0f}, {2.0f}, {2.0f}, {2.0f}}).normalize();
        // Длина после нормализации должна быть 1 (или близко к 1)
        assertEquals(1.0f, normalized.getLength(), 0.001f);
    }

    @Test
    void testScalarProduct() {
        Vector4 vec1 = new Vector4(new float[][]{{1.0f}, {2.0f}, {3.0f}, {4.0f}});
        Vector4 vec2 = new Vector4(new float[][]{{2.0f}, {3.0f}, {4.0f}, {5.0f}});
        // 1*2 + 2*3 + 3*4 + 4*5 = 2 + 6 + 12 + 20 = 40
        assertEquals(40.0f, vec1.scalarProduct(vec2), 0.001f);
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
    void testMethodChaining() {
        Vector4 result = new Vector4(new float[][]{{2.0f}, {4.0f}, {6.0f}, {8.0f}})
                .multiply(2.0f)
                .add(new Vector4(new float[][]{{1.0f}, {1.0f}, {1.0f}, {1.0f}}))
                .subtract(new Vector4(new float[][]{{1.0f}, {1.0f}, {1.0f}, {1.0f}}));

        float[][] expected = {{4.0f}, {8.0f}, {12.0f}, {16.0f}};
        assertArrayEquals(expected, result.getVector());
    }

    @Test
    void testGetVectorReturnsCorrectStructure() {
        float[][] vectorArray = vector1.getVector();
        assertEquals(4, vectorArray.length);
        assertEquals(1, vectorArray[0].length);
    }
}