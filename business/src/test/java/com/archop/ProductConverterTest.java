package com.archop;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.archop.converter.ProductConverter;
import com.archop.entity.Product;
import com.archop.dto.ProductDTO;
import com.archop.dto.ProductDetailsDTO;

@DisplayName("Tests de ProductConverter")
public class ProductConverterTest {

    private ProductConverter productConverter; // Instance de la classe à tester
    private Product product; // Instance d'un objet Product utilisé dans les tests

    /**
     * Méthode exécutée avant chaque test pour initialiser les objets nécessaires.
     */
    @BeforeEach
    void setUp() {

        productConverter = new ProductConverter(); // Initialisation du convertisseur
        product = new Product(1L, "Produit 1", "Description 1", 10.0, 15.0, LocalDateTime.now(), LocalDateTime.now()); // Création d'un objet Product pour les tests
    }

    /**
     * Test de la conversion d'une entité Product en ProductDTO.
     */
    @Test
    @DisplayName("Convertir une entité en DTO") 
    void testEntityToDto() {

        // Act : Exécution de la méthode à tester
        ProductDTO dto = productConverter.entityToDto(product);

        // Assert : Vérifications sur le résultat de la conversion
        assertNotNull(dto, "Le DTO ne doit pas être null"); 
        assertEquals(product.getId(), dto.getId()); 
        assertEquals(product.getName(), dto.getName()); 
        assertEquals(product.getDescription(), dto.getDescription()); 
        assertEquals(product.getSellingPrice(), dto.getSellingPrice()); 
    }

    /**
     * Teste si la conversion retourne null lorsque l'entité passée est null.
     */
    @Test
    @DisplayName("Retourne null si l'entité est null") 
    void shouldReturnNullWhenEntityIsNull() {

        // Act : Exécution de la méthode avec une valeur null
        ProductDTO dto = productConverter.entityToDto(null);

        // Assert : Vérifie que le résultat est bien null
        assertNull(dto, "Si l'entité est null, le DTO doit être null");
    }

    /**
     * Teste de la conversion d'un ProductDTO en entité Product.
     */

    @Test
    @DisplayName("Convertir un DTO en entité") 
    void testDtoToEntity() {

        // Création d'un objet ProductDTO
        ProductDTO dto = new ProductDTO(); 
        dto.setId(product.getId()); 
        dto.setName(product.getName()); 
        dto.setDescription(product.getDescription());  
        dto.setSellingPrice(product.getSellingPrice());

        // Création d'un objet ProductDetailsDTO
        ProductDetailsDTO dtoDetails = new ProductDetailsDTO(); 
        dtoDetails.setPurchasePrice(product.getPurchasePrice()); 
        dtoDetails.setCreatedAt(product.getCreatedAt()); 
        dtoDetails.setUpdatedAt(product.getUpdatedAt()); 



        // Act : Exécution de la méthode à tester
        Product entity = productConverter.dtoToEntity(dto, dtoDetails);

        // Assert : Vérifications sur le résultat de la conversion
        assertNotNull(entity, "L'entité ne doit pas être null"); 
        assertEquals(product.getId(), entity.getId());
        assertEquals(product.getName(), entity.getName()); 
        assertEquals(product.getDescription(), entity.getDescription()); 
        assertEquals(product.getPurchasePrice(), entity.getPurchasePrice()); 
        assertEquals(product.getSellingPrice(), entity.getSellingPrice());
        assertEquals(product.getCreatedAt(), entity.getCreatedAt()); 
        assertEquals(product.getUpdatedAt(), entity.getUpdatedAt()); 
    }

    @Test
    @DisplayName("Retourne null si le dto est null") 
    void shouldReturnNullWhenDTOIsNull() {

        // Act : Exécution de la méthode avec une valeur null
        Product entity = productConverter.dtoToEntity(null, null);

        // Assert : Vérifie que le résultat est bien null
        assertNull(entity, "Si l'entité est null, le DTO doit être null");
    }

}
