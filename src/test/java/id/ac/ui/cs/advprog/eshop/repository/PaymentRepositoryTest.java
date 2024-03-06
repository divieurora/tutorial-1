package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        payments = new ArrayList<>();
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP12345678OYY");
        Payment paymentVoucher = new Payment("1", orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        payments.add(paymentVoucher);
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "Jl. Jalan");
        paymentDataCOD.put("deliveryFee", "100000");
        Payment paymentCOD = new Payment("2", orders.get(0), PaymentMethod.COD.getValue(), paymentDataCOD);
        payments.add(paymentCOD);
    }

    @Test
    void testFindByIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(findResult.getId(), payments.get(0).getId());
        assertEquals(findResult.getOrder(), payments.get(0).getOrder());
        assertEquals(findResult.getMethod(), payments.get(0).getMethod());
        assertEquals(findResult.getPaymentData(), payments.get(0).getPaymentData());
    }

    @Test
    void testFindByIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("omo");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(2, result.size());
    }
}
