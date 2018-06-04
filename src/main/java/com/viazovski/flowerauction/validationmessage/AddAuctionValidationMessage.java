package com.viazovski.flowerauction.validationmessage;

/**
 * {@code AddAuctionValidationMessage} holds information about add auction input errors.
 */
public class AddAuctionValidationMessage implements ValidationMessage {

    private String addAuctionNameError;

    private String addAuctionEventDateError;

    public void setAddAuctionNameError(String addAuctionNameError) {
        this.addAuctionNameError = addAuctionNameError;
    }

    public void setAddAuctionEventDateError(String addAuctionEventDateError) {
        this.addAuctionEventDateError = addAuctionEventDateError;
    }

    @Override
    public boolean isEmpty() {
        return addAuctionNameError == null && addAuctionEventDateError == null;
    }
}
