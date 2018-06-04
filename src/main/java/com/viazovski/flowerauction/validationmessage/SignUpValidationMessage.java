package com.viazovski.flowerauction.validationmessage;

/**
 * {@code SignUpValidationMessage} holds information about sing up input errors.
 */
public class SignUpValidationMessage implements ValidationMessage {

    private String signUpLoginError;

    private String signUpPasswordError;

    private String signUpConfirmPasswordError;

    public void setSignUpLoginError(String signUpLoginError) {
        this.signUpLoginError = signUpLoginError;
    }

    public void setSignUpPasswordError(String signUpPasswordError) {
        this.signUpPasswordError = signUpPasswordError;
    }

    public void setSignUpConfirmPasswordError(String signUpConfirmPasswordError) {
        this.signUpConfirmPasswordError = signUpConfirmPasswordError;
    }

    @Override
    public boolean isEmpty() {
        return signUpLoginError == null && signUpPasswordError == null && signUpConfirmPasswordError == null;
    }
}
