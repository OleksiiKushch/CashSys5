$(".rejectAllLink").click(function () {
    if ($(this).hasClass("btn-secondary")) {
        $(this).removeClass("btn-secondary").addClass("btn-primary");
        $(".rejectSwitcher").prop('checked', true);
        $(".rejectInputAmount").prop('disabled', false);
    } else {
        $(this).removeClass("btn-primary").addClass("btn-secondary");
        $(".rejectSwitcher").prop('checked', false);
        $(".rejectInputAmount").prop('disabled', true);
    }
    checkAllRejectSwitchers();
});