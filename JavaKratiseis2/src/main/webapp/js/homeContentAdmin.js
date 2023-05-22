$("#insert").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-insert").addClass("active");
    $(".form-info").text("INSERT NEW MOVIE");
    $("html, body").animate({ scrollTop: $("#show-form-insert").offset().top }, "slow");
    $("#message-insert").fadeOut(1000);
});

$("#assign").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-assign").addClass("active");
    $(".form-info").text("ASSIGN MOVIE TO CINEMA");
    $("html, body").animate({ scrollTop: $("#show-form-assign").offset().top }, "slow");
    $("#message-assign").fadeOut(1000);

});

$("#delete").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-delete").addClass("active");
    $(".form-info").text("DELETE MOVIE");
    $("html, body").animate({ scrollTop: $("#show-form-delete").offset().top }, "slow");
    $("#message-delete").fadeOut(1000);
});











