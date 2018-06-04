package com.viazovski.flowerauction.model;

import java.util.Objects;

/**
 * {@code Sales} represents sales table.
 */
public class Sales {

    private int buyerToId;

    private int flowerId;

    private int commission;

    public int getBuyerToId() {
        return buyerToId;
    }

    public void setBuyerToId(int buyerToId) {
        this.buyerToId = buyerToId;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return buyerToId == sales.buyerToId &&
                flowerId == sales.flowerId &&
                commission == sales.commission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerToId, flowerId, commission);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "buyerToId=" + buyerToId +
                ", flowerId=" + flowerId +
                ", commission=" + commission +
                '}';
    }
}
