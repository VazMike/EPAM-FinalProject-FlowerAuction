$(() => {
    $.i18n().load({
        en: "../../js/i18n/en.json",
        ru: "../../js/i18n/ru.json"
    }).done(() => {
            $.i18n().locale = "${sessionScope.lang.toString()}".toLowerCase();
            manageRequestsServerCall();
        }
    )
});

function manageRequestsServerCall() {
    $.post("manage-requests", data => generateTables(data))
}

function generateTables(data) {
    let json = JSON.parse(data);
    generateBuyerRequests(json["buyerAuctions"], json["auctions"], json["buyers"]);
    generateFlowerRequests(json["auctions"], json["flowers"]);
    $("[data-toggle='popover']").popover({
        html: true,
        trigger: "hover"
    })
}

function generateBuyerRequests(requests, auctions, buyers) {
    let buyerRequestsElement = $("<tbody></tbody>").attr("id", "buyer-table-body");
    $.each(requests, (i, el) => {
        let auction = auctions.find(a => a.auctionId === el.auctionId);
        let buyer = buyers.find(b => b.buyerId === el.buyerId);

        let aAuctionId = $("<a></a>")
            .attr("data-toggle", "popover")
            .attr("data-title", $.i18n("quickView-auction-title"))
            .attr("data-content", quickAuctionView(auction))
            .text(auction["auctionId"]);
        let tdAuctionId = $("<td></td>").append(aAuctionId);
        let aBuyerId = $("<a></a>")
            .attr("data-toggle", "popover")
            .attr("data-title", $.i18n("quickView-buyer-title"))
            .attr("data-content", quickBuyerView(buyer))
            .text(buyer["buyerId"]);
        let tdBuyerId = $("<td></td>").append(aBuyerId);
        let tdAction = $(
            "<td>" +
            "<button class='btn btn-light' onclick='removeBuyerFromBuyerRequest(this)'>" +
            "<span class=\"fa fa-close\"></span>" +
            "</button>" +
            "<button class='btn btn-light' onclick='addBuyerToAuction(this)'>" +
            "<span class=\"fa fa-check\"></span>" +
            "</button>" +
            "</td>"
        );
        $("<tr></tr>")
            .append(tdAuctionId)
            .append(tdBuyerId)
            .append(tdAction)
            .appendTo(buyerRequestsElement)
    });
    $("#buyer-table-body").remove();
    $("#buyer-table").append(buyerRequestsElement)
}

function generateFlowerRequests(auctions, flowers) {
    let flowerRequestsElement = $("<tbody></tbody>").attr("id", "flower-table-body");
    $.each(flowers, (i, el) => {
        let auction = auctions.find(a => a.auctionId === el.auctionId);

        let aAuctionId = $("<a></a>")
            .attr("data-toggle", "popover")
            .attr("data-title", $.i18n("quickView-auction-title"))
            .attr("data-content", quickAuctionView(auction))
            .text(auction["auctionId"]);
        let tdAuctionId = $("<td></td>").append(aAuctionId);
        let aFlowerId = $("<a></a>")
            .attr("data-toggle", "popover")
            .attr("data-title", $.i18n("quickView-flower-title"))
            .attr("data-content", quickFlowerView(el))
            .text(el["flowerId"]);
        let tdFlowerId = $("<td></td>").append(aFlowerId);
        let tdAction = $(
            "<td>" +
            "<button class='btn btn-light' onclick='removeFlowerFromFlowerRequests(this)'>" +
            "<span class=\"fa fa-close\"></span>" +
            "</button>" +
            "<button class='btn btn-light' onclick='addFlowerToAuction(this)'>" +
            "<span class=\"fa fa-check\"></span>" +
            "</button>" +
            "</td>"
        );
        $("<tr></tr>")
            .append(tdAuctionId)
            .append(tdFlowerId)
            .append(tdAction)
            .appendTo(flowerRequestsElement)
    });
    $("#flower-table-body").remove();
    $("#flower-table").append(flowerRequestsElement)
}

function addBuyerToAuction(button) {
    buyerPostAction(button, "accept-buyer")
}

function removeBuyerFromBuyerRequest(button) {
    buyerPostAction(button, "reject-buyer")
}

function buyerPostAction(button, uri) {
    let tr = $(button).closest("tr");
    let td = tr.children();
    $.post(uri,
        {
            auctionId: $(td[0]).text(),
            buyerId: $(td[1]).text()
        },
        () => tr.remove()
    )
}

function addFlowerToAuction(button) {
    flowerPostAction(button, "accept-flower")
}

function removeFlowerFromFlowerRequests(button) {
    flowerPostAction(button, "reject-flower")
}

function flowerPostAction(button, uri) {
    let tr = $(button).closest("tr");
    let td = tr.children();
    $.post(uri,
        {
            flowerId: $(td[1]).text(),
        },
        () => tr.remove()
    )
}
