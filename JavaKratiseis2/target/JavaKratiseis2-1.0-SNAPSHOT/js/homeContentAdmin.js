$(document).ready(function() {
    $("#insert").click(function() {
        $(".show-form").removeClass("active");
        $("#show-form-insert").addClass("active");
        $(".form-info").text("INSERT NEW FILM");
    });

    $("#assign").click(function() {
        $(".show-form").removeClass("active");
        $("#show-form-assign").addClass("active");
        $(".form-info").text("ASSIGN MOVIE TO CINEMA");
    });

    $("#delete").click(function() {
        $(".show-form").removeClass("active");
        $("#show-form-delete").addClass("active");
        $(".form-info").text("DELETE MOVIE");
    });
});






