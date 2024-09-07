package com.product_service.product_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductDetailDto {
    Optional<String> name= Optional.empty();
    Optional<String> description =Optional.empty();
    Optional<BigDecimal> price=Optional.empty();
}
