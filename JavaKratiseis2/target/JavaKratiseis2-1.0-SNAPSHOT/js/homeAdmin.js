function openForm(formId) {
    // Hide all forms
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.classList.add('hidden');
    });

    // Show the selected form
    const selectedForm = document.getElementById(formId);
    selectedForm.classList.remove('hidden');
}
