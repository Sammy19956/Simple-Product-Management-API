package com.example.simpleproductmanagement.repository;

import com.example.simpleproductmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product findProductByProductCode(UUID uuid);
    List<Product> findProductsByProductNameContaining(String name);

    @Query(nativeQuery = true, value = "select * from productmgt.product where price > 100 and quantity_in_store = 0")
    List<Product> findProductsWithPriceAboveHundred();
}
