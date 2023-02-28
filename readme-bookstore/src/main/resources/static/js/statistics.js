// Reference the camvas object in the DOM.
const $chart = document.querySelector("#chart");
// The tags in the X axis.
const tags = ["Libros subidos", "Usuarios registrados", "Reseñas escritas", "Anuncios publicados"]


var books = document.querySelector("#books");
var users = document.querySelector("#users");
var reviews = document.querySelector("#reviews");
var offers = document.querySelector("#offers");

// First data set:
const genreStats = {
    label: "Números generales",
    data: [books.dataset.num, users.dataset.num, reviews.dataset.num, offers.dataset.num], // Tags data
    backgroundColor: 'rgba(54, 162, 235, 0.2)',
    borderColor: 'rgba(54, 162, 235, 1)',
    borderWidth: 1,
};

new Chart($chart, {
    type: 'bar', // Chart type
    data: {
        labels: tags,
        datasets: [
            genreStats,
            // Here more data...
        ]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }],
        },
    }
});