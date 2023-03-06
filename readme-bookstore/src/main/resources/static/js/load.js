var currentPage = 0;

function moreContent() {
    switchMoreContentButtonActivation(true)
    currentPage = currentPage + 1;
    $.ajax({
        type: 'GET',
        url: '/books?currentPage=' + currentPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var books = $(data).find('#Books').html();
            var noMoreBooks = $(data).find('#noMoreBooks').html();
            if (noMoreBooks == undefined) {
                $('#Books').append(books)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentReviewsPage = 0
function moreBookReviews(id) {
    console.log('hola?')
    switchMoreContentButtonActivation(true)
    currentReviewsPage  = currentReviewsPage  + 1;
    $.ajax({
        type: 'GET',
        url: '/book/' + id + '?currentReviewsPage=' + currentReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var bookReviews = $(data).find('#Reviews').html();
            var noMoreBookReviews = $(data).find('#noMoreReviews').html();
            if (noMoreBookReviews == undefined) {
                $('#Reviews').append(bookReviews)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreBookReviewsButton').css("display", "none");
            }
        }
    })
}

var currentOffersPage = 0
function moreBookOffers(id) {
    switchMoreContentButtonActivation(true)
    currentOffersPage = currentFavoritesPage + 1;
    $.ajax({
        type: 'GET',
        url: '/book/' + id + '?currentOffersPage=' + currentOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var bookOffers = $(data).find('#Offers').html();
            var noMoreBookOffers = $(data).find('#noMoreOffers').html();
            if (noMoreBookOffers == undefined) {
                $('#Offers').append(bookOffers)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreBookOffersButton').css("display", "none");
            }
        }
    })
}

var currentFavoritesPage = 0
function moreUserFavorites() {
    switchMoreContentButtonActivation(true)
    currentFavoritesPage = currentFavoritesPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user-page?currentFavoritesPage=' + currentFavoritesPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var favoriteBooks = $(data).find('#favoriteBooks').html();
            var noMoreFavoriteBooks = $(data).find('#noMoreFavorites').html();
            if (noMoreFavoriteBooks == undefined) {
                $('#favoriteBooks').append(favoriteBooks)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentOffersPage = 0
function moreUserOffers() {
    switchMoreContentButtonActivation(true)
    currentOffersPage = currentOffersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user-page?currentOffersPage=' + currentOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var userOffers = $(data).find('#userOffers').html();
            var noMoreUserOffers = $(data).find('#noMoreUserOffers').html();
            if (noMoreUserOffers == undefined) {
                $('#userOffers').append(userOffers)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}




function switchMoreContentButtonActivation(disabled) {
    $('#moreContentButton').attr('disabled', disabled);
    if (disabled) {
        $('#moreContentSpinner').css("display", "block");
        $('#moreContentText').css("display", "none");
    } else {
        $('#moreContentSpinner').css("display", "none");
        $('#moreContentText').css("display", "block");
    }
}