package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @BeforeEach
    void setUp() {
    }

    @InjectMocks
    HomeController homePageController;

    @Test
    void homePageTitle() {
        String result = homePageController.homePage();
        assertEquals("HomePage", result);
    }
}