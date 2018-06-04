$(() => profileServerCall());

function profileServerCall() {
    $.post('profile', data => fillFields(data));
}

function fillFields(data) {
    const json = JSON.parse(data);
    $(document).data('first-name', json.firstName);
    $(document).data('last-name', json.lastName);
    $(document).data('dob', json.dateOfBirth);
    $('#profile-first-name-input').val(json.firstName);
    $('#profile-last-name-input').val(json.lastName);
    $('#profile-dob-input').val(json.dateOfBirth);
}

function editProfileServerCall() {
    $.post('edit-profile',
        {
            firstName: $('#profile-first-name-input').val(),
            lastName: $('#profile-last-name-input').val(),
            dob: $('#profile-dob-input').val()
        },
        () => onSuccessfulEdit()
    );
}

function makeEditable() {
    setInputsReadOnly(false);
    $('#edit-profile-button-div').hide();
    $('#edit-manage-buttons-div').show();
}

function makeUneditable() {
    revertInputs();
    setInputsReadOnly(true);
    $('#edit-profile-button-div').show();
    $('#edit-manage-buttons-div').hide();
}

function onSuccessfulEdit() {
    saveInputs();
    makeUneditable();
}

function saveInputs() {
    $(document).data('first-name', $('#profile-first-name-input').val());
    $(document).data('last-name', $('#profile-last-name-input').val());
    $(document).data('dob', $('#profile-dob-input').val());
}

function revertInputs(firstName, lastName, dob) {
    $('#profile-first-name-input').val($(document).data('first-name'));
    $('#profile-last-name-input').val($(document).data('last-name'));
    $('#profile-dob-input').val($(document).data('dob'));
}

function setInputsReadOnly(isReadOnly) {
    $('#profile-first-name-input').prop('readonly', isReadOnly);
    $('#profile-last-name-input').prop('readonly', isReadOnly);
    $('#profile-dob-input').prop('readonly', isReadOnly);
}