import objects.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    public void shouldCreateTriangle() {
        Triangle triangle = Triangle.create(100, 30, 50);
        Assertions.assertNotNull(triangle);
    }

    @Test
    public void shouldNotCreateTriangleAndThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(0, 0, 0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(0, 90, 90));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(90, 0, 90));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(90, 90, 0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(-10, 100, 90));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(90, -10, 100));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(100, 90, -10));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(80, 30, 50));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.create(80, 130, 50));
    }
}
