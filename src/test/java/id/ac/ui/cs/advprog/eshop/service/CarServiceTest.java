package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        Mockito.when(carRepository.createCar(car)).thenReturn(car);
        carService.create(car);

        Mockito.when(carRepository.findById("1")).thenReturn(car);
        Car savedCar = carService.findById("1");
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllCar() {
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car();
        car1.setCarId("1");
        car1.setCarName("Nanang");
        car1.setCarColor("Black");
        car1.setCarQuantity(1);

        Car car2 = new Car();
        car2.setCarId("2");
        car2.setCarName("Pika");
        car2.setCarColor("Yellow");
        car2.setCarQuantity(2);

        carList.add(car1);
        carList.add(car2);

        Mockito.when(carRepository.findAll()).thenReturn(carList.iterator());
        List<Car> cars = carService.findAll();

        assertEquals(carList.size(), cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        Mockito.when(carRepository.createCar(car)).thenReturn(car);
        carService.create(car);
        verify(carRepository).createCar(car);
        Mockito.when(carRepository.update("1", car)).thenReturn(car);
        carService.update("1", car);
        verify(carRepository).update("1", car);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Nanang");
        car.setCarColor("Black");
        car.setCarQuantity(1);

        carService.deleteCarById(car.getCarId());
        verify(carRepository).delete(car.getCarId());
    }
}
