(function($) {
    "use strict"

    $('p.text-truncate').parent('a').addClass("text-truncate");

    let showDetails = $('.show-detail-content');
    for (let i = 0; i < showDetails.length; i++) {
        $(showDetails[i]).click(function (){
            $(showDetails[i]).parent().first().removeClass("text-truncate");
            $(showDetails[i]).parent().first().children().removeClass("text-truncate");
            $(showDetails[i]).hide();
            $(showDetails[i]).next().show();
        });
    }

    let closeDetails = $('.close-detail-content');
    for (let i = 0; i < closeDetails.length; i++) {
        $(closeDetails[i]).click(function (){
            $(closeDetails[i]).parent().children(":first").addClass("text-truncate");
            $(closeDetails[i]).parent().children(":first").children().addClass("text-truncate");
            $(closeDetails[i]).hide();
            $(closeDetails[i]).prev().show();
        });
    }

})(jQuery);
