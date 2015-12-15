/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadAddresses();


//click handler for add button

    $('#add-button').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'address',
            data: JSON.stringify({
                lastName: $('#add-last-name').val(),
                firstNames: $('#add-first-name').val().split(","),
                streetNumber: $('#add-street-num').val(),
                streetName: $('#add-street').val(),
                aptNum: $('#add-apt').val(),
                cityName: $('#add-city').val(),
                stateName: $('#add-state').val(),
                zipCode: $('#add-zip').val(),
                phoneNumbers: $('#add-phone').val().split(","),
                emailAddresses: $('#add-email').val().split(",")
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-last-name').val('');
            $('#add-first-name').val('');
            $('#add-street-num').val('');
            $('#add-street').val('');
            $('#add-apt').val('');
            $('#add-city').val('');
            $('#add-state').val('');
            $('#add-zip').val('');
            $('#add-phone').val('');
            $('#add-email').val('');
            $('.form-error').text('');


            loadAddresses();

        }).error(function (data, status) {

            $.each(data.responseJSON.validationErrors, function (index, validationError) {
                ($('#' + validationError.fieldName).text(validationError.message));
            }
            );
        });
    });


//click handler for search button

    $('#search-button').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'search/addresses',
            data: JSON.stringify({
                lastName: $('#search-last-name').val(),
                stateName: $('#search-state').val(),
                cityName: $('#search-city').val(),
                zipCode: $('#search-zip').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            'dataType': 'json'


        }).success(function (data, status) {


            $('#search-last-name').val('');
            $('#search-state').val('');
            $('#search-city').val('');
            $('#search-zip').val('');


            fillAddressList(data, status);

        });
    });

});

//get everything
function loadAddresses()
{
    clearAddressList();

    $.ajax({
        url: "addresses"
    }).success(function (data, status) {

        fillAddressList(data, status);

    });

}

function fillAddressList(listAddressesSorted, status) {



    var addressList = $('#addressList');
    //var count = 1;



    $.each(listAddressesSorted, function (index, address) {
        //address.defineProperties(resultId, {value: count, enumerable: true});



        addressList.append($('<li>').html(address.lastName + "<br>"
                + address.streetNumber + " " + address.streetName
                + " " + address.aptNum + " " + "<br>" + address.cityName + ", "
                + address.stateName + " " + address.zipCode + "<br><div id="
                + address.addressId + "_phone_nums><div>"
                + "<div id=" + address.addressId + "_details></div>")

                .append($('<a>').attr({
                    'data-address-id': address.addressId,
                    'data-target': '#details-pane',
                    'data-toggle': 'modal'
                }).text('Details |'))

                .append($('<a>').attr({
                    'data-address-id': address.addressId,
                    'data-target': '#edit-pane',
                    'data-toggle': 'modal'
                }).text('| Edit |'))

                .append($('<a>').attr({
                    'data-address-id': address.addressId,
                    'data-target': '#delete-pane',
                    'data-toggle': 'modal'
                }).text('| Delete')));
    });
}




$('#details-pane').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {

        $('#address-last-name-det').text(address.lastName);
        $('#address-first-names-det').text(address.firstNames);
        $('#address-line-one-det').html(address.streetNumber + " " + address.streetName + " " + address.aptNum);
        $('#address-line-two-det').html(address.cityName + ", " + address.stateName + " " + address.zipCode);
        $('#phone-numbers-det').text(address.phoneNumbers);
        $('#email-addresses-det').text(address.emailAddresses);
    });
});



function clearAddressList() {
    $('#addressList').empty();
}



$('#edit-pane').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {

        var addressId = $("address-id");
        addressId.text(address.addressId);

        modal.find('#address-id').text(address.addressId);
        modal.find('#edit-first-name').val(address.firstNames);
        modal.find('#edit-last-name').val(address.lastName);
        modal.find('#edit-street-num').val(address.streetNumber);
        modal.find('#edit-street-name').val(address.streetName);
        modal.find('#edit-apt-num').val(address.aptNum);
        modal.find('#edit-city').val(address.cityName);
        modal.find('#edit-state').val(address.stateName);
        modal.find('#edit-zip-code').val(address.zipCode);
        modal.find('#edit-phone').val(address.phoneNumbers);
        modal.find('#edit-email').val(address.emailAddresses);
    });
});



    $('#edit-button').click(function (event) {

        event.preventDefault();
//        var element = $(event.relatedTarget);
//        var addressId = element.data('address-id');

        $.ajax({
            type: 'PUT',
            url: 'address/' + addressId,
            data: JSON.stringify({
                lastName: $('#edit-last-name').val(),
                firstNames: $('#edit-first-name').val().split(","),
                streetNumber: $('#edit-street-num').val(),
                streetName: $('#edit-street-name').val(),
                aptNum: $('#edit-apt-num').val(),
                cityName: $('#edit-city').val(),
                stateName: $('#edit-state').val(),
                zipCode: $('#edit-zip-code').val(),
                phoneNumbers: $('#edit-phone').val().split(","),
                emailAddresses: $('#edit-email').val().split(",")
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function () {
            loadAddresses();
        });
    });









$('#delete-pane').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {

        $('#address-last-name').text(address.lastName);
        $('#address-first-names').text(address.firstNames);
        $('#address-line-one').html(address.streetNumber + " " + address.streetName + " " + address.aptNum);
        $('#address-line-two').html(address.cityName + ", " + address.stateName + " " + address.zipCode);
        $('#phone-numbers').text(address.phoneNumbers);
        $('#email-addresses').text(address.emailAddresses);
    });

    $('#delete_btn').click(function (event) {


        $('#delete-pane').modal('hide');
        $.ajax({
            type: 'DELETE',
            url: 'address/' + addressId
        }).success(function () {
            loadAddresses();

        });


    });

});








