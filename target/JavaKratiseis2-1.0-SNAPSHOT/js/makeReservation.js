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
});
