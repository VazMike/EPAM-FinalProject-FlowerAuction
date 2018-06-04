$(() => {
    $.i18n().load({
        en: "../../js/i18n/en.json",
        ru: "../../js/i18n/ru.json"
    }).done(() => {
            $.i18n().locale = "${sessionScope.lang.toString()}".toLowerCase();
            requestServerCall();
        }
    )
});

function requestServerCall() {
    showAvailableFlowers();
    $.post("request",
        function (data) {
            let json = JSON.parse(data);
            let auctionElements = $("<tbody></tbody>").attr("id", "request-auction-table-body");
            $.each(json["auctions"], function (i, el) {
                let ba = json["buyerAuctions"].find(ba => ba.auctionId == el.auctionId);
                let state = determineState(ba);
                let aAuctionId = $("<a></a>")
                    .attr("data-toggle", "popover")
                    .attr("data-title", $.i18n("quickView-auction-title"))
                    .attr("data-content", quickAuctionView(el))
                    .text(el["auctionId"]);
                let tdAuctionId = $("<td></td>").append(aAuctionId);
                let tdAction = $(
                    "<td>" +
                    "<button class='btn btn-light'" + state.disabled1 + " onclick='makeAuctionRequest(this)'>" +
                    state.message +
                    "</button>" +
                    "<button class='btn btn-light'" + state.disabled2 + " onclick='sendFlowers(this)'>" +
                    $.i18n("button-sendFlowers") +
                    "</button>" +
                    "</td>"
                );
                $("<tr></tr>")
                    .append(tdAuctionId)
                    .append(tdAction)
                    .appendTo(auctionElements)
            });
            $("#request-auction-table-body").remove();
            $("#request-auction-table").append(auctionElements);
            $("[data-toggle='popover']").popover({
                html: true,
                trigger: "hover"
            })
        })
}

function determineState(ba) {
    let state = {};
    if (!ba) {
        state.message = $.i18n("button-request");
        state.disabled1 = "";
        state.disabled2 = "disabled"
    } else if (ba.buyerAccepted) {
        state.message = $.i18n("button-accepted");
        state.disabled1 = "disabled";
        state.disabled2 = ""
    } else {
        state.message = $.i18n("button-pending");
        state.disabled1 = "disabled";
        state.disabled2 = "disabled"
    }
    return state
}

function makeAuctionRequest(button) {
    let td = $(button).closest("tr").children();
    let auctionId = $(td[0]).text();
    $.post("make-auction-request",
        {
            auctionId: auctionId
        },
        function () {
            $(button).text("Pending");
            $(button).prop("disabled", true)
        })
}

function showAvailableFlowers() {
    $.post("find-available-flowers",
        function (data) {
            let flowers = JSON.parse(data);
            let flowerElements = $("<tbody></tbody>").attr("id", "request-flower-table-body");
            $.each(flowers, function (i, el) {
                let aFlowerId = $("<a></a>")
                    .attr("data-toggle", "popover")
                    .attr("data-title", $.i18n("quickView-flower-title"))
                    .attr("data-content", quickFlowerView(el))
                    .text(el["flowerId"]);
                let tdFlowerId = $("<td></td>").append(aFlowerId);
                let tdCheckbox = $("<td><input type='checkbox'/></td>");
                $("<tr></tr>")
                    .append(tdFlowerId)
                    .append(tdCheckbox)
                    .appendTo(flowerElements)
            });
            $("#request-flower-table-body").remove();
            $("#request-flower-table").append(flowerElements)
        })
}

function sendFlowers(button) {
    let td = $(button).closest("tr").children();
    let auctionId = $(td[0]).text();
    let selectedFlowerElements = $("#request-flower-table-body").children("tr")
        .filter(function (i, el) {
            let checkCell = $(el).children()[1];
            let checkBox = $(checkCell).children(":checkbox")[0];
            return $(checkBox).is(":checked")
        });
    let flowerIds = selectedFlowerElements
        .map(function () {
            let idCell = $(this).children()[0];
            return $(idCell).text()
        })
        .toArray();
    if (flowerIds.length == 0) {
        return
    }
    $.post("make-flower-request",
        {
            auctionId: auctionId,
            flowerIds: JSON.stringify(flowerIds)
        },
        function () {
            selectedFlowerElements.remove()
        })
}
