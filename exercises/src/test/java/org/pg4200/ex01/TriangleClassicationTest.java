package org.pg4200.ex01;

import org.junit.jupiter.api.Test;
import org.pg4200.ex01.TriangleClassification.Classification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pg4200.ex01.TriangleClassification.Classification.*;
import static org.pg4200.ex01.TriangleClassification.classify;

public class TriangleClassicationTest {

    @Test
    public void not_a_triangle_zero_length_side() {
        Classification res = classify(0, 60, 60);
        assertEquals(res, NOT_A_TRIANGLE);
    }

    @Test
    public void all_equal_sides() {
        Classification res = classify(60, 60, 60);
        assertEquals(res, EQUILATERAL);
    }

    @Test
    public void not_a_triangle_too_short_sides() {
        Classification res = classify( 120, 40, 20);
        assertEquals(res, NOT_A_TRIANGLE);
    }

    @Test
    public void two_equal_sides() {
        Classification res = classify(30, 20, 20);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void all_sides_different() {
        Classification res = classify(30, 20, 15);
        assertEquals(res, SCALENE);
    }
}
