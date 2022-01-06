function checkAllRejectSwitchers() {
    let allRejectSwitchers = document.getElementsByClassName("rejectSwitcher");
    let validation = [];
    for (let i = 0; i < allRejectSwitchers.length; i++) {
        validation.push(allRejectSwitchers.item(i).checked);
    }

    let checkerAllChecked = validation => validation.every(v => v === true);
    if (checkerAllChecked(validation)) {
        $(".rejectAllLink").removeClass("btn-secondary").addClass("btn-primary");
    } else  {
        $(".rejectAllLink").removeClass("btn-primary").addClass("btn-secondary");
    }

    let checkerAllUnchecked = validation => validation.every(v => v === false);
    document.getElementById("saveReject").disabled =  checkerAllUnchecked(validation);

}