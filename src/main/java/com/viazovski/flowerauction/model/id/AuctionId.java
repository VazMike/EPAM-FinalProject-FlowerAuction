package com.viazovski.flowerauction.model.id;

import com.viazovski.flowerauction.model.Auction;

/**
 * {@code AuctionId} represents PK of Auction model
 */
public class AuctionId implements Id<Auction> {

    private int auctionId;

    public AuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getAuctionId() {
        return auctionId;
    }
}
