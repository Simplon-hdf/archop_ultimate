package com.archop.repository;

import com.archop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Recherches simples par champs
    Optional<Product> findByName(String name);
    List<Product> findBySellingPriceLessThan(Double price);
    List<Product> findBySellingPriceGreaterThan(Double price);
    List<Product> findByNameContainingIgnoreCase(String keyword);
    
    // Recherches combinées
    List<Product> findByNameContainingIgnoreCaseAndSellingPriceBetween(String keyword, Double minPrice, Double maxPrice);
    
    // Requêtes personnalisées avec JPQL
    @Query("SELECT p FROM Product p WHERE p.sellingPrice BETWEEN :minPrice AND :maxPrice ORDER BY p.sellingPrice ASC")
    List<Product> findProductsInSellingPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    @Query("SELECT p FROM Product p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchInDescription(@Param("keyword") String keyword);
    
    // Requêtes avec agrégation
    @Query("SELECT COUNT(p), MIN(p.sellingPrice), MAX(p.sellingPrice), AVG(p.sellingPrice) FROM Product p")
    Object[] getProductStats();
    
    // Requête native SQL (si nécessaire pour des requêtes complexes)
    @Query(value = "SELECT * FROM products WHERE sellingPrice > (SELECT AVG(sellingPrice) FROM products)", nativeQuery = true)
    List<Product> findProductsAboveAverageSellingPrice();
} 
