package com.viazovski.flowerauction.validationmessage;

/**
 * {@code AddFlowerValidationMessage} holds information about add flower input errors.
 */
public class AddFlowerValidationMessage implements ValidationMessage {

    private String addFlowerNameError;

    private String addFlowerValueError;

    public void setAddFlowerNameError(String addFlowerNameError) {
        this.addFlowerNameError = addFlowerNameError;
    }

    public void setAddFlowerValueError(String addFlowerValueError) {
        this.addFlowerValueError = addFlowerValueError;
    }

    @Override
    public boolean isEmpty() {
        return addFlowerNameError == null && addFlowerValueError == null;
    }
}
