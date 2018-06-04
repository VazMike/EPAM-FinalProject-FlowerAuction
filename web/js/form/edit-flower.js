function editFlowerServerCall() {
    let selectEdit = $("#edit-flower-modal").data("selectEdit");
    let td = $(selectEdit).closest("tr").children();
    let flowerId = $(td[0]).text();
    let name = $("#edit-flower-name-input").val();
    let value = $("#edit-flower-value-input").val();
    const isValid = validateEditFlowerForm(name, value);
    if (!isValid) {
        return;
    }
    $.post("edit-flower",
        {
            flowerId: flowerId,
            name: name,
            value: value
        },
            data => editTableRow(data)
    );

    function editTableRow(data) {
        let message = JSON.parse(data);
        if ($.isEmptyObject(message)) {
            $(td[1]).text(name);
            $(td[2]).text(value);
            $("#edit-flower-modal").modal('hide');
            $('.modal-backdrop').remove()
        } else {
            showEditFlowerErrors(message["addFlowerNameError"], message["addFlowerValueError"])
        }
    }
}

function validateEditFlowerForm(name, value) {
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
    showEditFlowerErrors(errorMessage.name, errorMessage.value);
    return $.isEmptyObject(errorMessage);
}

function showEditFlowerErrors(nameMsg, valueMsg) {
    showOneError($("#edit-flower-name-input"), $("#edit-flower-name-error-small"), nameMsg);
    showOneError($("#edit-flower-value-input"), $("#edit-flower-value-error-small"), valueMsg)
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