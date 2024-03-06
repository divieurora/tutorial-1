package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id,  Order order, String method, Map<String, String>paymentData){
    }
    public Payment(Order order, String method, Map<String, String>paymentData){
    }

}