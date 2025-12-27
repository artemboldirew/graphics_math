package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector3f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector3fTest {

    private final float DELTA = 0.0001f;

    @Test
    void testConstructor() {
        float[] data = {1.0f, 2.0f, 3.0f};
        Vector3f vector = new Vector3f(data);
        assertArrayEquals(data, vector.getVector(), DELTA);
    }

    @Test
    void testAdd() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f b = new Vector3f(new float[]{4.0f, 5.0f, 6.0f});
        Vector3f result = a.add(b);
        assertArrayEquals(new float[]{5.0f, 7.0f, 9.0f}, result.getVector(), DELTA);
    }

    @Test
    void testAddInPlace() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f b = new Vector3f(new float[]{4.0f, 5.0f, 6.0f});
        Vector3f result = a.addInPlace(b);
        assertArrayEquals(new float[]{5.0f, 7.0f, 9.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testSub() {
        Vector3f a = new Vector3f(new float[]{5.0f, 6.0f, 7.0f});
        Vector3f b = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f result = a.sub(b);
        assertArrayEquals(new float[]{4.0f, 4.0f, 4.0f}, result.getVector(), DELTA);
    }

    @Test
    void testSubInPlace() {
        Vector3f a = new Vector3f(new float[]{5.0f, 6.0f, 7.0f});
        Vector3f b = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f result = a.subInPlace(b);
        assertArrayEquals(new float[]{4.0f, 4.0f, 4.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testMul() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f result = a.mul(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f, 6.0f}, result.getVector(), DELTA);
    }

    @Test
    void testMulInPlace() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f result = a.mulInPlace(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f, 6.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDiv() {
        Vector3f a = new Vector3f(new float[]{2.0f, 4.0f, 6.0f});
        Vector3f result = a.div(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f}, result.getVector(), DELTA);
    }

    @Test
    void testDivInPlace() {
        Vector3f a = new Vector3f(new float[]{2.0f, 4.0f, 6.0f});
        Vector3f result = a.divInPlace(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f, 3.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testGetLength() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 2.0f});
        assertEquals(3.0f, a.getLength(), DELTA);
    }

    @Test
    void testNormalize() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 2.0f});
        Vector3f result = a.normalize();
        assertEquals(1.0f, result.getLength(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDot() {
        Vector3f a = new Vector3f(new float[]{1.0f, 2.0f, 3.0f});
        Vector3f b = new Vector3f(new float[]{4.0f, 5.0f, 6.0f});
        assertEquals(32.0f, a.dot(b), DELTA);
    }

    @Test
    void testCross() {
        Vector3f a = new Vector3f(new float[]{1.0f, 0.0f, 0.0f});
        Vector3f b = new Vector3f(new float[]{0.0f, 1.0f, 0.0f});
        Vector3f result = a.cross(b);
        assertArrayEquals(new float[]{0.0f, 0.0f, 1.0f}, result.getVector(), DELTA);
    }

    @Test
    void testGetXGetYGetZ() {
        Vector3f a = new Vector3f(new float[]{1.5f, 2.5f, 3.5f});
        assertEquals(1.5f, a.getX(), DELTA);
        assertEquals(2.5f, a.getY(), DELTA);
        assertEquals(3.5f, a.getZ(), DELTA);
    }
}