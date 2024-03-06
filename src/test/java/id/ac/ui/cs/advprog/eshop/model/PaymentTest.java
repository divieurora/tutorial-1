package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Payment> payments;
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        this.orders.add(order1);
        Order order2 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Bambang Sudrajat");
        this.orders.add(order2);
    }

    @Test
    public void testCreatePaymentWithEmptyMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678OYY");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(new Order(orders.get(0).getId(), orders.get(0).getProducts(), orders.get(0).getOrderTime(),
                    orders.get(0).getAuthor()), "", paymentData);
        }, "Method cannot be empty");
    }

    @Test
    public void testCreatePaymentWithNullMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678OYY");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(new Order(orders.get(0).getId(), orders.get(0).getProducts(), orders.get(0).getOrderTime(),
                    orders.get(0).getAuthor()), null, paymentData);
        }, "Method cannot be null");
    }

    @Test
    public void testCreatePaymentSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678OYY");
        Payment payment = new Payment("1", orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(orders.get(0), payment.getOrder());
    }

    @Test
    public void testCreatePaymentWithInvalidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentData);
        });
    }

    @Test
    public void testCreatePaymentNotStartWithEshop() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOK12345678AAA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentData);
        });
    }

    @Test
    public void testCreatePaymentNotEquals8Numbers() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234567890A");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentData);
        });
    }

    @Test
    public void testCreatePaymentCODSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Jalan");
        paymentData.put("deliveryFee", "100000");
        Payment payment = new Payment("2", orders.get(1), PaymentMethod.COD.getValue(), paymentData);
        assertEquals(PaymentMethod.COD.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(orders.get(1), payment.getOrder());
    }

    @Test
    public void testCreatePaymentCODBlankAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "100000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2", orders.get(1), PaymentMethod.COD.getValue(), paymentData);
        });
    }

    @Test
    public void testCreatePaymentCODBlankDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Jalan");
        paymentData.put("deliveryFee", "");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2", orders.get(1), PaymentMethod.COD.getValue(), paymentData);
        });
    }

    @Test
    public void testCreatePaymentWithDifferentMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", orders.get(0), "OTHER_METHOD", paymentData);
        });
    }

}
