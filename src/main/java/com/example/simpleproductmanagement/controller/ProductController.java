package com.example.simpleproductmanagement.controller;

import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.Services.ProductServiceImpl;
import com.example.simpleproductmanagement.response.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductServiceImpl productService;

    @Operation(summary = "save Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added Successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Product Details",
                    content = @Content)})
    @PostMapping("save-product")
    public ResponseEntity<APIResponse<Product>> saveProduct(@RequestBody ProductDTO productDTO){
       return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("update-product/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){

        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @GetMapping("get-product-by-code/{code}")
    public ResponseEntity<APIResponse<Product>> getProductByCode(@PathVariable String code){
        return new ResponseEntity<>(productService.getProductByCode(code), HttpStatus.OK);
    }

    @GetMapping("get-all-products")
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<APIResponse<Product>> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @GetMapping("search-by-name")
    public ResponseEntity<APIResponse<List<Product>>> searchByName(@RequestParam String name){
        return ResponseEntity.ok(productService.searchProductByName(name));
    }

    @GetMapping("get-products-with-price-above-hundred")
    public ResponseEntity<APIResponse<List<Product>>> getProductsWithPriceAboveHundred(){
        return ResponseEntity.ok(productService.getProductsWithPriceAboveHundred());
    }
}
