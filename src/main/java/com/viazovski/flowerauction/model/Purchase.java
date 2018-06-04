package com.viazovski.flowerauction.model;

import java.util.Objects;

/**
 * {@code Purchase} represents purchase table.
 */
public class Purchase {

    private int buyerFromId;

    private int flowerId;

    private int commission;

    public int getBuyerFromId() {
        return buyerFromId;
    }

    public void setBuyerFromId(int buyerFromId) {
        this.buyerFromId = buyerFromId;
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
        Purchase purchase = (Purchase) o;
        return buyerFromId == purchase.buyerFromId &&
                flowerId == purchase.flowerId &&
                commission == purchase.commission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerFromId, flowerId, commission);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "buyerFromId=" + buyerFromId +
                ", flowerId=" + flowerId +
                ", commission=" + commission +
                '}';
    }
}
