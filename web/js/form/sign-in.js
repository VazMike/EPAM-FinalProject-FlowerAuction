function signInServerCall() {
    let login = $("#sign-in-login-input").val();
    let password = $("#sign-in-password-input").val();
    const isValid = validateSignInForm(login, password);
    if (!isValid) {
        return;
    }
    $.post("sign-in",
        {
            login: login,
            password: password
        },
        function (data) {
            let json = JSON.parse(data);
            if ($.isEmptyObject(json)) {
                $("#welcome-form").submit();
            } else {
                showSignInErrors(json["signInLoginError"], json["signInPasswordError"]);
            }
        }
    );
}

function validateSignInForm(login, password) {
    let errorMessage = {};
    if (!login) {
        errorMessage.login = "Login is empty";
    }
    if (!password) {
        errorMessage.password = "Password is empty";
    }
    showSignInErrors(errorMessage.login, errorMessage.password);
    return $.isEmptyObject(errorMessage);
}

function showSignInErrors(loginMsg, passwordMsg) {
    showOneError($("#sign-in-login-input"), $("#sign-in-login-error-small"), loginMsg);
    showOneError($("#sign-in-password-input"), $("#sign-in-password-error-small"), passwordMsg);
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

function clearSignInInput() {
    let logInput = $("#sign-in-login-input");
    let pasInput = $("#sign-in-password-input");
    let logError = $("#sign-in-login-error-small");
    let pasError = $("#sign-in-password-error-small");
    logInput.val("");
    pasInput.val("");
    showOneError(logInput, logError, null);
    showOneError(pasInput, pasError, null);
}