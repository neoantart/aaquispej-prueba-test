package com.indracompany.prueba.controller.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceRequest {

    private Long brandId;

    private Long productId;

    private LocalDateTime appDate;

}
