package com.viazovski.flowerauction.model;

import java.util.Objects;

/**
 * {@code CreditCard} represents credit_card table.
 */
public class CreditCard {

    private int creditCardId;

    private int ownerId;

    private String number;

    private String password;

    private int balance;

    public CreditCard() {}

    public CreditCard(int ownerId, String number, String password, int balance) {
        this.ownerId = ownerId;
        this.number = number;
        this.password = password;
        this.balance = balance;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return creditCardId == that.creditCardId &&
                ownerId == that.ownerId &&
                balance == that.balance &&
                Objects.equals(number, that.number) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId, ownerId, number, password, balance);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardId=" + creditCardId +
                ", ownerId=" + ownerId +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
