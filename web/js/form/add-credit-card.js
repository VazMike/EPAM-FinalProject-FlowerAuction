function addCreditCardServerCall() {
    let number = $("#add-credit-card-number-input").val();
    let password = $("#add-credit-card-password-input").val();
    let balance = $("#add-credit-card-balance-input").val();
    const isValid = validateAddCreditCardForm(number, password, balance);
    if (!isValid) {
        return;
    }
    $.post("add-credit-card",
        {
            number: number,
            password: password,
            balance: balance
        },
            data => addCreditCardToTable(data)
    )
}

function addCreditCardToTable(data) {
    let json = JSON.parse(data);
    let message = json["message"];
    if ($.isEmptyObject(message)) {
        generateCreditCardRow(json["creditCard"]);
        $("#add-credit-card-modal").modal('hide');
        $('.modal-backdrop').remove()
    } else {
        showAddCreditCardErrors(
            message["addCreditCardNumberError"],
            message["addCreditCardPasswordError"],
            message["addCreditCardBalanceError"]
        )
    }
}

function generateCreditCardRow(creditCard) {
    let tdCreditCardId = $("<td></td>").text(creditCard["creditCardId"]);
    let tdNumber = $("<td></td>").text(creditCard["number"]);
    let tdBalance = $("<td></td>").text(creditCard["balance"]);
    let tdAction = $(
        "<td>" +
        "<button class='btn btn-light' onclick='removeCreditCard(this)'>" +
        "<span class='fa fa-trash'></span>" +
        "</td>"
    );
    let trAdd = $("#add-credit-card-button").closest("tr");
    $("<tr></tr>")
        .append(tdCreditCardId)
        .append(tdNumber)
        .append(tdBalance)
        .append(tdAction)
        .insertBefore(trAdd)
}

function validateAddCreditCardForm(number, password, balance) {
    let errorMessage = {};
    if (!number) {
        errorMessage.number = "Number is empty"
    }
    if (!/\d{16}/.test(number)) {
        errorMessage.number = "Number must represent a sequence of 16 numbers";
    }
    if (!password) {
        errorMessage.password = "Password is empty";
    }
    if (!/\d{4}/.test(password)) {
        errorMessage.password = "Password must represent a sequence of 4 numbers";
    }
    if (!balance) {
        errorMessage.balance= "Balance is empty";
    }
    if (parseInt(balance) < 0) {
        errorMessage.balance= "Balance should must contain positive value";
    }
    showAddCreditCardErrors(
        errorMessage.number,
        errorMessage.password,
        errorMessage.balance
    );
    return $.isEmptyObject(errorMessage);
}

function showAddCreditCardErrors(numberMsg, passwordMsg, balanceMsg) {
    showOneError($("#add-credit-card-number-input"), $("#add-credit-card-number-error-small"), numberMsg);
    showOneError($("#add-credit-card-password-input"), $("#add-credit-card-password-error-small"), passwordMsg);
    showOneError($("#add-credit-card-balance-input"), $("#add-credit-card-balance-error-small"), balanceMsg)
}

function showOneError(input, error, msg) {
    if (msg) {
        input.addClass("is-invalid");
        error.text(msg)
    } else {
        input.removeClass("is-invalid");
        error.text("")
    }
}