package com.example.payments.controllers;

import com.example.payments.PaymentsApplication;
import com.example.payments.dtos.PaymentRequestDTO;
import com.example.payments.services.PaymentService;
import com.example.payments.services.RazorpayPaymentService;
import com.example.payments.services.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public String createPaymentLink(@RequestBody PaymentRequestDTO paymentRequestDTO) throws StripeException {
        String url = paymentService.generatePaymentLink(
                                paymentRequestDTO.getOrderId(),
                                paymentRequestDTO.getAmount()
                        );
        return url;
    }

    @PostMapping("/webhooks")
    public String handleWebhook(){
        System.out.println("request for webhook received");
        System.out.println("updating db....");
        return "webhook completed";
    }
}

