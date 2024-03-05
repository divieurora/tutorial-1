package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @InjectMocks
    CarController carController;
    @Mock
    CarServiceImpl carService;

    @Test
    void testCreateCarPage() {
        Model model = mock(Model.class);
        String result = carController.createCarPage(model);
        assertEquals("createCar", result);
    }

    @Test
    void testCreateCarPost() {
        Model model = mock(Model.class);
        String result = carController.createCarPost(new Car(), model);
        assertEquals("redirect:listCar", result);
    }

    @Test
    void testCarListPost() {
        Model model = mock(Model.class);
        String result = carController.carListPage(model);
        assertEquals("carList", result);
    }

    @Test
    void testDeleteProduct() {
        String carId = "1";
        String result = carController.deleteCar("1");
        assertEquals("redirect:listCar", result);
    }

    @Test
    void testEditCarPage() {
        Model model = mock(Model.class);
        Car foundCar = new Car();
        Mockito.when(carService.findById("1")).thenReturn(foundCar);

        String result = carController.editCarPage("1", model);

        Mockito.verify(carService).findById("1");
        assertEquals("editCar", result);
        Mockito.verify(model).addAttribute("car", foundCar);
    }

    @Test
    void testEditCarPost() {
        Model model = mock(Model.class);
        Car car = new Car();
        String result = carController.editCarPost(car, model);
        assertEquals("redirect:listCar", result);
    }
}
