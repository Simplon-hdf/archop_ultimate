package com.archop.converter;

import com.archop.dto.ProductDTO;
import com.archop.dto.ProductDetailsDTO;
import com.archop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDTO entityToDto(Product product) {

        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO(
                product.getId(),
                product.getName() != null ? product.getName() : "Nom non-forni",
                product.getDescription() != null ? product.getDescription() : "Description non-fornie",
                product.getSellingPrice() != null ? product.getSellingPrice() : 0.0);

        return dto;
    }

    public Product dtoToEntity(ProductDTO dto, ProductDetailsDTO productDetailsDTO) {

        if (dto == null || productDetailsDTO == null) {
            return null;
        }

        Product product = new Product(
                dto.getId(),
                dto.getName() != null ? dto.getName() : "Nom non-forni",
                dto.getDescription() != null ? dto.getDescription() : "Description non-fornie",
                dto.getSellingPrice() != null ? dto.getSellingPrice() : 0.0,
                productDetailsDTO.getPurchasePrice() != null ? productDetailsDTO.getPurchasePrice() : 0.0,
                productDetailsDTO.getCreatedAt() != null ? productDetailsDTO.getCreatedAt() : null,
                productDetailsDTO.getUpdatedAt() != null ? productDetailsDTO.getUpdatedAt() : null
                );

        return product;
    }

    public List<ProductDTO> entityListToDtoList(List<Product> products) {
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

}