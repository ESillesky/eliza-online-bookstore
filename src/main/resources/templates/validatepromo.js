var $ = function (id) {
    return document.getElementById(id);
}

var isInt = function (str) {
    return !isNaN(str) && Number.isInteger(parseInt(str));
}
function hasNum(str) {
    var matches = str.match(/(\d+)/);
    if (matches) {
        return true;
    }
}


function validateForm() {
    var promoname = $("promoname").value;
    var promocode = $("promocode").value;
    var discount = $("discount").value;
    var sDate = $("sDate").value;
    var eDate = $("eDate").value;
    var flag = true;


    if (promoname== "") {
        flag = false;
        $("error1").innerHTML = "Please enter promo name";
        $("promoname").focus();
    }
    if (promocode == "") {
        flag = false;
        $("error2").innerHTML = "Please enter promo code";
        $("promocode").focus();
    }
    if (discount == "") {
        flag = false;
        $("error3").innerHTML = "Please enter discount amount";
        $("lnamecard").focus();
    } else if (!isInt(discount)) {
        flag = false;
        $("error3").innerHTML = "Discount must be numeric";
        $("discount").focus();
    }

    if (sDate == "") {
        flag = false;
        $("error4").innerHTML = "Please choose start date";
        $("sDate").focus();
    } else if (!isInt(sDate)) {
        flag = false;
        $("error4").innerHTML = "Start date must be valid";
        $("sDate").focus();
    }

    if (eDate == "") {
        flag = false;
        $("error5").innerHTML = "Please choose end date";
        $("eDate").focus();
    } else if (!isInt(eDate)) {
        flag = false;
        $("error5").innerHTML = "End date must be valid";
        $("eDate").focus();
    }
    return flag;

}


var undo = function () {
    $("error1").innerHTML = "*";
    $("error2").innerHTML = "*";
    $("error3").innerHTML = "*";
    $("error4").innerHTML = "*";
    $("error5").innerHTML = "*";
}

window.onload = function () {
    $("button").onclick = undo;
}