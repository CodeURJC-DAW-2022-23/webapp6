var currentPage = 0;

function moreContent() {

    switchMoreContentButtonActivation(true)

    currentPage = currentPage + 1;

    $.ajax('https://localhost:8443/api/books?numPage=' + currentPage,{

        error: function(){

            console.log('Something went wrong')

            switchMoreContentButtonActivation(false)

        },

        success: function(data){

            console.log(data)

            if (data != undefined && data.content != undefined && data.content.length > 0){

                for(let book of data.content){
                    
                    addBook(book)

                }

                switchMoreContentButtonActivation(false)

            } else {

                $('#moreContentButton').css("display", "none");

            }

        }

    })

};



function addBook(book){

    $('#Books').append(

        '<div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">' +
                '<div class="featured__item">'+
                    '<div class="featured__item__pic set-bg">'+
                        '<img src="/books/' + book.id + '/image">'+
                    '</div>'+
                    '<div class="featured__item__text">'+
                        '<h6><a href="/book/' + book.id + '">' + book.title + '</a></h6>'+
                    '</div>'+
                '</div>'+
        '</div>'
        )

}


function switchMoreContentButtonActivation(disabled){
    $('#moreContentButton').attr('disabled', disabled);
    if (disabled){

        $('#moreContentSpinner').css("display", "block");

        $('#moreContentText').css("display", "none");

    } else {

        $('#moreContentSpinner').css("display", "none");

        $('#moreContentText').css("display", "block");

    }

}