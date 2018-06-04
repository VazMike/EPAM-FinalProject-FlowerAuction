function signUpServerCall() {
    let login = $("#sign-up-login-input").val();
    let password = $("#sign-up-password-input").val();
    let confirmPassword = $("#sign-up-confirm-password-input").val();
    const isValid = validateSignUpForm(login, password, confirmPassword);
    if (!isValid) {
        return;
    }
    $.post("sign-up",
        {
            login: login,
            password: password,
            confirmPassword: confirmPassword
        },
        function (data) {
            let json = JSON.parse(data);
            if ($.isEmptyObject(json)) {
                $("#welcome-form").submit();
            } else {
                showSignUpErrors(json["signUpLoginError"], json["signUpPasswordError"], json["signUpConfirmPasswordError"]);
            }
        })
}

function validateSignUpForm(login, password, confirmPassword) {
    let errorMessage = {};
    if (!login) {
        errorMessage.login = "Login is empty";
    }
    if (!password) {
        errorMessage.password = "Password is empty";
    }
    if (!confirmPassword) {
        errorMessage.confirmPassword = "Confirm Password is empty";
    }
    if (password !== confirmPassword) {
        errorMessage.password = "Password and Confirm Password do not match";
        errorMessage.confirmPassword = "Password and Confirm Password do not match";
    }
    showSignUpErrors(errorMessage.login, errorMessage.password, errorMessage.confirmPassword);
    return $.isEmptyObject(errorMessage);
}

function showSignUpErrors(loginMsg, passwordMsg, confirmPasswordMsg) {
    showOneError($("#sign-up-login-input"), $("#sign-up-login-error-small"), loginMsg);
    showOneError($("#sign-up-password-input"), $("#sign-up-password-error-small"), passwordMsg);
    showOneError($("#sign-up-confirm-password-input"), $("#sign-up-confirm-password-error-small"), confirmPasswordMsg);
}

function showOneError(input, error, msg) {
    if (msg) {
        input.addClass("is-invalid");
        error.text(msg);
    } else {
        input.removeClass("is-invalid");
        error.text("");
    }
}

function clearSignUpInput() {
    let logInput = $("#sign-up-login-input");
    let pasInput = $("#sign-up-password-input");
    let conInput = $("#sign-up-confirm-password-input");
    let logError = $("#sign-up-login-error-small");
    let pasError = $("#sign-up-password-error-small");
    let conError = $("#sign-up-confirm-password-error-small");
    logInput.val("");
    pasInput.val("");
    conInput.val("");
    showOneError(logInput, logError, null);
    showOneError(pasInput, pasError, null);
    showOneError(conInput, conError, null);
}
