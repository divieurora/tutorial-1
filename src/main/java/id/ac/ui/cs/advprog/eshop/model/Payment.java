package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;

    public Payment(String id,  Order order, String method, Map<String, String>paymentData){
        this(order, method, paymentData);
        this.id = id;
    }
    public Payment(Order order, String method, Map<String, String>paymentData){
        if (method == null || method.trim().isEmpty()) {
            throw new IllegalArgumentException("Method cannot be empty");
        }
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.order = order;
        try{
            setPaymentData(paymentData);
            setStatus(PaymentStatus.SUCCESS.getValue());
        }catch (IllegalArgumentException e){
            setStatus(PaymentStatus.REJECTED.getValue());
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status=status;
        }else{
            throw new IllegalArgumentException();
        }
    }

    protected void setPaymentData(Map<String, String> paymentData){
        if (PaymentMethod.contains(this.method)) {
            throw new IllegalArgumentException();
        } else{
            this.paymentData = null;
        }
    }
}