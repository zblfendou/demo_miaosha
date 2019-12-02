package com.example.demo.model;

public class Order {
    private Long id;

    private Long goodId;

    private Integer buyNum;

    public Order(Builder builder) {
        setId(builder.id);
        setGoodId(builder.goodId);
        setBuyNum(builder.buyNum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Order() {
    }

    public static final class Builder {
        private Long id;
        private Long goodId;
        private Integer buyNum;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder goodId(Long val) {
            goodId = val;
            return this;
        }

        public Builder buyNum(Integer val) {
            buyNum = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
