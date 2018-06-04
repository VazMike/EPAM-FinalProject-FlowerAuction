$(() => {
    $.i18n().load({
        en: "../../js/i18n/en.json",
        ru: "../../js/i18n/ru.json"
    }).done(() => {
            $.i18n().locale = "${sessionScope.lang.toString()}".toLowerCase();
            manageUsersServerCall();
        }
    );
});

function manageUsersServerCall() {
    $.post("manage-users", data => generateUserTable(data));
}

function generateUserTable(data) {
    let users = JSON.parse(data);
    let userElements = $("<tbody></tbody>").attr("id", "user-table-body");
    $.each(users, (i, el) => {
        let tdBuyerId = $("<td></td>").text(el["buyerId"]);
        let tdLogin = $("<td></td>").text(el["login"]);
        let tdAction = $(
            "<td>" +
            "<button class='btn btn-light' onclick='removeUser(this)'>" +
            "<span class='fa fa-trash'></span>" +
            "</button>" +
            "</button>" +
            "</td>"
        );
        $("<tr></tr>")
            .append(tdBuyerId)
            .append(tdLogin)
            .append(tdAction)
            .appendTo(userElements)
    });
    $("#user-table-body").remove();
    $("#user-table").append(userElements);
}

function removeUser(button) {
    let tr = $(button).closest("tr");
    $.post("remove-user",
        {
            userId: $(tr.children()[0]).text()
        },
        () => tr.remove()
    );
}
