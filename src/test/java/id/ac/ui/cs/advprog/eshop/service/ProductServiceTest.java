package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
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
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("Product A");
        product.setProductName("Barang");
        product.setProductQuantity(69);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        Mockito.when(productRepository.findById("Product A")).thenReturn(product);
        Product savedProduct = productService.findById("Product A");
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }


    @Test
    void testEditProduct(){
        Product product = new Product();
        product.setProductId("Product A");
        product.setProductName("Sampo");
        product.setProductQuantity(50);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        Mockito.when(productRepository.edit(product)).thenReturn(product);
        Product resultEdit = productService.edit(product);

        assertEquals("Product A", resultEdit.getProductId());
        assertEquals("Sampo", resultEdit.getProductName());
        assertEquals(50, resultEdit.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        // Test for delete method
        Product product = new Product();
        product.setProductId("Product A");
        product.setProductName("Barang");
        product.setProductQuantity(69);

        Mockito.when(productRepository.delete(product.getProductId())).thenReturn(product);
        Product deletedProduct = productService.delete(product);

        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertEquals(product.getProductName(), deletedProduct.getProductName());
        assertEquals(product.getProductQuantity(), deletedProduct.getProductQuantity());
    }

    @Test
    void testFindAllProducts() {
        // Test for findAll method
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("Product A");
        product1.setProductName("Barang");
        product1.setProductQuantity(69);

        Product product2 = new Product();
        product2.setProductId("Product B");
        product2.setProductName("Sampo");
        product2.setProductQuantity(50);

        productList.add(product1);
        productList.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(productList.iterator());
        List<Product> resultProducts = productService.findAll();

        assertEquals(productList.size(), resultProducts.size());
        assertTrue(resultProducts.contains(product1));
        assertTrue(resultProducts.contains(product2));
    }
}