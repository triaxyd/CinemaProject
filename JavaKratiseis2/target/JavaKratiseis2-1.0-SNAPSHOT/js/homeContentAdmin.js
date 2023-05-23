document.addEventListener("DOMContentLoaded", function(){

    var formInsert = document.getElementById('insertMovie');
    var formAssign = document.getElementById('assignMovie');
    var formDelete = document.getElementById('deleteMovie');

    var submitButtonInsert = document.getElementById('submitInsert');


    submitButtonInsert.addEventListener('click', function(event){
        event.preventDefault();
        checkInsertForm();
    });

    function checkInsertForm(){
        var title =  document.getElementById('title');
        var content = document.getElementById('content');
        var length = document.getElementById('length');
        var type = document.getElementById('type');
        var summary = document.getElementById('summary');
        var director = document.getElementById('director');

        var errorMessage = document.getElementById('message-insert');

        if(title.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        }
        if(content.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        }

        if(length.value < 1 || length.value > 999){
            errorMessage.textContent = 'All fields are required';
        }

        if(type.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        }

        if(summary.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        }

        if(director.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        }

        if (title.value.trim() === '' || content.value.trim() === ''
            || (length < 1 || length > 999) || type.value.trim() ==='' || summary.value.trim() === ''
            || director.value.trim()===''){
            errorMessage.textContent = 'All fields are required';
        } else {
            errorMessage.textContent = '';
            var form = document.getElementById('insertMovie');
            form.submit();
        }
    }
});


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











