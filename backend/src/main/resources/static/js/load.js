var currentPage = 0;
const valores = window.location.search;

function moreContent() {
    const div = "moreContentButton";
    const spinner = "moreContentSpinner";
    const text = "moreContentText";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentPage = currentPage + 1;
    console.log(valores);
    const urlParams = new URLSearchParams(valores);
    var searchtext = urlParams.get('searchtext');
    if (searchtext == null)
        $.ajax({
            type: 'GET',
            url: '/books?currentPage=' + currentPage,
            error: function () {
                console.log('Something went wrong')
                switchMoreContentButtonActivation(false, div, spinner, text)
            },
            success: function (data) {
                var books = $(data).find('#Books').html();
                var noMoreBooks = $(data).find('#noMoreBooks').html();
                if (noMoreBooks == undefined) {
                    $('#Books').append(books)
                    switchMoreContentButtonActivation(false, div, spinner, text)
                } else {
                    $('#moreContentButton').css("display", "none");
                }
            }
        }) 
    else
        $.ajax({
            type: 'GET',
            url: '/books?searchtext=' + searchtext + '&currentPage=' + currentPage,
            error: function () {
                console.log('Something went wrong')
                switchMoreContentButtonActivation(false, div, spinner, text)
            },
            success: function (data) {
                var books = $(data).find('#Books').html();
                var noMoreBooks = $(data).find('#noMoreBooks').html();
                if (noMoreBooks == undefined) {
                    $('#Books').append(books)
                    switchMoreContentButtonActivation(false, div, spinner, text)
                } else {
                    $('#moreContentButton').css("display", "none");
                }
            }
        })
}

var currentReviewsPage = 0
function moreBookReviews(id) {
    const div = "moreBookReviewsButton";
    const spinner = "moreContentSpinnerBookReviews";
    const text = "moreContentTextBookReviews";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentReviewsPage  = currentReviewsPage  + 1;
    $.ajax({
        type: 'GET',
        url: '/books/' + id + '?currentReviewsPage=' + currentReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var bookReviews = $(data).find('#Reviews').html();
            var noMoreBookReviews = $(data).find('#noMoreReviews').html();
            if (noMoreBookReviews == undefined) {
                $('#Reviews').append(bookReviews)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreBookReviewsButton').css("display", "none");
            }
        }
    })
}

var currentOffersPage = 0
function moreBookOffers(id) {
    const div = "moreBookOffersButton";
    const spinner = "moreContentSpinnerBookOffers";
    const text = "moreContentTextBookOffers";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentOffersPage = currentOffersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/books/' + id + '?currentOffersPage=' + currentOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var bookOffers = $(data).find('#Offers').html();
            var noMoreBookOffers = $(data).find('#noMoreOffers').html();
            if (noMoreBookOffers == undefined) {
                $('#Offers').append(bookOffers)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreBookOffersButton').css("display", "none");
            }
        }
    })
}

var currentFavoritesPage = 0
function moreUserFavorites() {
    const div = "moreContentButtonUserFavorites";
    const spinner = "moreContentSpinnerUserFavorites";
    const text = "moreContentTextUserFavorites";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentFavoritesPage = currentFavoritesPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user?currentFavoritesPage=' + currentFavoritesPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var favoriteBooks = $(data).find('#favoriteBooks').html();
            var noMoreFavoriteBooks = $(data).find('#noMoreFavorites').html();
            if (noMoreFavoriteBooks == undefined) {
                $('#favoriteBooks').append(favoriteBooks)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreContentButtonUserFavorites').css("display", "none");
            }
        }
    })
}

var currentOffersPage = 0
function moreUserOffers() {
    const div = "moreOfferContentButton";
    const spinner = "moreContentSpinnerUserOffers";
    const text = "moreContentTextUserOffers";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentOffersPage = currentOffersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user?currentOffersPage=' + currentOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var userOffers = $(data).find('#userOffers').html();
            var noMoreUserOffers = $(data).find('#noMoreUserOffers').html();
            if (noMoreUserOffers == undefined) {
                $('#userOffers').append(userOffers)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreOfferContentButton').css("display", "none");
            }
        }
    })
}

