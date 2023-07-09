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
    $('.select-provoli-button').click(function(event) {
        event.preventDefault();
        var provoliId = $(this).attr('data-provoli-id');
        var customerId = $('#customerId').val();

        $('#provoliId').val(provoliId);

        var url = 'makeReservation.jsp?provoliId=' + provoliId + '&customerId=' + customerId;
        window.location.href = url;
    });
});






























