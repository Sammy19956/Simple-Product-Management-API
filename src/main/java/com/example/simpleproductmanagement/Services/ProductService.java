package com.example.simpleproductmanagement.Services;

import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.response.APIResponse;

import java.util.List;

public interface ProductService {

    APIResponse<Product> saveProduct(ProductDTO productDTO);

    APIResponse<Product> updateProduct(Long productId, ProductDTO productDTO);

    APIResponse<List<Product>> getAllProducts();
}
