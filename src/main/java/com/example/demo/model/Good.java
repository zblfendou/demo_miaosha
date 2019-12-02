package com.example.demo.model;

import java.io.Serializable;

public class Good implements Serializable {
    private Long id;

    private String name;

    private Integer stock;

    private Integer reduceStock;


    public Good() {
    }

    public Good(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setStock(builder.stock);
        setReduceStock(builder.reduceStock);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getReduceStock() {
        return reduceStock;
    }

    public void setReduceStock(Integer reduceStock) {
        this.reduceStock = reduceStock;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Integer stock;
        private Integer reduceStock;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder stock(Integer val) {
            stock = val;
            return this;
        }

        public Builder reduceStock(Integer val) {
            reduceStock = val;
            return this;
        }

        public Good build() {
            return new Good(this);
        }
    }
}
