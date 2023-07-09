$(document).ready(function() {
    var maxTimeLimit = 10 * 60 * 1000;


    var timerElement = document.getElementById('timer');


    var startTime = new Date().getTime();
    var endTime = startTime + maxTimeLimit;
    updateTimer();

    function updateTimer() {
        var currentTime = new Date().getTime();
        var remainingTime = endTime - currentTime;

        if (remainingTime <= 0) {
            sessionStorage.clear();
            window.location.href = "../index.jsp";
            return;
        }

        var minutes = Math.floor((remainingTime % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);

        timerElement.textContent = minutes + ' MINUTES ' + seconds + ' SECONDS ';

        setTimeout(updateTimer, 1000);
    }


    var form = document.getElementById('make-reservation');
    var remainingSeats = document.getElementById('seats');
    var numOfSeats = document.getElementById('numOfSeats');
    var noSeats = document.getElementById('no-seats-message');

    function checkRemainingSeats() {
        var remainingSeatsInt = parseInt(remainingSeats.innerText.trim(), 10);
        var selectedSeats = parseInt(numOfSeats.value, 10);

        if (remainingSeatsInt - selectedSeats >= 0) {
            noSeats.setAttribute('hidden','');
            noSeats.innerText = "";

        } else {
            noSeats.removeAttribute('hidden');
            noSeats.innerText = "PLEASE SELECT LESS SEATS";
            event.preventDefault();
        }
    }

    form.addEventListener('submit', checkRemainingSeats);

    var selectSeats = document.getElementById('select-seats-options');
    selectSeats.addEventListener('change', function() {
        numOfSeats.value = selectSeats.value;
        checkRemainingSeats();
    });
    checkRemainingSeats();


});
