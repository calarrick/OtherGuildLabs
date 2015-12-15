/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadDvds();
});
function loadDvds() {

    $.ajax({
        url: "dvds"
    }).success(function (data, status) {

        clearMovieList();
        fillDvdList(data, status);
    });
}

$('#add-movie').click(function (event) {

    event.preventDefault();
    $('#add-panel').slideToggle();
});
$('#add-button').click(function (event) {

    event.preventDefault();
    addDvd();
    $('#add-panel').slideUp();
    clearMovieList();
    setTimeout(loadDvds(), 5000);
});
function addDvd() {

    event.preventDefault();
    var year = $('#add-year').val();
    var month = $('#add-month').val().slice(0, 3);
    var day = $('#add-day').val();
    var date = new Date(month + " " + day + " " + year).toISOString().split("T")[0];
    $.ajax({
        type: 'POST',
        url: 'dvd',
        data: JSON.stringify({
            title: $('#add-title').val(),
            director: $('#add-director').val(),
            studio: $('#add-studio').val(),
            mpaaRating: $('#add-rating').val(),
            releaseDateString: date,
            note: $('#add-note').val()
        }),
        headers:
                {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
        'dataType': 'json'
    })

            .success(function (data, status) {
                $('#add-title').val('');
                $('#add-director').val('');
                $('#add-studio').val('');
                $('#add-rating').val('');
                $('#add-year').val('');
                $('#add-month').val('');
                $('#add-day').val('');
                $('#add-note').val('');
                $('#validationErrors').empty();
                $('.form-error').text('');
            }).error(function (data, status) {
        $('#validationErrors').empty();
        $.each(data.responseJSON.validationErrors, function (index, validationError) {
            var errorDiv = $('#validationErrors');
            errorDiv.append(validationError.message).append($('<br>'));
            $('#' + validationError.fieldName).text(validationError.message);
        });
    });
}



function loadStats() {

    clearStats();
    $.ajax({
        url: "oldest"})
            .success(function (data, status) {

                $('#oldest-header').append().text('Your oldest movie');
                $.each(data, function (index, dvd) {

                    var releaseDate = dvd.releaseDate;
                    var year = releaseDate.year;
                    $('#old-movie-list').append(($('<li>').html(
                            dvd.title + ', ' + year))
                            );
                });
            });
    $.ajax({
        url: "newest"})
            .success(function (data, status) {

                $('#newest-header').append().text('Your newest movie');
                $.each(data, function (index, dvd) {

                    var releaseDate = dvd.releaseDate;
                    var year = releaseDate.year;
                    $('#new-movie-list').append(($('<li>').html(
                            dvd.title + ', ' + year))
                            );
                });
            });
    $.ajax({
        url: "average_age"})
            .success(function (data, status) {
                $('#average-header').append().text('The average age of your movies');
                $('#average-age').append(data);
            });
}

function clearStats() {

    $('#old-movie-list').empty();
    $('#new-movie-list').empty();
    $('#average-age').empty();
}


$('#search-opts').click(function () {
    $('#search-panel').slideDown();
    $('#stats-panel').slideUp();
    $('#search').addClass('active');
    $('#stats').removeClass('active');
    $('#home').removeClass('active');
}
);
$('#home-opts').click(function () {

    $('#search-panel').slideUp();
    $('#stats-panel').slideUp();
    $('#search').removeClass('active');
    $('#stats').removeClass('active');
    $('#home').addClass('active');
});
$('#stats-opts').click(function () {
    $('#search-panel').slideUp();
    $('#stats-panel').slideDown();
    $('#stats').addClass('active');
    $('#search').removeClass('active');
    $('#home').removeClass('active');
    loadStats();
});
function loadDvdsDate() {

    $.ajax({
        url: "dvds_date"
    }).success(function (data, status) {

        fillDvdList(data, status);
    });
}

$('#by-title').click(function (e) {
    e.preventDefault();
    clearMovieList();
    loadDvds();
});
$('#by-date').click(function (e) {
    e.preventDefault();
    clearMovieList();
    loadDvdsDate();
});


function fillDvdList(listDvds, status) {

    $.each(listDvds, function (index, dvd) {

        var releaseDate = dvd.releaseDate;
        var year = releaseDate.year;
        var month = releaseDate.month.toString().toLowerCase().capitalizeFirstLetter();
        var detailsId = '<a id=' + dvd.id + '_details>';
        var detailsPanelId = '<div id=' + dvd.id + '_details_panel class=details_panel>';
        var movieList = $('#main-movie-list');


        movieList.append($('<li>').html(detailsId +
                dvd.title + ', ' + year + ' (Details) </a>').append(($(detailsPanelId)).
                html('Directed by: ' + dvd.director + ', Studio: ' + dvd.studio
                        + '<br> Release date: ' + month + ' ' + year + ', MPAA Rating: '
                        + dvd.mpaaRating + '<br>Added Notes: ' + dvd.note + '<br>' + '<a id=' + dvd.id + '_delete>Delete Dvd?</a></div>'
                        + '<br><div id=' + dvd.id + '_delete-confirm></div>')
                )
                );
        $('#' + dvd.id + '_details').click(function () {
            $('#' + dvd.id + '_details_panel').slideToggle();
        });
        $('#' + dvd.id + '_delete').click(function () {
            $('#' + dvd.id + '_delete-confirm').append($('<p class="warning-message">').html("Do you really want to delete "
                    + dvd.title + "? <a id='delete_yes'>Yes</a>").click(function () {

                
                deleteDvd(dvd.id);
            })



                    );
        });
    }
    );
}



function deleteDvd(id) {


    $.ajax({
        type: 'DELETE',
        url: 'dvd/' + id
    }).success(function () {
        loadDvds();
    });
}



$('#search-button').click(function (event) {

    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'search/dvds',
        data: JSON.stringify({
            title: $('#search-title').val(),
            director: $('#search-director').val(),
            studio: $('#search-studio').val()

        }),
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        'dataType': 'json'


    }).success(function (data, status) {


        $('#search-title').val('');
        $('#search-director').val('');
        $('#search-studio').val('');
        clearMovieList();
        fillDvdList(data, status);
    });
});
function clearMovieList() {
    $('#main-movie-list').empty();
}


String.prototype.capitalizeFirstLetter = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}
;
