package com.viazovski.flowerauction.model;

import java.util.Objects;

/**
 * {@code Flower} represents flower table.
 */
public class Flower {

    private int flowerId;

    private int ownerId;

    private Integer auctionId;

    private boolean flowerAccepted;

    private String name;

    private int value;

    public Flower() {}

    public Flower(int ownerId, String name, int value) {
        this.ownerId = ownerId;
        this.name = name;
        this.value = value;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public boolean isFlowerAccepted() {
        return flowerAccepted;
    }

    public void setFlowerAccepted(boolean flowerAccepted) {
        this.flowerAccepted = flowerAccepted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return flowerId == flower.flowerId &&
                ownerId == flower.ownerId &&
                flowerAccepted == flower.flowerAccepted &&
                value == flower.value &&
                Objects.equals(auctionId, flower.auctionId) &&
                Objects.equals(name, flower.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowerId, ownerId, auctionId, flowerAccepted, name, value);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "flowerId=" + flowerId +
                ", ownerId=" + ownerId +
                ", auctionId=" + auctionId +
                ", flowerAccepted=" + flowerAccepted +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
