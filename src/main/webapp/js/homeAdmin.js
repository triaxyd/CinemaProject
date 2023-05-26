function openForm(formId) {
    const result_container = document.getElementById('result-container');

    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.classList.add('hidden');
    });

    const selectedForm = document.getElementById(formId);
    selectedForm.classList.remove('hidden');

    selectedForm.scrollIntoView({ behavior: 'smooth', block: 'start' });
}
