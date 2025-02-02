# Archop Ultimate

Application de gestion de panier d'achat avec une architecture n-tiers.

## Architecture

Le projet est divisé en 3 modules :

- **persistence** : Gestion de la persistance des données (entités et repositories)
- **business** : Logique métier (services et DTOs)
- **presentation** : API REST (contrôleurs)

## Prérequis

- Java 21
- Maven
- PostgreSQL 16.6
- Base de données `archop` créée sur le port 5433

## Configuration de la base de données

La base de données doit être configurée avec :
- URL : `jdbc:postgresql://localhost:5433/archop`
- Username : `postgres`
- Password : `root`

## Installation

1. Cloner le repository :
```bash
git clone [URL_DU_REPO]
cd archop_ultimate
```

2. Compiler le projet :
```bash
mvn clean install
```

3. Lancer l'application :
```bash
cd presentation
mvn spring-boot:run
```

## API REST

### Produits

- **Lister tous les produits**
  ```bash
  curl http://localhost:8080/api/products
  ```

- **Créer un produit**
  ```bash
  curl -X POST -H "Content-Type: application/json" \
       -d '{"name":"Produit", "description":"Description", "price":99.99}' \
       http://localhost:8080/api/products
  ```

- **Voir un produit**
  ```bash
  curl http://localhost:8080/api/products/{id}
  ```

### Panier

- **Voir le panier**
  ```bash
  curl http://localhost:8080/api/cart
  ```

- **Ajouter au panier**
  ```bash
  curl -X POST "http://localhost:8080/api/cart/items?productId={id}&quantity={quantité}"
  ```

- **Supprimer du panier**
  ```bash
  curl -X DELETE "http://localhost:8080/api/cart/items/{productId}"
  ```

- **Voir le total du panier**
  ```bash
  curl http://localhost:8080/api/cart/total
  ```

- **Valider le panier**
  ```bash
  curl -X POST "http://localhost:8080/api/cart/checkout"
  ```

## Fonctionnalités

- ✅ Affichage des produits
- ✅ Interface d'ajout/modification/suppression de produits
- ✅ Ajout des produits dans un panier
- ✅ Validation du panier avec suppression de son contenu
- ✅ Base de données PostgreSQL pour les produits
- ✅ Architecture n-tiers
- ✅ Utilisation d'un ORM (Hibernate/JPA)

## Technologies utilisées

- Spring Boot 3.4.2
- Spring Data JPA
- PostgreSQL
- Maven
- JUnit
- Lombok