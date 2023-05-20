document.addEventListener("DOMContentLoaded", function() {
    const menuLinks = document.querySelectorAll("header nav ul li  a");

    menuLinks.forEach(function(link) {
        link.addEventListener("click", function(e) {
            e.preventDefault();
            const targetSectionId = link.getAttribute("href");
            const targetSection = document.querySelector(targetSectionId);
            targetSection.scrollIntoView({ behavior: "smooth"});
        });
    });
});