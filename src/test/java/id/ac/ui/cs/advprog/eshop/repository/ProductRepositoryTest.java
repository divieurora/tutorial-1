package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindById() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);

        productRepository.create(product1);
        productRepository.create(product2);

        Product foundProduct = productRepository.findById("1");
        assertEquals(product1, foundProduct);
    }

    @Test
    public void testFindByIdNegative() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);

        productRepository.create(product1);
        productRepository.create(product2);

        Product foundProduct = productRepository.findById("3");
        assertNull(foundProduct);
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator <Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductNegative() {
        Product editedProduct = new Product();
        editedProduct.setProductId("12345");
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(200);

        assertDoesNotThrow(() -> productRepository.edit(editedProduct));
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("A");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Bango");
        Product result = productRepository.edit(product);
        assertEquals("Sampo Cap Bango", result.getProductName());
        Product notExist = new Product();
        result = productRepository.edit(notExist);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Product deletedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(deletedProduct);
    }
}