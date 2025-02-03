package com.archop.service;

import com.archop.converter.ProductConverter;
import com.archop.dto.ProductDTO;
import com.archop.entity.Product;
import com.archop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public List<ProductDTO> getAllProducts() {
        return productConverter.entityListToDtoList(productRepository.findAll());
    }

    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(productConverter::entityToDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productConverter.dtoToEntity(productDTO);
        product = productRepository.save(product);
        return productConverter.entityToDto(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        Product updatedProduct = productConverter.dtoToEntity(productDTO);
        updatedProduct.setId(existingProduct.getId());
        updatedProduct = productRepository.save(updatedProduct);
        
        return productConverter.entityToDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
} 