var currentReviewsPage = 0
function moreUserReviews() {
    const div = "moreReviewsContentButton";
    const spinner = "moreContentSpinnerUserReviews";
    const text = "moreContentTextUserReviews";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentReviewsPage = currentReviewsPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user?currentReviewsPage=' + currentReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var userReviews = $(data).find('#userReviews').html();
            var noMoreUserReviews = $(data).find('#noMoreUserReviews').html();
            if (noMoreUserReviews == undefined) {
                $('#userReviews').append(userReviews)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreReviewsContentButton').css("display", "none");
            }
        }
    })
}

var currentHistoryPage = 0
function moreUserHistory() {
    const div = "moreHistorialContentButton";
    const spinner = "moreContentSpinnerUserHistory";
    const text = "moreContentTextUserHistory";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentHistoryPage = currentHistoryPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user?currentHistoryPage=' + currentHistoryPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var userHistory = $(data).find('#userHistory').html();
            var noMoreUserHistory = $(data).find('#noMoreUserHistory').html();
            if (noMoreUserHistory == undefined) {
                $('#userHistory').append(userHistory)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreHistorialContentButton').css("display", "none");
            }
        }
    })
}

var currentAdminOffersPage = 0
function moreAdminOffers() {
    const div = "moreAdminOffersContentButton";
    const spinner = "moreContentSpinnerAdminOffers";
    const text = "moreContentTextAdminOffers";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentAdminOffersPage = currentAdminOffersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentOffersPage=' + currentAdminOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var adminOffers = $(data).find('#adminOffers').html();
            var noMoreAdminOffers = $(data).find('#noMoreAdminOffers').html();
            if (noMoreAdminOffers == undefined) {
                $('#adminOffers').append(adminOffers)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreAdminOffersContentButton').css("display", "none");
            }
        }
    })
}

var currentAdminReviewsPage = 0
function moreAdminReviews() {
    const div = "moreAdminReviewsContentButton";
    const spinner = "moreContentSpinnerAdminReviews";
    const text = "moreContentTextAdminReviews";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentAdminReviewsPage = currentAdminReviewsPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentReviewsPage=' + currentAdminReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var adminReviews = $(data).find('#adminReviews').html();
            var noMoreAdminReviews = $(data).find('#noMoreAdminReviews').html();
            if (noMoreAdminReviews == undefined) {
                $('#adminReviews').append(adminReviews)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreAdminReviewsContentButton').css("display", "none");
            }
        }
    })
}

var currentAdminUsersPage = 0
function moreAdminUsers() {
    const div = "moreAdminUsersContentButton";
    const spinner = "moreContentSpinnerAdminUsers";
    const text = "moreContentTextAdminUsers";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentAdminUsersPage = currentAdminUsersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentUsersPage=' + currentAdminUsersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var adminUsers = $(data).find('#adminUsers').html();
            var noMoreAdminUsers = $(data).find('#noMoreAdminUsers').html();
            if (noMoreAdminUsers == undefined) {
                $('#adminUsers').append(adminUsers)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreAdminUsersContentButton').css("display", "none");
            }
        }
    })
}

var currentAdminBooksPage = 0
function moreAdminBooks() {
    const div = "moreAdminBooksContentButton";
    const spinner = "moreContentSpinnerAdminBooks";
    const text = "moreContentTextAdminBooks";
    switchMoreContentButtonActivation(true, div, spinner, text)
    currentAdminBooksPage = currentAdminBooksPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentBooksPage=' + currentAdminBooksPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false, div, spinner, text)
        },
        success: function (data) {
            var adminBooks = $(data).find('#adminBooks').html();
            var noMoreAdminBooks = $(data).find('#noMoreAdminBooks').html();
            if (noMoreAdminBooks == undefined) {
                $('#adminBooks').append(adminBooks)
                switchMoreContentButtonActivation(false, div, spinner, text)
            } else {
                $('#moreAdminBooksContentButton').css("display", "none");
            }
        }
    })
}

function switchMoreContentButtonActivation(disabled, div, spinner, text) {
    $('#'+div).attr('disabled', disabled);
    if (disabled) {
        $('#'+spinner).css("display", "block");
        $('#'+text).css("display", "none");
    } else {
        $('#'+spinner).css("display", "none");
        $('#'+text).css("display", "block");
    }
}