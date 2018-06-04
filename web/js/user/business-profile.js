$(() => businessProfileServerCall());

function businessProfileServerCall() {
    $.post("business-profile", data => generateTables(data))
}

function generateTables(data) {
    let json = JSON.parse(data);
    generateFlowerTable(json["flowers"]);
    generateCreditCardTable(json["creditCards"])
}

function generateFlowerTable(flowers) {
    let flowerElements = $("<tbody></tbody>").attr("id", "flower-table-body");
    $.each(flowers, (i, el) => {
        let tdFlowerId = $("<td></td>").text(el["flowerId"]);
        let tdName = $("<td></td>").text(el["name"]);
        let tdValue = $("<td></td>").text(el["value"]);
        let tdAuctionId = $("<td></td>").text(el["auctionId"]);
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
        $("<tr></tr>")
            .append(tdFlowerId)
            .append(tdName)
            .append(tdValue)
            .append(tdAuctionId)
            .append(tdAction)
            .appendTo(flowerElements)
    });
    let trAdd = $(
        "<tr><td></td><td></td><td></td><td></td>" +
        "<td>" +
        "<button class='btn btn-light' data-toggle='modal' data-target='#add-flower-modal' id='add-flower-button'>Add+</button>" +
        "</td>" +
        "</tr>"
    );
    flowerElements
        .append(trAdd)
        .appendTo("#flower-table")
}

function generateCreditCardTable(creditCards) {
    let creditCardElements = $("<tbody></tbody>").attr("id", "credit-card-table-body");
    $.each(creditCards, (i, el) => {
        let tdCreditCardId = $("<td></td>").text(el["creditCardId"]);
        let tdNumber = $("<td></td>").text(el["number"]);
        let tdBalance = $("<td></td>").text(el["balance"]);
        let tdAction = $(
            "<td>" +
            "<button class='btn btn-light' onclick='removeCreditCard(this)'>" +
            "<span class='fa fa-trash'></span>" +
            "</td>"
        );
        $("<tr></tr>")
            .append(tdCreditCardId)
            .append(tdNumber)
            .append(tdBalance)
            .append(tdAction)
            .appendTo(creditCardElements)
    });
    let trAdd = $(
        "<tr><td></td><td></td><td></td>" +
        "<td>" +
        "<button class='btn btn-light' data-toggle='modal' data-target='#add-credit-card-modal' id='add-credit-card-button'>Add+</button>" +
        "</td>" +
        "</tr>"
    );
    creditCardElements
        .append(trAdd)
        .appendTo("#credit-card-table")
}


function editFlower(button) {
    let td = $(button).closest("tr").children();
    $("#edit-flower-name-input").val($(td[1]).text());
    $("#edit-flower-value-input").val($(td[2]).text());
    let form = $("#edit-flower-modal");
    form.data("selectEdit", button);
    form.modal('show')
}

function removeFlower(button) {
    let tr = $(button).closest("tr");
    let flowerId = $(tr.children()[0]).text();
    $.post("remove-flower",
        {
            flowerId: flowerId
        },
        () => tr.remove()
    )
}

function removeCreditCard(button) {
    let tr = $(button).closest("tr");
    let creditCardId = $(tr.children()[0]).text();
    $.post("remove-credit-card",
        {
            creditCardId: creditCardId
        },
        () => tr.remove()
    )
}