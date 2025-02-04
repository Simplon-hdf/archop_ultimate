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

@DisplayName("Tests de ProductConverter") // Définit un titre global pour les tests
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
    @DisplayName("Convertir une entité en DTO") // Nom explicite du test
    void testEntityToDto() {
        // Act : Exécution de la méthode à tester
        ProductDTO dto = productConverter.entityToDto(product);

        // Assert : Vérifications sur le résultat
        assertNotNull(dto, "Le DTO ne doit pas être null"); // Vérifie que le DTO n'est pas null
        assertEquals(product.getId(), dto.getId()); // Vérifie que l'ID est bien converti
        assertEquals(product.getName(), dto.getName()); // Vérifie que le nom est bien converti
        assertEquals(product.getDescription(), dto.getDescription()); // Vérifie que la description est bien convertie
        assertEquals(product.getSellingPrice(), dto.getSellingPrice()); // Vérifie que le prix est bien converti
    }

    /**
     * Teste si la conversion retourne null lorsque l'entité passée est null.
     */
    @Test
    @DisplayName("Retourne null si l'entité est null") // Nom explicite du test
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
    @DisplayName("Convertir un DTO en entité") // Nom explicite du test
    void testDtoToEntity() {

        ProductDTO dto = new ProductDTO(); // Création d'un objet ProductDTO
        dto.setId(product.getId()); // Initialisation de l'ID
        dto.setName(product.getName()); // Initialisation du nom
        dto.setDescription(product.getDescription());  // Initialisation de la description
        dto.setSellingPrice(product.getSellingPrice()); // Initialisation du prix

        ProductDetailsDTO dtoDetails = new ProductDetailsDTO(); // Création d'un objet ProductDetailsDTO
        dtoDetails.setPurchasePrice(product.getPurchasePrice()); // Initialisation du prix
        dtoDetails.setCreatedAt(product.getCreatedAt()); // Initialisation de la date de création
        dtoDetails.setUpdatedAt(product.getUpdatedAt()); // Initialisation de la date de mise à jour



        // Act : Exécution de la méthode à tester
        Product entity = productConverter.dtoToEntity(dto, dtoDetails);

        // Assert : Vérifications sur le résultat
        assertNotNull(entity, "L'entité ne doit pas être null"); // Vérifie que l'entité n'est pas null
        assertEquals(product.getId(), entity.getId()); // Vérifie que l'ID est bien converti
        assertEquals(product.getName(), entity.getName()); // Vérifie que le nom est bien converti
        assertEquals(product.getDescription(), entity.getDescription()); // Vérifie que la description est bien convertie
        assertEquals(product.getPurchasePrice(), entity.getPurchasePrice()); // Vérifie que le prix est bien converti
        assertEquals(product.getSellingPrice(), entity.getSellingPrice()); // Vérifie que le prix est bien converti
        assertEquals(product.getCreatedAt(), entity.getCreatedAt()); // Vérifie que la date de création est bien convertie
        assertEquals(product.getUpdatedAt(), entity.getUpdatedAt()); // Vérifie que la date de mise à jour est bien convertie
    }

    @Test
    @DisplayName("Retourne null si le dto est null") // Nom explicite du test
    void shouldReturnNullWhenDTOIsNull() {
        // Act : Exécution de la méthode avec une valeur null
        Product entity = productConverter.dtoToEntity(null, null);

        // Assert : Vérifie que le résultat est bien null
        assertNull(entity, "Si l'entité est null, le DTO doit être null");
    }


}
