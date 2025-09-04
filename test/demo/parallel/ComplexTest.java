package demo.parallel;

import org.junit.jupiter.api.Test;

import demo.parallel.Complex;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    private static final double EPS = 1e-9;

    @Test
    void plus_shouldAddInPlace_andReturnThis() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(-4, 5);
        Complex ret = a.plus(b);
        assertSame(a, ret, "plus must return this");
        assertEquals(-3, a.re(), EPS);
        assertEquals(7,  a.im(), EPS);
    }

    @Test
    void minus_shouldSubtractInPlace_andReturnThis() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(-4, 5);
        Complex ret = a.minus(b);
        assertSame(a, ret);
        assertEquals(5,  a.re(), EPS);
        assertEquals(-3, a.im(), EPS);
    }

    @Test
    void times_complex_shouldMultiplyInPlace_andReturnThis() {
        Complex a = new Complex(3, 2);
        Complex b = new Complex(-5, 4);
        // (3+2i)*(-5+4i) = (-23 + 2i)
        Complex ret = a.times(b);
        assertSame(a, ret);
        assertEquals(-23, a.re(), EPS);
        assertEquals(  2, a.im(), EPS);
    }

    @Test
    void times_scalar_shouldScaleInPlace() {
        Complex a = new Complex(-3.5, 2.0);
        a.times(2.0);
        assertEquals(-7.0, a.re(), EPS);
        assertEquals( 4.0, a.im(), EPS);
    }

    @Test
    void conjugate_shouldFlipImagSign_inPlace() {
        Complex a = new Complex(1.25, -3.75);
        a.conjugate(); // -> 1.25 + 3.75i
        assertEquals( 1.25, a.re(), EPS);
        assertEquals( 3.75, a.im(), EPS);
        a.conjugate(); // обратно
        assertEquals( 1.25, a.re(), EPS);
        assertEquals(-3.75, a.im(), EPS);
    }

    @Test
    void copy_shouldBeIndependentObject() {
        Complex a = new Complex(1, 1);
        Complex c = a.copy();
        assertNotSame(a, c);
        a.plus(new Complex(1, 2)); // меняем a
        assertEquals(1, c.re(), EPS);
        assertEquals(1, c.im(), EPS);
    }

    @Test
    void lengthSQ_shouldMatchFormula() {
        Complex a = new Complex(3, -4);
        assertEquals(25, a.lengthSQ(), EPS);
    }

    @Test
    void chaining_shouldWorkLeftToRight() {
        Complex a = new Complex(1, 1);
        // ((1+i) + (2-3i)) * (0.5) -> (3 - 2i) * 0.5 -> (1.5 - i)
        a.plus(new Complex(2, -3)).times(0.5);
        assertEquals(1.5, a.re(), EPS);
        assertEquals(-1.0, a.im(), EPS);
    }
}