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


function toggleMovieInfo(element) {
    var movieInfo = element.parentElement.querySelector(".provoli-info");
    //var movieInfoElements = document.getElementsByClassName("provoli-info");

    /*
    for (var i = 0; i < movieInfoElements.length; i++) {
        if (movieInfoElements[i] !== movieInfo) {
            movieInfoElements[i].classList.remove("active");
        }
    }

     */
    movieInfo.classList.toggle("active");
}
