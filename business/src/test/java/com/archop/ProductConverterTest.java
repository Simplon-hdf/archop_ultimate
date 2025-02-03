package com.archop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.DisplayName;

import com.archop.converter.ProductConverter;
import com.archop.entity.Product;
import com.archop.dto.ProductDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Tests de ProductConverter")
public class ProductConverterTest {

    private ProductConverter productConverter;
    private Product product;

    @BeforeEach
    void setUp() {
        productConverter = new ProductConverter();
        product = new Product(1L, "Produit 1", "Description 1", 10.0);
    }

    @Test
    @DisplayName("Convertir une entité en DTO")
    void testEntityToDto() {
        // Act
        ProductDTO dto = productConverter.entityToDto(product);

        // Assert
        assertNotNull(dto, "Le DTO ne doit pas être null");
        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getDescription(), dto.getDescription());
        assertEquals(product.getPrice(), dto.getPrice());
    }

    @Test
    @DisplayName("Retourne null si l'entité est null")
    void shouldReturnNullWhenEntityIsNull() {
        // Act
        ProductDTO dto = productConverter.entityToDto(null);

        // Assert
        assertNull(dto, "Si l'entité est null, le DTO doit être null");
    }
}
