var currentPage = 0;
const valores = window.location.search;

function moreContent() {
    switchMoreContentButtonActivation(true)
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
    else
        $.ajax({
            type: 'GET',
            url: '/books?searchtext=' + searchtext + '&currentPage=' + currentPage,
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

var currentReviewsPage = 0
function moreUserReviews() {
    switchMoreContentButtonActivation(true)
    currentReviewsPage = currentReviewsPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user-page?currentReviewsPage=' + currentReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var userReviews = $(data).find('#userReviews').html();
            var noMoreUserReviews = $(data).find('#noMoreUserReviews').html();
            if (noMoreUserReviews == undefined) {
                $('#userReviews').append(userReviews)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentHistoryPage = 0
function moreUserHistory() {
    switchMoreContentButtonActivation(true)
    currentHistoryPage = currentHistoryPage + 1;
    $.ajax({
        type: 'GET',
        url: '/user-page?currentHistoryPage=' + currentHistoryPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var userHistory = $(data).find('#userHistory').html();
            var noMoreUserHistory = $(data).find('#noMoreUserHistory').html();
            if (noMoreUserHistory == undefined) {
                $('#userHistory').append(userHistory)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentOffersPage = 0
function moreAdminOffers() {
    switchMoreContentButtonActivation(true)
    currentOffersPage = currentOffersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentOffersPage=' + currentOffersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var adminOffers = $(data).find('#adminOffers').html();
            var noMoreAdminOffers = $(data).find('#noMoreAdminOffers').html();
            if (noMoreAdminOffers == undefined) {
                $('#adminOffers').append(adminOffers)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentReviewsPage = 0
function moreAdminReviews() {
    switchMoreContentButtonActivation(true)
    currentReviewsPage = currentReviewsPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentReviewsPage=' + currentReviewsPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var adminReviews = $(data).find('#adminReviews').html();
            var noMoreAdminReviews = $(data).find('#noMoreAdminReviews').html();
            if (noMoreAdminReviews == undefined) {
                $('#adminReviews').append(adminReviews)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentUsersPage = 0
function moreAdminUsers() {
    switchMoreContentButtonActivation(true)
    currentUsersPage = currentUsersPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentUsersPage=' + currentUsersPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var adminUsers = $(data).find('#adminUsers').html();
            var noMoreAdminUsers = $(data).find('#noMoreAdminUsers').html();
            if (noMoreAdminUsers == undefined) {
                $('#adminUsers').append(adminUsers)
                switchMoreContentButtonActivation(false)
            } else {
                $('#moreContentButton').css("display", "none");
            }
        }
    })
}

var currentBooksPage = 0
function moreAdminBooks() {
    switchMoreContentButtonActivation(true)
    currentBooksPage = currentBooksPage + 1;
    $.ajax({
        type: 'GET',
        url: '/admin?currentBooksPage=' + currentBooksPage,
        error: function () {
            console.log('Something went wrong')
            switchMoreContentButtonActivation(false)
        },
        success: function (data) {
            var adminBooks = $(data).find('#adminBooks').html();
            var noMoreAdminBooks = $(data).find('#noMoreAdminBooks').html();
            if (noMoreAdminBooks == undefined) {
                $('#adminBooks').append(adminBooks)
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