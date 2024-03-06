package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentVoucher;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp() {
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
    void testAddPaymentVoucher() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP12345678OYY");
        Payment paymentVoucher = payments.get(0);
        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));
        paymentVoucher = paymentService.addPayment(paymentVoucher.getOrder(), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);

        doReturn(paymentVoucher).when(paymentRepository).findById(paymentVoucher.getId());
        Payment findResult = paymentService.getPayment(paymentVoucher.getId());

        assertEquals(paymentVoucher.getId(),findResult.getId() );
        assertEquals(paymentVoucher.getMethod(), findResult.getMethod() );
        assertEquals(paymentVoucher.getStatus(), findResult.getStatus() );
    }

    @Test
    void testAddPaymentCOD() {
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "Jl. Jalan");
        paymentDataCOD.put("deliveryFee", "100000");
        Payment paymentCOD = payments.get(1);
        doReturn(paymentCOD).when(paymentRepository).save(any(Payment.class));
        paymentCOD = paymentService.addPayment(paymentCOD.getOrder(), PaymentMethod.COD.getValue(), paymentDataCOD);

        doReturn(paymentCOD).when(paymentRepository).findById(paymentCOD.getId());
        Payment findResult = paymentService.getPayment(paymentCOD.getId());

        assertEquals(paymentCOD.getId(),findResult.getId() );
        assertEquals(paymentCOD.getMethod(), findResult.getMethod() );
        assertEquals(paymentCOD.getStatus(), findResult.getStatus() );
    }

    @Test
    void testGetAllPayment() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> payment = paymentService.getAllPayment();
        assertSame(payments,payment);
    }

    @Test
    void testGetPaymentIfFound() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testSetStatus() {
        assertEquals(PaymentStatus.SUCCESS.getValue(),payments.get(0).getStatus());
        paymentService.setStatus(payments.get(0), PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payments.get(0).getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payments.get(0).getOrder().getStatus());

        paymentService.setStatus(payments.get(0), PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(),payments.get(0).getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payments.get(0).getOrder().getStatus());
    }
}