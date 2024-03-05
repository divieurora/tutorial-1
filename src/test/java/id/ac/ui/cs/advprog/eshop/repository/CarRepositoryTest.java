package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testCreateCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        Car createdCar = carRepository.createCar(car);

        assertNotNull(createdCar);
        assertEquals("1", createdCar.getCarId());
        assertEquals("Nanang", createdCar.getCarName());
        assertEquals("Black", createdCar.getCarColor());
        assertEquals(1, createdCar.getCarQuantity());
    }

    @Test
    public void testCreateCarGeneratesUUID() {
        Car car = new Car();
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        Car createdCar = carRepository.createCar(car);

        assertNotNull(createdCar);
        assertNotNull(createdCar.getCarId());
    }

    @Test
    public void testCarFindById() {
        Car car1 = new Car();
        car1.setCarId("1");
        car1.setCarName("Nanang");
        car1.setCarColor("Black");
        car1.setCarQuantity(1);
        carRepository.createCar(car1);

        Car car2 = new Car();
        car2.setCarId("2");
        car2.setCarName("Pika");
        car2.setCarColor("Yellow");
        car2.setCarQuantity(2);
        carRepository.createCar(car2);

        Car foundCar = carRepository.findById("1");
        assertEquals("1", foundCar.getCarId());
        assertEquals("Nanang", foundCar.getCarName());
        assertEquals("Black", foundCar.getCarColor());
        assertEquals(1, foundCar.getCarQuantity());
    }

    @Test
    public void testCarFindByIdNegative() {
        Car car1 = new Car();
        car1.setCarId("1");
        car1.setCarName("Nanang");
        car1.setCarColor("Black");
        car1.setCarQuantity(1);
        carRepository.createCar(car1);

        Car car2 = new Car();
        car2.setCarId("2");
        car2.setCarName("Pika");
        car2.setCarColor("Yellow");
        car2.setCarQuantity(2);
        carRepository.createCar(car2);

        Car foundCar = carRepository.findById("3");
        assertNull(foundCar);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator <Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Car car1 = new Car();
        car1.setCarId("1");
        car1.setCarName("Nanang");
        car1.setCarColor("Black");
        car1.setCarQuantity(1);
        carRepository.createCar(car1);

        Car car2 = new Car();
        car2.setCarId("2");
        car2.setCarName("Pika");
        car2.setCarColor("Yellow");
        car2.setCarQuantity(2);
        carRepository.createCar(car2);

        Iterator <Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        List<Car> foundCars = new ArrayList<>();
        while (carIterator.hasNext()) {
            foundCars.add(carIterator.next());
        }
        assertEquals(2, foundCars.size());
        assertEquals(car1.getCarId(), foundCars.get(0).getCarId());
        assertEquals(car1.getCarName(), foundCars.get(0).getCarName());
        assertEquals(car1.getCarColor(), foundCars.get(0).getCarColor());
        assertEquals(car1.getCarQuantity(), foundCars.get(0).getCarQuantity());

        assertEquals(car2.getCarId(), foundCars.get(1).getCarId());
        assertEquals(car2.getCarName(), foundCars.get(1).getCarName());
        assertEquals(car2.getCarColor(), foundCars.get(1).getCarColor());
        assertEquals(car2.getCarQuantity(), foundCars.get(1).getCarQuantity());
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);
        carRepository.createCar(car);

        car.setCarName("Nunung");
        Car result = carRepository.update("1", car);
        assertEquals("Nunung", result.getCarName());
    }

    @Test
    void testUpdateCarNegative() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        assertDoesNotThrow(() -> carRepository.update("1", car));
    }

    @Test
    public void testUpdateCarData() {
        Car car = new Car();
        car.setCarName("Owew");
        car.setCarColor("Blue");
        car.setCarQuantity(3);
        Car createdCar = carRepository.createCar(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Towewew");
        updatedCar.setCarColor("Green");
        updatedCar.setCarQuantity(5);
        Car result = carRepository.update(createdCar.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("Towewew", result.getCarName());
        assertEquals("Green", result.getCarColor());
        assertEquals(5, result.getCarQuantity());

        assertEquals(1, carRepository.carData.size());
        Car foundCar = carRepository.carData.get(0);
        assertEquals("Towewew", foundCar.getCarName());
        assertEquals("Green", foundCar.getCarColor());
        assertEquals(5, foundCar.getCarQuantity());

        Car foundById = carRepository.findById(createdCar.getCarId());
        assertNotNull(foundById);
        assertEquals("Towewew", foundById.getCarName());
        assertEquals("Green", foundById.getCarColor());
        assertEquals(5, foundById.getCarQuantity());

        Car notExist = carRepository.update("a", updatedCar);
        assertNull(notExist);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);
        carRepository.createCar(car);

        carRepository.delete(car.getCarId());
        Car deletedCar = carRepository.findById(car.getCarId());
        assertNull(deletedCar);
    }
}
