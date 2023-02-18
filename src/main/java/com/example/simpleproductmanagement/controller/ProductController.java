package com.example.simpleproductmanagement.controller;

import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.Services.ProductServiceImpl;
import com.example.simpleproductmanagement.response.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("save-product")
    public ResponseEntity<APIResponse<Product>> saveProduct(@RequestBody ProductDTO productDTO){
       return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.OK);
    }

    @PutMapping("update-product/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable Long id){

        productService.updateProduct(id, product);

    }

    @GetMapping("getAllProducts")
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


}
