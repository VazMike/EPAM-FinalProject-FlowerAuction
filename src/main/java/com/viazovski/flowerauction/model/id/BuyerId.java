package com.viazovski.flowerauction.model.id;

import com.viazovski.flowerauction.model.Buyer;

/**
 * {@code BuyerId} represents PK of Buyer model
 */
public class BuyerId implements Id<Buyer> {

    private int buyerId;

    public int getBuyerId() {
        return buyerId;
    }

    public BuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
}
