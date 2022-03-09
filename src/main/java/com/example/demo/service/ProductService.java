package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity add(ProductDto request) {
        ProductEntity product = new ProductEntity();
        product.setName(request.getProductName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        //save to db
        return productRepository.save(product);
    }

    public List<ProductEntity> fetch(boolean isInStock, long maxPrice) {
        if (maxPrice == 0) {
            if (isInStock) {
                //fetch all product with stock >0
                return fetchAllInStock();
            } else {
                //fetch all
                return fetchAll();
            }
        } else {
            if (isInStock) {
                //fetch all product with stock >0
                return fetchMaxPrice(maxPrice);
            } else {
                //fetch all
                return fetchMaxPriceAll(maxPrice);
            }
        }


    }

    public List<ProductEntity> fetchAll() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> fetchAllInStock() {
        //Return available stock
        return productRepository.findByStockGreaterThan(0);
    }

    public List<ProductEntity> fetchMaxPrice(long harga) {
        //Return product in price range
        return productRepository.findByStockGreaterThanAndPriceLessThanEqual(0, harga);
    }

    public List<ProductEntity> fetchMaxPriceAll(long harga) {
        //Return product in price range
        return productRepository.findByPriceLessThanEqual(harga);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity getById(long id) {
        return productRepository.findById(id).orElse(new ProductEntity());
    }

    public ProductEntity UpdateStock(UpdateStockDto request) {
        //Get product by id from DB
        ProductEntity product = getById(request.getId());
        //Update stock
        long currentStock = product.getStock();
        long updatedStock = currentStock + request.getNumberOfStock();
        product.setStock(updatedStock);
        //Update DB data
        return productRepository.save(product);
    }
}
