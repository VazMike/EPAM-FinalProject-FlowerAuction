package com.viazovski.flowerauction.model;

import java.util.Objects;

/**
 * {@code BuyerAuction} represents buyer_auction table.
 */
public class BuyerAuction {

    private int buyerId;

    private int auctionId;

    private boolean buyerAccepted;

    public BuyerAuction() {}

    public BuyerAuction(int buyerId, int auctionId, boolean buyerAccepted) {
        this.buyerId = buyerId;
        this.auctionId = auctionId;
        this.buyerAccepted = buyerAccepted;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public boolean isBuyerAccepted() {
        return buyerAccepted;
    }

    public void setBuyerAccepted(boolean buyerAccepted) {
        this.buyerAccepted = buyerAccepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerAuction that = (BuyerAuction) o;
        return buyerId == that.buyerId &&
                auctionId == that.auctionId &&
                buyerAccepted == that.buyerAccepted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, auctionId, buyerAccepted);
    }

    @Override
    public String toString() {
        return "BuyerAuction{" +
                "buyerId=" + buyerId +
                ", auctionId=" + auctionId +
                ", buyerAccepted=" + buyerAccepted +
                '}';
    }
}
