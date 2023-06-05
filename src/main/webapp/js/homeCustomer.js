var originalUrl = window.location.href.split('?')[0];
window.history.replaceState({}, document.title, originalUrl);

document.addEventListener("DOMContentLoaded", function() {
    const menuLinks = document.querySelectorAll("header nav ul li  a");

    menuLinks.forEach(function(link) {
        link.addEventListener("click", function(e) {
            e.preventDefault();
            const targetSectionId = link.getAttribute("href");
            const targetSection = document.querySelector(targetSectionId);
            targetSection.scrollIntoView({ behavior: "smooth"});
        });
    });
});


$(document).ready(function() {
    $('.provoli-movie-name').click(function() {
        var movieId = '';
        var cinemaId = '';

        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('.provoles-cinemas-box').removeClass('show');
            $('.provoli-cinema-id').removeClass('selected');
        } else {
            movieId = $(this).attr('id').replace('provoli-movie-', '');
            var cinemasBox = $('#provoli-cinemas-' + movieId);
            $('.provoles-cinemas-box').removeClass('show');
            $('.provoli-cinema-id').removeClass('selected');
            cinemasBox.addClass('show');
            $('.provoli-movie-name').removeClass('selected');
            $(this).addClass('selected');
        }
        $('#movieId').val(movieId);
        $('#cinemaId').val(cinemaId);
        updateSelectedMovie();
        updateSelectedCinema();
    });

    $('.provoli-cinema-id').not('.provoli-cinema-id-not-available').click(function() {
        var cinemaId = $(this).attr('id').replace('provoli-cinema-', '').replace('Cinema ', '');
        $('#cinemaId').val(cinemaId);
        $('.provoli-cinema-id').removeClass('selected');
        $(this).addClass('selected');
        updateSelectedCinema();
    });

    function updateSelectedMovie() {
        var movieId = $('#movieId').val();
        var movieTitle = $('#provoli-movie-' + movieId).text().trim();
        $('.selected-movie').text("Selected Movie: " + movieTitle);
    }

    function updateSelectedCinema() {
        var cinemaId = $('#cinemaId').val();
        $('.selected-cinema').text("Selected Cinema ID: " + cinemaId);
    }

    $('.reservation-button input[type="button"]').click(function() {
        var selectedMovieId = $('#movieId').val();
        var selectedCinemaId = $('#cinemaId').val();

        if (selectedMovieId !== '' && selectedCinemaId !== '') {
            var encodedMovieId = encodeURIComponent(selectedMovieId);
            var encodedCinemaId = encodeURIComponent(selectedCinemaId);

            var url = 'makeReservation.jsp?movieId=' + encodedMovieId + '&cinemaId=' + encodedCinemaId;
            window.location.href = url;
        }
    });
});







