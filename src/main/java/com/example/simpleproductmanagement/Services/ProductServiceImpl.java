package com.example.simpleproductmanagement.Services;

import com.example.simpleproductmanagement.exceptions.ProductNotFoundException;
import com.example.simpleproductmanagement.response.APIResponse;
import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public APIResponse<Product> saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductCode(UUID.randomUUID());
        product.setProductName(productDTO.getProductName());
        product.setManufacturer(productDTO.getManufacturer());
        product.setPrice(productDTO.getPrice());
        product.setStatus(productDTO.getStatus());
        product.setQuantityInStore(productDTO.getQuantityInStore());
        productRepository.save(product);
        return new APIResponse<>(true, "Product added Successfully", product);
    }

    @Override
    public APIResponse<Product> updateProduct(Long productId, Product product){
        Product product1 = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
        product.setProductCode(product.getProductCode());
        product.setProductName(product.getProductName());
        product.setManufacturer(product.getManufacturer());
        product.setQuantityInStore(product.getQuantityInStore());
        product.setPrice(product.getPrice());
        productRepository.save(product);
        return  new APIResponse<>(true, "Product updated successfully", product);
    }

    @Override
    public APIResponse<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new APIResponse<>(true, "Successful", products);
    }


}
