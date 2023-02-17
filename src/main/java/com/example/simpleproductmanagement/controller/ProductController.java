package com.example.simpleproductmanagement.controller;

import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.Services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("save-product")
    public Product saveProduct(@RequestBody ProductDTO productDTO){
       return productService.saveProduct(productDTO);
    }

    @PutMapping("update-product")
    public void updateProduct(@RequestBody Product product, @PathVariable Long productId){

        productService.updateProduct(productId, product);

    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }


}
