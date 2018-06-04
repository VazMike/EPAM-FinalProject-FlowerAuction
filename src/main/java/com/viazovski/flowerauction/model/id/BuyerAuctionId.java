package com.viazovski.flowerauction.model.id;

import com.viazovski.flowerauction.model.BuyerAuction;

/**
 * {@code AuctionId} represents PK of Auction model
 */
public class BuyerAuctionId implements Id<BuyerAuction> {

    private int buyerId;

    private int auctionId;

    public BuyerAuctionId(int buyerId, int auctionId) {
        this.buyerId = buyerId;
        this.auctionId = auctionId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getAuctionId() {
        return auctionId;
    }
}
