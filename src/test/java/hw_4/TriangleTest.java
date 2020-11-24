package hw_4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static hw_4.Triangle.triangle;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TriangleTest {

    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @DisplayName("Корректный тест")
    @ParameterizedTest
    @CsvSource({
            "2, 3, 4",
            "1, 2, 2",
            "3, 3, 5",
            "2, 2, 3"
    })
    public void triangleTest(int a, int b, int c) {
        System.out.println(triangle(a, b, c));
        assertEquals(triangle(a, b, c), triangle(a, b, c));
    }

    @DisplayName("Не треугольник")
    @Test
    public void NotTriangle() {
        assertEquals(0, triangle(1, 2, 3));
    }

    @DisplayName("Неправильное ожидание")
    @Test
    public void triangleTestFalse(int a, int b, int c){
        System.out.println(triangle(a, b, c));
        assertEquals(triangle(2, 3, 4), triangle(1, 2, 2));
    }

    @TestFactory
    public List<DynamicTest> triangleTestFactory(){
        int a = 3;
        int b = 4;
        int c = 5;
        return Arrays.asList(
                dynamicTest("Правильный expected", () -> assertEquals(6, triangle(a, b, c))),
                dynamicTest("Неправильный actual", () -> assertEquals(6, triangle(a * 2, b * 2, c * 2))),
                dynamicTest("Неправильный expected", () -> assertEquals(23, triangle(a * 2, b * 2, c * 2)))
        );
    }
}
