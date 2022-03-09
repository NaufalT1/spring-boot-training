package com.example.demo.dto;

public class UpdateStockDto {
    private long id;
    private long numberOfStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumberOfStock() {
        return numberOfStock;
    }

    public void setNumberOfStock(long numberOfStock) {
        this.numberOfStock = numberOfStock;
    }
}
