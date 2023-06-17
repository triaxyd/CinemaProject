
var originalUrl = window.location.href.split('?')[0];
window.history.replaceState({}, document.title, originalUrl);



document.addEventListener("DOMContentLoaded", function(){


    var insertMovieButton = document.getElementById('insert');
    var assignMovieButton = document.getElementById('assign');
    var deleteProvoliButton = document.getElementById('delete-provoli');

    var formInsert = document.getElementById('insertMovie');
    var formAssign = document.getElementById('assignMovie');
    var formDeleteProvoli = document.getElementById('deleteProvoli');

    var inputsInsert = document.querySelectorAll('#insertMovie input');
    var inputsAssign = document.querySelectorAll('#assignMovie input');
    var selectAssign = document.querySelectorAll('#assignMovie select')
    var inputsDeleteProvoli = document.querySelectorAll('#deleteProvoli input');



    insertMovieButton.addEventListener('click',function(){
        inputsInsert.forEach(input =>{
            input.style.border="none";
        })
    });

    assignMovieButton.addEventListener('click',function(){
        inputsAssign.forEach(input =>{
            input.style.border="none";
        })
        selectAssign.forEach(select => {
            select.style.border ="none";
        })
    });

    deleteProvoliButton.addEventListener('click',function(){
        inputsDeleteProvoli.forEach(input =>{
            input.style.border="none";
        })
    });



    document.getElementById('submitInsert').addEventListener('click', function(event){
        event.preventDefault();
        checkInsertForm();
    });

    document.getElementById('submitAssign').addEventListener('click', function(event){
        event.preventDefault();
        checkAssignForm();
    });

    document.getElementById('submitDeleteProvoli').addEventListener('click', function(event){
        event.preventDefault();
        checkDeleteProvoliForm();
    });




    function checkInsertForm(){
        //var errorMessage = document.getElementById('message-insert');
        var bSubmit = true;
        inputsInsert.forEach( input => {

            input.addEventListener('focus', function() {
                this.style.border = 'none';
            });
            if(input.value.trim()===''){
                input.style.border = "2px solid red";
                bSubmit = false;
            }else{
                input.style.border = "none";
            }
            if (input.id==='length'){
                if(!(/^[0-9]+$/.test(input.value))){
                    input.style.border = "2px solid red";
                    bSubmit = false;
                }
            }
        })
        if(bSubmit){
            formInsert.submit();
        }
    }


    /*
    function checkAssignForm(){
        var bSubmit = true;
        inputsAssign.forEach( input => {

            input.addEventListener('focus', function() {
                this.style.border = 'none';
            });
            if(input.value.trim()===''){
                input.style.border = "2px solid red";
                bSubmit = false;
            }else{
                input.style.border = "none";
            }
            if (!(input.id==='submitAssign')){
                if(!(/^[0-9]+$/.test(input.value))){
                    input.style.border = "2px solid red";
                    bSubmit = false;
                }
            }
        })
        if(bSubmit){
            formAssign.submit();
        }
    }

     */
    function checkAssignForm() {
        var bSubmit = true;

        var movieId = document.getElementById('movie-id');
        var cinemaId = document.getElementById('cinema-id');

        var assignInputs = [movieId, cinemaId];

        assignInputs.forEach(input => {
            input.addEventListener('focus', function() {
                this.style.border = 'none';
            });

            if(input.value.trim()===''){
                input.style.border = "2px solid red";
                bSubmit = false;
            }else{
                input.style.border = "none";
            }
            if (!(input.id==='submitAssign')){
                if(!(/^[0-9]+$/.test(input.value))){
                    input.style.border = "2px solid red";
                    bSubmit = false;
                }
            }
        });

        if (bSubmit) {
            formAssign.submit();
        }
    }



    function checkDeleteProvoliForm(){
        var bSubmit = true;
        inputsDeleteProvoli.forEach( input => {
            input.addEventListener('focus', function() {
                this.style.border = 'none';
            });
            if(input.value.trim()===''){
                input.style.border = "2px solid red";
                bSubmit = false;
            }else{
                input.style.border = "none";
            }
            if (!(input.id==='submitDeleteProvoli')){
                if(!(/^[0-9]+$/.test(input.value))){
                    input.style.border = "2px solid red";
                    bSubmit = false;
                }
            }
        })
        if(bSubmit){
            formDeleteProvoli.submit();
        }
    }

});




$("#insert").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-insert").addClass("active");
    $(".form-info").text("INSERT NEW MOVIE");
    $("html, body").animate({ scrollTop: $("#show-form-insert").offset().top }, "slow");
    $("#message-insert").fadeOut(10000);
});

$("#assign").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-assign").addClass("active");
    $(".form-info").text("ASSIGN MOVIE TO CINEMA");
    $("html, body").animate({ scrollTop: $("#show-form-assign").offset().top }, "slow");
    $("#message-assign").fadeOut(10000);

});

$("#delete-provoli").click(function() {
    $(".show-form").removeClass("active");
    $("#show-form-delete-provoli").addClass("active");
    $(".form-info").text("DELETE PROVOLI");
    $("html, body").animate({ scrollTop: $("#show-form-delete-provoli").offset().top }, "slow");
    $("#message-delete").fadeOut(10000);
});











