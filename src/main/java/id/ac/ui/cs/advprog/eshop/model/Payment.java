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
        setPaymentData(paymentData);
    }

    private void setPaymentData(Map<String, String>paymentData){
        if (method.equals(PaymentMethod.VOUCHER.getValue())){
            int numbers = 0;
            for (int i=0; i<paymentData.get("voucherCode").length(); i++){
                if (Character.isDigit(paymentData.get("voucherCode").charAt(i))){
                    numbers+=1;
                }
            }
            if (paymentData.get("voucherCode").length()!=16 ||
                    !paymentData.get("voucherCode").startsWith("ESHOP") ||
                    numbers!=8){
                throw new IllegalArgumentException();
            }
        } else if (method.equals(PaymentMethod.COD.getValue())){
            if (paymentData.get("address").isBlank() ||
                    paymentData.get("deliveryFee").isBlank()){
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
    }
}