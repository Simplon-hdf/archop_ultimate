package com.archop.converter;

import com.archop.dto.ProductDTO;
import com.archop.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    
    public ProductDTO entityToDto(Product product) {

        if (product == null) { 
            return null;
        }
        
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public Product dtoToEntity(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }

    public List<ProductDTO> entityListToDtoList(List<Product> products) {
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
} 