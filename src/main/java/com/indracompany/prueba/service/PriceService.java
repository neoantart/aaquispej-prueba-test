package com.indracompany.prueba.service;

import java.util.Comparator;
import java.util.List;

import com.indracompany.prueba.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.prueba.controller.entity.PriceRequest;
import com.indracompany.prueba.controller.entity.PriceResponse;
import com.indracompany.prueba.entity.Price;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceResponse getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate());
        PriceResponse priceResponse = null;
        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(p -> p.getPriority())).get();
            priceResponse = makePriceResponse(priceRequest, price);
        }
        return priceResponse;
    }

    private PriceResponse makePriceResponse(PriceRequest priceRequest, Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setAppDate(priceRequest.getAppDate());
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setFinalPrice(price.getPrice());
        priceResponse.setPrice(price.getPriceList());
        priceResponse.setProductId(price.getProductId());
        return priceResponse;
    }

}
