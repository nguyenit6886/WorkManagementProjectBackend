(function($) {
    "use strict"

    const sweetSuccessCancels = $(".sweet-success-cancel");
    for (let i = 0; i < sweetSuccessCancels.length; i++) {
        sweetSuccessCancels[i] != null ? sweetSuccessCancels[i].onclick = function () { 
            let url_delete = $(sweetSuccessCancels[i]).data('url');
            let link_tag = "<a href='" + url_delete + "' class='link-delete text-white'>Đồng ý</a>";
            swal({ title: "Bạn có chắc muốn xóa không ?", text: "Bạn sẽ không thể khôi phục phần đã xóa này !!", type: "warning", showCancelButton: !0, confirmButtonColor: "#DD6B55", confirmButtonText: link_tag, cancelButtonText: "Hủy bỏ", closeOnConfirm: !1, closeOnCancel: !1 }, function (e) { e ? swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success") : swal("Cancelled !!", "Hey, your imaginary file is safe !!", "error") }) 
        } : sweetSuccessCancels[i];
    };

})(jQuery);
