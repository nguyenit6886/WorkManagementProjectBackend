(function($) {
    "use strict"

    $('#btn-update').click(function(){
        $('.workprogress-input').css("display", "block");
        $('.custom-file').css("display", "block");
        $('#inputState').removeAttr("disabled");
        $('.workprogress-old').css("display", "none");
    });

    $('#autoresizing').on('input', function () {
        this.style.height = 'auto';
          
        this.style.height = 
                (this.scrollHeight) + 'px';
    });

    // Comment Of WorkProgress
    let btnEditCmt = $('.btn-edit-cmt');
    for (let i = 0; i < btnEditCmt.length; i++) {
        $(btnEditCmt[i]).click(function () {
            $(this).parents(".item-content-cmt").children(".before-edit-cmt").css("display", "none");
            $(this).parents(".item-content-cmt").children(".after-edit-cmt").css("display", "block");
        });
    }

    let btnCancelEditCmt = $('.btn-cancel-edit-cmt');
    for (let i = 0; i < btnCancelEditCmt.length; i++) {
        $(btnCancelEditCmt[i]).click(function () {
            $(this).parents(".item-content-cmt").children(".before-edit-cmt").css("display", "block");
            $(this).parents(".item-content-cmt").children(".after-edit-cmt").css("display", "none");
        });
    }

    

})(jQuery);