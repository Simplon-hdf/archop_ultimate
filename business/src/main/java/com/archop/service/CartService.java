package com.archop.service;

import com.archop.dto.CartItemDTO;
import com.archop.entity.Product;
import com.archop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final Map<Long, CartItemDTO> items = new HashMap<>();
    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<CartItemDTO> getItems() {
        return new ArrayList<>(items.values());
    }

    public void addItem(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItemDTO cartItem = items.getOrDefault(productId, CartItemDTO.builder()
                .productId(productId)
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(0)
                .build());

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setTotal(cartItem.getPrice() * cartItem.getQuantity());
        items.put(productId, cartItem);
    }

    public void removeItem(Long productId) {
        items.remove(productId);
    }

    public void clearCart() {
        items.clear();
    }

    public Double getTotal() {
        return items.values().stream()
                .mapToDouble(CartItemDTO::getTotal)
                .sum();
    }
} 