function addAuctionServerCall() {
    let name = $("#add-auction-name-input").val();
    let eventDate = $("#add-auction-event-date-input").val();
    const isValid = validateAddAuctionForm(name, eventDate);
    if (!isValid) {
        return;
    }
    $.post("add-auction",
        {
            name: name,
            eventDate: eventDate
        },
            data => addAuctionToTable(data)
    )
}

function addAuctionToTable(data) {
    let json = JSON.parse(data);
    let message = json["message"];
    if ($.isEmptyObject(message)) {
        generateAuctionRow(json["auction"]);
        $("#add-auction-modal").modal('hide');
        $('.modal-backdrop').remove()
    } else {
        showAddAuctionErrors(message["addAuctionNameError"], message["addAuctionEventDateError"])
    }
}

function generateAuctionRow(auction) {
    let tdAuctionId = $("<td></td>").text(auction["auctionId"]);
    let tdName = $("<td></td>").text(auction["name"]);
    let tdEventDate = $("<td></td>").text(auction["eventDate"]);
    let tdAction = $(
        "<td>" +
        "<button class='btn btn-light' onclick='editAuction(this)'>" +
        "<span class='fa fa-pencil'></span>" +
        "</button>" +
        "<button class='btn btn-light' onclick='removeAuction(this)'>" +
        "<span class='fa fa-trash'></span>" +
        "</button>" +
        "<button class='btn btn-light' onclick='showAuction(this)'>" +
        "<span class='fa fa-arrow-right'></span>" +
        "</button>" +
        "</td>"
    );
    let trAdd = $("#add-auction-button").closest("tr");
    $("<tr></tr>")
        .append(tdAuctionId)
        .append(tdName)
        .append(tdEventDate)
        .append(tdAction)
        .insertBefore(trAdd);
}

function validateAddAuctionForm(name, eventDate) {
    let errorMessage = {};
    if (!name) {
        errorMessage.name = "Name is empty";
    }
    if (!eventDate) {
        errorMessage.eventDate = "Event date is empty";
    }
    showAddAuctionErrors(errorMessage.name, errorMessage.eventDate);
    return !$.isEmptyObject(errorMessage);

}

function showAddAuctionErrors(nameMsg, eventDateMsg) {
    showOneError($("#add-auction-name-input"), $("#add-auction-name-error-small"), nameMsg);
    showOneError($("#add-auction-event-date-input"), $("#add-auction-event-date-error-small"), eventDateMsg);
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