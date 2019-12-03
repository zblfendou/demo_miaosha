package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Long id;

    private Long goodId;

    private Integer buyNum;

    private Integer payState;

    private Date createTime;

    public Order(Builder builder) {
        setId(builder.id);
        setGoodId(builder.goodId);
        setBuyNum(builder.buyNum);
        setPayState(builder.payState);
        setCreateTime(builder.createTime);
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

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Order() {
    }

    public static final class Builder {
        private Long id;
        private Long goodId;
        private Integer buyNum;
        private Integer payState;
        private Date createTime;

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

        public Builder payState(Integer val) {
            payState = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
