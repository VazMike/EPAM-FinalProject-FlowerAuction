package com.viazovski.flowerauction.validationmessage;

/**
 * {@code ChangePasswordValidationMessage} holds information about change password input errors.
 */
public class ChangePasswordValidationMessage implements ValidationMessage {

    private String oldPasswordError;

    private String newPasswordError;

    private String confirmNewPasswordError;

    public void setOldPasswordError(String oldPasswordError) {
        this.oldPasswordError = oldPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    public void setConfirmNewPasswordError(String confirmNewPasswordError) {
        this.confirmNewPasswordError = confirmNewPasswordError;
    }

    @Override
    public boolean isEmpty() {
        return oldPasswordError == null && newPasswordError == null && confirmNewPasswordError == null;
    }
}
