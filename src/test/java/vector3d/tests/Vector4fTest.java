package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector4f;
import io.github.artemboldirew.vector3d.core.Vector3f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector4fTest {

    private final float DELTA = 0.0001f;

    @Test
    void testConstructor() {
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        Vector4f vector = new Vector4f(data);
        assertArrayEquals(data, vector.getVector(), DELTA);
    }

    @Test
    void testAdd() {
        Vector4f a = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f b = new Vector4f(new float[]{5.0f, 6.0f, 7.0f, 8.0f});
        Vector4f result = a.add(b);
        assertArrayEquals(new float[]{6.0f, 8.0f, 10.0f, 12.0f}, result.getVector(), DELTA);
    }

    @Test
    void testAddInPlace() {
        Vector4f a = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f b = new Vector4f(new float[]{5.0f, 6.0f, 7.0f, 8.0f});
        Vector4f result = a.addInPlace(b);
        assertArrayEquals(new float[]{6.0f, 8.0f, 10.0f, 12.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testSub() {
        Vector4f a = new Vector4f(new float[]{5.0f, 6.0f, 7.0f, 8.0f});
        Vector4f b = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f result = a.sub(b);
        assertArrayEquals(new float[]{4.0f, 4.0f, 4.0f, 4.0f}, result.getVector(), DELTA);
    }

    @Test
    void testSubInPlace() {
        Vector4f a = new Vector4f(new float[]{5.0f, 6.0f, 7.0f, 8.0f});
        Vector4f b = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f result = a.subInPlace(b);
        assertArrayEquals(new float[]{4.0f, 4.0f, 4.0f, 4.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testMul() {
        Vector4f a = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f result = a.mul(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f, 6.0f, 8.0f}, result.getVector(), DELTA);
    }

    @Test
    void testMulInPlace() {
        Vector4f a = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f result = a.mulInPlace(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f, 6.0f, 8.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDiv() {
        Vector4f a = new Vector4f(new float[]{2.0f, 4.0f, 6.0f, 8.0f});
        Vector4f result = a.div(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f, 4.0f}, result.getVector(), DELTA);
    }

    @Test
    void testDivInPlace() {
        Vector4f a = new Vector4f(new float[]{2.0f, 4.0f, 6.0f, 8.0f});
        Vector4f result = a.divInPlace(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f, 4.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testGetLength() {
        Vector4f a = new Vector4f(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        assertEquals(2.0f, a.getLength(), DELTA);
    }

    @Test
    void testNormalize() {
        Vector4f a = new Vector4f(new float[]{2.0f, 0.0f, 0.0f, 0.0f});
        Vector4f result = a.normalize();
        assertEquals(1.0f, result.getLength(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDot() {
        Vector4f a = new Vector4f(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        Vector4f b = new Vector4f(new float[]{5.0f, 6.0f, 7.0f, 8.0f});
        assertEquals(70.0f, a.dot(b), DELTA);
    }

    @Test
    void testNdc() {
        Vector4f a = new Vector4f(new float[]{2.0f, 4.0f, 6.0f, 2.0f});
        Vector3f result = a.ndc();
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f}, result.getVector(), DELTA);
        // Проверяем, что исходный вектор не изменился
        assertArrayEquals(new float[]{2.0f, 4.0f, 6.0f, 2.0f}, a.getVector(), DELTA);
    }

    @Test
    void testNdcInPlace() {
        Vector4f a = new Vector4f(new float[]{2.0f, 4.0f, 6.0f, 2.0f});
        Vector4f result = a.ndcInPlace();
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f, 1.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testGetXGetYGetZGetW() {
        Vector4f a = new Vector4f(new float[]{1.5f, 2.5f, 3.5f, 4.5f});
        assertEquals(1.5f, a.getX(), DELTA);
        assertEquals(2.5f, a.getY(), DELTA);
        assertEquals(3.5f, a.getZ(), DELTA);
        assertEquals(4.5f, a.getW(), DELTA);
    }
}