var originalUrl = window.location.href.split('?')[0];
window.history.replaceState({}, document.title, originalUrl);


function openForm(formId) {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.classList.add('hidden');
    });


    const selectedForm = document.getElementById(formId);
    selectedForm.classList.remove('hidden');

    selectedForm.scrollIntoView({ behavior: 'smooth', block: 'start' });

    const form = document.getElementById('add-user');
    form.addEventListener('submit', checkForm); // Remove the parentheses after checkForm

    function checkForm(event) {
        event.preventDefault();
        const passwordInput = document.getElementById('password-add');
        const password = passwordInput.value;
        const validPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        if (!validPassword.test(password)) {
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = 'Password must contain at least 1 special character and number';
            return;
        }
        form.submit();
    }
}

