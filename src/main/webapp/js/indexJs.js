document.addEventListener("DOMContentLoaded", function(){
    var loginButton = document.querySelector('button[type="submit"]');




    loginButton.addEventListener('click', function(event){
        event.preventDefault();
        checkLoginForm();
    });


    function checkLoginForm(){
        var usernameInput =  document.getElementById('username');
        var passwordInput = document.getElementById('password');
        var errorElement = document.getElementById('error-message');

        if (usernameInput.value.trim() === '' || passwordInput.value.trim() === '') {
            errorElement.textContent = 'Username and Password are required';
        }else if(passwordInput.value.length<8){
            errorElement.textContent = 'Password must be at least 8 characters';
        } else {
            // Clear the error message
            errorElement.textContent = '';

            //Succesful login
            var form = document.getElementById('login-form');
            form.submit();
        }
    }



});