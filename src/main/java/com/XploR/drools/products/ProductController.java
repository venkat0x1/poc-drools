package com.XploR.drools.products;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private  KieContainer kieContainer;

    @PostMapping("/final-price")
    private String getProductFinalPrice(@RequestBody ProductFinalPriceDTO productFinalPriceRequest) {

        // create a new kie session
        KieSession kieSession = kieContainer.newKieSession();

        ProductFinalPriceDTO productFinalPriceDTO = new ProductFinalPriceDTO();

        // Let's assume we have one product with id = 1, price = 1000
        productFinalPriceDTO.setProductId(1);
        productFinalPriceDTO.setFinalPrice(1000.0);

        // set the credit card type
        productFinalPriceDTO.setCreditCardType(productFinalPriceRequest.getCreditCardType());

        // pass the object to the session
        kieSession.setGlobal("productFinalPriceDTO", productFinalPriceDTO);
        kieSession.insert(productFinalPriceDTO);

        // fire all rules
        kieSession.fireAllRules();
        kieSession.dispose();
        return "Your product final price is " + productFinalPriceDTO.getFinalPrice();
    }
}
