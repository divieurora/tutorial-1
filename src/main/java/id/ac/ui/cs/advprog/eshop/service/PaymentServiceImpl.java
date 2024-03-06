package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.enums.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment;
        if (method.equals(PaymentMethod.VOUCHER.getValue())){
            payment = createPaymentVoucher(order, method, paymentData);
        }
        else if (method.equals(PaymentMethod.COD.getValue())){
            payment = createPaymentCOD(order, method, paymentData);
        }
        else{
            payment = new Payment(order, method, paymentData);
        }
        return paymentRepository.save(payment);
    }

    @Override
    public void setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (payment.getStatus().equals(PaymentStatus.SUCCESS.getValue())){
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        }else if(payment.getStatus().equals(PaymentStatus.REJECTED.getValue())){
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.getAllPayments();
    }

    public Payment createPaymentVoucher(Order order, String method, Map<String, String> paymentData){
        return new PaymentVoucher(order,method,paymentData);
    }
    public Payment createPaymentCOD(Order order, String method, Map<String, String>paymentData){
        return new PaymentCOD(order,method,paymentData);
    }

}
