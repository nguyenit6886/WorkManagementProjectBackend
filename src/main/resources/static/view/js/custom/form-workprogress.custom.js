(function($) {
    "use strict"

    $('#btn-update').click(function(){
        $('.workprogress-input').css("display", "block");
        $('.workprogress-old').css("display", "none");
    });

    $('#autoresizing').on('input', function () {
        this.style.height = 'auto';
          
        this.style.height = 
                (this.scrollHeight) + 'px';
    });

})(jQuery);