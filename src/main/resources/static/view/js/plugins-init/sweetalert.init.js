const sweetWrongs = document.querySelectorAll(".sweet-wrong");
for(let i = 0; i < sweetWrongs.length; i++) {
    sweetWrongs[i] != null ? sweetWrongs[i].onclick = function () { sweetAlert("Oops...", "Something went wrong !!", "error") } : sweetWrongs[i];
};
const sweetMessages = document.querySelectorAll(".sweet-message");
for (let i = 0; i < sweetMessages.length; i++) {
    sweetMessages[i] != null ? sweetMessages[i].onclick = function () { swal("Hey, Here's a message !!") } : sweetMessages[i];
};
const sweetTexts = document.querySelectorAll(".sweet-text");
for (let i = 0; i < sweetTexts.length; i++) {
    sweetTexts[i] != null ? sweetTexts[i].onclick = function () { swal("Hey, Here's a message !!", "It's pretty, isn't it?") } : sweetTexts[i];
};
const sweetSuccess = document.querySelectorAll(".sweet-success");
for (let i = 0; i < sweetSuccess.length; i++) {
    sweetSuccess[i] != null ? sweetSuccess[i].onclick = function () { swal("Hey, Good job !!", "You clicked the button !!", "success") } : sweetSuccess[i];
};
const sweetConfirms = document.querySelectorAll(".sweet-confirm");
for (let i = 0; i < sweetConfirms.length; i++) {
    sweetConfirms[i] != null ? sweetConfirms[i].onclick = function () { swal({ title: "Are you sure to delete ?", text: "You will not be able to recover this imaginary file !!", type: "warning", showCancelButton: !0, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes, delete it !!", closeOnConfirm: !1 }, function () { swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success") }) } : sweetConfirms[i];
};
const sweetSuccessCancels = document.querySelectorAll(".sweet-success-cancel");
for (let i = 0; i < sweetSuccessCancels.length; i++) {
    sweetSuccessCancels[i] != null ? sweetSuccessCancels[i].onclick = function () { swal({ title: "Are you sure to delete ?", text: "You will not be able to recover this imaginary file !!", type: "warning", showCancelButton: !0, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes, delete it !!", cancelButtonText: "No, cancel it !!", closeOnConfirm: !1, closeOnCancel: !1 }, function (e) { e ? swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success") : swal("Cancelled !!", "Hey, your imaginary file is safe !!", "error") }) } : sweetSuccessCancels[i];
};
const sweetImageMessages = document.querySelectorAll(".sweet-image-message");
for (let i = 0; i < sweetImageMessages.length; i++) {
    sweetImageMessages[i] != null ? sweetImageMessages[i].onclick = function () { swal({ title: "Sweet !!", text: "Hey, Here's a custom image !!", imageUrl: "../assets/images/hand.jpg" }) } : sweetImageMessages[i];
}
const sweetHTMLs = document.querySelectorAll(".sweet-html");
for (let i = 0; i < sweetHTMLs.length; i++) {
    sweetHTMLs[i] != null ? sweetHTMLs[i].onclick = function () { swal({ title: "Sweet !!", text: "<span style='color:#ff0000'>Hey, you are using HTML !!<span>", html: !0 }) } : sweetHTMLs[i];
}
const sweetAutos = document.querySelectorAll(".sweet-auto");
for (let i = 0; i < sweetAutos.length; i++) {
    sweetAutos[i] != null ? sweetAutos[i].onclick = function () { swal({ title: "Sweet auto close alert !!", text: "Hey, i will close in 2 seconds !!", timer: 2e3, showConfirmButton: !1 }) } : sweetAutos[i];
}
const sweetPrompts = document.querySelectorAll(".sweet-prompt");
for (let i = 0; i < sweetPrompts.length; i++) {
    sweetPrompts[i] != null ? sweetPrompts[i].onclick = function () { swal({ title: "Enter an input !!", text: "Write something interesting !!", type: "input", showCancelButton: !0, closeOnConfirm: !1, animation: "slide-from-top", inputPlaceholder: "Write something" }, function (e) { return !1 !== e && ("" === e ? (swal.showInputError("You need to write something!"), !1) : void swal("Hey !!", "You wrote: " + e, "success")) }) } : sweetPrompts[i];
}
const sweetAjax = document.querySelectorAll(".sweet-ajax");
for (let i = 0; i < sweetAjax.length; i++) {
    sweetAjax[i] != null ? sweetAjax[i].onclick = function () { swal({ title: "Sweet ajax request !!", text: "Submit to run ajax request !!", type: "info", showCancelButton: !0, closeOnConfirm: !1, showLoaderOnConfirm: !0 }, function () { setTimeout(function () { swal("Hey, your ajax request finished !!") }, 2e3) }) } : sweetAjax[i];
}
