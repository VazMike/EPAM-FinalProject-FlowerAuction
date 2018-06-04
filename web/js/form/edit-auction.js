function editAuctionServerCall() {
    let selectEdit = $("#edit-auction-modal").data("selectEdit");
    let td = $(selectEdit).closest("tr").children();
    let auctionId = $(td[0]).text();
    let name = $("#edit-auction-name-input").val();
    let eventDate = $("#edit-auction-event-date-input").val();
    const isValid = validateEditAuctionForm(name, eventDate);
    if (!isValid) {
        return;
    }
    $.post("edit-auction",
        {
            auctionId: auctionId,
            name: name,
            eventDate: eventDate
        },
            data => editTableRow(data)
    );

    function editTableRow(data) {
        let message = JSON.parse(data);
        if ($.isEmptyObject(message)) {
            $(td[1]).text(name);
            $(td[2]).text(eventDate);
            $("#edit-auction-modal").modal('hide');
            $('.modal-backdrop').remove();
        } else {
            showEditAuctionErrors(message["addAuctionNameError"], message["addAuctionEventDateError"]);
        }
    }
}

function validateEditAuctionForm(name, eventDate) {
    let errorMessage = {};
    if (!name) {
        errorMessage.name = "Name is empty";
    }
    if (!eventDate) {
        errorMessage.eventDate = "Event date is empty";
    }
    showEditAuctionErrors(errorMessage.name, errorMessage.eventDate);
    return $.isEmptyObject(errorMessage);
}

function showEditAuctionErrors(nameMsg, eventDateMsg) {
    showOneError($("#edit-auction-name-input"), $("#edit-auction-name-error-small"), nameMsg);
    showOneError($("#edit-auction-event-date-input"), $("#edit-auction-event-date-error-small"), eventDateMsg);
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