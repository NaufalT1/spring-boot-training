package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductEntity> getProducts(@RequestParam(value = "inStock", defaultValue = "0") boolean isInStock,
                                           @RequestParam(value = "maxPrice", defaultValue = "0") long maxPrice) {
        //Add code to get all product list here
        return productService.fetch(isInStock, maxPrice);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable("id") String id) {
        //Add code to get product here
        return productService.getById(Long.parseLong(id));
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto) {
        //Add code to post here
        return productService.add(productDto);
        //return new CommonResponse("Successfully add new product");
    }

    @PutMapping("/stock")
    public ProductEntity updateStock(@RequestBody UpdateStockDto request) {
        //menyambungkan DTO ke Controller menggunakan Request Body
        //Add code to post here
        return productService.UpdateStock(request);
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") String id) {
        // Menggunakan @PathVariable membuat tdk perlu memasukkan nama parameter dll.
        // Cukup mskkan param-nya saja
        //Add code to get product list here
        productService.delete(Long.parseLong(id));
        return new CommonResponse("Successfully delete product");
    }
}
