package com.example.payments.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentService implements PaymentService{

    @Override
    public String generatePaymentLink(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51Pda52KxepUPyLsyrd5lAGiD83nPEoNrgvytCIlL0laArUJs7pkLSYBnQzd7IFaiqKzgnVuXap6M1x8o94q7AB8900lokGblNr";

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();

        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
