package com.archop.controller;

import com.archop.dto.CartItemDTO;
import com.archop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getCart() {
        return ResponseEntity.ok(cartService.getItems());
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        cartService.addItem(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {
        cartService.removeItem(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkout() {
        cartService.clearCart();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotal() {
        return ResponseEntity.ok(cartService.getTotal());
    }
} 