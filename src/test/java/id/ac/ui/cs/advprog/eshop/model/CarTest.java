package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("1");
        this.car.setCarName("Nanang");
        this.car.setCarColor("Black");
        this.car.setCarQuantity(1);
    }

    @Test
    void testGetCarId() { assertEquals("1", this.car.getCarId()); }

    @Test
    void testGetCarName() { assertEquals("Nanang", this.car.getCarName()); }

    @Test
    void testGetCarColor() { assertEquals("Black", this.car.getCarColor()); }

    @Test
    void testGetCarQuantity() { assertEquals(1, this.car.getCarQuantity()); }
}
