(function($) {
    "use strict"

    let progressBar = $('.progress-bar');
    for (let i = 0; i < progressBar.length; i++) {
        console.log($(progressBar[i]).attr("aria-valuenow"));
        switch($(progressBar[i]).attr("aria-valuenow")) {
            case '0':
                $(progressBar[i]).css('width', '0%');
                break;
            case '1':
                $(progressBar[i]).css('width', '50%');
                break;
            case '2':
                $(progressBar[i]).css('width', '100%');
                break;
            default:
                $(progressBar[i]).css('width', '0%');
                break;
        }
    }

})(jQuery);