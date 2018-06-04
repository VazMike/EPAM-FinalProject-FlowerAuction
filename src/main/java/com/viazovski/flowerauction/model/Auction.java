package com.viazovski.flowerauction.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * {@code Auction} represents auction table.
 */
public class Auction {

    private int auctionId;

    private String name;

    private Timestamp eventDate;

    public Auction() {}

    public Auction(String name, Timestamp eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }

    public Auction(int auctionId, String name, Timestamp eventDate) {
        this.auctionId = auctionId;
        this.name = name;
        this.eventDate = eventDate;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return auctionId == auction.auctionId &&
                Objects.equals(name, auction.name) &&
                Objects.equals(eventDate, auction.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId, name, eventDate);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", name='" + name + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
