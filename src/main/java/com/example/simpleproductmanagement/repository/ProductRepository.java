package com.example.simpleproductmanagement.repository;

import com.example.simpleproductmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product findProductByProductCode(UUID uuid);
    List<Product> findProductsByProductNameContaining(String name);
}
