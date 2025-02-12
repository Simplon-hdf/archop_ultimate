package com.archop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailsDTO {

    private Double purchasePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
