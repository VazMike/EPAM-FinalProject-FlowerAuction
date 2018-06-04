function addFlowerServerCall() {
    const name = $("#add-flower-name-input").val();
    const value = $("#add-flower-value-input").val();
    const isValid = validateAddFlowerForm(name, value);
    if (!isValid) {
        return;
    }
    $.post("add-flower",
        {
            name: name,
            value: value
        },
            data => addFlowerToTable(data)
    );
}

function addFlowerToTable(data) {
    let json = JSON.parse(data);
    let message = json["message"];
    if ($.isEmptyObject(message)) {
        generateFlowerRow(json["flower"]);
        $("#add-flower-modal").modal('hide');
        $('.modal-backdrop').remove();
    } else {
        showAddFlowerErrors(message["addFlowerNameError"], message["addFlowerValueError"]);
    }
}

function generateFlowerRow(flower) {
    let tdFlowerId = $("<td></td>").text(flower["flowerId"]);
    let tdName = $("<td></td>").text(flower["name"]);
    let tdValue = $("<td></td>").text(flower["value"]);
    let tdAuctionId = $("<td></td>").text(flower["auctionId"]);
    let tdAction = $(
        "<td>" +
        "<button class='btn btn-light' onclick='editFlower(this)'>" +
        "<span class='fa fa-pencil'></span>" +
        "</button>" +
        "<button class='btn btn-light' onclick='removeFlower(this)'>" +
        "<span class='fa fa-trash'></span>" +
        "</button>" +
        "</td>"
    );
    let trAdd = $("#add-flower-button").closest("tr");
    $("<tr></tr>")
        .append(tdFlowerId)
        .append(tdName)
        .append(tdValue)
        .append(tdAuctionId)
        .append(tdAction)
        .insertBefore(trAdd);
}

function validateAddFlowerForm(name, value) {
    let errorMessage = {};
    if (!name) {
        errorMessage.name = "Name is empty";
    }
    if (!value) {
        errorMessage.value = "Value is empty";
    }
    let intValue = parseInt(value);
    if (intValue < 1 || intValue > 10) {
        errorMessage.value = "Value must be in range from 1 to 10 inclusive";
    }
    showAddFlowerErrors(errorMessage.name, errorMessage.value);
    return $.isEmptyObject(errorMessage);
}

function showAddFlowerErrors(nameMsg, valueMsg) {
    showOneError($("#add-flower-name-input"), $("#add-flower-name-error-small"), nameMsg);
    showOneError($("#add-flower-value-input"), $("#add-flower-value-error-small"), valueMsg);
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