document.addEventListener("DOMContentLoaded", function(){
    var signUpButton = document.querySelector('button[type="submit"]');

    signUpButton.addEventListener('click', function(event){
        event.preventDefault();
        checkSignUpForm();
    });


    function checkSignUpForm(){
        const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        const validPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        var nameInput = document.getElementById('name');
        var usernameInput =  document.getElementById('username');
        var emailInput = document.getElementById('email');
        var passwordInput = document.getElementById('password');
        var errorElement = document.getElementById('error-message');

        if ( nameInput.value.trim() === '' || usernameInput.value.trim() === '' || passwordInput.value.trim() === '' || emailInput.value.trim() ==='' ) {
            errorElement.textContent = 'Enter all fields';
        }else if(!emailInput.value.match(validRegex)){
            errorElement.textContent = 'Enter valid email address';
        }
        else if(passwordInput.value.length<8){
            errorElement.textContent = 'Password must be at least 8 characters';
        }
        if(!(validPassword.test(passwordInput.value))){
            errorElement.textContent = 'Password must contain at least 1 special character and number';
        }
        else {
            // Clear the error message
            errorElement.textContent = '';

            //Succesful login
            var form = document.getElementById('signup-form');
            form.submit();
        }
    }
});