package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.*;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Payment addPayment(Order order, String method, Map<String, String> paymentData);
    void setStatus(Payment payment, String status);
    Payment getPayment(String id);
    List<Payment> getAllPayment();
}
