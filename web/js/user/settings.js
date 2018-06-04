function setInitialLocale(locale) {
    console.log(locale);
    const isEnglish = locale === 'EN';
    $('#locale-select option[value="EN"]').prop('selected', isEnglish);
    $('#locale-select option[value="RU"]').prop('selected', !isEnglish);
}

function changeLocale(select) {
    console.log(select.value);
    $.post('change-locale',
        {
            locale: select.value
        },
        () => location.reload());
}

function changePassword() {
    $('#change-password-div').hide();
    $('#save-new-password-div').show();
}

function cancelChangePassword() {
    $('#change-password-div').show();
    $('#save-new-password-div').hide();
}

function changePasswordServerCall() {
    const oldPassword = $('#settings-old-password-input').val();
    const newPassword = $('#settings-new-password-input').val();
    const confirmNewPassword = $('#settings-confirm-new-password-input').val();
    const isValid = validateChangePasswordForm(oldPassword, newPassword, confirmNewPassword);
    if (!isValid) {
        return;
    }
    $.post('change-password',
        {
            oldPassword: oldPassword,
            newPassword: newPassword,
            confirmNewPassword: confirmNewPassword
        },
        data => onSuccessfulChangePassword(data)
    );
}

function validateChangePasswordForm(oldPassword, newPassword, confirmNewPassword) {
    let errorMessage = {};
    if (!oldPassword) {
        errorMessage.oldPassword = 'Old Password is empty';
    }
    if (!newPassword) {
        errorMessage.newPassword = 'New Password is empty';
    }
    if (!confirmNewPassword) {
        errorMessage.confirmNewPassword = 'Confirm New Password is empty';
    }
    if (confirmNewPassword !== newPassword) {
        const msg = 'New Password and Confirm New Password do not match';
        errorMessage.newPassword = msg;
        errorMessage.confirmNewPassword = msg;
    }
    console.log(oldPassword);
    console.log(newPassword);
    console.log(confirmNewPassword);
    console.log(errorMessage);
    showSettingsErrors(
        errorMessage.oldPassword,
        errorMessage.newPassword,
        errorMessage.confirmNewPassword
    );
    return $.isEmptyObject(errorMessage);
}

function onSuccessfulChangePassword(data) {
    let json = JSON.parse(data);
    if ($.isEmptyObject(json)) {
        clearSettingsInput();
        cancelChangePassword();
    } else {
        showSettingsErrors(json['oldPasswordError'], json['newPasswordError'], json['confirmNewPasswordError']);
    }
}

function showSettingsErrors(oldMsg, newMsg, confMsg) {
    showOneError($('#settings-old-password-input'), $('#settings-old-password-error-small'), oldMsg);
    showOneError($('#settings-new-password-input'), $('#settings-new-password-error-small'), newMsg);
    showOneError($('#settings-confirm-new-password-input'), $('#settings-confirm-new-password-error-small'), confMsg);
}

function showOneError(input, error, msg) {
    if (msg) {
        input.addClass('is-invalid');
        error.text(msg);
    } else {
        input.removeClass('is-invalid');
        error.text('');
    }
}

function clearSettingsInput() {
    let oldInput = $('#settings-old-password-input');
    let newInput = $('#settings-new-password-input');
    let conInput = $('#settings-confirm-new-password-input');
    let oldError = $('#settings-old-password-error-small');
    let newError = $('#settings-new-password-error-small');
    let conError = $('#settings-confirm-new-password-error-small');
    oldInput.val('');
    newInput.val('');
    conInput.val('');
    showOneError(oldInput, oldError, null);
    showOneError(newInput, newError, null);
    showOneError(conInput, conError, null);
}