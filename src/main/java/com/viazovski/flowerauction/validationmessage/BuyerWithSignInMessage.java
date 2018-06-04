package com.viazovski.flowerauction.validationmessage;

import com.viazovski.flowerauction.model.Buyer;

/**
 * {@code BuyerWithSignInMessage} is used in sign in command as the only place
 * to receive some other data besides errorMessage.
 */
public class BuyerWithSignInMessage {

    public Buyer buyer;

    public SignInValidationMessage signInValidationMessage;

    public BuyerWithSignInMessage(Buyer buyer, SignInValidationMessage signInValidationMessage) {
        this.buyer = buyer;
        this.signInValidationMessage = signInValidationMessage;
    }
}
