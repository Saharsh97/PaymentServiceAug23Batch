package com.example.payments.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    String orderId;
    Long amount;
}

// 100.90 -> 10090
// 58.75  -> 5875
// 500000.00 -> 50000000
// 60 -> 6000
