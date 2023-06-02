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
        var movieId = $(this).attr('id').replace('provoli-movie-', '');
        var cinemasBox = $('#provoli-cinemas-' + movieId);
        $('.provoles-cinemas-box').not(cinemasBox).hide();
        cinemasBox.toggle();
    });
});


