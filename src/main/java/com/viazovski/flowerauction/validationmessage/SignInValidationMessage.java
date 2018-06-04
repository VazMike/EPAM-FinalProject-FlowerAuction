package com.viazovski.flowerauction.validationmessage;

/**
 * {@code SignInValidationMessage} holds information about sing in input errors.
 */
public class SignInValidationMessage implements ValidationMessage {

    private String signInLoginError;

    private String signInPasswordError;

    public void setSignInLoginError(String signInLoginError) {
        this.signInLoginError = signInLoginError;
    }

    public void setSignInPasswordError(String signInPasswordError) {
        this.signInPasswordError = signInPasswordError;
    }

    @Override
    public boolean isEmpty() {
        return signInLoginError == null && signInPasswordError == null;
    }
}
