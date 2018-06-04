$(() => {
    $.i18n().load({
        en: "../../js/i18n/en.json",
        ru: "../../js/i18n/ru.json"
    }).done(() => {
            $.i18n().locale = "${sessionScope.lang.toString()}".toLowerCase();
            manageAuctionsServerCall();
            $("#add-auction-modal").on("hidden.bs.modal", clearAddModal);
        }
    )
});

function manageAuctionsServerCall() {
    $.post('manage-auctions', data => generateAuctionTable(data));
}

function generateAuctionTable(data) {
    let json = JSON.parse(data);
    console.log(json);
    $(document).data('json', json);
    let auctionElements = $('<tbody></tbody>');
    $.each(json['auctions'], (i, el) => {
        let tdAuctionId = $('<td></td>').text(el.auctionId);
        let tdName = $('<td></td>').text(el.name);
        let tdEventDate = $('<td></td>').text(el.eventDate);
        let tdAction = $(
            '<td>' +
            '<button class="btn btn-light" onclick="editAuction(this)">' +
            '<span class="fa fa-pencil"></span>' +
            '</button>' +
            '<button class="btn btn-light" onclick="removeAuction(this)">' +
            '<span class="fa fa-trash"></span>' +
            '</button>' +
            '<button class="btn btn-light" onclick="showAuction(this)">' +
            '<span class="fa fa-arrow-right"></span>' +
            '</button>' +
            '</td>'
        );
        $('<tr></tr>')
            .append(tdAuctionId)
            .append(tdName)
            .append(tdEventDate)
            .append(tdAction)
            .appendTo(auctionElements)
    });
    let trAdd = $(
        '<tr><td></td><td></td><td></td>' +
        '<td>' +
        '<button class="btn btn-light" data-toggle="modal" data-target="#add-auction-modal" id="add-auction-button">' +
        'Add+' +
        '</button>' +
        '</td>' +
        '</tr>'
    );
    auctionElements
        .append(trAdd)
        .appendTo('#auction-table');
}

function generateBuyerTable(buyers, buyerAuctions, auctionId) {
    console.log(auctionId);
    console.log(buyerAuctions);
    console.log(buyers);
    let buyerIds = buyerAuctions
        .filter(ba => ba.auctionId == auctionId)
        .map(ba => ba.buyerId);
    console.log('Buyer Ids');
    console.log(buyerIds);
    let buyerElements = $('<tbody></tbody>').attr('id', 'buyer-table-body');
    $.each(buyers, (i, el) => {
        if (!buyerIds.includes(el.buyerId)) {
            return;
        }
        let aBuyerId = $('<a></a>')
            .attr('data-toggle', 'popover')
            .attr('data-title', $.i18n('quickView-buyer-title'))
            .attr('data-content', quickBuyerView(el))
            .text(el['buyerId']);
        let tdBuyerId = $('<td></td>').append(aBuyerId);
        let tdAction = $(
            '<td>' +
            '<button class="btn btn-light" onclick="removeBuyer(this)">' +
            '<span class="fa fa-trash"></span>' +
            '</button>' +
            '</td>'
        );
        $('<tr></tr>')
            .append(tdBuyerId)
            .append(tdAction)
            .appendTo(buyerElements);
    });
    $('#buyer-table-body').remove();
    $('#buyer-table').append(buyerElements);
}

function generateFlowerTable(flowers, auctionId) {
    flowers = flowers.filter(f => f.auctionId == auctionId);
    let flowerElements = $('<tbody></tbody>').attr('id', 'flower-table-body');
    $.each(flowers, (i, el) => {
        let aFlowerId = $('<a></a>')
            .attr('data-toggle', 'popover')
            .attr('data-title', $.i18n('quickView-flower-title'))
            .attr('data-content', quickFlowerView(el))
            .text(el['flowerId']);
        let tdFlowerId = $('<td></td>').append(aFlowerId);
        let tdAction = $(
            '<td>' +
            '<button class="btn btn-light" onclick="removeFlower(this)">' +
            '<span class="fa fa-trash"></span>' +
            '</button>' +
            '</td>'
        );
        $('<tr></tr>')
            .append(tdFlowerId)
            .append(tdAction)
            .appendTo(flowerElements)
    });
    $('#flower-table-body').remove();
    $('#flower-table').append(flowerElements);
}

function editAuction(button) {
    let td = $(button).closest('tr').children();
    $('#edit-auction-name-input').val($(td[1]).text());
    $('#edit-auction-event-date-input').val($(td[2]).text());
    let form = $('#edit-auction-modal');
    form.data('selectEdit', button);
    form.modal("show");
}

function showAuction(button) {
    $('#buyer-table').data('selectedAuction', button);
    $(button).closest('tr').addClass("table-active").siblings().removeClass("table-active");

    let json = $(document).data('json');
    let td = $(button).closest('tr').children();
    let auctionId = $(td[0]).text();

    let buyers = json['buyers'];
    let buyerAuctions = json['buyerAuctions'];
    generateBuyerTable(buyers, buyerAuctions, auctionId);
    let flowers = json['flowers'];
    generateFlowerTable(flowers, auctionId);
    $('[data-toggle="popover"]').popover({
        html: true,
        trigger: 'hover'
    });
}

function removeAuction(button) {
    let tr = $(button).closest('tr');
    let auctionId = $(tr.children()[0]).text();
    $.post('remove-auction',
        {
            auctionId: auctionId
        },
        () => tr.remove()
    )
}

function removeBuyer(button) {
    let selectedAuction = $('#buyer-table').data('selectedAuction');
    let tdSelected = $(selectedAuction).closest('tr').children();
    let auctionId = $(tdSelected[0]).text();
    let tr = $(button).closest('tr');
    let buyerId = $(tr.children()[0]).text();
    $.post('reject-buyer',
        {
            auctionId: auctionId,
            buyerId: buyerId
        }, function () {
            tr.remove();
            let json = $(document).data('json');
            json['buyerAuctions'] = json['buyerAuctions'].filter(ba => ba.buyerId !== buyerId || ba.auctionId !== auctionId);
        });
}

function removeFlower(button) {
    let tr = $(button).closest('tr');
    let flowerId = $(tr.children()[0]).text();
    $.post('reject-flower',
        {
            flowerId: flowerId,
        }, function () {
            tr.remove();
            let json = $(document).data('json');
            json['flowers'] = json['flowers'].filter(f => f.flowerId !== flowerId);
        });
}

function clearAddModal() {
    $('#add-auction-name-input').val('');
    $('#add-auction-event-date-input').val('');
}
