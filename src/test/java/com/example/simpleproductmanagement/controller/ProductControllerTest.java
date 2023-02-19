package com.example.simpleproductmanagement.controller;

import com.example.simpleproductmanagement.Services.ProductService;
import com.example.simpleproductmanagement.Services.ProductServiceImpl;
import com.example.simpleproductmanagement.dto.ProductDTO;
import com.example.simpleproductmanagement.entity.Product;
import com.example.simpleproductmanagement.enums.Status;
import com.example.simpleproductmanagement.response.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private ProductController productController;

    @Autowired
    private  MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    void saveProduct() throws Exception {
        when(productService.saveProduct(any(ProductDTO.class)))
                .thenReturn(new APIResponse<>(true, "Success", getProduct()));
    }

    @Test
    void updateProduct() throws Exception {

        Product product = getProduct();
        ProductDTO productDTO = new ProductDTO("Mango", 22, "Alen", Status.AVAILABLE,10);

        when(productService.updateProduct(any(Long.class), any(ProductDTO.class)))
                .thenReturn(new APIResponse<>(true, "Successful", product));
    }

    @Test
    void getProductByCode() throws Exception {
        Product product = getProduct();
        when(productService.getProductByCode(any(String.class)))
                .thenReturn(new APIResponse<>(true, "Successful", product));
        mockMvc.perform(get("/api/v1/get-product-by-code/{code}", product.getProductCode())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message", is("Successful")))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(new APIResponse<>(true, "Successful", getProductList()));
        mockMvc.perform(get("/api/v1/get-all-products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    void deleteProduct() throws Exception {
        when(productService.deleteProduct(any(Long.class)))
                .thenReturn(new APIResponse<>(true, "Successful", null));
        mockMvc.perform(delete("/api/v1/delete-product/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void searchByName() throws Exception {
        when(productService.searchProductByName(any(String.class)))
                .thenReturn(new APIResponse<>(true, "Successful",getProductList()));
    }

    @Test
    void getProductsWithPriceAboveHundred() throws Exception {
        when(productService.getProductsWithPriceAboveHundred())
                .thenReturn(new APIResponse<>(true, "Successful",getProductList()));

        mockMvc.perform(get("/api/v1/get-products-with-price-above-hundred")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    public Product getProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Corn-flakes");
        product.setProductCode(UUID.randomUUID());
        product.setStatus(Status.AVAILABLE);
        product.setPrice(25);
        product.setManufacturer("Nasco");
        product.setQuantityInStore(10);
        return product;
    }

    public List<Product> getProductList(){
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();

        product.setProductName("Guava");
        product.setProductCode(UUID.randomUUID());
        product.setPrice(100);

        product1.setProductName("Grapefruit");
        product1.setProductCode(UUID.randomUUID());
        product1.setPrice(30);

        product1.setProductName("Avocado");
        product1.setProductCode(UUID.randomUUID());
        product1.setPrice(10);

        return List.of(product, product1, product2);
    }
}