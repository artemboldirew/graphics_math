package vector3d.tests;

import io.github.artemboldirew.vector3d.core.Vector2f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2fTest {

    private final float DELTA = 0.0001f;

    @Test
    void testConstructor() {
        float[] data = {1.0f, 2.0f};
        Vector2f vector = new Vector2f(data);
        assertArrayEquals(data, vector.getVector(), DELTA);
    }

    @Test
    void testAdd() {
        Vector2f a = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f b = new Vector2f(new float[]{3.0f, 4.0f});
        Vector2f result = a.add(b);
        assertArrayEquals(new float[]{4.0f, 6.0f}, result.getVector(), DELTA);
    }

    @Test
    void testAddInPlace() {
        Vector2f a = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f b = new Vector2f(new float[]{3.0f, 4.0f});
        Vector2f result = a.addInPlace(b);
        assertArrayEquals(new float[]{4.0f, 6.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testSub() {
        Vector2f a = new Vector2f(new float[]{5.0f, 6.0f});
        Vector2f b = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f result = a.sub(b);
        assertArrayEquals(new float[]{4.0f, 4.0f}, result.getVector(), DELTA);
    }

    @Test
    void testSubInPlace() {
        Vector2f a = new Vector2f(new float[]{5.0f, 6.0f});
        Vector2f b = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f result = a.subInPlace(b);
        assertArrayEquals(new float[]{4.0f, 4.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testMul() {
        Vector2f a = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f result = a.mul(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f}, result.getVector(), DELTA);
    }

    @Test
    void testMulInPlace() {
        Vector2f a = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f result = a.mulInPlace(2.0f);
        assertArrayEquals(new float[]{2.0f, 4.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDiv() {
        Vector2f a = new Vector2f(new float[]{2.0f, 4.0f});
        Vector2f result = a.div(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f}, result.getVector(), DELTA);
    }

    @Test
    void testDivInPlace() {
        Vector2f a = new Vector2f(new float[]{2.0f, 4.0f});
        Vector2f result = a.divInPlace(2.0f);
        assertArrayEquals(new float[]{1.0f, 2.0f}, a.getVector(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testGetLength() {
        Vector2f a = new Vector2f(new float[]{3.0f, 4.0f});
        assertEquals(5.0f, a.getLength(), DELTA);
    }

    @Test
    void testNormalize() {
        Vector2f a = new Vector2f(new float[]{3.0f, 4.0f});
        Vector2f result = a.normalize();
        assertEquals(1.0f, result.getLength(), DELTA);
        assertSame(a, result);
    }

    @Test
    void testDot() {
        Vector2f a = new Vector2f(new float[]{1.0f, 2.0f});
        Vector2f b = new Vector2f(new float[]{3.0f, 4.0f});
        assertEquals(11.0f, a.dot(b), DELTA);
    }

    @Test
    void testGetXGetY() {
        Vector2f a = new Vector2f(new float[]{1.5f, 2.5f});
        assertEquals(1.5f, a.getX(), DELTA);
        assertEquals(2.5f, a.getY(), DELTA);
    }
}