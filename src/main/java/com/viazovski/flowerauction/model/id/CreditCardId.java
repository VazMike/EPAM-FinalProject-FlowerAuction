package com.viazovski.flowerauction.model.id;

import com.viazovski.flowerauction.model.CreditCard;

/**
 * {@code CreditCardId} represents PK of CreditCard model
 */
public class CreditCardId implements Id<CreditCard> {

    private int creditCardId;

    public CreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getCreditCardId() {
        return creditCardId;
    }
}
