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

        var movieId;
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('.provoles-cinemas-box').removeClass('show');
            $('.provoli-cinema-id').removeClass('selected');
            movieId = null;
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
        $('#cinemaId').val(null);
        updateSelectedMovie();
        updateSelectedCinema();
    });

    $('.provoli-cinema-id').not('.provoli-cinema-id-not-available').click(function() {
        var cinemaId = $(this).attr('id').replace('provoli-cinema-', '').replace('Cinema ', '');
        $('#cinemaId').val(cinemaId);
        updateSelectedCinema();
        $('.provoli-cinema-id').removeClass('selected');
        $(this).addClass('selected');
    });

    function updateSelectedMovie() {
        var movieId = $('#movieId').val();
        var movieTitle = $('#provoli-movie-' + movieId).text();
        $('.selected-movie').text("Selected Movie: " + movieTitle);
    }

    function updateSelectedCinema() {
        var cinemaId = $('#cinemaId').val();
        var cinemaText = $('#provoli-cinema-' + cinemaId).text();
        $('.selected-cinema').text("Selected Cinema: " + cinemaText);
    }
});





