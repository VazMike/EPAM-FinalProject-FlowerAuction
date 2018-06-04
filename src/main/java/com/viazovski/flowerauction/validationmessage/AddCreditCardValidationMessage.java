package com.viazovski.flowerauction.validationmessage;

/**
 * {@code AddCreditCardValidationMessage} holds information about add credit card input errors.
 */
public class AddCreditCardValidationMessage implements ValidationMessage {

    private String addCreditCardNumberError;

    private String addCreditCardPasswordError;

    private String addCreditCardBalanceError;

    public void setAddCreditCardNumberError(String addCreditCardNumberError) {
        this.addCreditCardNumberError = addCreditCardNumberError;
    }

    public void setAddCreditCardPasswordError(String addCreditCardPasswordError) {
        this.addCreditCardPasswordError = addCreditCardPasswordError;
    }

    public void setAddCreditCardBalanceError(String addCreditCardBalanceError) {
        this.addCreditCardBalanceError = addCreditCardBalanceError;
    }

    @Override
    public boolean isEmpty() {
        return addCreditCardNumberError == null && addCreditCardPasswordError == null && addCreditCardBalanceError == null;
    }
}
