var originalUrl = window.location.href.split('?')[0];
window.history.replaceState({}, document.title, originalUrl);


function openForm(formId) {
    const result_container_search = document.getElementById('result-container-search');
    const result_container_delete = document.getElementById('result-container-delete');

    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.classList.add('hidden');
    });

    const selectedForm = document.getElementById(formId);
    selectedForm.classList.remove('hidden');

    selectedForm.scrollIntoView({ behavior: 'smooth', block: 'start' });
}
