package com.example.simpleproductmanagement.Services;

import com.example.simpleproductmanagement.exceptions.ProductNotFoundException;
import com.example.simpleproductmanagement.response.APIResponse;
import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public APIResponse<Product> saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductCode(UUID.randomUUID());
        product.setManufacturer(productDTO.getManufacturer());
        product.setPrice(productDTO.getPrice());
        product.setStatus(productDTO.getStatus());
        product.setQuantityInStore(productDTO.getQuantityInStore());
        productRepository.save(product);
        return new APIResponse<>(true, "Product added Successfully", product);
    }

    @Override
    public APIResponse<Product> updateProduct(Long productId, ProductDTO productDTO) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

            product.setProductName(productDTO.getProductName());
            product.setManufacturer(productDTO.getManufacturer());
            product.setStatus(productDTO.getStatus());
            product.setPrice(productDTO.getPrice());
            product.setQuantityInStore(productDTO.getQuantityInStore());
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
            return new APIResponse<>(true, "Product updated successfully", product);
        } catch (Exception ex) {
            return new APIResponse<>(true, ex.getMessage(), null);
        }
    }

    @Override
    public APIResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new APIResponse<>(true, "Successful", products);
    }

    @Override
    public APIResponse<Product> getProductByCode(String uuid) {
        try {
            Product product = productRepository.findProductByProductCode(UUID.fromString(uuid));
            if (product == null) {
                throw new ProductNotFoundException("Product with code " + uuid + " not found");
            } else {
                return new APIResponse<>(true, "Successful", product);
            }

        } catch (Exception ex) {
            return new APIResponse<>(true, ex.getMessage(), null);
        }

    }

    @Override
    public APIResponse<Product> deleteProduct(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
            productRepository.delete(product);
            return new APIResponse<>(true, "Product deleted successfully", null);
        } catch (Exception ex) {
            return new APIResponse<>(true, ex.getMessage(), null);
        }
    }

    @Override
    public APIResponse<List<Product>> searchProductByName(String name) {
        try {

            List<Product> products = productRepository.findProductsByProductNameContaining(name);
            if (products.size() == 0){
                throw new ProductNotFoundException("There is no such product in our store");
            }
            return new APIResponse<>(true, "Successful",products);
        } catch (Exception ex) {
            return new APIResponse<>(true, ex.getMessage(), null);
        }

    }
}