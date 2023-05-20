$("#insert").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-insert").addClass("active");
    $(".form-info").text("INSERT NEW FILM");
    $("html, body").animate({ scrollTop: $("#show-form-insert").offset().bottom }, "slow");
});

$("#assign").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-assign").addClass("active");
    $(".form-info").text("ASSIGN MOVIE TO CINEMA");
    $("html, body").animate({ scrollTop: $("#show-form-assign").offset().bottom }, "slow");

});

$("#delete").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-delete").addClass("active");
    $(".form-info").text("DELETE MOVIE");
    $("html, body").animate({ scrollTop: $("#show-form-delete").offset().bottom }, "slow");
});











