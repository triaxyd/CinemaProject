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

    const movieSelect = document.getElementById('provoli-movie-select');
    const movieIdInput = document.getElementById('movieId');
    const cinemaIdInput = document.getElementById('cinemaId');
    const dateInput = document.getElementById('date');
    const timeInput = document.getElementById('time');

    var dateBoxItems = document.querySelectorAll('.date-box-item');

    movieSelect.addEventListener('change', function() {
        const selectedMovieId = movieSelect.value;
        movieIdInput.value = selectedMovieId;
        dateInput.value = '';

        dateBoxItems.forEach(function(dateBoxItem) {
            dateBoxItem.style.background = '#baa';
        });
        updateCinemaAndTime(selectedMovieId, '');
    });

    dateBoxItems.forEach(function(dateBoxItem) {
        dateBoxItem.addEventListener('click', function() {
            if (movieIdInput.value) {
                dateBoxItems.forEach(function(dateBoxItem) {
                    dateBoxItem.style.background = '#baa';
                });
                this.style.background = '#422';
                var dateBoxId = this.id;
                var date = dateBoxId.replace('date-box-', '');
                dateInput.value = date;
                updateCinemaAndTime(movieIdInput.value, date);
            }
        });
    });

    function updateCinemaAndTime(movieId, date) {
        // Clear previous content
        $('.cinema-and-time-box').empty();

        // Filter provoles based on selected movie and date
        var filteredProvoles = provolesList.filter(function(provoles) {
            return provoles.movieId === movieId && provoles.date === date;
        });

        // Append new content based on filteredProvoles
        filteredProvoles.forEach(function(provoles) {
            var cinema = provoles.cinema;
            var time = provoles.time;

            var content = '<div class="cinema-time-item">' +
                '<div class="cinema">' + cinema + '</div>' +
                '<div class="time">' + time + '</div>' +
                '</div>';
            $('.cinema-and-time-box').append(content);
        });
    }


    $('.reservation-button input[type="button"]').click(function() {
        var selectedMovieId = $('#movieId').val();
        var selectedDate = $('#date').val();
        var selectedCinemaId = $('#cinemaId').val();
        var selectedTime = $('#time').val();

        if (selectedMovieId !== '' && selectedCinemaId !== '' && selectedDate!=='' && selectedTime!=='') {
            var encodedMovieId = encodeURIComponent(selectedMovieId);
            var encodedCinemaId = encodeURIComponent(selectedCinemaId);

            var url = 'makeReservation.jsp?movieId=' + encodedMovieId + '&cinemaId=' + encodedCinemaId;
            window.location.href = url;
        }
    });

});





























