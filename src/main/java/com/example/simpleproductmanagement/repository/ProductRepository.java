package com.example.simpleproductmanagement.repository;

import com.example.simpleproductmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
