$(".rejectSwitcher").click(function () {
    let id = $(this).val();
    document.getElementById("rejectInputAmount" + id).disabled = !$(this).prop('checked');
    checkAllRejectSwitchers();
});